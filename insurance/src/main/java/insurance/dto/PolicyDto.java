package insurance.dto;

import java.io.Serializable;
import java.time.LocalDate;

import insurance.dto.enums.InsuranceType;
import lombok.*;

@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
public @Data class PolicyDto implements Serializable {

	private int policyNumber;
	private InsuranceType insuranceType;

	private LocalDate policyEffectiveDate;
	private LocalDate policyExpireDate;
	private LocalDate policyBreakPoint;
	private LocalDate createDate;

	private double totalAmount;
	private boolean active;
	private String additionalInfo;
	
	private int agentID;
	String regNumberOfVehicle;
	int [] driversID;
	int legalEntityID;
}
