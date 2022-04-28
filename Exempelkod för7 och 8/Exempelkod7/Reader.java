import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Reader {


    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: Reader fileName");
            System.exit(0);
        }
        String fileName = args[0];
        try{
            FileReader fileReader = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fileReader);

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            fileReader.close();
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.printf("%s not found%n", fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
