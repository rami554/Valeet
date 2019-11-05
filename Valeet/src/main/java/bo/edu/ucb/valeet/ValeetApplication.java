package bo.edu.ucb.valeet;

import bo.edu.ucb.valeet.Bot.ValeetBot;
import bo.edu.ucb.valeet.domain.ValPerson;
import bo.edu.ucb.valeet.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@SpringBootApplication
@EnableJpaAuditing
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
