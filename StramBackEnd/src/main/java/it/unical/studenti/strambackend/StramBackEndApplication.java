package it.unical.studenti.strambackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;



@SpringBootApplication
@ServletComponentScan
public class StramBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(StramBackEndApplication.class, args);
	}

}
