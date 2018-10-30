package insurance.entities.losses;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import insurance.entities.*;

@Table(name = "hovalosses")
@Entity
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

	public HovaLossJpa(String id, LocalDate eventDate, LocalDate creationDate, AddressJpa addressJpa, boolean ambulance,
			double costAmbulance, int daysOfDsability, double averageSalaryInDay, String injury,
			double amountСompensation, PolicyJpa policyJpa, PersonJpa driver, List<PersonJpa> victims,
			EmployeeJpa employeeOfLosses) {
		this.id = id;
		this.eventDate = eventDate;
		this.creationDate = creationDate;
		this.addressJpa = addressJpa;
		this.ambulance = ambulance;
		this.costAmbulance = costAmbulance;
		this.daysOfDsability = daysOfDsability;
		this.averageSalaryInDay = averageSalaryInDay;
		this.injury = injury;
		this.amountСompensation = amountСompensation;
		this.policyJpa = policyJpa;
		this.driver = driver;
		this.victims = victims;
		this.employeeOfLosses = employeeOfLosses;
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
