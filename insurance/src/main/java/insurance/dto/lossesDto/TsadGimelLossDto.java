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
	private AddressJpa adress; //?????????
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
}
