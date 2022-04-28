import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class t {

    static class Djur implements Comparable<Djur>{
        @Override
        public int compareTo(Djur o) {
            return 0;
        }
    }

    static class Katt extends Djur {

    }

    static class Hund extends Djur {
    }


    public static int sum(Number[] numbers) {
        int sum = 0;
        for (Number number : numbers) {
            sum += number.intValue();
        }
        return sum;
    }

    public static int sum(List<Number> numbers) {
        int i = 0;
        for (Number number : numbers) {
            i += number.intValue();
        }
        return i;
    }

    public static <T extends Comparable<? super T>> T max(T a, T b) {
        return a.compareTo(b) > 0 ? a : b;
    }


    public static <T> void copy(List<T> from, List<? super T> to) {
        to.clear();
        for (T t : from) {
            to.add(t);
        }
    }

    public static void copyDjur(List<? extends Djur> from, List<Djur> to) {
        to.clear();
        for (Djur djur : from) {
            to.add(djur);
        }
    }

    public static <T extends Number> int sumAnyNumber(List<? extends Number> numbers) {
        int i = 0;
        for (Number number : numbers) {
            i += number.intValue();
        }
        return i;
    }

    public static void main(String[] args) {
        List<Hund> from = List.of(new Hund(), new Hund());
        List<Object> to = new ArrayList<>();
        copy(from, to);

        //copyDjur(from, to);

        List<Integer> ints = List.of(1, 2, 3);
        List<Object> doubles = List.of(1.1, 2.2);
        List<? super Number> nums = doubles;
        nums.add(1.2);

        Djur a = max(new Katt(), new Hund());
    }
}
