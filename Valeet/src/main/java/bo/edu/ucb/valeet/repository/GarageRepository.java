package bo.edu.ucb.valeet.repository;

import bo.edu.ucb.valeet.domain.ValGarage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GarageRepository extends JpaRepository<ValGarage, Integer>  {
ValGarage findByGarageId(int garageId);
}
