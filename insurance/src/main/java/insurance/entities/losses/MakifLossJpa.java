package insurance.entities.losses;

import java.time.LocalDate;

import javax.persistence.*;

import insurance.dto.enums.MakifRisk;
import insurance.entities.*;
import lombok.*;

@Table(name = "makiflosses")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public @Data class MakifLossJpa {
	@Id
	private String id; // format M0000/YY
	private LocalDate eventDate;
	private LocalDate creationDate;
	@Embedded
	private AddressJpa adressEvent;

	@ManyToOne
	private PersonJpa driver;

	private double totalDamage;
	private boolean towtruck;
	private String damages;

	private String appraiser;
	@Enumerated(EnumType.STRING)
	private MakifRisk makifRisk;
	private double amounСompensation;

	@ManyToOne
	private PolicyJpa policyJpa;
	@ManyToOne
	private LegalEntityJpa vehicleServiceJpa;
	@ManyToOne
	private VehicleJpa vehicleCulprit; // second party vehicle

	@ManyToOne
	private EmployeeJpa employeeOfLosses;

}