package exceptions;

public class RequestFailedException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RequestFailedException(String message) {
		super(message);
	}
}
