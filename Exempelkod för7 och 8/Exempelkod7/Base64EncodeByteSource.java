import java.util.Base64;

public class Base64EncodeByteSource implements ByteSource {

    private ByteSource source;

    public Base64EncodeByteSource(ByteSource source) {
        this.source = source;
    }

    @Override
    public void write(byte[] data) {
        source.write(encode(data));
    }

    @Override
    public byte[] read() {
        return decode(source.read());
    }

    private byte[] decode(byte[] data) {
        return Base64.getDecoder().decode(data);
    }

    private byte[] encode(byte[] data) {
        return Base64.getEncoder().encode(data);
    }
}
