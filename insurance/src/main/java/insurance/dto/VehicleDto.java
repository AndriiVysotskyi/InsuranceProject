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

	private int personOwnerID;

	private int legalEntityOwnerID;

	String vehicleModel;
	
	// owner is legalEntity, creatData
		public VehicleDto(String regNumber, int year, float engineVolume, double actualPrice, String color, int kilometrage,
				String vinnumber, int legalEntityOwnerID, String vehicleModel) {
			this.regNumber = regNumber;
			this.year = year;
			this.engineVolume = engineVolume;
			this.actualPrice = actualPrice;
			this.color = color;
			this.kilometrage = kilometrage;
			this.vinnumber = vinnumber;
			this.legalEntityOwnerID = legalEntityOwnerID;
			this.vehicleModel = vehicleModel;
		}

		public VehicleDto(String regNumber, int year, float engineVolume, double actualPrice, String color,
				int kilometrage, String vinnumber, String vehicleModel, int personOwnerID) {
			this.regNumber = regNumber;
			this.year = year;
			this.engineVolume = engineVolume;
			this.actualPrice = actualPrice;
			this.color = color;
			this.kilometrage = kilometrage;
			this.vinnumber = vinnumber;
			this.personOwnerID = personOwnerID;
			this.vehicleModel = vehicleModel;
		}
		
		
		
}
