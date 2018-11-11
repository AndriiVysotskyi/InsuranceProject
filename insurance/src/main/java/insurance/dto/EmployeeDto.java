package insurance.dto;

import java.io.Serializable;

import insurance.dto.enums.EmpPosition;
import lombok.*;

@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
public @Data class EmployeeDto implements Serializable {
	private int workersId;
	private String login;
	private String firstName;
	private String lastName;
	private EmpPosition position;
	
	// workersId has to be created in server
	public EmployeeDto(String firstName, String lastName, EmpPosition position) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.position = position;
	}	
	
	
	
}
