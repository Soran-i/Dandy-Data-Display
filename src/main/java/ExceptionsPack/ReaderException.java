package ExceptionsPack;

/**
 * A class defining the Reader Exceptions. These are thrown for any errors reading the World bank database or for any computation errors. 
 * @author steph
 *
 */
public class ReaderException extends Exception {
	/**
	 * Constructor for the reader exceptiosn that creates a java exception using the input message. is thrown for any errors reading the worldbank api or for any computation errors. 
	 * @param message error message detailing the type of error
	 */
	public ReaderException(String message) {
		super(message); 
	}
}
