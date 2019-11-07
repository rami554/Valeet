package bo.edu.ucb.valeet.repository;

import bo.edu.ucb.valeet.domain.ValVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface VehicleRepository extends JpaRepository<ValVehicle, Integer>  {
    List<ValVehicle> findByTelegramId(int telegramId);
}
