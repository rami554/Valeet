package bo.edu.ucb.valeet.bl;

import bo.edu.ucb.valeet.dto.Status;
import bo.edu.ucb.valeet.domain.ValPerson;
import bo.edu.ucb.valeet.domain.ValGarage;
import bo.edu.ucb.valeet.domain.ValVehicle;
import bo.edu.ucb.valeet.repository.PersonRepository;
import bo.edu.ucb.valeet.repository.GarageRepository;
import bo.edu.ucb.valeet.repository.VehicleRepository;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import jdk.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Location;
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

    public int processUpdate(Update update) throws MalformedURLException {
        LOGGER.info("RECIBIENDO UPDATE: {}",update);
        String napo;
        URL url=null;
        int result = 0;
        if(isNewUser(update)){
            LOGGER.info("Primer acceso de: {} ",update.getMessage().getFrom() );
            result = 1;
        }
        else{
            String newSecondLastName,newEmail,newPersonalId,newLicensePlate,newName,newAddress, newTotalSpots, newOccupiedSpots,
            newRate, newZone;
            double newLatitude, newLongitude;
            List<ValGarage> allGarages;
            Integer idPerson;
            ValVehicle newVehicle;
            ValGarage newGarage;
            ValPerson valPerson = personRepository.findByTelegramId(update.getMessage().getFrom().getId());
            int last_conversation = valPerson.getLastResponse();
            switch (last_conversation){

                case 1:
                    idPerson = valPerson.getPersonId();
                    LOGGER.info("Buscando el usuario{}: ",idPerson);
                    valPerson = personRepository.findById(idPerson).get();
                    newSecondLastName = update.getMessage().getText();
                        valPerson.setSecondLastName(newSecondLastName);
                        personRepository.save(valPerson);
                        result = 2;
                    break;

                case 2:
                    idPerson = valPerson.getPersonId();
                    LOGGER.info("Buscando el usuario{}: ",idPerson);
                    valPerson = personRepository.findById(idPerson).get();
                    newEmail = update.getMessage().getText();
                        valPerson.setEmail(newEmail);
                        personRepository.save(valPerson);
                        result = 3;
                    break;

                case 3:
                    idPerson = valPerson.getPersonId();
                    LOGGER.info("Buscando el usuario{}: ",idPerson);
                    valPerson = personRepository.findById(idPerson).get();
                    newPersonalId = update.getMessage().getText();
                    valPerson.setPersonalId(newPersonalId);
                    personRepository.save(valPerson);
                    result = 4;
                    break;

//Menu del usuario

                case 4:
                    idPerson = valPerson.getPersonId();
                    LOGGER.info("Buscando el usuario{}: ",idPerson);
                    valPerson = personRepository.findById(idPerson).get();
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
                    valPerson = personRepository.findById(idPerson).get();
                    LOGGER.info("Buscando el usuario {}",idPerson);
                    newLicensePlate = update.getMessage().getText();
                        newVehicle.setPersonId(valPerson);
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
                    BigDecimal rate = new BigDecimal(0.00);
                    valPerson = personRepository.findById(idPerson).get();
                    LOGGER.info("Buscando el usuario {}",idPerson);
                    newName = update.getMessage().getText();
                    newGarage.setPersonId(valPerson);
                    newGarage.setName(newName);
                    newGarage.setAddress("NA");
                    newGarage.setTotalSpots(0);
                    newGarage.setFreeSpots(0);
                    newGarage.setOccupiedSpots(0);
                    newGarage.setRate(rate);

                    newGarage.setLat(0.0);
                    newGarage.setLongitude(0.0);
                    newGarage.setZone("NA");
                    newGarage.setStatus(1);
                    garageRepository.save(newGarage);
                        result = 8;
                    break;

                case 8:
                    idPerson = valPerson.getPersonId();
                    LOGGER.info("Buscando el usuario {}",idPerson);
                    newAddress = update.getMessage().getText();
                    allGarages = garageRepository.findAll();
                    newGarage = getLastGarage(allGarages, idPerson);
                    newGarage.setAddress(newAddress);
                    garageRepository.save(newGarage);
                    result = 9;
                    break;

                case 9:
                    idPerson = valPerson.getPersonId();
                    LOGGER.info("Buscando el usuario {}",idPerson);
                    newTotalSpots = update.getMessage().getText();
                    allGarages = garageRepository.findAll();
                    newGarage = getLastGarage(allGarages, idPerson);
                    newGarage.setTotalSpots(Integer.parseInt(newTotalSpots));
                    garageRepository.save(newGarage);
                    result = 10;
                    break;

                case 10:
                    idPerson = valPerson.getPersonId();
                    LOGGER.info("Buscando el usuario {}",idPerson);
                    int freeSpots;
                    newOccupiedSpots = update.getMessage().getText();
                    allGarages = garageRepository.findAll();
                    newGarage = getLastGarage(allGarages, idPerson);
                    freeSpots = newGarage.getTotalSpots() - Integer.parseInt(newOccupiedSpots);
                    newGarage.setTotalSpots(Integer.parseInt(newOccupiedSpots));
                    newGarage.setFreeSpots(freeSpots);
                    garageRepository.save(newGarage);
                    result = 11;
                    break;

                case 11:
                    idPerson = valPerson.getPersonId();
                    LOGGER.info("Buscando el usuario {}",idPerson);
                    newRate = update.getMessage().getText();
                    allGarages = garageRepository.findAll();
                    newGarage = getLastGarage(allGarages, idPerson);
                    newGarage.setRate(new BigDecimal(newRate));
                    garageRepository.save(newGarage);
                    result = 12;
                    break;

                case 12:
                    if (!update.getMessage ().hasLocation ())
                    {
                        result=12;

                    }
                    else {
                        idPerson = valPerson.getPersonId ();
                        LOGGER.info ( "Buscando el usuario{}: ", idPerson );
                        LOGGER.info ( "Ubicacion obtenida{}: ", idPerson );
                        valPerson = personRepository.findById ( idPerson ).get ();
                        allGarages = garageRepository.findAll ();
                        newGarage = getLastGarage ( allGarages, idPerson );

                        newGarage.setLat ( update.getMessage ().getLocation ().getLatitude () );
                        newGarage.setLongitude ( update.getMessage ().getLocation ().getLongitude () );
                        garageRepository.save ( newGarage );
                        result = 13;

                    }
                    break;



                case 13:
                    idPerson = valPerson.getPersonId();
                    LOGGER.info("Buscando el usuario {}",idPerson);
                    newZone = update.getMessage().getText();
                    allGarages = garageRepository.findAll();
                    newGarage = getLastGarage(allGarages, idPerson);
                    newGarage.setZone(newZone);
                    garageRepository.save(newGarage);
                    result = 4;
                    break;
                     }
                    valPerson.setLastResponse(result);
                    personRepository.save(valPerson);



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
            persona.setPersonalId("-");
            persona.setEmail("-");
            persona.setLastResponse(1);
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