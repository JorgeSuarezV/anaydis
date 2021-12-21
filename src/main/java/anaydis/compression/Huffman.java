package anaydis.compression;

import anaydis.search.ArrayMap;
import anaydis.search.Map;
import anaydis.search.RandomizedTreeMap;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;


public class Huffman implements Compressor {
    @Override
    public void encode(@NotNull InputStream inputStream, @NotNull OutputStream outputStream) throws IOException {
        Map<Integer,Integer> frecTable = new ArrayMap<Integer, Integer>(Comparator.naturalOrder());
        List<Integer> text = new ArrayList<>();
        int length = buildFrecTable(frecTable, text,inputStream);

        PriorityQueue<Node> priorityQueue = generatePriorityQueue(frecTable);
        Node huffmanTree = generateTree(priorityQueue);
        Map<Integer, Bits> huffmanTable = generateTable(huffmanTree);
        encodeTable(outputStream, huffmanTable);
        outputStream.write(0xFF);
        writeInt(length, outputStream);
        outputStream.write(0xFF);
        writeEncode(outputStream, huffmanTable, text);
    }

    private void writeEncode(OutputStream outputStream, Map<Integer, Bits> table, List<Integer> text) throws IOException {
        Bits actual = new Bits();
        Bits aux = new Bits();

        for (Integer integer : text){

            if (actual.isFull()){
                outputStream.write(actual.value);
                actual = aux;
            }
            aux = actual.add(table.get(integer));
        }

        if (!actual.isEmpty()){
            outputStream.write(actual.value << 8 - actual.length);

            if (actual.isFull()){
                outputStream.write(aux.value << 8-aux.length);
            }

        }
    }

    private PriorityQueue<Node> generatePriorityQueue(Map<Integer,Integer> map) {
        Iterator<Integer> iterator = map.keys();
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        while (iterator.hasNext()){
            Integer actual = iterator.next();
            priorityQueue.add(new Node(actual, map.get(actual)));
        }
        return priorityQueue;
    }

    private void writeInt(Integer integer, OutputStream outputStream) throws IOException {
        while (integer-127>0){
            outputStream.write(127);
            integer-= 127;
        }

        outputStream.write(integer);
    }

    private Node generateTree(PriorityQueue<Node> queue){
        while (queue.size() > 1){
            Node nodeLeft = queue.poll();
            Node nodeRight = queue.poll();
            queue.add(new Node(nodeLeft, nodeRight, null, nodeLeft.frequency +nodeRight.frequency));
        }
        return queue.poll();
    }

    @Override
    public void decode(@NotNull InputStream input, @NotNull OutputStream output) throws IOException {
        Map<Bits,Integer> table = generateDecodeTable(input);
        int length = readInt(input);
        int i = 0;
        int current = input.read();
        Bits pivot = new Bits();

        while (current != -1 && i < length){

            for (int j = 7; j >= 0 && i<length; j--) {

                pivot.add(bitAt(current,j));

                if (table.containsKey(pivot)){
                    output.write(table.get(pivot));
                    pivot = new Bits();
                    i++;
                }
            }
            current = input.read();
        }
    }

    private int readInt(InputStream input) throws IOException {
        int number = 0;
        int actual = input.read();
        while (actual != 0xFF){

            number+= actual;
            actual = input.read();
        }
        return number;
    }

    private boolean bitAt(int bit, int pos) {
        return pos < 8 && (bit >>> pos & 1) != 0;
    }


    private Map<Bits, Integer> generateDecodeTable(InputStream input) throws IOException {
        RandomizedTreeMap<Bits, Integer> table = new RandomizedTreeMap<>(new BitsComparator());
        int actual = input.read();
        while (actual != 0xFF){
            int length = input.read();
            int value = input.read();
            table.put(new Bits(value, length), actual);
            actual = input.read();
        }
        return table;
    }

    private ArrayMap<Integer, Bits> generateTable(Node node) {
        ArrayMap<Integer,Bits> map = new ArrayMap<Integer, Bits>(Comparator.naturalOrder());
        generateTableRecursive(node,map,new Bits());
        return map;
    }

    private void generateTableRecursive(Node node, ArrayMap<Integer, Bits> map, Bits bits){
        if (node.isLeaf()) map.put(node.value, bits);
        else {
            Bits left = bits.copy();
            Bits right= bits.copy();
            generateTableRecursive(node.left,map,left.add(false));
            generateTableRecursive(node.right,map,right.add(true));
        }
    }

    private void encodeTable(OutputStream outputStream, Map<Integer, Bits> map) throws IOException {
        Iterator<Integer> iterator = map.keys();
        while (iterator.hasNext()){
            int value = iterator.next();
            Bits bits = map.get(value);
            outputStream.write(value);
            outputStream.write(bits.length);
            outputStream.write(bits.value);
        }
    }

    private Integer buildFrecTable(Map<Integer, Integer> map, List<Integer> text, InputStream inputStream) throws IOException {
        int actual = inputStream.read();
        while(actual != -1){
            map.put(actual, (map.containsKey(actual)) ? map.get(actual)+1 :1);
            text.add(actual);
            actual = inputStream.read();
        }
        return text.size();
    }


    private class Node implements Comparable<Node> {
        Node left;
        Node right;
        Integer value;
        Integer frequency;
        Integer height;

        Node(int value, int frequency) {
            this.value = value;
            this.frequency = frequency;
            this.height = 0;
        }

        Node(Node left, Node right, Integer value, int frequency) {
            this.left = left;
            this.right = right;
            this.value = value;
            this.frequency = frequency;
            this.height = right.height > left.height ? right.height + 1 : left.height + 1;
        }

        public boolean isLeaf(){
            return left == null && right == null;
        }

        @Override
        public int compareTo(@NotNull Node o) {
            int cmp = frequency.compareTo(o.frequency);

            int f1 = cmp > 0 ? frequency : o.frequency;
            int f2 = cmp > 0 ? o.frequency : frequency;

            if(f2*4 < f1) return cmp;

            int r = height - o.height;
            int hcmp = r > 1 ? 1 : r < -1 ? -1 : 0;
            return hcmp != 0 ? hcmp : cmp;
        }
    }

    private class Bits{
        private int value;
        private byte length;

        public Bits(int value, int length) {
            this.value = value;
            this.length = (byte) length;
        }

        public Bits() {
        }

        public Bits add(boolean bit) {
            value = (value << 1) | (bit ? 1 : 0);
            length++;
            return this;
        }

        public int getValue() { return value; }
        public int getLength() {return length;}

        public Bits copy() {
            final Bits bits = new Bits();
            bits.value = value;
            bits.length = length;
            return bits;
        }

        @Override public String toString() {
            final StringBuilder builder = new StringBuilder();
            int aux = 1 << length;
            while(aux > 1) {
                aux = aux >> 1;
                builder.append((value & aux) == 0 ? "0":"1");
            }
            return builder.toString();
        }

        Bits add(@NotNull Bits b){
            if(length == 8) return b;
            int emptyBits = 8 - length;
            if(emptyBits >= b.length){
                value = value << b.length | b.value;
                length += b.length;
                return new Bits();
            }
            int k = b.length - emptyBits;
            length = 8;
            value = value << emptyBits | b.value >>> k;
            return new Bits((b.value << (8-k)) >>> (8-k) , k);
        }

        boolean isEmpty(){return length == 0;}
        boolean isFull(){return length == 8;}


    }

    private class BitsComparator implements Comparator<Huffman.Bits>{

        @Override
        public int compare(Huffman.Bits o1, Huffman.Bits o2) {
            int cmp = Integer.compare(o1.value, o2.value);
            return cmp == 0 ? Integer.compare(o1.length, o2.length) : cmp;
        }

    }
}