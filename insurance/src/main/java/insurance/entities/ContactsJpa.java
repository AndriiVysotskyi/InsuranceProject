package insurance.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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

	public ContactsJpa() {
		super();
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
