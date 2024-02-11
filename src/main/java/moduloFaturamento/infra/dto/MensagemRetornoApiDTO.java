package moduloFaturamento.infra.dto;

import java.time.Instant;

/**
 * @author Ivan Alves
 * <b>Constroi um ojeto generico para servir de mensagem de retorno de API para o front end</b>
 */
public class MensagemRetornoApiDTO {

	private int codeStatus;
	private String tituloStatus;
	private Instant dataHora;

	public int getCodeStatus() {
		return codeStatus;
	}
	public void setCodeStatus(int codeStatus) {
		this.codeStatus = codeStatus;
	}
	public String getTituloStatus() {
		return tituloStatus;
	}
	public void setTituloStatus(String tituloStatus) {
		this.tituloStatus = tituloStatus;
	}
	public Instant getDataHora() {
		return dataHora;
	}
	public void setDataHora(Instant dataHora) {
		this.dataHora = dataHora;
	}
	
	
	
}
