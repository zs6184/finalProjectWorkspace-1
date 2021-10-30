package tw.springbootfinal.booking.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author
 **/
@ControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(AjaxException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> ajaxHandler(AjaxException exception){
        // TODO
        System.out.println("ajax error");
//        return exception.getMessage();
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(MVCException.class)
//    public String errorHandler(MVCException exception){
//        // TODO
//        return "redirect:/bookings/get";
//    }
//    @ExceptionHandler(Exception.class)
//    public String errorHandler(Exception exception){
//        // TODO
//        return "redirect:/bookings/get";
//    }
}
