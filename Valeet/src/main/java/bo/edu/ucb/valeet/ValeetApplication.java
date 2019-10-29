package bo.edu.ucb.valeet;

import bo.edu.ucb.valeet.Bot.ValeetBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@SpringBootApplication
public class ValeetApplication {

	public static void main(String[] args) {
		SpringApplication.run(ValeetApplication.class, args);

		ApiContextInitializer.init();
		TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
		try {
			telegramBotsApi.registerBot(new ValeetBot());

		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

}
