package moduloFaturamento.validacoes.ocorrenciaLeitura;

import org.springframework.stereotype.Service;

@Service
public class OcorrenciaLeituraCadastroValidacao extends OcorrenciaLeituraValidacao{

    public void gerenciarValidacaoParaCadastrar(Short cdOcorrencia){
        super.gerarExcessaoCodigoOcorrenciaInformadoJaExistenteNaBaseDeDados(cdOcorrencia);
    }
}
