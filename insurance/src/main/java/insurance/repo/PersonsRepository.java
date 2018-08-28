package insurance.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import insurance.entities.PersonJpa;

public interface PersonsRepository extends JpaRepository<PersonJpa, Integer> {

}
