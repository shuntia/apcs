import java.util.Arrays;
import java.util.Scanner;

public class RandomSearchTester {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of elements in the array: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = (int)(Math.random()*Integer.MAX_VALUE);
        }
        System.out.println("Sorting the array...");
        Arrays.sort(arr);
        System.out.println("Array sorted.");
        System.out.println("Enter the number of times to search for items: ");
        int num = sc.nextInt();

        double totalBinaryTime = 0;
        double totalSequentialTime = 0;
        StringBuilder output = new StringBuilder();

        // Binary search loop
        output.append("\n==================== Binary Search ====================\n");
        output.append(String.format("%-20s %-20s %-20s%n", "Search Type", "Target Value", "Result Index"));
        output.append("-------------------------------------------------------\n");
        double startTime = System.nanoTime();
        for(int i=0;i<num;i++){
            int randomIndex = (int)(Math.random()*n);
            int numToSearchFor = arr[randomIndex];
            int index = SearchLibrary.binarySearch(numToSearchFor, arr, false);
            String result = (index == -1) ? "Not Found" : String.valueOf(index);
            output.append(String.format("%-20s %-20d %-20s%n", "Binary Search", numToSearchFor, result));
        }
        double endTime = System.nanoTime();
        totalBinaryTime = (endTime - startTime);
        output.append("=======================================================\n\n");

        // Sequential search loop
        output.append("================== Sequential Search ==================\n");
        output.append(String.format("%-20s %-20s %-20s%n", "Search Type", "Target Value", "Result Index"));
        output.append("-------------------------------------------------------\n");
        startTime = System.nanoTime();
        for(int i=0;i<num;i++){
            int randomIndex = (int)(Math.random()*n);
            int numToSearchFor = arr[randomIndex];
            int index = SearchLibrary.sequentialSearch(numToSearchFor, arr, false);
            String result = (index == -1) ? "Not Found" : String.valueOf(index);
            output.append(String.format("%-20s %-20d %-20s%n", "Sequential Search", numToSearchFor, result));
        }
        endTime = System.nanoTime();
        totalSequentialTime = (endTime - startTime);
        output.append("=======================================================\n\n");

        output.append(String.format("Total time taken for binary search: %.2f ms%n", totalBinaryTime / 1000000));
        output.append(String.format("Total time taken for sequential search: %.2f ms%n", totalSequentialTime / 1000000));

        // Print all output at once
        System.out.print(output.toString());
    }
}