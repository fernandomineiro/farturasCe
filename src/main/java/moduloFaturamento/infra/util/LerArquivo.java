package moduloFaturamento.infra.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LerArquivo {

	@Value("${rabbitmq.link}")
	public String rabbitMQ;

	public String LerLinkRabbitMQ() {

		return rabbitMQ;
	}
}
