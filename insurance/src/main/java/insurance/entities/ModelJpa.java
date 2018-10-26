package insurance.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name = "vehicle_model")
@Entity

public class ModelJpa {
	@Id
	String modelName;
	
	String company;
	String country;
	int modelYear;
	double basicTarif;

	@OneToMany(mappedBy = "vehicleModel")
	List<VehicleJpa> vehicles;

	

	public ModelJpa() {
	}

	public ModelJpa(String modelName, String company, String country, int modelYear, double basicTarif) {
		this.modelName = modelName;
		this.company = company;
		this.country = country;
		this.modelYear = modelYear;
		this.basicTarif = basicTarif;
	}

	public String getModelName() {
		return modelName;
	}

	public String getCompany() {
		return company;
	}

	public String getCountry() {
		return country;
	}

	public int getModelYear() {
		return modelYear;
	}

	public double getBasicTarif() {
		return basicTarif;
	}
	
	public List<VehicleJpa> getVehicles() {
		return vehicles;
	}

}