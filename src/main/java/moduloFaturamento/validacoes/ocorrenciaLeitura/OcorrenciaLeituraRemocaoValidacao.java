package moduloFaturamento.validacoes.ocorrenciaLeitura;

import org.springframework.stereotype.Service;

@Service
public class OcorrenciaLeituraRemocaoValidacao extends OcorrenciaLeituraValidacao{

    public void gerenciarValidacaoDadosParaDeletar(Short cdOcorrencia){
        super.gerarExcessaoOcorrenciaLeituraNaoEncontrato(cdOcorrencia);
    }
}
