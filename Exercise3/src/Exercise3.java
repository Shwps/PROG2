
import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

public class Exercise3 {


    private final List<Recording> recordings = new ArrayList<>();

    public void exportRecordings(String fileName) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (Recording rec : recordings) {
                printWriter.println("<recording>");
                printWriter.println("   <artist>" + rec.getArtist() + "</artist>");
                printWriter.println("   <title>" + rec.getTitle() + "</title>");
                printWriter.println("   <year>" + rec.getYear() + "</year>");
                printWriter.println("   <genres>");
                for (String gen : rec.getGenre()) {
                    printWriter.println("       <genre>" + gen + "</genre>");
                }
                printWriter.println("   </genres>");
                printWriter.println("</recording>");
            }

            printWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void importRecordings(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotFoundException("Filen fanns inte");
        }
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int amountOfRecordings = parseInt(bufferedReader.readLine());
            for (int i = 0; i < amountOfRecordings; i++) {
                String firstRow = bufferedReader.readLine();
                String[] splitFirstRow = firstRow.split(";");
                String title = splitFirstRow[1];
                String artist = splitFirstRow[0];
                int year = parseInt(splitFirstRow[2]);
                int amountOfGenres = parseInt(bufferedReader.readLine());
                Set<String> genres = new HashSet<>();
                for (int l = 0; l < amountOfGenres; l++) {
                    genres.add(bufferedReader.readLine());
                }
                Recording rec = new Recording(title, artist, year, genres);
                recordings.add(rec);
            }
            fileReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public Map<Integer, Double> importSales(String fileName) throws FileNotFoundException {
        Map<Integer, Double> sales = new HashMap<>();

        File file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotFoundException("Filen fanns inte");
        }
        try {
            FileInputStream fis = new FileInputStream(file);
            DataInputStream dis = new DataInputStream(fis);

            int amountOfEntries = dis.readInt();
            for (int i = 0; i < amountOfEntries; i++) {
                int year = dis.readInt();
                int mon = dis.readInt();
                int day = dis.readInt();
                Double value = dis.readDouble();
                Integer key = (year * 100 + mon);
                if(sales.containsKey(key)){
                    value += sales.get(key);
                }
                sales.put(key, value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sales;
    }

    public List<Recording> getRecordings() {
        return Collections.unmodifiableList(recordings);
    }

    public void setRecordings(List<Recording> recordings) {
        this.recordings.clear();
        this.recordings.addAll(recordings);
    }
}

