package anaydis.compression;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class RunLenghtEncoder implements Compressor{

    @Override
    public void encode(@NotNull InputStream input, @NotNull OutputStream output) throws IOException {
        int previous = input.read();
        int actual = input.read();
        int count = 0;
        if (actual != -1) {
            while (previous != -1) {
                if (previous == actual && count < 256) {
                    count++;
                } else {
                    if(count != 1){
                        output.write(0xFF);
                        output.write(count);
                    }
                    output.write(previous);
                    count = 1;
                }
                previous = actual;
                actual = input.read();
            }
        }
    }

    @Override
    public void decode(@NotNull InputStream input, @NotNull OutputStream output) throws IOException {
        int amount = 1;
        boolean amountNext = false;
        int current = input.read();
        while (current!=-1){
            if(amountNext) {
                amount = current;
                amountNext = false;
            }
            else if(current == 0xFF){
                amountNext = true;
            }
            else {
                for (int i = 0; i < amount; i++) {
                    output.write(current);
                }
                amount = 1;
            }
            current = input.read();
        }
    }
}
