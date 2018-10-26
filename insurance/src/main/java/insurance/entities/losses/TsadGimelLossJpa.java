package insurance.entities.losses;

import java.time.LocalDate;

import javax.persistence.*;

import insurance.entities.*;

@Entity
@Table(name = "tsadGimelLosses")
public class TsadGimelLossJpa {
	@Id
	String id; // format T0000/YY
	LocalDate eventDate;
	LocalDate creationDate;
	
	@OneToOne
	ContactsJpa address;

	@ManyToOne
	PersonJpa driver;

	boolean totalDamage;
	boolean towtruck;
	String damages;

	String appraiser;

	double amountСompensation;

	@ManyToOne
	VehicleJpa victimsVehicle;

	@OneToMany
	LegalEntityJpa vehicleServiceJpa;

	@ManyToOne
	PolicyJpa policyJpa;

	@ManyToOne
	EmployeeJpa employeeOfLosses;

	public TsadGimelLossJpa() {
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

	public boolean isTotalDamage() {
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

	public double getAmountСompensation() {
		return amountСompensation;
	}

}
