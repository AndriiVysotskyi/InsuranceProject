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

	@OneToMany (mappedBy = "vehicleVictim")
	List<TsadGimelLossJpa> tsadGimelLosses;

	@OneToMany (mappedBy = "vehicleCulprit")
	List<MakifLossJpa> makifLoss;

}
