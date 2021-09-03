package anaydis.sort.performanceMains;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {

    private final FileWriter csv;

    public CSVWriter(String doc) throws IOException {
        this.csv = new FileWriter(new File("src\\test\\java\\anaydis\\sort", doc));
    }

    public void write(String text)throws IOException{
        csv.append(text);
        csv.append("\n");
        csv.flush();
    }
}
