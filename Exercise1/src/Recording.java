public abstract class Recording extends Item implements Vat25 {
    protected final int year;
    protected final double price;
    protected final int condition;
    String artist;

    public Recording(String name, String artist, int year, int condition, double price) {
        super(name);
        this.artist = artist;
        this.year = year;
        this.condition = condition;
        this.price = price;
    }

    protected abstract String getType();

    public String toString() {
        return getType() + ": Name: " + name + ", Artist: " + artist + ", Year: " + year + ", Condition: " + condition + ", Original price: " + price + "Current price: " + getPrice() + "Price incl. VAT: " + getPricePlusVAT();
    }


}

