public interface ByteSource {

    void write(byte[] data);

    byte[] read();
}
