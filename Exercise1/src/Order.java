import java.util.Arrays;
import java.util.List;


public class Order {
    private final long orderNumber = ++counter;
    private static long counter;
    private final List<Item> items;

    public Order(Item... items) {
        this.items = Arrays.asList(items);
    }

    public double getTotalValue() {
        double totalSum = 0;
        for(Item item : items){
            totalSum += item.getPrice();
        }
        return totalSum;
    }

    public double getTotalValuePlusVAT() {
        double totalSum = 0;
        for(Item item : items){
            totalSum += item.getPricePlusVAT();
        }
        return totalSum;
    }

    private String ReceiptStringGenerator (){
        String orderString = "";
        for (Item item : items){
            orderString += String.format("%s%n",item.toString());
            //orderString += String.format("%s, Price: %s, Price incl. VAT: %s%n",item.toString(),item.getPrice(),item.getPricePlusVAT());
        }
        return orderString;
    }

    public String getReceipt() {
        return String.format("Receipt for order #%d,%n-------------------%n%s%n" +
                "Total excl. VAT:%f%n Total incl. VAT: %f%n-------------------",orderNumber,ReceiptStringGenerator (),getTotalValue(),getTotalValuePlusVAT());
    }

}
