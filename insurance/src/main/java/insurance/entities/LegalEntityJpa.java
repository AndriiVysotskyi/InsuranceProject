package insurance.entities;

import java.util.List;

import javax.persistence.*;

import insurance.entities.losses.MakifLossJpa;
import insurance.entities.losses.TsadGimelLossJpa;

@Entity
@Table(name = "legal_entity")
public class LegalEntityJpa {
	@Id
	@GeneratedValue
	private int id;

	int idNumber;
	String firstName;
	String lastName;
	String companyName;

	@OneToOne
	ContactsJpa contactsJpa;

//	@OneToMany(mappedBy = "legalEntity")
//	List<PolicyJpa> policies;

	@OneToMany(mappedBy = "legalEntityOwner")
	List<VehicleJpa> vehicles;

	@OneToMany(mappedBy = "vehicleServiceJpa")
	List<MakifLossJpa> makifLosses;

	@OneToMany(mappedBy = "vehicleServiceJpa")
	List<TsadGimelLossJpa> tsadGimelLosses;

	public LegalEntityJpa() {
		super();
	}

	public int getId() {
		return id;
	}

	public int getIdNumber() {
		return idNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getCompanyName() {
		return companyName;
	}

}
