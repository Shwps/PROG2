import java.io.*;

public class Ex3Test {
    public static void main(String[] args) throws FileNotFoundException {

    Exercise3 t = new Exercise3();

    t.importRecordings("recording_input.txt");
    t.exportRecordings("testRun.txt");


    }
}


