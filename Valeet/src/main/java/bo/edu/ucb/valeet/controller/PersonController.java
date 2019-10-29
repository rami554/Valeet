package bo.edu.ucb.valeet.controller;

import bo.edu.ucb.valeet.domain.ValPerson;
import bo.edu.ucb.valeet.repository.PersonRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/person"})

public class PersonController {

    private PersonRepository repository;

    PersonController(PersonRepository personRepository) {
        this.repository = personRepository;
    }

    @GetMapping
    public List findAll(){
        return repository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<ValPerson> findById(@PathVariable long id){
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ValPerson create(@RequestBody ValPerson person){
        return repository.save(person);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<ValPerson> update(@PathVariable("id") long id,
                                          @RequestBody ValPerson person){
        return repository.findById(id)
                .map(record -> {
                    record.setFirstName(person.getFirstName());
                    record.setFirstLastName(person.getFirstLastName());
                    record.setSecondLastName(person.getSecondLastName());
                    record.setEmail(person.getEmail());
                    record.setPassword(person.getPassword());
                    record.setPersonalId(person.getPersonalId());
                    record.setParkingAdmin(person.getParkingAdmin());
                    record.setStatus(person.getStatus());
                    ValPerson updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
