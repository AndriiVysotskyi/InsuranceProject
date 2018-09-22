package insurance.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import insurance.entities.losses.HovaLossJpa;

public interface HovaLossRepository extends JpaRepository<HovaLossJpa, String>{

}
