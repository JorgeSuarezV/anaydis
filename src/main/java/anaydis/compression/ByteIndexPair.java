package anaydis.compression;

import org.jetbrains.annotations.NotNull;

public class ByteIndexPair implements Comparable<ByteIndexPair> {
        int index;
        Integer b;

        ByteIndexPair(int index, int b) {
            this.index = index;
            this.b = b;
        }


        @Override
        public boolean equals(Object o) {
            return index == ((ByteIndexPair)o).index;
        }

        @Override
        public int compareTo(@NotNull ByteIndexPair i) {
            return b.compareTo(i.b);
        }
    }