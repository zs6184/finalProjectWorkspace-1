package tw.springbootfinal.users.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) //宣告http狀態碼
public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	//需要一個空建構子
	public UserNotFoundException() {
	}

	public UserNotFoundException(String message) {
		super(message);//呼叫父類別
	}

}
