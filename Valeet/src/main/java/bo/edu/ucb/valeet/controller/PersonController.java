package bo.edu.ucb.valeet.controller;

import bo.edu.ucb.valeet.domain.ValPerson;
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

public class PersonController {

    PersonRepository repository;
    @Autowired
    public PersonController(PersonRepository repository) {

        this.repository = repository;
    }

    public PersonController() {

    }

    public List findByTelegramId(int id) {

    return repository.findByTelegramId(id);
}

    public ValPerson create(ValPerson person) {

        return repository.save(person);
    }
}
