package insurance.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.*;

@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
public @Data class PolicyEditLogDto implements Serializable {
	private int policyJpa;
	private String editedTableName;
	private LocalDate editDate;
	private String additionalInfo;
	private String editedBy;
}

