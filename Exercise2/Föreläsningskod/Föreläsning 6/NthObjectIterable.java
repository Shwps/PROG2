import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Predicate;

public class NthObjectIterable implements Iterable<Integer> {

    private ArrayList<Integer> ints;
    private int nth;

    public NthObjectIterable(ArrayList<Integer> ints, int nth) {
        this.ints = ints;
        this.nth = nth;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<Integer> {
        private int currentPos = 0;

        @Override
        public boolean hasNext() {
            return currentPos < ints.size();
        }

        @Override
        public Integer next() {
            Integer next = ints.get(currentPos);
            currentPos += nth;
            return next;
        }
    }


    public static void main(String[] args) {
        ArrayList<Integer> ints = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            ints.add(i);
        }

        int nth = 3;
        for (int i = 0; i < ints.size(); i++) {
            if (i % 3 == 0) {
                System.out.println(ints.get(i));
            }
        }

        NthObjectIterable noi = new NthObjectIterable(ints, nth);
        for (Integer integer : noi) {
            System.out.println(integer);
        }
    }
}




