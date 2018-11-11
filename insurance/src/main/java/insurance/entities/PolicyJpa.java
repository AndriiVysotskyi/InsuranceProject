package insurance.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import insurance.dto.enums.InsuranceType;
import insurance.entities.losses.*;
import lombok.*;

@Table(name = "policies")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public @Data class PolicyJpa {
	@Id
//	String id; // format PH(PT, PM)0000/YY
	private String id;

	@Enumerated(EnumType.STRING)
	private InsuranceType insuranceType;

	private LocalDate policyEffectiveDate;
	private LocalDate policyExpireDate;
	private LocalDate policyBreakPoint;
	private LocalDateTime createDate;

	private double totalAmount;
	private boolean active;

	private String additionalInfo;

	@ManyToOne
	private EmployeeJpa agent;

	@OneToMany(mappedBy = "policyJpa")
	private List<BillJpa> bills;

	@OneToMany(mappedBy = "policyJpa")
	private List<MakifLossJpa> makifLosses;
	@OneToMany(mappedBy = "policyJpa")
	private List<HovaLossJpa> hovaLosses;
	@OneToMany(mappedBy = "policyJpa")
	private List<TsadGimelLossJpa> tsadGimelLosses;

	@ManyToOne
	private VehicleJpa vehicle;

	@ManyToMany(mappedBy = "policies")
	private Set<PersonJpa> drivers;

	public String getId() {
		return id;
	}

	public PolicyJpa(String id, InsuranceType insuranceType, LocalDate policyEffectiveDate, LocalDate policyExpireDate,
			LocalDateTime createDate, double totalAmount, boolean active, String additionalInfo, EmployeeJpa agent,
			VehicleJpa vehicle, Set<PersonJpa> drivers) {
		this.id=id;
		this.insuranceType = insuranceType;
		this.policyEffectiveDate = policyEffectiveDate;
		this.policyExpireDate = policyExpireDate;
		this.createDate = createDate;
		this.active = active;
		this.additionalInfo = additionalInfo;
		this.agent = agent;
		this.vehicle = vehicle;
		this.drivers = drivers;
	}

	
//	@ManyToOne
//	LegalEntityJpa legalEntity;

}
