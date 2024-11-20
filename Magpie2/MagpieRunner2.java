import java.util.Scanner;

/**
 * A simple class to run the Magpie class.
 * @author Laurie White
 * @version April 2012
 */
public class MagpieRunner2
{

	/**
	 * Create a Magpie, give it user input, and print its replies.
	 */
	public static void main(String[] args)
	{
		Magpie2 maggie = new Magpie2();
		
		System.out.println (maggie.getGreeting());
        try (Scanner in = new Scanner (System.in)) {
            String statement = in.nextLine();
            
            while (!statement.toUpperCase().equals("BYE"))
            {
                System.out.println (maggie.getResponse(statement));
                statement = in.nextLine();
            }
        }
	}

}
