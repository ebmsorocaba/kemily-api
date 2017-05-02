package kemily.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("controller") //Aqui se coloca o package para scanear os controllers
public class KemilyApplication {

	public static void main(String[] args) {
		SpringApplication.run(KemilyApplication.class, args);
	}
}
