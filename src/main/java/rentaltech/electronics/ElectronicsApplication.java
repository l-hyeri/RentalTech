package rentaltech.electronics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
//@SpringBootApplication
public class ElectronicsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElectronicsApplication.class, args);
	}

}
