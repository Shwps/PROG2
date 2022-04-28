public class LinkedList {

    private static class Node {
        private Object value = null;
        private Node next = null;
    }

    private Node first;
    private int size = 0;

    public LinkedList() {
        this.first = null;
    }

    public void add(Object value) {
        if (first == null) {
            first = new Node();
            first.value = value;
        } else {
            Node last = first;
            while (last.next != null) {
                last = last.next;
            }
            last.next = new Node();
            last.next.value = value;
        }
        size += 1;
    }

    public void addFirst(Object value) {
        if (first == null) {
            first = new Node();
            first.value = value;
        } else {
            Node node = new Node();
            node.value = value;
            node.next = first;
            first = node;
        }
        size += 1;
    }

    public void remove(Object value) {
        if (first.value.equals(value)) {
            first = first.next;
            size -= 1;
        } else {
            Node node = first;
            while (node != null) {
                if (node.next != null && node.next.value.equals(value)) {
                    node.next = node.next.next;
                    size -= 1;
                    break;
                }
                node = node.next;
            }
        }
    }

    public int size() {
        return size;
    }

    public Object get(int index) {
        Node last = first;
        int i = 0;
        while (i < index) {
            last = last.next;
            i += 1;
        }
        return last.value;
    }

}
