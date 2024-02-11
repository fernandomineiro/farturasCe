package moduloFaturamento.validacoes.feriado;

import org.springframework.stereotype.Service;

@Service
public class FeriadoCadastroValidacao extends FeriadoValidacao{

    public void gerenciarValidacaoParaCadastrar(Short idLocalidade, Short idTipoFeriado, Integer idUnidadeFederativa, Integer dataFeriado){
        super.gerarExcessaoLocalidadeInformadaETipoFeriadoDiferenteMunicipal(idLocalidade, idTipoFeriado);
        super.gerarExcessaoFederacaoInformadaETipoFeriadoDiferenteEstadual(idUnidadeFederativa, idTipoFeriado);
        super.gerarExcessaoTipoFeriadoMunicialELocalidadeNaoInformada(idTipoFeriado, idLocalidade);
        super.gerarExcessaoTipoFeriadoEstadualEFederacaoNaoInformada(idTipoFeriado, idUnidadeFederativa);
        super.gerarExcessaoFeriadoJaCadastradoParaMesmaDataFeriadoELocalidadeETipoFederacao(dataFeriado, idLocalidade, idUnidadeFederativa);
    }
}
