package moduloFaturamento.infra.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LogErro {

	Logger logger = LoggerFactory.getLogger(LogErro.class);

	public void logErro(String erro, String origem) {

		logger.error("Ocorreu um erro: " + origem + " - " + erro);
	}

	public void logErroGeral(Object e, String origem) {
		logger.error("Ocorreu um erro: " + origem + " - " + e);
	}

}
