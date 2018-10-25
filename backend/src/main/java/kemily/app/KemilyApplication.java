package kemily.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@ComponentScan("config") //Aqui se coloca o package para scanear os controllers
public class KemilyApplication {

	public static void main(String[] args) {
		SpringApplication.run(KemilyApplication.class, args);
	}

	@GetMapping("/")
	public String home() {
		return "Home Page";
	}

}
