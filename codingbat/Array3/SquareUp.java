import java.util.Arrays;
public class SquareUp {
    public int[] squareUp(int n) {
        int[] result = new int[n * n];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (j <= i) {
                    result[i * n - j] = j;
                }
            }
        }
        return result;
    }
    public static void main(String[] args) {
        // Test cases
        SquareUp obj = new SquareUp();
        System.out.println(Arrays.toString(obj.squareUp(3)));
        System.out.println(Arrays.toString(obj.squareUp(2)));
        System.out.println(Arrays.toString(obj.squareUp(4)));
    }
}