package insurance.entities;

import java.util.List;

import javax.persistence.*;
import lombok.*;

@Table(name = "vehicle_model")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public @Data class ModelJpa {
	@Id
	private String modelName;
	
	private String company;
	private String country;
	private int modelYear;
	private double basicTarif;

	@OneToMany(mappedBy = "vehicleModel")
	private List<VehicleJpa> vehicles;

	public ModelJpa(String modelName, String company, String country, int modelYear, double basicTarif) {
		this.modelName = modelName;
		this.company = company;
		this.country = country;
		this.modelYear = modelYear;
		this.basicTarif = basicTarif;
	}

}