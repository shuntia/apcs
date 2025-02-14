
import java.util.*;
import java.util.function.BinaryOperator;

public class SortPerformanceTest {

    public static void main(String[] args) {
        int[] sizes = {10, 100, 1000, 10000};
        int numTests = 1000; // Number of tests to run for each size
        StringBuilder table = new StringBuilder();

        for (int size : sizes) {
            List<Long> bubbleTimes = new ArrayList<>();
            List<Long> insertionTimes = new ArrayList<>();
            List<Long> selectionTimes = new ArrayList<>();
            List<Long> mergeTimes = new ArrayList<>();
            List<Long> stalinTimes = new ArrayList<>();
            List<Long> idTimes = new ArrayList<>();

            for (int i = 0; i < numTests; i++) {
                int[] baseArr = generateRandomArray(size);
                bubbleTimes.add(measureTime(baseArr, "bubble"));
                insertionTimes.add(measureTime(baseArr, "insertion"));
                selectionTimes.add(measureTime(baseArr, "selection"));
                mergeTimes.add(measureTime(baseArr, "merge"));
                stalinTimes.add(measureTime(baseArr, "stalin"));
                idTimes.add(measureTime(baseArr, "intelligent"));
            }

            table.append(String.format("Length %d:%n", size));
            table.append(formatStats("Bubble", bubbleTimes));
            table.append(formatStats("Insertion", insertionTimes));
            table.append(formatStats("Selection", selectionTimes));
            table.append(formatStats("Merge", mergeTimes));
            table.append(formatStats("Stalin", stalinTimes));
            table.append(formatStats("Intelligent", idTimes));
            table.append("\n");
        }

        System.out.println(table.toString());
    }

    private static long measureTime(int[] arr, String method) {
        int[] copy = Arrays.copyOf(arr, arr.length);
        long start = System.nanoTime();
        switch (method) {
            case "bubble" ->
                SortLibrary.bubbleSort(copy);
            case "insertion" ->
                SortLibrary.insertionSort(copy);
            case "selection" ->
                SortLibrary.selectionSort(copy);
            case "merge" ->
                SortLibrary.mergeSort(copy);
            case "stalin" ->
                SortLibrary.stalinSort(copy);
            case "bogo" ->
                SortLibrary.bogoSort(copy);
            case "intelligent" ->
                SortLibrary.intelligentDesignSort(copy);
        }
        return System.nanoTime() - start;
    }

    private static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(size * 10);
        }
        return arr;
    }

    private static String formatStats(String sortType, List<Long> times) {
        Collections.sort(times);
        long min = times.get(0);
        long max = times.get(times.size() - 1);
        long sum = times.stream().mapToLong(Long::longValue).sum();
        long avg = sum / times.size();
        long median = times.get(times.size() / 2);
        long range = max - min;
        long mode = times.stream()
                .reduce(BinaryOperator.maxBy(Comparator.comparingLong(o -> Collections.frequency(times, o))))
                .orElse(-1L);
        double mean = times.stream().mapToLong(Long::longValue).average().orElse(0.0);

        return String.format("%-12s | Min: %10d ns | Avg: %10d ns | Max: %10d ns | Median: %10d ns | Mode: %10d ns | Range: %10d ns | Mean: %10.2f ns%n",
                sortType, min, avg, max, median, mode, range, mean);
    }
}
