package insurance.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import insurance.entities.AddressJpa;

public interface PersonsAddressRepository extends JpaRepository<AddressJpa, Integer> {

}
