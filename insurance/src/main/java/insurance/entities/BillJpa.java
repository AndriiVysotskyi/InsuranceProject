package insurance.entities;

import java.time.LocalDate;

import javax.persistence.*;

import insurance.dto.enums.StatusBill;

@Table(name = "bills")
@Entity
public class BillJpa {
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	PolicyJpa policyJpa;
	
	double paymentSum;
	LocalDate createdDate;
	double balance;
	@Enumerated(EnumType.STRING)
	StatusBill status;
}