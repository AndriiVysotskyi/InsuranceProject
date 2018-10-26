package insurance.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import insurance.entities.losses.TsadGimelLossJpa;

@Entity
@Table(name = "contacts")
public class ContactsJpa {
	@Id
	@GeneratedValue
	private int id;

	String emailAddress;
	int phoneNumber;

	@Embedded
	AddressJpa addressJpa;
	int flatNumber;
	int zipCode;

	@OneToOne
	TsadGimelLossJpa tsadGimelLossJpa;
	
	public ContactsJpa() {
		super();
	}

	public ContactsJpa(String emailAddress, int phoneNumber, AddressJpa addressJpa, int flatNumber, int zipCode) {
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
		this.addressJpa = addressJpa;
		this.flatNumber = flatNumber;
		this.zipCode = zipCode;
	}


	public int getId() {
		return id;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public int getFlatNumber() {
		return flatNumber;
	}

	public int getZipCode() {
		return zipCode;
	}

}
