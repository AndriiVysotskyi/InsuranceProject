package insurance.entities.losses;

import java.time.LocalDate;
import javax.persistence.*;

import insurance.entities.*;
import lombok.*;

@Table(name = "tsadgimellosses")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public @Data class TsadGimelLossJpa {
	@Id
	private String id; // format T0000/YY
	private LocalDate eventDate;
	private LocalDate creationDate;

	@OneToOne
	private ContactsJpa address;

	@ManyToOne
	private PersonJpa driver;

	private boolean totalDamage;
	private boolean towtruck;
	private String damages;

	private String appraiser;

	private double amountСompensation;

	@ManyToOne
	private VehicleJpa victimsVehicle;

	@ManyToOne
	private LegalEntityJpa vehicleServiceJpa;

	@ManyToOne
	private PolicyJpa policyJpa;

	@ManyToOne
	private EmployeeJpa employeeOfLosses;

}
