package insurance.dto;

import java.io.Serializable;

import insurance.dto.enums.EmpPosition;
import lombok.*;

@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
public @Data class EmployeeDto implements Serializable {
	private int workersId;
	private String firstName;
	private String lastName;
	private EmpPosition position;	
	
}
