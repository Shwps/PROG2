public class TreeSet<T extends Comparable<T>> {

    private static class Node<T extends Comparable<T>> {
        private T value;
        private Node<T> left; // <
        private Node<T> right; // >
    }

    private Node<T> root = null;
    private int size = 0;

    public void add(T value) {
        if (root == null) {
            root = new Node<T>();
            root.value = value;
            size += 1;
        } else {
            Node<T> node = root;
            while (true) {
                if (value.compareTo(node.value) < 0) {
                    if (node.left == null) {
                        node.left = new Node<T>();
                        node.left.value = value;
                        size += 1;
                        break;
                    } else {
                        node = node.left;
                    }
                } else if (value.compareTo(node.value) > 0) {
                    if (node.right == null) {
                        node.right = new Node<T>();
                        node.right.value = value;
                        size += 1;
                        break;
                    } else {
                        node = node.right;
                    }
                } else {
                    return;
                }
            }
        }
    }

    public boolean contains(T value) {
        Node<T> node = root;
        while (node != null) {
            if (value.compareTo(node.value) < 0) {
                node = node.left;
            } else if (value.compareTo(node.value) > 0) {
                node = node.right;
            } else {
                return true;
            }
        }
        return false;
    }


}
