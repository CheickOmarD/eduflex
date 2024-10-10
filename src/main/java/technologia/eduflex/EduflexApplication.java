package technologia.eduflex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("technologia.eduflex.repositories")
public class EduflexApplication {

	public static void main(String[] args) {
		SpringApplication.run(EduflexApplication.class, args);
	}

}
