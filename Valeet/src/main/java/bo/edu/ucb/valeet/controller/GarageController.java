package bo.edu.ucb.valeet.controller;

import bo.edu.ucb.valeet.domain.ValGarage;

import bo.edu.ucb.valeet.repository.GarageRepository;

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

public class GarageController {

    GarageRepository repository1;
    @Autowired
    public GarageController(GarageRepository repository1) {

        this.repository1 = repository1;
    }

    public GarageController() {

    }

    public List findByTelegramId(int id) {

        return repository1.findByTelegramId(id);
    }

    public ValGarage create(ValGarage garage) {
        return repository1.save ( garage );
    }

}
