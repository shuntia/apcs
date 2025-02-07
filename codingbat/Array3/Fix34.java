import java.util.Arrays;

public class Fix34 {
    public static int[] fix34(int[] nums) {
        int n=nums.length;
        int next4=0;
        while(next4<n && nums[next4]!=4){
            next4++;
        }
        for(int i=0;i<n;i++){
            if(nums[i]==3){
                int temp=nums[i+1];
                nums[i+1]=nums[next4];
                nums[next4]=temp;
                while(next4<n && nums[next4]!=4){
                    next4++;
                }
            }
        }
        return nums;
    }
    public static void main(String[] args){
        // Test cases
        System.out.println(Arrays.toString(fix34(new int[]{1, 3, 1, 4}))); // [1, 3, 4, 1]
        System.out.println(Arrays.toString(fix34(new int[]{1, 3, 1, 4, 4, 3, 1}))); // [1, 3, 4, 1, 1, 3, 4]
        System.out.println(Arrays.toString(fix34(new int[]{3, 2, 2, 4}))); // [3, 4, 2, 2]
        System.out.println(Arrays.toString(fix34(new int[]{3, 1, 4, 3, 1, 4}))); // [3, 4, 1, 3, 4, 1]
    }
}
