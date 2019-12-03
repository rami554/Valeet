package bo.edu.ucb.valeet.Bot;

import bo.edu.ucb.valeet.domain.ValGarage;
import com.google.maps.*;
import bo.edu.ucb.valeet.bl.BotBl;
import bo.edu.ucb.valeet.bl.PersonBl;
import bo.edu.ucb.valeet.bl.GarageBl;
import bo.edu.ucb.valeet.bl.VehicleBl;
import bo.edu.ucb.valeet.domain.ValPerson;
import bo.edu.ucb.valeet.domain.ValVehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;


public class ValeetBot extends TelegramLongPollingBot {

    private BotBl botBl;
    private PersonBl personBl;
    private VehicleBl vehicleBl;
    private GarageBl garageBl;
    private int chatid;
    private float latitud;
    private float longitud;
    private int reply_to_message_id;

    public ValeetBot(BotBl botBl, PersonBl personBl, VehicleBl vehicleBl, GarageBl garageBl){
        this.botBl=botBl;
        this.garageBl=garageBl;
        this.personBl=personBl;
        this.vehicleBl=vehicleBl;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && (update.getMessage().hasText() || update.getMessage ().hasLocation ())) {
            int conversation = 0;
            try {
                conversation = botBl.processUpdate(update);
            } catch (MalformedURLException e) {
                e.printStackTrace ();
            }

            response(conversation, update);
        }
    }
    public void response(int conversation, Update update){
        List<String> responses = new ArrayList<>();

        ReplyKeyboardMarkup rkm=null;
        KeyboardButton kb=null;
        switch (conversation){

//Registro inicial

            case 1:
                responses.add("Bienvenido a Valeet!");
                responses.add("Para comenzar necesitamos algunos datos personales");
                responses.add("Ingresa tu segundo apellido (ingresa '-' si no lo tienes");
                break;
            case 2:
                responses.add("Ingresa tu e-mail");
                break;
            case 3:
                responses.add("Ingresa tu CI o pasaporte");
                break;

//Menu del usuario

            case 4:
                responses.add("¿Que deseas hacer a continuación?");
                rkm= createReplyKeyboardMenu();
                break;

//Registro de Vehiculo

            case 5:
                responses.add("Registro de nuevo Vehiculo");
                responses.add("Ingresa la placa de tu vehiculo (ejemplo 1020XKL");
                break;

//Busqueda de Vehiculos

            case 6:
                responses.add("A continuacion se detallan tus vehiculos registrados");
                ValPerson valPerson = personBl.findByTelegramId(update);
                ValPerson persona = personBl.findByPersonId(valPerson.getPersonId());
                List<ValVehicle> all = vehicleBl.all();
                for(ValVehicle vehicle: all){
                    if(vehicle.getPersonId().getPersonId() == persona.getPersonId()){
                        responses.add("Vehiculo con placa: "+ vehicle.getLicensePlate());
                    }
                }
                rkm= createOkMenu();
                break;

 //Registro de parqueo

            case 7:
                responses.add("Registro de Parqueo");
                responses.add("Ingresa el nombre de tu parqueo");
                break;
            case 8:
                responses.add("Ingresa la direccion de tu parqueo");
                break;
            case 9:
                responses.add("Cuantos espacios en total tiene tu parqueo?");
                break;
            case 10:
                responses.add("Cuantos espacios se encuentran ocupados en este momento?");
                break;
            case 11:
                responses.add("Cual es la tarifa por hora? (Ej: 10.50)");
                break;
            case 12:
                responses.add("Envia la ubicacion de tu garaje (debes encontrarte fisicamente en el lugar)");

                rkm = sendLocation ( );


                break;
            case 13:
                responses.add("En que zona se encuentra tu parqueo?");
                rkm= createOkMenu();
                break;
    //buscar parqueo
            case 14:
                responses.add ( "A continuacion se detallan tus vehiculos registrados" );
                ValPerson valPerson1 = personBl.findByTelegramId ( update );
                ValPerson persona1 = personBl.findByPersonId ( valPerson1.getPersonId () );
                List<ValGarage> al = garageBl.all ();
                for (ValGarage garage:al){
                    if (garage.getPersonId ().getPersonId ()==persona1.getPersonId ())
                    {
                        responses.add("nombre de garage: "+ garage.getName ());
                        responses.add("nombre de garage: "+ garage.getName ());
                        responses.add("calle de garage: "+ garage.getAddress ());
                        responses.add("zona de garage: "+ garage.getZone ());
                        responses.add("latitud de garage: "+ garage.getLat ());
                        responses.add("nombre de garage: "+ garage.getLongitude ());
                    }
                }
                rkm= createOkMenu();
               break;

        }
        for(String messageText: responses) {
            SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                    .setChatId(update.getMessage().getChatId())
                    .setText(messageText);
            if(rkm!=null){
                message.setReplyMarkup(rkm);
            }else{
                ReplyKeyboardRemove keyboardMarkupRemove = new ReplyKeyboardRemove();
                message.setReplyMarkup(keyboardMarkupRemove);
            }
            try {
                this.execute(message);

            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private ReplyKeyboardMarkup createOkMenu(){
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        // Create the keyboard (list of keyboard rows)
        List<KeyboardRow> keyboard = new ArrayList<>();
        // Create a keyboard row
        KeyboardRow row = new KeyboardRow();
        // Set each button, you can also use KeyboardButton objects if you need something else than text
        row.add("OK");
        // Add the first row to the keyboard
        keyboard.add(row);
        // Create another keyboard row
        // Set the keyboard to the markup
        keyboardMarkup.setKeyboard(keyboard);
        // Add it to the message
        return keyboardMarkup;

    }

    private ReplyKeyboardMarkup sendLocation(){



        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        KeyboardButton keyboardButton = new KeyboardButton();
        // Create the keyboard (list of keyboard rows)
        List<KeyboardRow> keyboard = new ArrayList<>();

        // Create a keyboard row
        KeyboardRow row = new KeyboardRow();
        // Set each button, you can also use KeyboardButton objects if you need something else than text
        keyboardButton.setText("Enviar Ubicacion");
        keyboardButton.setRequestLocation(true);
        row.add(keyboardButton);
        // Add the first row to the keyboard
        keyboard.add(row);
        // Create another keyboard row
        // Set the keyboard to the markup
        keyboardMarkup.setKeyboard(keyboard);
        // Add it to the message


        return keyboardMarkup;

    }
    private ReplyKeyboardMarkup createReplyKeyboardMenu() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("Registrar Vehículo");
        // Add the first row to the keyboard
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("Ver Vehículos");
        keyboard.add(row);
        // Create another keyboard row
        row = new KeyboardRow();
        // Set each button for the second line
        row.add("Registrar Parqueo");
        // Add the second row to the keyboard
        keyboard.add(row);
        row = new KeyboardRow();
        // Set each button for the second line
        row.add("Buscar Parqueo");
        // Add the second row to the keyboard
        keyboard.add(row);
        row = new KeyboardRow();
        // Set each button for the second line
        row.add("Ver Mis Reservas");
        // Add the second row to the keyboard
        keyboard.add(row);
        // Set the keyboard to the markup
        keyboardMarkup.setKeyboard(keyboard);
        // Add it to the message
        return keyboardMarkup;
    }

    public String getBotUsername() {
        return "NeroBot";
    }

    public String getBotToken() {
        return  "992793333:AAGEKSr1njNT7HfW_ty4cmucVNDoTQIghdk";
    }
}
