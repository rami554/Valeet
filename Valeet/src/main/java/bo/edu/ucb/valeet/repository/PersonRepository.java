package bo.edu.ucb.valeet.repository;

import bo.edu.ucb.valeet.domain.ValPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<ValPerson, Long>  {
}
