package insurance.dto.lossesDto;

import java.io.Serializable;
import java.time.LocalDate;

import insurance.entities.AddressJpa;
import lombok.*;

@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
public @Data class TsadGimelLossDto implements Serializable{
	private String id;		
	private LocalDate event;
	private LocalDate creation;
	private String location;
	private int driverID;
	
	boolean totalDamage;
	boolean towtruck;
	String damages;
	String appraiser;
	double amounСompensation;

	private String vehicleVictimRegNumber;
	private int vehicleServiceID;
	private int policyID;

	private int employeeOfLossesID;

	public TsadGimelLossDto(LocalDate event, String location, int driverID, boolean towtruck,
			String vehicleVictimRegNumber, int policyID, int employeeOfLossesID) {
		this.event = event;
		this.location = location;
		this.driverID = driverID;
		this.towtruck = towtruck;
		this.vehicleVictimRegNumber = vehicleVictimRegNumber;
		this.policyID = policyID;
		this.employeeOfLossesID = employeeOfLossesID;
	}
	
	
}
