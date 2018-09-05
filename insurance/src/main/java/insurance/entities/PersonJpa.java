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
	int personId;
	@Enumerated(EnumType.STRING)
	Title title;

	String firstName;
	String lastName;
	LocalDate birthDate;

	@Enumerated(EnumType.STRING)
	Gender gender;

	long licenseNumber;
	LocalDate licenseIssueDate;
	LocalDate licenseExpirationDate;

	LocalDate createDate;

	@OneToOne
	ContactsJpa contactsJpa;

	@ManyToMany(mappedBy = "drivers")
	List<PolicyJpa> policies;

	@OneToMany(mappedBy = "owner")
	List<VehicleJpa> vehicles;

	@ManyToMany(mappedBy = "driver")
	List<HovaLossJpa> driversHovaLosses;
	@ManyToMany(mappedBy = "victim")
	List<HovaLossJpa> victimsHovaLosses;

	@OneToMany(mappedBy = "driver")
	List<TsadGimelLossJpa> tsadGimelLosses;

	public PersonJpa() {
		super();
	}

	public int getPersonId() {
		return personId;
	}

	public Title getTitle() {
		return title;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public Gender getGender() {
		return gender;
	}

	public long getLicenseNumber() {
		return licenseNumber;
	}

	public LocalDate getLicenseIssueDate() {
		return licenseIssueDate;
	}

	public LocalDate getLicenseExpirationDate() {
		return licenseExpirationDate;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}
	
	

}
