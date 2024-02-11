package moduloFaturamento.validacoes.cobrancaFatura;

import moduloFaturamento.dto.cobrancaFatura.CobrancaFaturaAtualizarDTO;
import org.springframework.stereotype.Service;

@Service
public class CobrancaFaturaAtualizarValidacao extends CobrancaFaturaValidacao {

    public void gerenciarValidacaoParaEditar(CobrancaFaturaAtualizarDTO dto){
        super.gerarExececaoCobrancaFaturaNaoEncontrado(dto.getId());
        super.gerarExcecaoSolicitacaoNaoEncontrada(dto.getRefSolicitacao(), dto.getCodSolicitacao(), dto.getSeqSolicitacao(), dto.getTipoSolicitacao());
        super.gerarExcecaoQualquerModificacaoCobrancaComServicoJurosEMultas(dto.getCodigoServico());
        super.gerarExcecaoReferenciaAFaturarAnteriorADataDeHoje(dto.getReferenciaFaturar());
    }
}
