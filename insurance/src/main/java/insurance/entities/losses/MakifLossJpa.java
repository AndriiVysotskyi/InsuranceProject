package insurance.entities.losses;

import java.time.LocalDate;

import javax.persistence.*;

import insurance.dto.enums.MakifRisk;
import insurance.entities.*;

@Table(name = "makiflosses")
@Entity
public class MakifLossJpa {
	@Id
	String id; // format M0000/YY
	LocalDate eventDate;
	LocalDate creationDate;
	@Embedded
	AddressJpa adressEvent;

	@ManyToOne
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
	@ManyToOne
	LegalEntityJpa vehicleServiceJpa;
	@ManyToOne
	VehicleJpa vehicleCulprit; // second party vehicle

	@ManyToOne
	EmployeeJpa employeeOfLosses;

	public MakifLossJpa() {
		super();
	}

	public String getId() {
		return id;
	}

	public LocalDate getEventDate() {
		return eventDate;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public AddressJpa getAdressEvent() {
		return adressEvent;
	}

	public double getTotalDamage() {
		return totalDamage;
	}

	public boolean isTowtruck() {
		return towtruck;
	}

	public String getDamages() {
		return damages;
	}

	public String getAppraiser() {
		return appraiser;
	}

	public MakifRisk getMakifRisk() {
		return makifRisk;
	}

	public double getAmounСompensation() {
		return amounСompensation;
	}

}