package moduloFaturamento.util;

import javax.validation.ConstraintViolationException;

/**
 * @author Ivan Alves
 * <b>Essa classe contém metodos que tratam as mensagens de excessão geradas pelo Spring Validation</b>
 * Ex: mensagens geradas através da validação das anotações @Size @NotNull em entidades
 *
 */
public class FormatarMensagemExceptionValidation {


	private ConstraintViolationException constraintViolationException;
	private String mensagemExcessaoSemtratamento;
	private String mensagemExcessaoFormatada;
	
	/**
	 * @author Ivan Alves
	 * <b>Esse é o pensador da classe, vai gerenciar os demais metodos na ordem 
	 * apropriada para retornar a mensagem de excessão</b>
	 * @param e - Excessão da classe ConstraintViolationException gerada e informada pelo cliente 
	 * @return String
	 */
	public String executarProcedimentoObterMensagemPersonalizadaUsuario(ConstraintViolationException e) {
		this.constraintViolationException = e;
		getMensagemPersonalizadaUsuario();
		formatarMensagemPersonalizadaUsuario();
		return this.mensagemExcessaoFormatada;
	}
	
	/**
	 * @author Ivan Alves
	 * <b>Meotod que vai obter a mensagem. a String resultante será bruta, suja...</b>
	 */
	private void getMensagemPersonalizadaUsuario() {
		this.mensagemExcessaoSemtratamento = this.constraintViolationException.getConstraintViolations().toString();
	}
	
	/**
	 * @author Ivan Alves
	 * <bEsse método vai deixar as coisas mais bonitas. Considere ele o front end dessa classe rsrs</b>
	 * Ele vai obter a String bruta (obtida no metodo getMensagemPersonalizadaUsuario()) e vai formata-la.
	 * Vai toda a informação gerada automaticamente pelo Spring, no fim salvara somente o texto personalizado informado
	 * no arquivo ValidationMenssage.properties
	 */
	private void formatarMensagemPersonalizadaUsuario() {
		int primeiraOcorrenciaAspasSimples = this.mensagemExcessaoSemtratamento.indexOf("'", 0);
		int primeiraOcorrenciaVirgula = this.mensagemExcessaoSemtratamento.indexOf(",", 0);
		this.mensagemExcessaoSemtratamento = this.mensagemExcessaoSemtratamento.substring(primeiraOcorrenciaAspasSimples, primeiraOcorrenciaVirgula);
		this.mensagemExcessaoFormatada =  this.mensagemExcessaoSemtratamento.substring(1, this.mensagemExcessaoSemtratamento.length() - 1);
	}
}
