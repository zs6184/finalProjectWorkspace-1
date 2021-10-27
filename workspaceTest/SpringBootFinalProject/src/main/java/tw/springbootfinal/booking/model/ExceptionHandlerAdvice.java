package tw.springbootfinal.booking.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandlerAdvice {

	public ResponseEntity<String> ajaxHandler(AjaxException exception){
		 System.out.println("ajax error");
		return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);

	}
	
	
	
}
