package insurance.entities;

import java.util.List;

import javax.persistence.*;

import insurance.dto.enums.EmpPosition;
import insurance.entities.losses.*;

@Entity
@Table(name = "employees")
public class EmployeeJpa {
	@Id
	private int workersId;
	String firstName;
	String lastName;

	@Enumerated(EnumType.STRING)
	EmpPosition position;

	@OneToMany(mappedBy = "agent")
	List<PolicyJpa> policies;

	@OneToMany(mappedBy = "employeeOfLosses")
	List<HovaLossJpa> hovaLosses;

	@OneToMany(mappedBy = "employeeOfLosses")
	List<MakifLossJpa> makifLosses;

	@OneToMany(mappedBy = "employeeOfLosses")
	List<TsadGimelLossJpa> tsadGimelLosses;

	public EmployeeJpa() {
		super();
	}

	
	public EmployeeJpa(int workersId, String firstName, String lastName, EmpPosition position) {
		this.workersId = workersId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.position = position;
	}


	public int getWorkersId() {
		return workersId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public EmpPosition getPosition() {
		return position;
	}

}