package insurance.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import insurance.entities.ModelJpa;

public interface VehiclesModelsRepository extends JpaRepository<ModelJpa, String> {

}
