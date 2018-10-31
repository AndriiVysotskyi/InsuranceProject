package insurance.entities;

import java.util.List;

import javax.persistence.*;
import insurance.entities.losses.*;
import lombok.*;

@Entity
@Table(name = "legal_entity")
@AllArgsConstructor
@NoArgsConstructor
public @Data class LegalEntityJpa {
	@Id
	@GeneratedValue
	private int id;

	private int idNumber;
	private String firstName;
	private String lastName;
	private String companyName;

	@OneToOne
	private ContactsJpa contactsJpa;

//	@OneToMany(mappedBy = "legalEntity")
//	List<PolicyJpa> policies;

	@OneToMany(mappedBy = "legalEntityOwner")
	private List<VehicleJpa> vehicles;

	@OneToMany(mappedBy = "vehicleServiceJpa")
	private List<MakifLossJpa> makifLosses;

	@OneToMany(mappedBy = "vehicleServiceJpa")
	private List<TsadGimelLossJpa> tsadGimelLosses;

	public LegalEntityJpa(int idNumber, String firstName, String lastName, String companyName,
			ContactsJpa contactsJpa) {
		this.idNumber = idNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.companyName = companyName;
		this.contactsJpa = contactsJpa;
	}
}
