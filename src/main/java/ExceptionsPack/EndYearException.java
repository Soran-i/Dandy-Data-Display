package ExceptionsPack;

/**
 * Class defining the End of Year Exception message that extends the java exception. Is thrown when an incompatable end of year is chosen. 
 * @author steph
 *
 */
public class EndYearException extends Exception {
	/**
	 * Constructor for the End of Year Exception. Generates an exception using the input message string 
	 * @param message to be displayed in error. 
	 */
	public EndYearException(String message) {
		super(message); 
	}
}

