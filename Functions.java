public class Functions {
	
	//Use the main subroutine to check your other subroutines.
	public static void main(String[] args) {
		double testSlope = slope(1,2,3,7); 
		System.out.println(testSlope); // testSlope should be 5/2 = 2.5
		
		// remember we can also System.out the result of the subroutine
		// without storing it in another variable like above.
		System.out.println(quadraticSolution(1,3,9)); 
		
		// You should write additional tests to make sure your functions
		// work for different cases (like the empty String).
		System.out.println(geometricMean(4,9)); 
		System.out.println(firstAndLast("Whdither"));
		System.out.println(middleCapitalization("fidEhfiKldhIK"));
	}
		
	/**
	 * This functions returns the slope of the line through the points
	 * (x1, y1) and (x2, y2).
	 * Precondition: The line must have a defined slope.
	 * @param x1 the x coordinate of the first point
	 * @param y1 the y coordinate of the second point
	 * @param x2 the x coordinate of the first point
	 * @param y2 the y coordinate of the second point
	 * @return the slope of the line
	 */
	public static double slope(double x1, double y1, double x2, double y2) {
		return (y1-y2)/(x1-x2);
	}
	
	/**
	 * This function returns the geometric mean of number1 and number2
	 * Precondition: number1 and number2 must both be positive
	 * @param number1 any positive number
	 * @param number2 any positive number 
	 * @return the geometric mean of the numbers
	 */
	public static double geometricMean(double number1, double number2) {
		return Math.sqrt(number1*number2);
	}
	
	/**
	 * This function returns the larger of the two solutions to the
	 * quadratic equation a*x^2 + b*x + c = 0.
	 * Precondition: A solution must exist.
	 * @param a the coefficient of the squared term
	 * @param b the coefficient of the linear term
	 * @param c the constant term
	 * @return the larger of the two solutions to the equation
	 */
	public static double quadraticSolution(double a, double b, double c) {
		return Math.max((-b-Math.sqrt(b*b-4*a*c))/(2*a),(-b+Math.sqrt(b*b-4*a*c))/(2*a));
	}
	
	/**
	 * This function returns a String consisting of the first and last letters
	 * of the parameter str.
	 * Example: firstAndLast("hello") --> "ho"
	 * Example: firstAndLast("x") --> "xx"
	 * Precondition: the parameter str must be a String of alpha-characters
	 * @param str any non-empty String
	 * @return the first and last letters of the parameter str
	 */
	public static String firstAndLast(String str) {
		return ""+str.charAt(0)+str.charAt(str.length()-1);
	}
	
	/**
	 * This function returns a modified version of the parameter str
	 * with the first and last letters lowercase and all the
	 * remaining letters capitalized.
	 * Example: middleCapitalization("Hello") --> "hELLo"
	 * Example: middleCapitalization("X") --> "x"
	 * Example: middleCapitalization("oK") --> "ok"
	 * Example: middleCapitalization("") --> ""
	 * @param str any String
	 * @return a modified version of the parameter str
	 */
	public static String middleCapitalization(String str) {
		return str.length()>3?""+str.substring(0,1).toLowerCase()+str.substring(1,str.length()-1).toUpperCase()+str.substring(str.length()-1).toLowerCase():str.toLowerCase();
	}
	
}
