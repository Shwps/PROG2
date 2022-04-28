import java.io.*;
import java.util.List;

public class Serialization {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        write();
        read();
    }

    private static void read() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("persons.obj");
        ObjectInputStream ois = new ObjectInputStream(fis);
        @SuppressWarnings("unchecked")
        List<Person> persons = (List<Person>) ois.readObject();
        System.out.println(persons);

        Persnr persnr = (Persnr) ois.readObject();
        System.out.println(persnr);
    }

    private static void write() throws IOException {
        List<Person> persons = List.of(
                new Person(Persnr.parsePersnr("890218-0011"), "Anna", 100),
                new Person(Persnr.parsePersnr("660210-1100"), "Britta", 77)
        );

        FileOutputStream fos = new FileOutputStream("persons.obj");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(persons);
        oos.writeObject(new Persnr(990909, 1122));
    }


}
