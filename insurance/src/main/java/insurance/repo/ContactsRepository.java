package insurance.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import insurance.entities.ContactsJpa;

public interface ContactsRepository extends JpaRepository<ContactsJpa, Integer>{

}
