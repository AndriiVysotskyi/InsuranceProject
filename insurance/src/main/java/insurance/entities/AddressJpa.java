package insurance.entities;

//import java.util.List;

import javax.persistence.Embeddable;
//import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
//import javax.persistence.Table;
//import javax.persistence.OneToOne;

//@Table(name = "addresses")
//@Entity
@Embeddable
public class AddressJpa {
	@Id
	@GeneratedValue
	private int id;

	String city;
	String street;

	String houseNumber;

//	@OneToOne(mappedBy = "adressPerson")
//	List<PersonJpa> persons;

	@OneToMany(mappedBy = "address")
	LegalEntityJpa legalEntity;

	boolean isGarageAddress;

	public AddressJpa() {
		super();
	}

	public int getId() {
		return id;
	}

	public String getCity() {
		return city;
	}

	public String getStreet() {
		return street;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public boolean isGarageAddress() {
		return isGarageAddress;
	}

}
