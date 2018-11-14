package kemily.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication//(exclude = org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class)
@ComponentScan("controller") //Aqui se coloca o package para scanear os controllers
@ComponentScan("Config")
@ComponentScan("Security")
@ComponentScan("jdbc.dao")
public class KemilyApplication {

	public static void main(String[] args) {
		SpringApplication.run(KemilyApplication.class, args);
	}

}
