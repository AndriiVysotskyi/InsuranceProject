package insurance.dto;

import java.io.Serializable;

import lombok.*;


@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
public @Data class  AdditionalInfoDto implements Serializable {
	
	private String city;
	private String  street;
	private String houseNumber;
	private int flatNumber;
	private int zipCode;
	private String emailAddress;
	private int phoneNumber;
	private boolean isGarageAddress;	
	
}
