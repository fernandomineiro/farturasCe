package moduloFaturamento.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UrlMicroservico {

	@Value("${microservico.arrecadacao}")
	private String urlArrecadacao;


	public String getUrlArrecadacao() {
		return urlArrecadacao;
	}

	public void setUrlArrecadacao(String urlArrecadacao) {
		this.urlArrecadacao = urlArrecadacao;
	}
}
