package insurance.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import insurance.entities.losses.MakifLossJpa;
import insurance.entities.losses.TsadGimelLossJpa;

@Table(name = "vehicles")
@Entity
public class VehicleJpa {
	@Id
	String regNumber;
	int year;
	float engineVolume;
	double actualPrice;
	String color;
	int kilometrage;
	String vinnumber;
	LocalDate createDate;
	boolean active;

	@ManyToOne
	PersonJpa owner;

	@ManyToOne
	LegalEntityJpa legalEntityOwner;

	@OneToMany(mappedBy = "vehicle")
	List<PolicyJpa> policies;

	@ManyToOne
	ModelJpa vehicleModel;

	@OneToMany(mappedBy = "victimsVehicle")
	List<TsadGimelLossJpa> tsadGimelLosses;

	@OneToMany(mappedBy = "vehicleCulprit")
	List<MakifLossJpa> makifLoss;

	public VehicleJpa() {
		super();
	}

	public VehicleJpa(String regNumber, int year, float engineVolume, double actualPrice, String color, int kilometrage,
			String vinnumber, LocalDate createDate, PersonJpa owner, LegalEntityJpa legalEntityOwner,
			ModelJpa vehicleModel) {
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

	public String getRegNumber() {
		return regNumber;
	}

	public int getYear() {
		return year;
	}

	public float getEngineVolume() {
		return engineVolume;
	}

	public double getActualPrice() {
		return actualPrice;
	}

	public String getColor() {
		return color;
	}

	public int getKilometrage() {
		return kilometrage;
	}

	public String getVinnumber() {
		return vinnumber;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public boolean isActive() {
		return active;
	}

	public PersonJpa getOwner() {
		return owner;
	}

	public LegalEntityJpa getLegalEntityOwner() {
		return legalEntityOwner;
	}

	public List<PolicyJpa> getPolicies() {
		return policies;
	}

	public ModelJpa getVehicleModel() {
		return vehicleModel;
	}

	public List<TsadGimelLossJpa> getTsadGimelLosses() {
		return tsadGimelLosses;
	}

	public List<MakifLossJpa> getMakifLoss() {
		return makifLoss;
	}

}
