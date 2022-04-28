public class Test {

    public static void main(String[] args) {
        FileByteSource fileSource = new FileByteSource("test.zip");
        ZipByteSource zipByteSource = new ZipByteSource(fileSource);
        Base64EncodeByteSource base64EncodeByteSource = new Base64EncodeByteSource(zipByteSource);
        StringByteSource source = new StringByteSource(base64EncodeByteSource);

        source.writeString("Hello world");
        System.out.println(source.readString());

        StringByteSource stringByteSource = new StringByteSource(new FileByteSource("test.txt"));
        stringByteSource.writeString("hello world!");
        System.out.println(stringByteSource.readString());
    }
}
