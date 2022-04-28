import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class CSVSpara {


    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter("persons.csv");
        PrintWriter printWriter = new PrintWriter(fileWriter);

        List<Person> persons = List.of(
                new Person(Persnr.parsePersnr("890218-0011"), "Anna", 100),
                new Person(Persnr.parsePersnr("660210-1100"), "Britta", 77)
        );

        for (Person person : persons) {
            printWriter.print(person.getPnr());
            printWriter.print(",");
            printWriter.print(person.getName());
            printWriter.print(",");
            printWriter.println(person.getVikt());
        }
        printWriter.close();

    }
}
