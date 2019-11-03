package bo.edu.ucb.valeet.Bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class ValeetBot extends TelegramLongPollingBot {

    public void onUpdateReceived(Update update) {
        String aux,usser,placa,garage, nit,zona,espacios,direccion;

        SendMessage message = new SendMessage ();
       /* System.out.println(update.getMessage().getText());

        message.setText(update.getMessage().getText());

        */
        String command = update.getMessage ().getText ();
        if (command.equals ( "/parqueo" )) {
            System.out.println ( "Donde necesitas tu parqueo?" );
            message.setText ( "Donde necesitas tu parqueo?" );

        }

        if (command.equals ( "/registrarme" )) {
            System.out.println ( "Deseas registrar un auto o un parqueo?" );
            message.setText ( "Deseas registrar un auto o un parqueo? o desea eliminar" );
            if (command.equals ( "/auto" )) {
                System.out.println ( "Favor de ingresar tu nombre de usuario , tu numero de placa y nit ejemplo(napo-4231tfx-123123)" );
                message.setText ( "Favor de ingresar tu nombre de usuario ,tu numero de placa y nit ejemplo(napo-4231tfx-123123)" );
                aux = update.getMessage ().getText ();
                System.out.println ( aux );
                String[] parts = aux.split ( "-");
                usser = parts[0];
                placa = parts[1];
                nit = parts[2];
            }
            if (command.equals ( "/garage" )) {
                System.out.println ( "Favor de ingresar tu nombre de usuario , tu zona  ,cantidad de  espacios ,  y nit ejemplo(napo-miraflores-50-23123)" );
                message.setText (  "Favor de ingresar tu nombre de usuario , tu zona  ,cantidad de  espacios ,  y nit ejemplo(napo-miraflores-50-23123)" );
                aux = update.getMessage ().getText ();
                System.out.println ( aux );
                String[] parts = aux.split ( "-");
                usser = parts[0];
                zona = parts[1];
                espacios= parts[2];
                nit =parts[3];
                System.out.println ( "Favor de ingresar tu direccion completa" );
                message.setText (  "Favor de ingresar tu direccion completa" );
                direccion = update.getMessage ().getText ();

            }
            if (command.equals ( "/eliminar" )){
                if (command.equals ( "/garage")){
                System.out.println ( "Favor de ingresar tu nombre de usuario  y zona ejemplo(napo-miraflores)" );
                message.setText ( "Favor de ingresar tu nombre de usuario  y zona ejemplo(napo-miraflores)" );
                aux = update.getMessage ().getText ();
                System.out.println ( aux );
            }
            if (command.equals ( "/auto" )){
                System.out.println ( "Favor de ingresar tu nombre de usuario  y placa ejemplo(napo-4231pfs)" );
                message.setText ( "Favor de ingresar tu nombre de usuario  y zona ejemplo(napo-4231pfs)" );
                aux = update.getMessage ().getText ();
                System.out.println ( aux );
            }
            }
        }



        message.setChatId ( update.getMessage ().getChatId () );
        try {
            execute ( message );
        } catch (TelegramApiException e) {
            e.printStackTrace ();
        }
    }
    public String getBotUsername() {
        return "NeroBot";
    }

    public String getBotToken() {
        return  "992793333:AAGEKSr1njNT7HfW_ty4cmucVNDoTQIghdk";
    }
}
