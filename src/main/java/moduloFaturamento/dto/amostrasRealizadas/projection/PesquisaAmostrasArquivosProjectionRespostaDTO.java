package moduloFaturamento.dto.amostrasRealizadas.projection;

import java.time.LocalDate;

public interface PesquisaAmostrasArquivosProjectionRespostaDTO {

	String getReferencia();

	String getTipoArquivo();
	
	String getUsuario();
	
	LocalDate getDataInsercao();
	
	boolean getFlagAmostraExigida();
}
