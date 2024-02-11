package moduloFaturamento.infra.dto;

import java.time.Instant;

/**
 * @author Ivan Alves
 * <b>Constroi um ojeto de mensagem de execessões</b>
 * Essa classe herda atributos e metodos da Classe MensagemRetornoApiDTO
 */
public class ExcessoesRetornoApiDTO extends MensagemRetornoApiDTO {
	
	/**
	 * @author Ivan Alves
	 * descrição da execessão gerada pelo Spring
	 */
	private String descricaoExcessao;
	
	
	/**
	 * @author Ivan Alves
	 * <b>Construtor para setar os atributos da classe</b>
	 * constroi a classe sem precisar chamar os metodos seters um por um
	 * @param codeStatus - código do status. Ex: 400
	 * @param tituloStatus -  descrição do código de status. Ex: 400 BAD_REQUEST
	 * @param descricaoExcessao - descrição da excessão gerada pelo Spring  
	 */
	public ExcessoesRetornoApiDTO(int codeStatus, String tituloStatus, String descricaoExcessao) {
		super();
		setCodeStatus(codeStatus);
		setTituloStatus(tituloStatus);
		setDataHora(Instant.now());
		this.descricaoExcessao = descricaoExcessao;
	}

	public ExcessoesRetornoApiDTO() {
		super();
	}
	
	public String getDescricaoExcessao() {
		return descricaoExcessao;
	}

	public void setDescricaoExcessao(String descricaoExcessao) {
		this.descricaoExcessao = descricaoExcessao;
	}
	
}
