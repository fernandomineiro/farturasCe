package moduloFaturamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FaturamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(FaturamentoApplication.class, args);
	}

}
