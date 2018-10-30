package insurance.repo;

import org.springframework.data.jpa.repository.JpaRepository;


import insurance.entities.EmployeeJpa;

public interface EmployeeRepository extends JpaRepository<EmployeeJpa, Integer>{

}
