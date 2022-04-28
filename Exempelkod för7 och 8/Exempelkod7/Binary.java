import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Binary {

    public static void main(String[] args) throws IOException {
        write();
        read();
    }

    private static void read() throws IOException {
        FileInputStream fis = new FileInputStream("test.dat");
        DataInputStream dis = new DataInputStream(fis);
        int antal = dis.readInt();
        double[] värden = new double[antal];
        for (int i = 0; i < antal; i++) {
            värden[i] = dis.readDouble();
        }
        System.out.println(Arrays.toString(värden));
    }

    private static void write() throws IOException {
        FileOutputStream fos = new FileOutputStream("test.dat");
        DataOutputStream dos = new DataOutputStream(fos);

        double[] värden = new double[]{10.0, Math.PI, 123.2321};
        dos.writeInt(värden.length);
        for (double v : värden) {
            dos.writeDouble(v);
        }
    }
}
