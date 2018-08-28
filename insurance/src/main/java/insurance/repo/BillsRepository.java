package insurance.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import insurance.entities.BillJpa;

public interface BillsRepository extends JpaRepository<BillJpa, Integer> {

}
