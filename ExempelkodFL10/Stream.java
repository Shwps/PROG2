import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Stream {

    record Employee(String name, int salary) {
    }


    public static void main(String[] args) {

        List<Integer> intList = List.of(1, 2, 3, 4, 5, 6, 20, 22);
        List<Integer> stora = filter(intList, (Integer x) -> {
            int threshold = 3;
            return x > threshold;
        });
        System.out.println(stora);

        List<Integer> ints = List.of(10, 20, 30, 40, 50, 60);
        ints.stream().filter(v -> v > 20).map(v -> v * 10).forEach(System.out::println);


        List<String> strs = List.of("Isak", "Anna", "Anders", "arne", "Ebba");

        HashSet<String> set = strs.parallelStream().map(String::toUpperCase).filter(s -> s.charAt(0) == 'A').collect(Collector.of(HashSet::new, HashSet::add, (strings, strings2) -> {
            HashSet<String> combined = new HashSet<>(strings);
            combined.addAll(strings2);
            return combined;
        }));
        System.out.println(set);


        int sum = IntStream.range(0, 10).reduce(0, (a, b) -> a + b);
        System.out.println(sum);

        List<Employee> employees = List.of(new Employee("Anna", 10000), new Employee("Arne", 9000), new Employee("Isak", 200));
        employees.stream().min(Comparator.comparing(Employee::salary)).ifPresent(System.out::println);


        // anyMatch processes elements in the Stream one element at a time until it finds a match according to the Predicate and returns true if it found a match
        if (employees.stream().map(Employee::name).anyMatch(s -> s.startsWith("I"))) {
            System.out.println("Någon börjar på I");
        }

        // allMatch processes elements in the Stream one element at a time until it fails a match according to the Predicate and returns false if an element failed the Predicate
        if (!employees.stream().map(Employee::name).allMatch(s -> s.startsWith("I"))) {
            System.out.println("Alla börjar inte på I");
        }

        //noneMatch processes elements in the Stream one element at a time until it finds a match according to the Predicate and returns false if an element  matches the Predicate
        if (employees.stream().map(Employee::name).noneMatch(s -> s.startsWith("J"))) {
            System.out.println("Det finns ingen som börjar på J");
        }


        System.out.println(employees.stream().sorted(Comparator.comparing(Employee::salary)).limit(2).map(Employee::salary).toList());
    }

    public static <T> List<T> filter(List<? extends T> list, Predicate<? super T> predicate) {
        List<T> newList = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)) {
                newList.add(t);
            }
        }
        return newList;
    }
}