public class StringByteSource implements ByteSource {
    private final ByteSource source;

    public StringByteSource(ByteSource source) {
        this.source = source;
    }

    @Override
    public void write(byte[] data) {
        source.write(data);
    }

    @Override
    public byte[] read() {
        return source.read();
    }

    public String readString() {
        return new String(read());
    }

    public void writeString(String str) {
        write(str.getBytes());
    }
}
