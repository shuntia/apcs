/**
 * A program to carry on conversations with a human user.
 * This is the initial version that:  
 * <ul><li>
 *       Uses indexOf to find strings
 * </li><li>
 * 		    Handles responding to simple words and phrases 
 * </li></ul>
 * This version uses a nested if to handle default responses.
 * @author Laurie White
 * @version April 2012
 */
public class Magpie2
{
	/**
	 * Get a default greeting 	
	 * @return a greeting
	 */
	public String getGreeting()
	{
		return "Hello, let's talk.";
	}
	
	/**
	 * Gives a response to a user statement
	 * 
	 * @param statement
	 *            the user statement
	 * @return a response based on the rules given
	 */
	public String getResponse(String statement)
	{
		String response;
		if (statement.contains("no"))
		{
			response = "Why so negative?";
		}
		else if (statement.contains("mother")
				|| statement.contains("father")
				|| statement.contains("sister")
				|| statement.contains("brother"))

		{
			response = "Tell me more about your family.";
		}else if(statement.contains("dog")||statement.contains("cat")){
			response="tell me more about your pet.";
		}else if(statement.contains("smith")){
			response="sounds like a good name.";
		}else if(statement.trim().equals("")){
			response="say something, please.";
		}
		else
		{
			response = getRandomResponse();
		}
		return response;
	}

	/**
	 * Pick a default response to use if nothing else fits.
	 * @return a non-committal string
	 */
	private String getRandomResponse()
	{
		String[] responses=new String[]{"Interesting, tell me more.","Hmmm.","Do you really think so?","You don't say.","What's that?", "Sounds cool!"};
		return responses[(int)(responses.length*Math.random())];
	}
}
