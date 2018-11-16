package insurance.entities.losses;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import insurance.entities.*;
import lombok.*;

@Table(name = "hovalosses")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public  class HovaLossJpa {
	@Id
	private String id; // format H0000/YY
	private LocalDate eventDate;
	private LocalDate creationDate;
	@Embedded
	private AddressJpa addressJpa;

	private boolean ambulance;
	private double costAmbulance;
	private int daysOfDsability;
	private double averageSalaryInDay;
	private String injury;
	private double amountСompensation;

	@ManyToOne
	private PolicyJpa policyJpa;

	@ManyToOne
	private PersonJpa driver;
	@ManyToMany
	private List<PersonJpa> victims;

	@ManyToOne
	private EmployeeJpa employeeOfLosses;
}
