package br.com.letscode.starwars.resistence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.letscode.starwars.resistence.config.ResistenceConstants;

@SpringBootApplication(scanBasePackages = ResistenceConstants.APP_BASE_PACKAGE)
public class StarWarsResistenceSocialNetworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarWarsResistenceSocialNetworkApplication.class, args);
    }

}
