package insurance.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import insurance.entities.LegalEntityJpa;

public interface LegalEntityRepository extends JpaRepository<LegalEntityJpa, Integer> {

}
