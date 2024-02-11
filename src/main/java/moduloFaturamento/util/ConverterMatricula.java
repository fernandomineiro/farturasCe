package moduloFaturamento.util;

import moduloFaturamento.excecoes.ExcecaoRegraNegocio;

public class ConverterMatricula {

	private static int ORDEM_MAGNITUDE = 10;

	private ConverterMatricula() {

	}

	public static Integer matriculaSemDV(String matricula) {
		
		try {

			Integer intMatricula = Integer.valueOf(matricula.replace("-", ""));
			
			if(intMatricula < 10) {
				throw new ExcecaoRegraNegocio("Matricula inválida");
			}
			
			return matriculaSemDV(intMatricula);
		} catch (Exception e) {

			return null;
		}
	}

	public static Integer matriculaSomenteDV(String matricula) {

		try {

			Integer intMatricula = Integer.valueOf(matricula.replace("-", ""));
			
			if(intMatricula < 10) {
				throw new ExcecaoRegraNegocio("Matricula inválida");
			}
			
			return matriculaSomenteDV(intMatricula);
		} catch (Exception e) {

			return null;
		}
	}

	public static Integer matriculaSemDV(Integer matricula) {

		return matricula == null ? null : removeDV(matricula);
	}

	public static Integer matriculaSomenteDV(Integer matricula) {

		return matricula == null ? null : devolveDV(matricula);
	}

	private static Integer removeDV(Integer matricula) {

		return matricula / ORDEM_MAGNITUDE;
	}

	private static Integer devolveDV(Integer matricula) {

		return matricula % ORDEM_MAGNITUDE;
	}
}
