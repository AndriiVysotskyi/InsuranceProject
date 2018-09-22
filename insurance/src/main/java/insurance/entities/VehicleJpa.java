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
	LegalEntityJpa LegalEntityOwner;

	@OneToMany(mappedBy = "vehicle")
	List<PolicyJpa> policies;

	@ManyToOne
	ModelJpa vehicleModel;

	@OneToMany(mappedBy = "victimsVehilce")
	List<TsadGimelLossJpa> tsadGimelLosses;

	@OneToMany(mappedBy = "vehicleCulprit")
	List<MakifLossJpa> makifLoss;

	public VehicleJpa() {
		super();
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

}
