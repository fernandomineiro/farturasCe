package moduloFaturamento.validacoes.cobrancaFatura;

import moduloFaturamento.dto.cobrancaFatura.CobrancaFaturaCadastrarDTO;
import org.springframework.stereotype.Service;

@Service
public class CobrancaFaturaCadastroValidacao extends CobrancaFaturaValidacao{

    public void gerenciarValidacaoParaCadastrar(CobrancaFaturaCadastrarDTO dto) {
        super.gerarExcecaoSolicitacaoNaoEncontrada(dto.getRefSolicitacao(), dto.getCodSolicitacao(), dto.getSeqSolicitacao(), dto.getTipoSolicitacao());
        super.gerarExcecaoTipoSolicitacaoInformadoPoremNumeroAusente(dto.getTipoSolicitacao(),dto.getRefSolicitacao(), dto.getCodSolicitacao(), dto.getSeqSolicitacao());
        super.gerarExcecaoTipoSolicitacaoNaoInformadoPoremNumeroInformado(dto.getTipoSolicitacao(),dto.getRefSolicitacao(), dto.getCodSolicitacao(), dto.getSeqSolicitacao());
        super.gerarExcecaoReferenciaAFaturarSuperiorATresMesesDaDataVigente(dto.getReferenciaFaturar());
        super.gerarExcecaoReferenciaAFaturarAnteriorADataDeHoje(dto.getReferenciaFaturar());
        super.gerarExcecaoQualquerModificacaoCobrancaComServicoJurosEMultas(dto.getCodigoServico());
    }
}
