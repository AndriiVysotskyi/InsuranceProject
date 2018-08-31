package insurance.entities.losses;

import java.time.LocalDate;

import javax.persistence.*;

import insurance.entities.*;

@Entity
@Table(name = "hovaLosses")
public class HovaLossJpa {
	@Id
	String id; // format H0000/YY
	LocalDate event;
	LocalDate creation;
	@OneToOne
	AddressJpa adress;

	@OneToMany
	PersonJpa driver;

	boolean ambulance;
	double costAmbulance;
	int daysOfDsability;
	double averageSalaryInDay;
	String inJury;
	double amountСompensation;

	@ManyToOne
	PolicyJpa policyJpa;

	@OneToMany
	PersonJpa victim;

	@ManyToOne
	EmployeeJpa employeeOfLosses;

}
