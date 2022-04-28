import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;
import java.util.zip.ZipOutputStream;

public class ZipByteSource implements ByteSource {
    private final ByteSource source;

    public ZipByteSource(ByteSource source) {
        this.source = source;
    }


    @Override
    public void write(byte[] data) {
        source.write(compress(data));
    }

    private byte[] compress(byte[] data) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DeflaterOutputStream dos = new DeflaterOutputStream(baos);
        try {
            dos.write(data);
            dos.close();
            return baos.toByteArray();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public byte[] read() {
        return decompress(source.read());
    }

    private byte[] decompress(byte[] read) {
        ByteArrayInputStream bais = new ByteArrayInputStream(read);
        InflaterInputStream iis = new InflaterInputStream(bais);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int v;
        try {
            while ((v = iis.read()) != -1) {
                baos.write(v);
            }
            baos.close();
            iis.close();
            return baos.toByteArray();
        } catch (IOException e) {
            return null;
        }
    }
}
