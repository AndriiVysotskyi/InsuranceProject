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
	String id; // format PH(PT, PM)0000/YY

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
	List<HovaLossJpa> hovaLosses;
	@OneToMany(mappedBy = "policyJpa")
	List<TsadGimelLossJpa> tsadGimelLosses;

	@ManyToOne
	VehicleJpa vehicle;

	@ManyToMany(mappedBy = "policies")
	List<PersonJpa> drivers;

	public PolicyJpa() {
		super();
	}

	public String getId() {
		return id;
	}

	public InsuranceType getInsuranceType() {
		return insuranceType;
	}

	public LocalDate getPolicyEffectiveDate() {
		return policyEffectiveDate;
	}

	public LocalDate getPolicyExpireDate() {
		return policyExpireDate;
	}

	public LocalDate getPolicyBreakPoint() {
		return policyBreakPoint;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public boolean isActive() {
		return active;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public EmployeeJpa getAgent() {
		return agent;
	}

//	@ManyToOne
//	LegalEntityJpa legalEntity;

}
