package insurance.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.*;

@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
public @Data class VehicleDto implements Serializable {
	private String regNumber;
	private int year;
	private float engineVolume;
	private double actualPrice;
	private String color;
	private int kilometrage;
	private String vinnumber;
	private LocalDate createDate;
	private boolean active;

	private int personOwnerID;

	private int LegalEntityOwnerID;

	String vehicleModel;
}
