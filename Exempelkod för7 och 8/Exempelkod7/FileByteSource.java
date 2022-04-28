import java.io.*;
import java.util.ArrayList;

public class FileByteSource implements ByteSource {

    private String file;

    public FileByteSource(String file) {
        this.file = file;
    }

    @Override
    public void write(byte[] data) {
        try (OutputStream out = new FileOutputStream(file)) {
            out.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public byte[] read() {
        try (InputStream in = new FileInputStream(file)) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int data;
            while ((data = in.read()) != -1) {
                baos.write(data);
            }
            return baos.toByteArray();
        } catch (IOException e) {
            return null;
        }
    }
}
