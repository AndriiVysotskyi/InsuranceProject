package insurance.dto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST) // reason=""
public class NoDataException extends RuntimeException {
	public NoDataException(String message) {
		super(message);
	}
}
