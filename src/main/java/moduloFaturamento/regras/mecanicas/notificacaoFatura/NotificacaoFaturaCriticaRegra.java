package moduloFaturamento.regras.mecanicas.notificacaoFatura;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import moduloFaturamento.excecoes.MsgGenericaPersonalizadaException;
import moduloFaturamento.util.ConverterMatricula;

@Service
public class NotificacaoFaturaCriticaRegra {

	public List<Integer> validarEConverterMatriculas(List<String> matriculasInput) {

		List<String> matriculas = new ArrayList<>();

		for (int i = 0; i < matriculasInput.size(); i++) {

			String matricula = matriculasInput.get(i);

			if (matricula != null && !"".equals(matricula.trim())) {

				matriculas.add(matricula);
			}
		}

		Pattern naoHifenizada = Pattern.compile("[0-9]{1,7}");
		Pattern hifenizada = Pattern.compile("[0-9]{1,7}-[0-9]{1}");

		Predicate<String> nHifPred = naoHifenizada.asMatchPredicate();
		Predicate<String> hifPred = hifenizada.asMatchPredicate();

		List<Integer> validas = new ArrayList<>();
		List<String> invalidas = new ArrayList<>();

		for (String matricula : matriculas) {

			if (hifPred.test(matricula)) {

				validas.add(ConverterMatricula.matriculaSemDV(matricula));
			} else if (nHifPred.test(matricula)) {

				validas.add(Integer.valueOf(matricula));
			} else {

				invalidas.add(matricula);
			}
		}

		if (!invalidas.isEmpty()) {

			throw new MsgGenericaPersonalizadaException(String.format("Os dados %s não são matrículas em formato válido.", invalidas));
		}
		
		return validas;
	}
}
