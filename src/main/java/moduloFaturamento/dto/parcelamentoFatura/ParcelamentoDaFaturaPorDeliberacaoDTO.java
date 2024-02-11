package moduloFaturamento.dto.parcelamentoFatura;

import java.util.ArrayList;
import java.util.List;

import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametro.IncentivoClienteParametroRespostaConsultaDTO;
import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametro.IncentivoClienteParametroTipoDTO;
import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametro.projection.IncentivoClienteParametroRespostaGridProjectionDTO;
import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametroDetalhe.projection.IncentivoClienteParametroDetalheRespostaGridProjectionDTO;

public class ParcelamentoDaFaturaPorDeliberacaoDTO {
	


	private int situacaoAgua;
	
	private int situacaoEsgoto;
    
	private GenericoWrapperDTO<List<IncentivoClienteParametroRespostaGridProjectionDTO>> listIncentivo;
  
	private ArrayList<IncentivoClienteParametroRespostaConsultaDTO> incentivoCliente;
	
	private List<IncentivoClienteParametroTipoDTO> tipoDeliberacao;
    
	public List<IncentivoClienteParametroTipoDTO> getTipoDeliberacao() {
		return tipoDeliberacao;
	}

	public void setTipoDeliberacao(List<IncentivoClienteParametroTipoDTO> tipoDeliberacao) {
		this.tipoDeliberacao = tipoDeliberacao;
	}
	
	public int getSituacaoAgua() {
		return situacaoAgua;
	}

	public void setSituacaoAgua(int situacaoAgua) {
		this.situacaoAgua = situacaoAgua;
	}

	public int getSituacaoEsgoto() {
		return situacaoEsgoto;
	}

	public void setSituacaoEsgoto(int situacaoEsgoto) {
		this.situacaoEsgoto = situacaoEsgoto;
	}

	public GenericoWrapperDTO<List<IncentivoClienteParametroRespostaGridProjectionDTO>> getListIncentivo() {
		return listIncentivo;
	}

	public void setListIncentivo(
			GenericoWrapperDTO<List<IncentivoClienteParametroRespostaGridProjectionDTO>> listIncentivo) {
		this.listIncentivo = listIncentivo;
	}

	public ArrayList<IncentivoClienteParametroRespostaConsultaDTO> getIncentivoCliente() {
		return incentivoCliente;
	}

	public void setIncentivoCliente(ArrayList<IncentivoClienteParametroRespostaConsultaDTO> incentivoCliente) {
		this.incentivoCliente = incentivoCliente;
	}

	    
}
