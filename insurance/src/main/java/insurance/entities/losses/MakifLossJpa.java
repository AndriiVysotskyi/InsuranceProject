package insurance.entities.losses;



import java.time.LocalDate;

import javax.persistence.*;

import insurance.dto.enums.MakifRisk;
import insurance.entities.*;
@Entity
@Table(name = "makifLosses")
public class MakifLossJpa {
	@Id
	String id;			//format M0000/YY
	LocalDate event;
	LocalDate creation;
	@OneToOne
	AddressJpa adressEvent;
	
	@OneToMany
	PersonJpa driver;
	
	double totalDamage;
	boolean towtruck;
	String damages;
	String appraiser;
	@Enumerated(EnumType.STRING)
	MakifRisk makifRisk;
	double amounСompensation;

	@ManyToOne
	PolicyJpa policyJpa;
	@OneToMany
	LegalEntityJpa vehicleServiceJpa;
	@ManyToOne
	VehicleJpa vehicleCulprit; //second party vehicle
	
	@ManyToOne
	EmployeeJpa employeeOfLosses;
}