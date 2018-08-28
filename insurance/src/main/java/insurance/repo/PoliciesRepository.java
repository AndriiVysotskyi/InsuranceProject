package insurance.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import insurance.entities.PolicyJpa;

public interface PoliciesRepository extends JpaRepository<PolicyJpa, Integer>{

}
