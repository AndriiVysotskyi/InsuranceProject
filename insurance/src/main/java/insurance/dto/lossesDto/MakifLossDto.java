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
	private LocalDate event;
	private LocalDate creation;
	private AddressJpa adress; //??????
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
}
