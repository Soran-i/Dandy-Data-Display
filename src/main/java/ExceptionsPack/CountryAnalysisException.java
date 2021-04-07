package ExceptionsPack;

/**
 * Class defining a Country Analysis Exception thrown when an invalid country is chosen for an analysis. 
 * @author steph
 *
 */
public class CountryAnalysisException extends Exception {
	/**
	 * Constructor that recieves the exception message and generates an exception for invalid country analyses. 
	 * @param message message to be displayed in error
	 */
	public CountryAnalysisException(String message) {
		super(message); 
	}
}
