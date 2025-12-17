import java.util.*;

public class Benchmark {
    public static void run() {

        System.out.println(Color.CYAN +
                "\n┌──────────────────────────────────────────┐" +
                "\n│      BENCHMARK ARRAYLIST vs LINKEDLIST   │" +
                "\n├──────────────────────────────────────────┤" +
                Color.RESET
        );

        int N = 1_000_000;

        System.out.println(Color.BLUE + "Menjalankan Benchmark (1 juta elemen)..." + Color.RESET);

        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }

        long start = System.nanoTime();
        arrayList.get(N - 1);
        long end = System.nanoTime();
        long timeArray = end - start;

        start = System.nanoTime();
        linkedList.get(N - 1);
        end = System.nanoTime();
        long timeLinked = end - start;

        System.out.println(Color.GREEN +
                "\nArrayList.get(last)  : " + timeArray + " ns" +
                "\nLinkedList.get(last) : " + timeLinked + " ns" +
                Color.RESET
        );

        System.out.println("└──────────────────────────────────────────┘");
    }
}
