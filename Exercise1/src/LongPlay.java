public class LongPlay extends Recording {

    public LongPlay(String name, String artist, int year, int condition, double price) {
        super(name, artist, year, condition, price);
    }

    public String getType() {
        return "Long Play Record ";
    }

    ;

    public double getPrice() {
        double finalPrice = price * (condition / 10.0) + ((2021 - year) * 5);
        if (finalPrice < 10.0) {
            return 10.0;
        }
        return finalPrice;
    }

}
