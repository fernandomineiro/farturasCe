package moduloFaturamento.validacoes.tarifa;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import moduloFaturamento.dto.tarifa.PesquisaTarifaRespostaDTO;

@Service
public class TarifaRegraParaValidacao extends TarifaValidacao {

    public void gerenciarValidacaoPesquisaExigida(LocalDate data) {
		super.gerarExcessaoDataTarifaInvalida(data);
	}

	public void gerenciarValidacaoPesquisaExistente(List<PesquisaTarifaRespostaDTO> listaTarifas){
		super.gerarExcessaoSeListaEstiverVazia(listaTarifas);
	}
    
}
