package bo.edu.ucb.valeet.bl;

import bo.edu.ucb.valeet.dto.Status;
import bo.edu.ucb.valeet.domain.ValPerson;
import bo.edu.ucb.valeet.domain.ValGarage;
import bo.edu.ucb.valeet.domain.ValVehicle;
import bo.edu.ucb.valeet.repository.PersonRepository;
import bo.edu.ucb.valeet.repository.GarageRepository;
import bo.edu.ucb.valeet.repository.VehicleRepository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;


@Service
public class BotBl {
    private static final Logger LOGGER = LoggerFactory.getLogger(BotBl.class);
    private PersonRepository personRepository;
    private GarageRepository garageRepository;
    private VehicleRepository vehicleRepository;
    @Autowired
    public BotBl(PersonRepository personRepository, GarageRepository garageRepository, VehicleRepository vehicleRepository) {
        this.personRepository = personRepository;
        this.garageRepository = garageRepository;
        this.vehicleRepository= vehicleRepository;
    }

    public int processUpdate(Update update){
        LOGGER.info("RECIBIENDO UPDATE: {}",update);
        int result = 0;
        if(isNewUser(update)){
            LOGGER.info("Primer acceso de: {} ",update.getMessage().getFrom() );
            result = 1;
        }
        else{
            String newSecondLastName,newEmail,newPersonalId,newLicensePlate,newName,newAddress;
            Integer idPerson;
            ValVehicle newVehicle;
            ValGarage newGarage;
            ValPerson newPerson;
            ValPerson valPerson = personRepository.findByTelegramId(update.getMessage().getFrom().getId());

            switch (result){

                case 1:
                    idPerson = valPerson.getPersonId();
                    LOGGER.info("Buscando el usuario{}: ",idPerson);
                    newPerson = personRepository.findById(idPerson).get();
                    newSecondLastName = update.getMessage().getText();
                        newPerson.setSecondLastName(newSecondLastName);
                        personRepository.save(newPerson);
                        result = 2;
                    break;

                case 2:
                    idPerson = valPerson.getPersonId();
                    LOGGER.info("Buscando el usuario{}: ",idPerson);
                    newPerson = personRepository.findById(idPerson).get();
                    newEmail = update.getMessage().getText();
                        newPerson.setEmail(newEmail);
                        personRepository.save(newPerson);
                        result = 3;
                    break;

                case 3:
                    idPerson = valPerson.getPersonId();
                    LOGGER.info("Buscando el usuario{}: ",idPerson);
                    newPerson = personRepository.findById(idPerson).get();
                    newPersonalId = update.getMessage().getText();
                    newPerson.setPersonalId(newPersonalId);
                    personRepository.save(newPerson);
                    result = 4;
                    break;

//Menu del usuario

                case 4:
                    idPerson = valPerson.getPersonId();
                    LOGGER.info("Buscando el usuario{}: ",idPerson);
                    newPerson = personRepository.findById(idPerson).get();
                    result = 4;

                    if(update.getMessage().getText().equals("Registrar Vehículo")){
                        result = 5;
                    }
                    if(update.getMessage().getText().equals("Ver Vehículos")){
                        result = 6;
                    }
                    if(update.getMessage().getText().equals("Registrar Parqueo")){
                        result = 7;
                    }
                    if(update.getMessage().getText().equals("Buscar Parqueo")){
                        result = 99;
                    }
                    if(update.getMessage().getText().equals("Ver Mis Reservas")){
                        result = 88;
                    }
                    break;

//Registro de vehiculo

                case 5:
                    idPerson = valPerson.getPersonId();
                    newVehicle = new ValVehicle();
                    newPerson = personRepository.findById(idPerson).get();
                    LOGGER.info("Buscando el usuario {}",idPerson);
                    newLicensePlate = update.getMessage().getText();
                        newVehicle.setPersonId(newPerson); //Falta el id?
                        newVehicle.setLicensePlate(newLicensePlate);
                        newVehicle.setStatus(1);
                        vehicleRepository.save(newVehicle);
                        result = 4;
                        break;
//Busqueda de vehiculos

                case 6:
                    result = 4;
                    break;

//Registro de parqueo
                case 7:
                    idPerson = valPerson.getPersonId();
                    newGarage = new ValGarage();
                    newPerson = personRepository.findById(idPerson).get();
                    LOGGER.info("Buscando el usuario {}",idPerson);
                    newName = update.getMessage().getText();
                    newGarage.setPersonId(newPerson);
                    newGarage.setName(newName);
                    newGarage.setAddress("NA");
                    newGarage.setTotalSpots(0);
                    newGarage.setFreeSpots(0);
                    newGarage.setOccupiedSpots(0);
                    newGarage.setRate(new BigDecimal(0.0));
                    newGarage.setLat(0.0);
                    newGarage.setLong1(0.0);
                    newGarage.setZone("NA");
                    newGarage.setStatus(1);
                    garageRepository.save(newGarage);
                        result = 4;
                    break;

   /*             case 8:
                    idPerson = valPerson.getPersonId();
                    newGarage = new ValGarage();
                    LOGGER.info("Buscando el usuario {}",idPerson);
                    newAddress = update.getMessage().getText();
                    newGarage.setPersonId(valPerson);
                    newGarage.setAddress(newAddress);
                    garageRepository.save(newGarage);
                    result = 9;
                    break;*/
                }
            //valPerson.setConversationId(result);
            //cpUserRepository.save(valPerson);
        }
        return result;
    }

    private boolean isNewUser(Update update){
        boolean response = false;
        User user = update.getMessage().getFrom();
        ValPerson valPerson = personRepository.findByTelegramId(user.getId());
        if(valPerson==null){
            ValPerson persona = new ValPerson();
            persona.setFirstName(user.getFirstName());

            if(user.getLastName() == null){
                persona.setFirstLastName("-");
            }
            else{
                persona.setFirstLastName(user.getLastName());
            }
            persona.setStatus(Status.ACTIVE.getStatus());
            persona.setPersonalId("NA");
            persona.setEmail("NA");
            persona.setParkingAdmin(1);
            persona.setSecondLastName("-");
            persona.setTelegramId(user.getId());
            personRepository.save(persona);
            response = true;
        }
        return response;
    }

    private ValGarage getLastGarage(List<ValGarage> all, Integer idUser){
        ValGarage lastGarage = null;
        for(ValGarage garage: all){
            if(garage.getPersonId().getPersonId() == idUser && garage.getStatus()==1){
                lastGarage = garage;
            }
        }
        return  lastGarage;
    }
}