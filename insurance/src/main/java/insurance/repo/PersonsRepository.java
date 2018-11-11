package insurance.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import insurance.entities.PersonJpa;

public interface PersonsRepository extends JpaRepository<PersonJpa, Integer> {

	PersonJpa findByPersonIdAndLicenseNumberNotNull(int x);

	@Query(value="select count(*) from personsRepo "
			+ "where id_person in :ids and license_number IS NOT Null", nativeQuery=true)
	int countPersonsJpa(@Param ("ids")List<Integer> personsIds);

	@Query(value="select count(*) from personsRepo "
			+ "where id_person in :ids", nativeQuery=true)
	int countListVictimJpa(@Param ("ids")List<Integer> victimID);
}
