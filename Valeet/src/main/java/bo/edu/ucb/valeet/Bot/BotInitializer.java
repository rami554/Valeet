package bo.edu.ucb.valeet.Bot;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;

@Component
public class BotInitializer {
    @Autowired
    ValeetBot valeetBot;


    //public BotInitializer(ValeetBot valeetBot) {
      //  this.valeetBot = valeetBot;
    //}

    public BotInitializer() {
    }

    @PostConstruct
    public void init() {
        //ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(valeetBot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
