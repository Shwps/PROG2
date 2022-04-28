import java.io.*;

public class Numrera {


    public static void main(String[] args) throws IOException {
        String file = args[0];
        BufferedReader reader = new BufferedReader(new FileReader(file));
        PrintWriter writer = new PrintWriter(new FileWriter(file + ".numrerad"));

        int lineNo = 1;
        String line;
        while ((line = reader.readLine()) != null) {
            writer.print(lineNo++);
            writer.print(": ");
            writer.println(line);
        }
        writer.close();
        reader.close();
    }
}
