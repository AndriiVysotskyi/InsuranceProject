package insurance.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import insurance.dto.enums.InsuranceType;
import insurance.entities.losses.*;

@Table(name = "policies")
@Entity
public class PolicyJpa implements Serializable{
	@Id
//	String id; // format PH(PT, PM)0000/YY
	int id;

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

	public int getId() {
		return id;
	}
	

	public PolicyJpa(int id, InsuranceType insuranceType, LocalDate policyEffectiveDate, LocalDate policyExpireDate,
			 LocalDate createDate, double totalAmount, boolean active, String additionalInfo,
			EmployeeJpa agent, VehicleJpa vehicle,List<PersonJpa> drivers) {
		this.id = id;
		this.insuranceType = insuranceType;
		this.policyEffectiveDate = policyEffectiveDate;
		this.policyExpireDate = policyExpireDate;
		this.createDate = createDate;
		this.active = active;
		this.additionalInfo = additionalInfo;
		this.agent = agent;
		this.vehicle=vehicle;
		this.drivers=drivers;
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
	
	public List<PersonJpa> getDrivers() {
		return drivers;
	}

	public void setDrivers(List<PersonJpa> drivers) {
		this.drivers = drivers;
	}

//	@ManyToOne
//	LegalEntityJpa legalEntity;

}
