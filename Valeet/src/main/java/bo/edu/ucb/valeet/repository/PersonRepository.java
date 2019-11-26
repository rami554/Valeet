package bo.edu.ucb.valeet.repository;

import bo.edu.ucb.valeet.domain.ValPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface PersonRepository extends JpaRepository<ValPerson, Integer>  {
ValPerson findByTelegramId(int telegramId);

}
