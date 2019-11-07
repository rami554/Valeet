package bo.edu.ucb.valeet.Bot;


import bo.edu.ucb.valeet.domain.ValGarage;
import bo.edu.ucb.valeet.domain.ValPerson;
import bo.edu.ucb.valeet.domain.ValVehicle;
import bo.edu.ucb.valeet.controller.PersonController;
import bo.edu.ucb.valeet.controller.GarageController;
import bo.edu.ucb.valeet.controller.VehicleController;
import bo.edu.ucb.valeet.repository.PersonRepository;
import bo.edu.ucb.valeet.repository.GarageRepository;
import bo.edu.ucb.valeet.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import java.util.List;
import java.util.logging.Logger;

@Component
public class ValeetBot extends TelegramLongPollingBot {

    @Autowired
    PersonController personController;
    VehicleController vehicleController;
    GarageController garageController;

    @Override
    public void onUpdateReceived(Update update) {
        String aux;
        int idtelegram;

        String usser;
        String pass;
        String email;
        String placa=null;
        String garage;
        String nit;
        String zona;
        int espacios;
        String direccion;
        String name;
        String lastname;
        String secondlastname = null;
        String personalid;

        SendMessage message = new SendMessage ();
       /* System.out.println(update.getMessage().getText());

        message.setText(update.getMessage().getText());   */
        String command = update.getMessage ().getText ();

        if (command.equals ( "/start" )) {
        name= update.getMessage ().getFrom ().getFirstName ();
        lastname =update.getMessage ().getFrom ().getLastName ();
        idtelegram = update.getMessage ().getFrom ().getId ();


        if (personController.findByTelegramId(idtelegram).isEmpty())
        {
            ValPerson persona = new ValPerson();
            persona.setTelegramId(idtelegram);
            persona.setPersonId(1);
            persona.setParkingAdmin(1);
            persona.setStatus(1);
            persona.setFirstName(name);
            persona.setFirstLastName(lastname);
            do {
                    System.out.println ( "favor ingrese su  apellido materno" );
                    message.setText ( "favor ingrese su  apellido materno" );
                    secondlastname=update.getMessage ().getText ();
                    persona.setSecondLastName ( update.getMessage ().getText () );
            }while (secondlastname!=null);
            do {
                    System.out.println ( "favor ingrese su  apellido materno" );
                    message.setText ( "favor ingrese su  apellido materno" );
                    email=update.getMessage ().getText ();
                    persona.setEmail(update.getMessage ().getText ());
            }while (email!=null);
            do {
                    // Logger.info("Hello");
                    System.out.println ( "favor ingrese su  apellido materno" );
                    message.setText ( "favor ingrese su  apellido materno" );
                    personalid=update.getMessage ().getText ();
                    persona.setPersonalId ( update.getMessage ().getText () );
            }while (personalid!=null);

            personController.create(persona);
            System.out.println ( "Usuario Creado" );
            message.setText (  "Usuario creado" );
        }
        else
        {   System.out.println ( "hola "+idtelegram+" "+name+" "+lastname+" como estas" );
            message.setText (  "hola "+idtelegram+" "+name+" "+lastname+secondlastname+" como estas" );}
           if (command.equals ( "/regitra_auto" )){

                ValVehicle auto=new ValVehicle (  );

                do {
                    System.out.println ( "favor ingrese su placa" );
                    message.setText ( "favor ingrese su placa" );
                    placa=update.getMessage ().getText ();
                    auto.setLicensePlate ( update.getMessage ().getText () );

                }while (placa!=null);

                vehicleController.create(auto);
                System.out.println ( "auto Creado" );
                message.setText (  "auto creado" );
            }
            else
            {   System.out.println ( "hola "+idtelegram+" "+name+" "+lastname+secondlastname+" ya crramos tu auto"+placa);
                message.setText (  "hola "+idtelegram+" "+name+" "+lastname+secondlastname+" ya crramos tu auto" );
            }

            if (command.equals ( "/regitra_garage" )){

                ValGarage parqueo =new ValGarage (  );

               // parqueo.setLat ( "123123" );
                //parqueo.setLong1 ( "5584" );
                do {
                    System.out.println ( "favor ingrese el nombre del Parqueo" );
                    message.setText ( "favor ingrese el nombre del Parqueo" );
                    name=update.getMessage ().getText ();
                    parqueo.setName ( update.getMessage ().getText () );

                }while (placa!=null);
                do {
                    System.out.println ( "favor ingrese el nombre del Parqueo" );
                    message.setText ( "favor ingrese el nombre del Parqueo" );

                    parqueo.setTotalSpots ( espacios=Integer.parseInt (  update.getMessage ().getText () ));

                }while (placa!=null);

                garageController.create ( parqueo );
                System.out.println ( "parqueo creado" );
                message.setText (  "Parqueo creado" );
            }
            else
            {   System.out.println ( "hola "+idtelegram+" "+name+" "+lastname+secondlastname+" ya crramos tu auto"+placa);
                message.setText (  "hola "+idtelegram+" "+name+" "+lastname+secondlastname+" ya crramos tu auto" );}



        }

        }






    public String getBotUsername() {
        return "NeroBot";
    }

    public String getBotToken() {
        return  "992793333:AAGEKSr1njNT7HfW_ty4cmucVNDoTQIghdk";
    }
}
