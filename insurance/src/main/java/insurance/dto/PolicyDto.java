package insurance.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

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
	 List<Integer> driversID;
	int legalEntityID;
	
	
	// constructor from client to server
	// createDate, totalAmount, active have to be created in server
	public PolicyDto( InsuranceType insuranceType, LocalDate policyEffectiveDate,
			LocalDate policyExpireDate, String additionalInfo,
			int agentID, String regNumberOfVehicle, List<Integer> driversID, int legalEntityID) {
		this.insuranceType = insuranceType;
		this.policyEffectiveDate = policyEffectiveDate;
		this.policyExpireDate = policyExpireDate;
		this.additionalInfo = additionalInfo;
		this.agentID = agentID;
		this.regNumberOfVehicle = regNumberOfVehicle;
		this.driversID = driversID;
		this.legalEntityID = legalEntityID;
	}
	
	
}
