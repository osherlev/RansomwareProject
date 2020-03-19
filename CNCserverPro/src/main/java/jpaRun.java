
import java.io.FileNotFoundException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class jpaRun {

	public static void main(String[] args) throws FileNotFoundException {

		SpringApplication.run(jpaRun.class, args);

	}
}