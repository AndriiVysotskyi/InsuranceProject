package insurance.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import insurance.entities.VehicleJpa;

public interface VehiclesRepository extends JpaRepository<VehicleJpa, String> {

}
