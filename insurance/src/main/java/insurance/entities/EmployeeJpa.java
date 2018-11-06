package insurance.entities;

import java.util.List;

import javax.persistence.*;

import insurance.dto.enums.EmpPosition;
import insurance.entities.losses.*;
import lombok.*;
@Entity
@Table(name = "employees")
@AllArgsConstructor
@NoArgsConstructor
public @Data class EmployeeJpa {
	@Id
	private int workersId;
	private String firstName;
	private String lastName;

	@Enumerated(EnumType.STRING)
	private EmpPosition position;

	@OneToMany(mappedBy = "agent")
	private List<PolicyJpa> policies;

	@OneToMany(mappedBy = "employeeOfLosses")
	private List<HovaLossJpa> hovaLosses;

	@OneToMany(mappedBy = "employeeOfLosses")
	private List<MakifLossJpa> makifLosses;

	@OneToMany(mappedBy = "employeeOfLosses")
	private List<TsadGimelLossJpa> tsadGimelLosses;
	
	public EmployeeJpa(int workersId, String firstName, String lastName, EmpPosition position) {
		this.workersId = workersId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.position = position;
	}

}