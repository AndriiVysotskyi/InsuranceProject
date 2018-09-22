package insurance.entities.losses;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import insurance.entities.*;

@Entity
@Table(name = "hovaLosses")
public class HovaLossJpa {
	@Id
	String id; // format H0000/YY
	LocalDate eventDate;
	LocalDate creationDate;
	@Embedded
	AddressJpa addressJpa;

	boolean ambulance;
	double costAmbulance;
	int daysOfDsability;
	double averageSalaryInDay;
	String injury;
	double amountСompensation;

	@ManyToOne
	PolicyJpa policyJpa;

	@ManyToOne
	PersonJpa driver;
	@ManyToMany
	List<PersonJpa> victims;

	@ManyToOne
	EmployeeJpa employeeOfLosses;

	public HovaLossJpa() {
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

	public boolean isAmbulance() {
		return ambulance;
	}

	public double getCostAmbulance() {
		return costAmbulance;
	}

	public int getDaysOfDsability() {
		return daysOfDsability;
	}

	public double getAverageSalaryInDay() {
		return averageSalaryInDay;
	}

	public String getInjury() {
		return injury;
	}

	public double getAmountСompensation() {
		return amountСompensation;
	}

}
