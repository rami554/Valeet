package bo.edu.ucb.valeet.bl;

import bo.edu.ucb.valeet.domain.ValBooking;
import bo.edu.ucb.valeet.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class BookingBl {

    BookingRepository repository;
    @Autowired
    public BookingBl(BookingRepository repository) {

        this.repository = repository;
    }

    public BookingBl() {

    }

    public ValBooking findByBookingId (int id) {

        return repository.findByBookingId(id);
    }

    public ValBooking create(ValBooking booking) {
        return repository.save ( booking );
    }

}
