package bo.edu.ucb.valeet.Bot;

import bo.edu.ucb.valeet.domain.ValPerson;
import bo.edu.ucb.valeet.controller.PersonController;
import bo.edu.ucb.valeet.repository.PersonRepository;
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

@Component
public class ValeetBot extends TelegramLongPollingBot {

    @Autowired
    PersonController personController;

    @Override
    public void onUpdateReceived(Update update) {
        String aux;
        int idtelegram;
        String usser;
        String pass;
        String email;
        String placa;
        String garage;
        String nit;
        String zona;
        String espacios;
        String direccion;
        String name;
        String lastname;
        String secondlastname;

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
        persona.setPersonId(1);
        persona.setFirstName(name);
        persona.setFirstLastName(lastname);
        persona.setEmail("abde@gmail.com");
        persona.setTelegramId(idtelegram);
        persona.setPersonalId("785475LP");
        persona.setParkingAdmin(1);
        persona.setStatus(1);
        personController.create(persona);
        System.out.println ( "Usuario Creado" );
        message.setText (  "Usuario creado" );
        }
        else
        {   System.out.println ( "hola "+idtelegram+" "+name+" "+lastname+" como estas" );
            message.setText (  "hola "+idtelegram+" "+name+" "+lastname+" como estas" );}

//
       /* ValPerson nueva = new ValPerson();
        nueva.setFirstName ( name);
        nueva.setPersonalId ( idp );
        nueva.setEmail (    "email" );
        nueva.setFirstLastName ( lastname );
        nueva.setSecondLastName ( "secondlastname" );
        nueva.setPassword ( "pass"  );*/
        //repository.save(nueva);
      // PersonController user =new PersonController ();
        //user.create ( nueva );
       // personRepository.save(nueva);
       // System.out.println ( "hola"+idtelegram+"--"+name+lastname+"como estas" );
        //message.setText (  "hola"+idtelegram+"--"+name+lastname+"como estas" );
        }

        if (command.equals ( "/registrarme" )) {
            System.out.println ( "favor ingrese su email" );
            message.setText ( "favor ingrese su email" );
            email=update.getMessage ().getText ();
            System.out.println ( "favor ingrese su password" );
            message.setText ( "favor ingrese su password" );
            pass=update.getMessage ().getText ();
            if (email.equals ( email )&&pass.equals ( pass )){
                System.out.println ( "que opcion desea" );
                message.setText ( "que opcion desea" );
              if (command.equals ( "/parqueo" )){
                  System.out.println ( "Donde desa su parqueo?" );
              }
              if (command.equals ( "/registrarme" )) {
                    System.out.println ( "Deseas registrar un auto o un parqueo?" );
                    message.setText ( "Deseas registrar un auto o un parqueo? o desea eliminar" );
                    if (command.equals ( "/auto" )) {
                        System.out.println ( "Favor de ingresar tu nombre de usuario " );
                        message.setText ( "Favor de ingresar tu nombre de usuario");
                        usser=update.getMessage ().getText ();
                        message.setText ( "Favor de ingresar tu nombre");
                        name=update.getMessage ().getText ();
                        message.setText ( "Favor de ingresar tu apellido paterno");
                        lastname=update.getMessage ().getText ();
                        message.setText ( "Favor de ingresar tu apellido materno");
                        secondlastname=update.getMessage ().getText ();
                        message.setText ( "Favor de ingresar tu correo electronico");
                        email=update.getMessage ().getText ();
                        message.setText ( "Favor de ingresar tu password");
                        pass=update.getMessage ().getText ();
                        message.setText ( "Favor ingrese su numero de placa");
                        placa=update.getMessage ().getText ();
                        message.setText ( " favor ingrese su nit");
                        nit= update.getMessage ().getText ();
                        System.out.println ( name+lastname+secondlastname+usser+email+pass+placa+nit+pass );
/*
                        ValPerson nueva = new ValPerson();
                        nueva.setFirstName ( name );
                        nueva.setEmail (    email );
                        nueva.setFirstLastName ( lastname );
                        nueva.setSecondLastName ( secondlastname );
                        nueva.setPassword ( pass  );
                        personRepository.save(nueva);*/
                    }
                  if (command.equals ( "/garage" )) {

                        message.setText (  "Favor de ingresar tu nombre de usuario ");
                        usser = update.getMessage ().getText ();
                        message.setText ( "Favor de ingresar tu nombre");
                        name=update.getMessage ().getText ();
                        message.setText ( "Favor de ingresar tu apellido paterno");
                        lastname=update.getMessage ().getText ();
                        message.setText ( "Favor de ingresar tu apellido materno");
                        secondlastname=update.getMessage ().getText ();
                        message.setText ( "Favor de ingresar tu correo electronico");
                        email=update.getMessage ().getText ();
                        message.setText ( "Favor de ingresar tu password");
                        pass=update.getMessage ().getText ();
                        message.setText ( "Favor ingresa tu zona ");
                        zona = update.getMessage ().getText ();
                        message.setText ( "Favor ingresa la cantidad de  espacios en tu garage");
                        espacios = update.getMessage ().getText ();
                        message .setText ( "Favor ingrese su nit ");
                        nit = update.getMessage ().getText ();
                        System.out.println ( name+lastname+secondlastname+usser+email+pass+zona+espacios+nit );
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

            }else {
                System.out.println ( "email o pass incorrecto intente de nuevo" );
                message.setText ( "email o pass incorrecto intente de nuevo" );
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
