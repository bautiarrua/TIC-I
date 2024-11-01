package org.example.tici;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TicIApplication {

    public static void main(String[] args) {
        // Cargar el archivo .env
        Dotenv dotenv = Dotenv.load();

        // Inicia la aplicación
        SpringApplication.run(TicIApplication.class, args);

    }

}
