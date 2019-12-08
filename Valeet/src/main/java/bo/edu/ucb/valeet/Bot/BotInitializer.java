package bo.edu.ucb.valeet.Bot;


import bo.edu.ucb.valeet.bl.BotBl;
import bo.edu.ucb.valeet.bl.GarageBl;
import bo.edu.ucb.valeet.bl.PersonBl;
import bo.edu.ucb.valeet.bl.VehicleBl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;

@Component napo
public class BotInitializer {

    BotBl botBl;
    GarageBl garageBl;
    PersonBl personBl;
    VehicleBl vehicleBl;

@Autowired
    public BotInitializer(BotBl botBl, GarageBl garageBl, PersonBl personBl, VehicleBl vehicleBl) {
    this.botBl=botBl;
    this.garageBl=garageBl;
    this.personBl=personBl;
    this.vehicleBl=vehicleBl;
    }

    public BotInitializer(){

    }

    @PostConstruct
    public void init() {

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new ValeetBot(botBl, personBl, vehicleBl, garageBl));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
