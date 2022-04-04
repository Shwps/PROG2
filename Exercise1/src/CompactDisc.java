public class CompactDisc extends Recording {
    public CompactDisc(String name, String artist, int year, int condition, double price) {
        super(name, artist, year, condition, price);
    }

    public String getType() {
        return "Compact Disc";
    }

    ;

    public double getPrice() {
        double finalPrice = price * (condition / 10.0);
        if (finalPrice < 10) {
            return 10.0;
        }
        return finalPrice;
    }

}
