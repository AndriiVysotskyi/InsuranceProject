package insurance.entities;

//import java.util.List;

import javax.persistence.Embeddable;
//import javax.persistence.Entity;
//import javax.persistence.Table;
//import javax.persistence.OneToOne;

@Embeddable
public class AddressJpa {

	String city;
	String street;

	String houseNumber;

	boolean isGarageAddress;

	public AddressJpa() {
		super();
	}

	public AddressJpa(String city, String street, String houseNumber, boolean isGarageAddress) {
		this.city = city;
		this.street = street;
		this.houseNumber = houseNumber;
		this.isGarageAddress = isGarageAddress;
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
