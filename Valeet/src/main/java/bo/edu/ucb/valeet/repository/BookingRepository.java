package bo.edu.ucb.valeet.repository;

import bo.edu.ucb.valeet.domain.ValBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<ValBooking, Integer>  {
    ValBooking findByBookingId(int bookingId);
}
