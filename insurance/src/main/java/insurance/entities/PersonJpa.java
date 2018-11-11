package insurance.entities;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;

import insurance.dto.AdditionalInfoDto;
import insurance.dto.enums.*;
import insurance.entities.losses.*;
import lombok.*;

@Entity
@Table(name = "persons")
@AllArgsConstructor
@NoArgsConstructor
public @Data class PersonJpa {
	@Id
	private int personId;
	@Enumerated(EnumType.STRING)
	private Title title;

	private String firstName;
	private String lastName;
	private LocalDate birthDate;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	private long licenseNumber;
	private LocalDate licenseIssueDate;
	private LocalDate licenseExpirationDate;

	private LocalDate createDate;

	
	@OneToOne
	private ContactsJpa contactsJpa;

	// @ManyToMany(mappedBy = "drivers")
	@ElementCollection(targetClass = PolicyJpa.class)
	private List<PolicyJpa> policies;

	@OneToMany(mappedBy = "owner")
	private List<VehicleJpa> vehicles;

	@OneToMany(mappedBy = "driver")
	private List<HovaLossJpa> driversHovaLosses;
	@ManyToMany(mappedBy = "victims")
	private List<HovaLossJpa> victimsHovaLosses;

	@OneToMany(mappedBy = "driver")
	private List<TsadGimelLossJpa> tsadGimelLosses;

	@OneToMany(mappedBy = "driver")
	private List<MakifLossJpa> makifLosses;


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

}
