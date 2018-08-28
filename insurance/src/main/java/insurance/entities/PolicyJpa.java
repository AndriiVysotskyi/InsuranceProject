package insurance.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import insurance.dto.enums.InsuranceType;
import insurance.entities.losses.*;

@Table(name = "policies")
@Entity
public class PolicyJpa {
	@Id
	private int policyNumber;
	
	@Enumerated(EnumType.STRING)
	InsuranceType insuranceType;
	
	LocalDate policyEffectiveDate;
	LocalDate policyExpireDate;
	LocalDate policyBreakPoint;
	LocalDate createDate;
	
	double totalAmount;
	boolean active;
	
	String additionalInfo;
	
	@ManyToOne
	EmployeeJpa agent;

	@OneToMany(mappedBy = "policyJpa")
	List<BillJpa> bills;
	
	@OneToMany(mappedBy = "policyJpa")
	List<MakifLossJpa> makifLosses;
	@OneToMany(mappedBy = "policyJpa")
	List<HovaLossJpa> hovafLosses;
	@OneToMany(mappedBy = "policyJpa")
	List<TsadGimelLossJpa> tsadGimelLosses;
	

	@ManyToOne
	VehicleJpa vehicle;
	@ManyToMany
	List<PersonJpa> drivers;
	@ManyToOne
	LegalEntityJpa legalEntity;
	
}
