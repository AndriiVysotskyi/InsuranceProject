package insurance.entities;

import java.util.List;

import javax.persistence.*;

import insurance.dto.enums.EmpPosition;
import insurance.entities.losses.*;

@Entity
@Table(name = "Employees")
public class EmployeeJpa {
	@Id
	private int workersId;
	String firstName;
	String lastName;
	
	@Enumerated(EnumType.STRING)
	EmpPosition position;
	
	@OneToMany(mappedBy="agent")
	List<PolicyJpa> policies;
	
	@OneToMany (mappedBy="employeeOfLosses")
	List <HovaLossJpa> hovaLosses;
	
	@OneToMany (mappedBy="employeeOfLosses")
	List <MakifLossJpa> makifLosses;
	
	@OneToMany (mappedBy="employeeOfLosses")
	List <TsadGimelLossJpa> tsadGimelLosses;
	
	
	
	
	
}