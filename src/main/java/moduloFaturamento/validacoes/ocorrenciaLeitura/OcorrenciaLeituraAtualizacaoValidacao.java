package moduloFaturamento.validacoes.ocorrenciaLeitura;

import org.springframework.stereotype.Service;

@Service
public class OcorrenciaLeituraAtualizacaoValidacao extends OcorrenciaLeituraValidacao {

    public void gerenciarValidacaoDadosParaAtualizar(Short cdOcorrencia){
        super.gerarExcessaoOcorrenciaLeituraNaoEncontrato(cdOcorrencia);
    }
}
