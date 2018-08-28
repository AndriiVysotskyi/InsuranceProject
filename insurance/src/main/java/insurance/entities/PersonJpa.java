package insurance.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import insurance.dto.enums.Gender;
import insurance.dto.enums.Title;
import insurance.entities.losses.HovaLossJpa;
import insurance.entities.losses.TsadGimelLossJpa;

@Entity
@Table(name = "persons")
public class PersonJpa {
	@Id
	int idPerson;
	@Enumerated(EnumType.STRING)
	Title title;
	
	String firstName;
	String lastName;
	LocalDate dateOfBirth;
	
	@Enumerated(EnumType.STRING)
	Gender gender;
	
	long licenseNumber;
	LocalDate licenseIssueDate;
	LocalDate licenseExpirationDate;
	
	LocalDate createDate;

	@ManyToOne
	AddressJpa adressPerson;
	
	@ManyToMany (mappedBy="drivers")
	List<PolicyJpa> policies;
	
	@OneToMany(mappedBy="owner")
	List<VehicleJpa> vehicles;
	
	@ManyToMany
	List<HovaLossJpa> hovaLosses;
	
	@ManyToMany
	List<TsadGimelLossJpa> tsadGimelLosses;
	
	}
