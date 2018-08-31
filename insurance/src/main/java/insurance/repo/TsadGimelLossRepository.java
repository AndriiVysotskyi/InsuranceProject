package insurance.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import insurance.entities.losses.TsadGimelLossJpa;

public interface TsadGimelLossRepository extends JpaRepository<TsadGimelLossJpa, String>{

}
