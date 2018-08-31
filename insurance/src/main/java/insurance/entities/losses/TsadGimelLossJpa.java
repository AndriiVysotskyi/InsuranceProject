package insurance.entities.losses;

import java.time.LocalDate;

import javax.persistence.*;

import insurance.entities.*;

@Entity
@Table(name = "tsadGimelLosses")
public class TsadGimelLossJpa {
	@Id
	String id;		//format T0000/YY
	LocalDate event;
	LocalDate creation;
	@OneToOne
	AddressJpa adress;
	@OneToMany
	PersonJpa driver;
	
	boolean totalDamage;
	boolean towtruck;
	String damages;
	String appraiser;
	double amounСompensation;

	@ManyToOne
	VehicleJpa vehicleVictim;
	@OneToMany
	LegalEntityJpa vehicleServiceJpa;
	@ManyToOne
	PolicyJpa policyJpa;
	
	@ManyToOne
	EmployeeJpa employeeOfLosses;

}
