package insurance.dto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT, reason="no policy")
public class NoPolicyException extends RuntimeException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/*public NoPolicyException() {
	
super("no policy");
}*/
}
