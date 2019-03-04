package insurance.dto;

import java.io.Serializable;

import lombok.*;

@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
public @Data class ModelDto implements Serializable {
	private String modelName;
	private String company;
	private String country;
	private int modelYear;
	private double basicTarif;

}


