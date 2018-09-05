package insurance.entities;

import java.time.LocalDate;

import javax.persistence.*;

@Table(name = "policies_edits_logs")
@Entity
public class PolicyEditLogJpa {
	@Id
	@GeneratedValue
	private int id;
	@ManyToOne
	PolicyJpa policyJpa;
	String editedTableName;
	LocalDate editDate;
	String additionalInfo;
	String editedBy;
}
