public class Book extends Item implements Vat6 {
    private final String author;
    private final boolean bound;
    private final double price;

    public Book(String name, String author, double price, boolean bound) {
        super(name);
        this.author = author;
        this.bound = bound;
        this.price = price;
    }

    public double getPrice() {
        if (bound) {
            return price * 1.25;
        }
            return price;
    }


    @Override
    public String toString() {
        return this.getClass() + ": Title: " + name + ", Author: " + author + ", Price: " + price + ", Format: " + bound +  "Current price: " + getPrice() + "Price incl. VAT: " + getPricePlusVAT();
    }


}
