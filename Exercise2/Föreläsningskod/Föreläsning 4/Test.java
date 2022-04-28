public class Test {

    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        list.add("isak");
        list.add("lisa");
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        list.remove("isak");
        list.remove(4);

        list.addFirst("Hello");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        HashSet set = new HashSet();

        set.add(10);
        set.add(11);
        set.add("hello");
        set.add(10);

        System.out.println(set.contains(10));
        System.out.println(set.contains("e"));
        System.out.println(set.size());

        TreeSet treeSet = new TreeSet();
        treeSet.add(10);
        treeSet.add(2);
        treeSet.add(100);
        treeSet.add(1);
        treeSet.add(10);
        treeSet.add(3);

        System.out.println(treeSet.contains(10));
        System.out.println(treeSet.contains(32));
        System.out.println(treeSet.size());

    }
}
