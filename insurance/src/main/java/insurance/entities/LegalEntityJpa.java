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
	boolean isActive;
	
	@ManyToOne
	AddressJpa adress;
	
	@OneToMany(mappedBy = "legalEntity")
	List<PolicyJpa> policies;
	
	@OneToMany(mappedBy="legalEntityOwner")
	List<VehicleJpa> vehicles;
	
	@OneToMany (mappedBy = "vehicleServiceJpa")
	List<MakifLossJpa> makifLosses;
	
	@OneToMany (mappedBy = "vehicleServiceJpa")
	List<TsadGimelLossJpa> tsadGimelLosses;
	
}
