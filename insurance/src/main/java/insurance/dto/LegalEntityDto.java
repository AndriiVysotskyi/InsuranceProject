package insurance.dto;

import java.io.Serializable;
import lombok.*;

@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
public @Data class LegalEntityDto implements Serializable {
	private int idNumber;
	private String firstName;
	private String lastName;
	private String companyName;
	
	private AdditionalInfoDto additionalInfo;

	public LegalEntityDto(int idNumber, String firstName, String lastName, String companyName) {
		this.idNumber = idNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.companyName = companyName;
	} 
	
	
}
