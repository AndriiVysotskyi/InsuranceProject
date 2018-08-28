package insurance.dto;

import java.io.Serializable;
import java.time.LocalDate;


import insurance.dto.enums.StatusBill;
import lombok.*;

@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
public @Data class BillDto implements Serializable {
	private int policyNumber;
	private double paymentSum;
	private LocalDate createdDate;
	private double balance;
	private StatusBill status;	
	
}
