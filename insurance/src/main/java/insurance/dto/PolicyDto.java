package insurance.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import insurance.dto.enums.InsuranceType;
import lombok.*;

@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
public @Data class PolicyDto implements Serializable {

	private String policyNumber;
	private InsuranceType insuranceType;

	private String policyEffectiveDate;
	private String policyExpireDate;
	private String policyBreakPoint;
	private String createDate;

	private double totalAmount;
	private boolean active;
	private String additionalInfo;

	private int agentID;
	String regNumberOfVehicle;
	List<Integer> driversID;

	public PolicyDto(InsuranceType insuranceType, String policyEffectiveDate, String policyExpireDate,
			String additionalInfo, int agentID, String regNumberOfVehicle, List<Integer> driversID, double totalAmount) {
		this.insuranceType = insuranceType;
		this.policyEffectiveDate = policyEffectiveDate;
		this.policyExpireDate = policyExpireDate;
		this.additionalInfo = additionalInfo;
		this.agentID = agentID;
		this.regNumberOfVehicle = regNumberOfVehicle;
		this.driversID = driversID;
		this.totalAmount = totalAmount;
	}

	
}
