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
}