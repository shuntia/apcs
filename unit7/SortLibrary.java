// **** YOUR NAME HERE: 󱎤 󰣇
// if you're not cool enough to have nerd fonts: Shunta Koga

import java.io.*;
import java.util.*;

public class SortLibrary {

    public static void main(String[] args) {
        // Test arrays you can use to check your sorts.
        // They represent common arrangements: random, already sorted, reversed, mostly sorted
        int[] random = new int[]{33, 94, 9, 40, 77, 82, 47, 15, 51, 64, 76, 28, 2, 85, 11};
        int[] alreadySorted = new int[]{2, 9, 11, 15, 28, 33, 40, 47, 51, 64, 76, 77, 82, 85, 94};
        int[] reversed = new int[]{94, 85, 82, 77, 76, 64, 51, 47, 40, 33, 28, 15, 11, 9, 2};
        int[] mostlySorted = new int[]{2, 85, 11, 15, 28, 33, 47, 40, 51, 64, 76, 77, 82, 9, 94};
        int[] longerArray = readArrayFile("smallArray.txt");
        int[] myCustomTest = new int[]{15, 23, 42, 4, 8, 16, 1};

        // ***Enter your array to sort here
        int[] arrayToSort = random; // arrayToSort will point to the array you choose
        int[] copyOfArrayToSort = Arrays.copyOf(arrayToSort, arrayToSort.length);

        // ***Enter which sort you want to test
        bubbleSort(arrayToSort);		// Call your sort method -- Remember array is modified in the method, not returned!
        Arrays.sort(copyOfArrayToSort);	// call java.util.Array's sort method for comparison

        if (arrayToSort.length < 50) {
            System.out.println("Result after sort: " + Arrays.toString(arrayToSort));
            System.out.println("Result should be: " + Arrays.toString(copyOfArrayToSort));
        }

        System.out.println("Sorts match? " + Arrays.equals(arrayToSort, copyOfArrayToSort));

    }

    // void normally would be OK.  
    // However, I want you to keep track of and return the number of swaps.
    public static int bubbleSort(int[] nums) {
        int swaps = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - 1; j++) {
                if (nums[j] < nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                    swaps++;
                }
            }
        }
        return swaps;
    }

    // void is OK.  'nums' simply receives a copy of reference to the unsorted
    // array 'arrayToSort' when method is called.  When your method finishes, 
    // 'arrayToSort' will point to the sorted array
    public static void insertionSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            for (int j = i; j > 0 && nums[j] < nums[j - 1]; j--) {
                int temp = nums[j];
                nums[j] = nums[j - 1];
                nums[j - 1] = temp;
            }
        }
    }

    public static void selectionSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int min = nums[i];
            int minindex = i;
            int j = i;
            for (; j < nums.length; j++) {
                min = min < nums[j] ? min : nums[j];
                minindex = min < nums[j] ? minindex : j;
            }
            nums[minindex] = nums[i];
            nums[i] = min;
        }
    }

    // if you use recursion here, you should be able to explain it (i.e. you didn't copy it).
    // After break, we will go over how to do this without recursion.
    public static void mergeSort(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
    }

    public static void mergeSort(int[] nums, int l, int r) {
        if (l >= r) {
            return;
        }
        mergeSort(nums, l, (r + l) / 2);
        mergeSort(nums, (r + l) / 2 + 1, r);
        merge(nums, l, (r + l) / 2, r);
    }

    public static void merge(int[] nums, int left, int mid, int right) {
        int[] L = new int[mid - left + 1];
        int[] R = new int[right - mid];
        for (int i = 0; i < L.length; i++) {
            L[i] = nums[left + i];
        }
        for (int i = 0; i < R.length; i++) {
            R[i] = nums[mid + 1 + i];
        }
        int i = 0, j = 0, k = left;
        while (i < L.length && j < R.length) {
            if (L[i] <= R[j]) {
                nums[k + i + j] = L[i++];

            } else {
                nums[k + i + j] = R[j++];
            }
        }
    }

    // Stalin sort for the spirits of the motherland.
    public static int[] stalinSort(int[] nums) {
        int[] sorted = new int[nums.length];
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                sorted[j] = nums[i];
                j++;
            } else if (nums[i] >= sorted[j - 1]) {
                sorted[j] = nums[i];
                j++;
            }
        }
        int[] finalSorted = new int[j];
        System.arraycopy(sorted, 0, finalSorted, 0, j);
        return finalSorted;
    }

    // Bogo sort is O(1) because I'm feeling lucky.
    public static void bogoSort(int[] nums) {
        Random rand = new Random();
        while (!isSorted(nums)) {
            for (int i = 0; i < nums.length; i++) {
                int randomIndex = rand.nextInt(nums.length);
                int temp = nums[i];
                nums[i] = nums[randomIndex];
                nums[randomIndex] = temp;
            }
        }
    }

    // intellegent design sort because I believe in "The Sorter"
    public static void intelligentDesignSort(int[] nums) {
        // The Sorter wills it
    }

    public static boolean isSorted(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return false;
            }
        }
        return true;
    }

    // Used for loading .txt file numbers into an array, in case you want to test a larger file
    public static int[] readArrayFile(String fileName) {
        try {
            Scanner scanner = new Scanner(new File(fileName));
            ArrayList<Integer> nums = new ArrayList<Integer>();
            while (scanner.hasNextInt()) {
                int n = scanner.nextInt();
                nums.add(n);
            }
            int[] numsArray = new int[nums.size()];
            for (int i = 0; i < numsArray.length; i++) {
                numsArray[i] = nums.get(i);
            }
            return numsArray;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
