package ExceptionsPack;

/**
 * Class defining the start year exceptions and extending the java exception. THrown any time an incompatible start year is selected. 
 * @author steph
 *
 */
public class StartYearException extends Exception {
	/**
	 * Constructor for the start year exception. Thrown any time there is an incomptible start year selected. 
	 * @param message message error message detailing the type of error
	 */
	public StartYearException(String message) {
		super(message); 
	}
}
