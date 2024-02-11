package moduloFaturamento.dto.imovelArrecadacao;

import java.time.LocalDate;

public class ArrecadacaoImovelDTO {

	private Short agenciaArrecadadora;

	private Short agenteArrecadador;

	private String contaCorrente;

	private LocalDate dataInclusaoD;

	public Short getAgenciaArrecadadora() {
		return agenciaArrecadadora;
	}

	public void setAgenciaArrecadadora(Short agenciaArrecadadora) {
		this.agenciaArrecadadora = agenciaArrecadadora;
	}

	public Short getAgenteArrecadador() {
		return agenteArrecadador;
	}

	public void setAgenteArrecadador(Short agenteArrecadador) {
		this.agenteArrecadador = agenteArrecadador;
	}

	public String getContaCorrente() {
		return contaCorrente;
	}

	public void setContaCorrente(String contaCorrente) {
		this.contaCorrente = contaCorrente;
	}

	

	public LocalDate getDataInclusaoD() {
		return dataInclusaoD;
	}

	public void setDataInclusaoD(LocalDate dataInclusaoD) {
		this.dataInclusaoD = dataInclusaoD;
	}

}
