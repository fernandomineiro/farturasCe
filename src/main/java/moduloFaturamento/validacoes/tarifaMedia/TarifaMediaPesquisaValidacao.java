package moduloFaturamento.validacoes.tarifaMedia;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

@Service
public class TarifaMediaPesquisaValidacao extends TarifaMediaValidacao{

    public void gerenciarValidacaoPesquisaTarifaMedia(LocalDate refFatura){
        super.gerarExcessaoDataReferenciaEValido(refFatura);
    }
    
}
