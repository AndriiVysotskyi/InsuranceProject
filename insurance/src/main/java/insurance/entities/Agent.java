package insurance.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "agents")
public class Agent {
	@Id
	private int workersId;
	String firstName;
	String lastName;
	
	@OneToMany(mappedBy="agent")
	List<PolicyJpa> policies;
}
