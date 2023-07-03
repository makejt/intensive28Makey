import org.example.makey.util.MyArrayList;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

       MyArrayList<Integer> list = new MyArrayList<>();
        list.add(10);
        list.add(50);
        list.add(-44);
        list.add(15);
        list.add(51);
        list.add(17);
        list.add(14);
        list.remove(2);

        System.out.println(list);
        System.out.println("capacity before adding set " + list.capacity());

        Set<Integer> set = IntStream.range(-5, 5).boxed().collect(Collectors.toSet());
        list.addAll(set);
        System.out.println(list);
        System.out.println("capacity after adding set " + list.capacity());

        System.out.println("List before sorting: " + list);
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        }, 0, list.size() - 1);

        System.out.println("List after sorting: " + list);
    }
}