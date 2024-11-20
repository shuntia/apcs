import java.io.IOException;
import packages.Reader;
public class Circle {
    public static void main(String[] args) throws IOException{
        Reader in = new Reader();
        System.out.print("Enter radius: ");
        double x = in.nextDouble();
        System.out.printf("circumference: %f%narea: %f%n", circumference(x),area(x));
    }
    public static double circumference(double r){
        return 2 * Math.PI * r;
    }
    public static double area(double r){
        return Math.PI * Math.pow(r, 2);
    }
}
