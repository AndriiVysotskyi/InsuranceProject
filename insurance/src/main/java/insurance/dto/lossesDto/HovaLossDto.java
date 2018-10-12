package insurance.dto.lossesDto;

import java.io.Serializable;
import java.time.LocalDate;

import insurance.dto.AdditionalInfoDto;
import insurance.entities.*;
import lombok.*;

@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
public @Data class HovaLossDto implements Serializable{
	private String id; 
	private LocalDate event;
	private LocalDate creation;
	private AdditionalInfoDto adress; //?????
	private int driverID;

	private boolean ambulance;
	private double costAmbulance;
	private int daysOfDsability;
	private double averageSalaryInDay;
	private String injury;
	private double amounСompensation;

	private int policyID;
	private int victimID;
	int employeeOfLossesID;

}
