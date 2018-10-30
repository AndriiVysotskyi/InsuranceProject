package insurance.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import insurance.dto.enums.Gender;
import insurance.dto.enums.Title;
import insurance.entities.losses.HovaLossJpa;
import insurance.entities.losses.MakifLossJpa;
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

	// @ManyToMany(mappedBy = "drivers")
	@ElementCollection(targetClass = PolicyJpa.class)
	List<PolicyJpa> policies;

	@OneToMany(mappedBy = "owner")
	List<VehicleJpa> vehicles;

	@OneToMany(mappedBy = "driver")
	List<HovaLossJpa> driversHovaLosses;
	@ManyToMany(mappedBy = "victims")
	List<HovaLossJpa> victimsHovaLosses;

	@OneToMany(mappedBy = "driver")
	List<TsadGimelLossJpa> tsadGimelLosses;

	@OneToMany(mappedBy = "driver")
	List<MakifLossJpa> makifLosses;

	public PersonJpa() {
		super();
	}

	public PersonJpa(int personId, Title title, String firstName, String lastName, LocalDate birthDate, Gender gender,
			long licenseNumber, LocalDate licenseIssueDate, LocalDate licenseExpirationDate, LocalDate createDate,
			ContactsJpa contactsJpa) {
		this.personId = personId;
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.gender = gender;
		this.licenseNumber = licenseNumber;
		this.licenseIssueDate = licenseIssueDate;
		this.licenseExpirationDate = licenseExpirationDate;
		this.createDate = createDate;
		this.contactsJpa = contactsJpa;
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
