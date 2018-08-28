package insurance.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name = "addresses")
@Entity
public class AddressJpa {
	@Id
	@GeneratedValue
	private int id;

	String city;
	String street;
	String houseNumber;
	int flatNumber;
	int zipCode;
	String emailAddress;
	int phoneNumber;

	@OneToMany(mappedBy = "adressPerson")
	List<PersonJpa> persons;

	@OneToMany(mappedBy = "adress")
	LegalEntityJpa legalEntity;

	boolean isGarageAddress;
}
