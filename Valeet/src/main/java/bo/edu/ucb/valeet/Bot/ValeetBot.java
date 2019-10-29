package bo.edu.ucb.valeet.Bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class ValeetBot extends TelegramLongPollingBot {

    public void onUpdateReceived(Update update) {
        System.out.println(update.getMessage().getText());
        SendMessage message = new SendMessage();
        message.setText(update.getMessage().getText());

        message.setChatId(update.getMessage().getChatId());
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return "Valeet_Bot";
    }

    public String getBotToken() {
        return  "941105687:AAGRRvJsE2_3beZ7k5-xXqC-CgJR4e-T2rQ";
    }
}
