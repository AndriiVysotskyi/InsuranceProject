package insurance.entities;

import javax.persistence.Embeddable;

import lombok.*;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public @Data class AddressJpa {

	private String city;
	private String street;
	private String houseNumber;
	private boolean isGarageAddress;

}
