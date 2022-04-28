import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Fil {

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter("fil.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.println(10);
        printWriter.println("hello world");
        printWriter.println(new ArrayList<String>());

        printWriter.close();
        fileWriter.close();

        FileReader fileReader = new FileReader("fil.txt");
        Scanner scanner = new Scanner(fileReader);
        scanner.nextLine();
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        bufferedReader.readLine();

        fileReader.close();
        bufferedReader.close();

        System.out.println("Hello world");
        Scanner in = new Scanner(System.in);
        in.nextInt();
    }
}
