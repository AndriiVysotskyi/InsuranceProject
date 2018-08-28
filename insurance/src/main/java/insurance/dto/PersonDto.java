package insurance.dto;

import java.io.Serializable;
import java.time.LocalDate;

import insurance.dto.enums.Gender;
import insurance.dto.enums.Title;
import lombok.*;

@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
public @Data class PersonDto implements Serializable {
	private int idPerson;
	private Title title;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private Gender gender;
	private long licenseNumber;
	private LocalDate licenseIssueDate;
	private LocalDate licenseExpirationDate;
	private LocalDate createDate;
}
