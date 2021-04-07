package ExceptionsPack;

/**
 * Exception class for the viewer analysis. Thrown any time for an incompatible viewer is selected for the analysis. 
 * @author steph
 *
 */
public class ViewerAnalysisException extends Exception {
	/**
	 * constructor for the viewer analysis exceptions. Thrown any time an incompatible viewer is selected for the analysis. 
	 * @param message message error message detailing the type of error
	 */
	public ViewerAnalysisException(String message) {  
		super(message); 
	}
}