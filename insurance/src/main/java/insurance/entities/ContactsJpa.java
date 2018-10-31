package insurance.entities;

import javax.persistence.*;

import insurance.entities.losses.TsadGimelLossJpa;
import lombok.*;

@Entity
@Table(name = "contacts")
@AllArgsConstructor
@NoArgsConstructor
public @Data class ContactsJpa {
	@Id
	@GeneratedValue
	private int contactId;

	private String emailAddress;
	private int phoneNumber;

	@Embedded
	private AddressJpa addressJpa;
	private int flatNumber;
	private int zipCode;

	@OneToOne
	private TsadGimelLossJpa tsadGimelLossJpa;

	public ContactsJpa(String emailAddress, int phoneNumber, AddressJpa addressJpa, int flatNumber, int zipCode) {
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
		this.addressJpa = addressJpa;
		this.flatNumber = flatNumber;
		this.zipCode = zipCode;
	}

	
}
