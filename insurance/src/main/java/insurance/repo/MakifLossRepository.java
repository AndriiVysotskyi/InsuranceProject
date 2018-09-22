package insurance.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import insurance.entities.losses.MakifLossJpa;

public interface MakifLossRepository extends JpaRepository<MakifLossJpa, String>{

}
