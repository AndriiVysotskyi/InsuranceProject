package insurance.dto.lossesDto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import insurance.dto.AdditionalInfoDto;
import insurance.entities.*;
import lombok.*;

@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
public @Data class HovaLossDto implements Serializable{
	private String id; 
	private LocalDate eventData;
	private LocalDate creation;
	private String location;
	private int driverID;

	private boolean ambulance;
	private double costAmbulance;
	private int daysOfDsability;
	private double averageSalaryInDay;
	private String injury;
	private double amounСompensation;

	private int policyID;
	private List<Integer> victimID;
	private int employeeOfLossesID;
	
	//create fields: id, createData
	// other fields like costAmbulanse, daysOfDsability, averageSalaryInDay, injury, amounСompensation will be adding after 
	public HovaLossDto(LocalDate eventData, String location, int driverID, boolean ambulance, int policyID,
			List<Integer> victimID, int employeeOfLossesID) {
		this.eventData = eventData;
		this.location = location;
		this.driverID = driverID;
		this.ambulance = ambulance;
		this.policyID = policyID;
		this.victimID = victimID;
		this.employeeOfLossesID = employeeOfLossesID;
	}

	
	
}
