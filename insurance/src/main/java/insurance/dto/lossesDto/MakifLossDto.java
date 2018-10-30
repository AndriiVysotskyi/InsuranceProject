package insurance.dto.lossesDto;

import java.io.Serializable;
import java.time.LocalDate;

import insurance.dto.enums.MakifRisk;
import insurance.entities.AddressJpa;
import lombok.*;

@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
public @Data class MakifLossDto implements Serializable{
	private String id;			
	private LocalDate eventData;
	private LocalDate creationData;
	private String location;
	private long driverID;
	
	double totalDamage;
	boolean towtruck;
	String damages;
	String appraiser;
	MakifRisk makifRisk;
	double amounСompensation;

	private int policyID;
	private int vehicleServiceID;
	private String vehicleCulpritRegNumber; //second party vehicle
	private int employeeOfLossesID;
	
	public MakifLossDto(LocalDate eventData, String location, long driverID, boolean towtruck, MakifRisk makifRisk,
			int policyID, String vehicleCulpritRegNumber, int employeeOfLossesID) {
		this.eventData = eventData;
		this.location = location;
		this.driverID = driverID;
		this.towtruck = towtruck;
		this.makifRisk = makifRisk;
		this.policyID = policyID;
		this.vehicleCulpritRegNumber = vehicleCulpritRegNumber;
		this.employeeOfLossesID = employeeOfLossesID;
	}

}
