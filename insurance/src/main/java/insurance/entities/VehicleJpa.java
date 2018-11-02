package insurance.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import insurance.entities.losses.*;
import insurance.entities.losses.TsadGimelLossJpa;
import lombok.*;

@Table(name = "vehicles")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public @Data class VehicleJpa {
	@Id
	private String regNumber;
	private int year;
	private float engineVolume;
	private double actualPrice;
	private String color;
	private int kilometrage;
	private String vinnumber;
	private LocalDate createDate;
	private boolean active;

	@ManyToOne
	private PersonJpa owner;

	@ManyToOne
	private LegalEntityJpa legalEntityOwner;

	@OneToMany(mappedBy = "vehicle")
	private List<PolicyJpa> policies;

	@ManyToOne
	private ModelJpa vehicleModel;

	@OneToMany(mappedBy = "victimsVehicle")
	private List<TsadGimelLossJpa> tsadGimelLosses;

	@OneToMany(mappedBy = "vehicleCulprit")
	private List<MakifLossJpa> makifLoss;

	public VehicleJpa(String regNumber, int year, float engineVolume, double actualPrice, String color, int kilometrage,
			String vinnumber, LocalDate createDate, PersonJpa owner, ModelJpa vehicleModel) {
		this.regNumber = regNumber;
		this.year = year;
		this.engineVolume = engineVolume;
		this.actualPrice = actualPrice;
		this.color = color;
		this.kilometrage = kilometrage;
		this.vinnumber = vinnumber;
		this.createDate = createDate;
		// this.active = active;
		this.owner = owner;
		this.legalEntityOwner = legalEntityOwner;
		this.vehicleModel = vehicleModel;
	}

	public VehicleJpa(String regNumber, int year, float engineVolume, double actualPrice, String color, int kilometrage,
			String vinnumber, LocalDate createDate, LegalEntityJpa owner, ModelJpa vehicleModel) {
		this.regNumber = regNumber;
		this.year = year;
		this.engineVolume = engineVolume;
		this.actualPrice = actualPrice;
		this.color = color;
		this.kilometrage = kilometrage;
		this.vinnumber = vinnumber;
		this.createDate = createDate;
		// this.active = active;
		this.legalEntityOwner = owner;
		this.vehicleModel = vehicleModel;
	}

}
