package bo.edu.ucb.valeet.bl;

import bo.edu.ucb.valeet.domain.ValBooking;
import bo.edu.ucb.valeet.dto.Status;
import bo.edu.ucb.valeet.domain.ValPerson;
import bo.edu.ucb.valeet.domain.ValGarage;
import bo.edu.ucb.valeet.domain.ValVehicle;
import bo.edu.ucb.valeet.repository.PersonRepository;
import bo.edu.ucb.valeet.repository.GarageRepository;
import bo.edu.ucb.valeet.repository.VehicleRepository;
import java.util.regex.Pattern;
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
            List<ValGarage> allGarages;
            Integer idPerson;
            ValVehicle newVehicle;
            ValGarage newGarage;
            ValBooking newBooking = null;
            Boolean checker;
            ValPerson valPerson = personRepository.findByTelegramId(update.getMessage().getFrom().getId());
            int last_conversation = valPerson.getLastResponse();
            switch (last_conversation){

                case 1:
                    idPerson = valPerson.getPersonId();
                    LOGGER.info("Buscando el usuario{}: ",idPerson);
                    valPerson = personRepository.findById(idPerson).get();
                    newSecondLastName = update.getMessage().getText();
                        checker = isLettersOnly(newSecondLastName);
                        if(checker) {
                            valPerson.setSecondLastName(newSecondLastName);
                        personRepository.save(valPerson);
                        result = 2;
                        }
                        else
                        {
                            result = 30;
                        }
                    break;
                case 2:
                    idPerson = valPerson.getPersonId();
                    LOGGER.info("Buscando el usuario{}: ",idPerson);
                    valPerson = personRepository.findById(idPerson).get();
                    newEmail = update.getMessage().getText();
                    checker = isEmail(newEmail);
                    if(checker) {
                        valPerson.setEmail(newEmail);
                        personRepository.save(valPerson);
                        result = 3;
                    }

                    else
                    {
                        result = 31;
                    }
                    break;

                case 3:
                    idPerson = valPerson.getPersonId();
                    LOGGER.info("Buscando el usuario{}: ",idPerson);
                    valPerson = personRepository.findById(idPerson).get();
                    newPersonalId = update.getMessage().getText();
                    checker = isLettersAndNumbersOnly(newPersonalId);
                    if(checker) {
                        valPerson.setPersonalId(newPersonalId);
                        personRepository.save(valPerson);
                        result = 4;
                    }
                    else
                    {
                        result = 32;
                    }
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
                    checker = isLettersAndNumbersOnly(newLicensePlate);
                    if(checker)
                    {
                        newVehicle.setPersonId(valPerson);
                        newVehicle.setLicensePlate(newLicensePlate);
                        newVehicle.setStatus(1);
                        vehicleRepository.save(newVehicle);
                        result = 4;
                    }
                    else {
                        result = 33;
                    }
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
                    checker = isNumbersOnly(newTotalSpots);
                    if(checker)
                    {
                    allGarages = garageRepository.findAll();
                    newGarage = getLastGarage(allGarages, idPerson);
                    newGarage.setTotalSpots(Integer.parseInt(newTotalSpots));
                    garageRepository.save(newGarage);
                    result = 10;}
                    else {
                        result = 34;
                    }
                    break;

                case 10:
                    idPerson = valPerson.getPersonId();
                    LOGGER.info("Buscando el usuario {}",idPerson);
                    int freeSpots;
                    newOccupiedSpots = update.getMessage().getText();
                    checker = isNumbersOnly(newOccupiedSpots);
                    if(checker)
                    {
                    allGarages = garageRepository.findAll();
                    newGarage = getLastGarage(allGarages, idPerson);
                    freeSpots = newGarage.getTotalSpots() - Integer.parseInt(newOccupiedSpots);
                    newGarage.setTotalSpots(Integer.parseInt(newOccupiedSpots));
                    newGarage.setFreeSpots(freeSpots);
                    garageRepository.save(newGarage);
                    result = 11;}
                    else
                    {
                        result = 35;
                    }
                    break;

                case 11:
                    idPerson = valPerson.getPersonId();
                    LOGGER.info("Buscando el usuario {}",idPerson);
                    newRate = update.getMessage().getText();
                    checker = isNumbersOnly(newRate);
                    if(checker)
                    {
                    allGarages = garageRepository.findAll();
                    newGarage = getLastGarage(allGarages, idPerson);
                    newGarage.setRate(new BigDecimal(newRate));
                    garageRepository.save(newGarage);
                    result = 12;}
                    else
                    {
                        result = 36;
                    }
                    break;

                case 12:
                    if (!update.getMessage ().hasLocation ())
                    {
                        result=12;

                    }
                    else {
                        idPerson = valPerson.getPersonId();
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
// Buscar parqueo
                case 14:
                    result=15;
                    break;
                case 15:
                    if (!update.getMessage ().hasLocation ())
                    {
                        result=15;

                    }
                    else {
                        idPerson = valPerson.getPersonId ();
                        LOGGER.info ( "Buscando el usuario{}: ", idPerson );
                        LOGGER.info ( "Ubicacion obtenida{}: ", idPerson );
                        valPerson = personRepository.findById ( idPerson ).get ();

                        newBooking.setLat ( update.getMessage ().getLocation ().getLatitude () );
                        newBooking.setLongitude ( update.getMessage ().getLocation ().getLongitude () );
                    }
                    result=16;
                    break;
                    // calacular los parqueos en el radio deseado
                case 16:
                    String radio ;
                    radio = update.getMessage().getText();
                    double ratio = Double.parseDouble(radio);






//Mensajes de error

                case 30:
                    idPerson = valPerson.getPersonId();
                    valPerson = personRepository.findById(idPerson).get();
                    newSecondLastName = update.getMessage().getText();
                    checker = isLettersOnly(newSecondLastName);
                    if(checker)
                    {
                        valPerson.setSecondLastName(newSecondLastName);
                        personRepository.save(valPerson);
                        result = 2;
                    }
                    else
                    {
                        result = 30;
                    }
                    break;

                case 31:
                    idPerson = valPerson.getPersonId();
                    valPerson = personRepository.findById(idPerson).get();
                    newEmail = update.getMessage().getText();
                    checker = isEmail(newEmail);
                    if(checker)
                    {
                        valPerson.setEmail(newEmail);
                        personRepository.save(valPerson);
                        result = 3;
                    }
                    else
                    {
                        result = 31;
                    }
                    break;

                case 32:
                idPerson = valPerson.getPersonId();
                valPerson = personRepository.findById(idPerson).get();
                newPersonalId = update.getMessage().getText();
                checker = isLettersAndNumbersOnly(newPersonalId);
                if(checker) {
                    valPerson.setPersonalId(newPersonalId);
                    personRepository.save(valPerson);
                    result = 4;
                }
                else
                {
                    result = 32;
                }
                break;

                case 33:

                idPerson = valPerson.getPersonId();
                newVehicle = new ValVehicle();
                valPerson = personRepository.findById(idPerson).get();
                newLicensePlate = update.getMessage().getText();
                checker = isLettersAndNumbersOnly(newLicensePlate);
                if(checker)
                {
                    newVehicle.setPersonId(valPerson);
                    newVehicle.setLicensePlate(newLicensePlate);
                    newVehicle.setStatus(1);
                    vehicleRepository.save(newVehicle);
                    result = 4;
                }
                else {
                    result = 33;
                }
                break;

                case 34:

                    idPerson = valPerson.getPersonId();
                    newTotalSpots = update.getMessage().getText();
                    checker = isNumbersOnly(newTotalSpots);
                    if(checker)
                    {
                        allGarages = garageRepository.findAll();
                        newGarage = getLastGarage(allGarages, idPerson);
                        newGarage.setTotalSpots(Integer.parseInt(newTotalSpots));
                        garageRepository.save(newGarage);
                        result = 10;}
                    else {
                        result = 34;
                    }
                    break;

                case 35:
                    idPerson = valPerson.getPersonId();
                    int freeSpotsX=0;
                    newOccupiedSpots = update.getMessage().getText();
                    checker = isNumbersOnly(newOccupiedSpots);
                    if(checker)
                    {
                        allGarages = garageRepository.findAll();
                        newGarage = getLastGarage(allGarages, idPerson);
                        freeSpotsX = newGarage.getTotalSpots() - Integer.parseInt(newOccupiedSpots);
                        newGarage.setTotalSpots(Integer.parseInt(newOccupiedSpots));
                        newGarage.setFreeSpots(freeSpotsX);
                        garageRepository.save(newGarage);
                        result = 11;}
                    else
                    {
                        result = 35;
                    }
                    break;

                case 36:

                    idPerson = valPerson.getPersonId();
                    newRate = update.getMessage().getText();
                    checker = isNumbersOnly(newRate);
                    if(checker)
                    {
                        allGarages = garageRepository.findAll();
                        newGarage = getLastGarage(allGarages, idPerson);
                        newGarage.setRate(new BigDecimal(newRate));
                        garageRepository.save(newGarage);
                        result = 12;}
                    else
                    {
                        result = 36;
                    }
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

    private boolean isSpacesOnly(String text){
        Boolean checker = true;
        for(int i=0;i<text.length();i++){
            char ch = text.charAt(i);
            if(ch != ' '){
                checker = false;
            }
            break;
        }
        return checker;
    }

    private boolean isLettersOnly(String text){
        Boolean checker = true;
        for(int i=0;i<text.length();i++){
            char ch = text.charAt(i);
            if(!Character.isLetter(ch) && ch != ' '){
                checker = false;
                break;
            }
        }
        if(isSpacesOnly(text)){
            checker = false;
        }
        return checker;
    }

    private boolean isEmail(String text){

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (text == null)
            return false;
            return pat.matcher(text).matches();

    }

    private boolean isLettersAndNumbersOnly(String text){
        boolean checker = true;
        for(int i=0;i<text.length();i++){
            char ch = text.charAt(i);
            if(!Character.isAlphabetic(ch) && !Character.isDigit(ch)){
                checker = false;
                break;
            }
        }
        return checker;
    }

    private  boolean isNumbersOnly(String text){
        boolean validation = true;
        for(int i=0;i<text.length();i++){
            if(!Character.isDigit(text.charAt(i))){
                validation = false;
                break;
            }
        }
        return validation;
    }

}