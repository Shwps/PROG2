public class ArrayList {
    private Object[] values;
    private int size;

    public ArrayList() {
        values = new Object[10];
        size = 0;
    }

    public int size() {
        return size;
    }

    public Object get(int index) {
        return this.values[index];
    }

    public void add(Object value) {
        ensureCapacity(size);
        values[size] = value;
        size += 1;
    }

    public void addFirst(Object value) {
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

    private void ensureCapacity(int size) {
        if (values.length <= size) {
            Object[] newValues = new Object[values.length + 10];
            for (int i = 0; i < values.length; i++) {
                newValues[i] = values[i];
            }
            values = newValues;
        }
    }

}
