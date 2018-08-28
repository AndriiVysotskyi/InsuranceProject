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
	private boolean isActive;	
	
}
