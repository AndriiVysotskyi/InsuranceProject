package insurance.entities;

import java.time.LocalDate;

import javax.persistence.*;

import insurance.dto.enums.StatusBill;
import lombok.*;

@Table(name = "bills")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public @Data class BillJpa {
	@Id
	@GeneratedValue
	private int id;

	@ManyToOne
	private PolicyJpa policyJpa;

	private double paymentSum;
	private LocalDate createdDate;
	private double balance;
	@Enumerated(EnumType.STRING)
	private StatusBill status;

}