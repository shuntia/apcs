
import static org.junit.Assert.*;

import org.junit.*;

public class DiverseArrayTest {

    int[] shortArray;
    int[][] diverseArray;

    @Before  // Anything marked with the @Before annotation is run before each test
    public void setUp() throws Exception {
        shortArray = new int[]{1, 2, 3};
        diverseArray = new int[][]{
            {1, 3, 2, 7, 3},
            {10, 10, 4, 6, 2},
            {5, 3, 5, 9, 6},
            {7, 6, 4, 2, 1}
        };
    }

    @Test
    public void testArraySum() {
        assertEquals(6, DiverseArray.arraySum(shortArray));
    }

    @Test
    public void testRowSums() {
        // can use assertArrayEquals(int[], int[]);
        // or can use assertTrue(Arrays.equals(int[], int[])
        // to check the contents of two 1D arrays being equal
        assertEquals(16, DiverseArray.rowSum(diverseArray, 0));

    }

    @Test
    public void testIsDiverseTrueCase() {
        asssertEquals(true,DiverseArray.isDiverse(diverseArray));
    }

    @Test
    public void testIsDiverseFalseCase() {
        assertEquals(false,DiverseArray.isDiverse(new int[][]{
            {},
            {},
            {},
            {}
        }
        ));
    }
    
    public static void main(String[] args) {
        org.junit.runner.JUnitCore.runClasses(DiverseArrayTest.class);
    }

}
