package tw.springbootfinal.booking.model;

public class MVCException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MVCException(String message) {
		super(message);
	}
	
	public MVCException() {
		
	}
}
