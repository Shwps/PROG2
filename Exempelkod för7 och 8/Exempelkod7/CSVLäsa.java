import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVLäsa {

    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("persons.csv");
        BufferedReader reader = new BufferedReader(fileReader);
        //List<Person> persons = reader.lines().map(CSVLäsa::parseLine).toList();

        List<Person> persons = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            persons.add(parseLine(line));
        }
        System.out.println(persons);
    }

    private static Person parseLine(String line) {
        String[] split = line.split(",");
        Persnr pnr = Persnr.parsePersnr(split[0]);
        String name = split[1];
        int vikt = Integer.parseInt(split[2]);
        return new Person(pnr, name, vikt);
    }
}
