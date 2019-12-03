package bo.edu.ucb.valeet.bl;

import bo.edu.ucb.valeet.domain.ValPerson;
import bo.edu.ucb.valeet.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Service

public class PersonBl {

    PersonRepository repository;
    @Autowired
    public PersonBl(PersonRepository repository) {

        this.repository = repository;
    }

    public PersonBl() {

    }

    public ValPerson findByTelegramId(Update update){
        ValPerson valPerson = repository.findByTelegramId(update.getMessage().getFrom().getId());
        return valPerson;
    }

    public ValPerson findByPersonId(Integer id){
        return repository.findById(id).get();
    }
    public ValPerson create(ValPerson person) {

        return repository.save(person);
    }
}
