package moduloFaturamento.validacoes.ocorrenciaLeitura;

import org.springframework.stereotype.Service;

@Service
public class OcorrenciaLeituraConsultaValidacao extends OcorrenciaLeituraValidacao {

    public void validarConsulta(Short cdOcorrencia){
        super.gerarExcessaoOcorrenciaLeituraNaoEncontrato(cdOcorrencia);
    }
}
