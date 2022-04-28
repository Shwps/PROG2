import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<T> implements Iterable<T> {
    private T[] values;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        values = (T[]) new Object[10];
        size = 0;
    }

    public int size() {
        return size;
    }

    public T get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        return this.values[index];
    }

    public void add(T value) {
        ensureCapacity(size);
        values[size] = value;
        size += 1;
    }

    public void addFirst(T value) {
        ensureCapacity(size + 1);
        for (int i = size - 1; i >= 1; i--) {
            values[i] = values[i - 1];
        }
        values[0] = value;
        size += 1;
    }

    public void remove(Object value) {
        int removeIndex = -1;
        for (int i = 0; i < size; i++) {
            if (values[i].equals(value)) {
                removeIndex = i;
                break;
            }
        }
        if (removeIndex >= 0) {
            for (int i = removeIndex + 1; i < size; i++) {
                values[i - 1] = values[i];
            }
            size -= 1;
        }
    }

    @SuppressWarnings("unchecked")
    private void ensureCapacity(int size) {
        if (values.length <= size) {
            T[] newValues = (T[]) new Object[values.length + 10];
            for (int i = 0; i < values.length; i++) {
                newValues[i] = values[i];
            }
            values = newValues;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int current = 0;

            @Override
            public boolean hasNext() {
                return current < ArrayList.this.size();
            }

            @Override
            public T next() {
                if (current >= ArrayList.this.size()) {
                    throw new NoSuchElementException();
                }
                T next = ArrayList.this.get(current);
                current += 1;
                return next;
            }
        };
    }

}
