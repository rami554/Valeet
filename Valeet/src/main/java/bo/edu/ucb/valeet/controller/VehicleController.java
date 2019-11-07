package bo.edu.ucb.valeet.controller;

import bo.edu.ucb.valeet.domain.ValVehicle;
import bo.edu.ucb.valeet.domain.ValPerson;
import bo.edu.ucb.valeet.repository.VehicleRepository;
import bo.edu.ucb.valeet.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.List;
import java.util.Optional;

@Component

public class VehicleController {

    VehicleRepository repository;
    @Autowired
    public VehicleController(VehicleRepository repository) {

        this.repository = repository;
    }

    public VehicleController() {

    }

    public List findByTelegramId(int id) {

        return repository.findByTelegramId(id);
    }

    public  ValVehicle create(ValVehicle vehicle) {

        return repository.save ( vehicle );
    }

}
