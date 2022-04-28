import java.util.*;

class Friends {
    public static void main(String[] args) {
        String[] hansArr = { "Arvid", "Anna", "Adam", "Albert", "August", "Doris" };
        String[] hennesArr = {"Stefan", "Sara", "Sune", "Hugo", "Lotta", "Anna", "Adam",
                "Albert", "Anton", "Zack"};

        String[] zacksArr = { "Hugo", "Albert", "Arvid", "Anna" };

        Set<String> hansVänner = new TreeSet<>(Arrays.asList(hansArr));
        Set<String> hennesVänner = new TreeSet<>(Arrays.asList(hennesArr));
        Set<String> zacksVänner = new TreeSet<>(Arrays.asList(zacksArr));

        // Bjud in alla till fest
        Set<String> bjudna = new TreeSet<>();
        bjudna.addAll(hansVänner);
        bjudna.addAll(hennesVänner);
        bjudna.addAll(zacksVänner);
        System.out.println(bjudna);

        Set<String> gemensammaVänner = new TreeSet<>(hansVänner);
        gemensammaVänner.retainAll(hennesVänner);
        System.out.println(gemensammaVänner);

        // Men Zack får inte vara med
        bjudna.remove("Zack");

        // Och inte heller någon som är vän med Zack
        bjudna.removeAll(zacksVänner);
        System.out.println(bjudna);

        // Om både Doris och Wilhelm är bjudna så ta bort Wilhelm
        if (bjudna.contains("Doris")) {
            bjudna.remove("Wilhelm");
        }
        System.out.println(bjudna);
        // Om Lotta är bjuden så ta bort alla som börjar på "A"
        if (bjudna.contains("Lotta")) {
            bjudna.removeIf(s -> s.startsWith("A"));
        }
        System.out.println(bjudna);


        // Gör en framslumpad placeringslista
        List<String> slump = new ArrayList<>(bjudna);
        Collections.shuffle(slump);
        System.out.println(slump.subList(0, 3));


    }
}
