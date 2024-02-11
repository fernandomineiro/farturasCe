package moduloFaturamento.validacoes.cobrancaFatura;

import moduloFaturamento.excecoes.MsgGenericaPersonalizadaException;
import moduloFaturamento.model.CobrancaServicoFatura;
import moduloFaturamento.regras.cobrancaFatura.spec.CobrancaFaturaTipoNumeroSolicitacaoEnum;
import moduloFaturamento.repository.CobrancaServicoFaturaRepository;
import moduloFaturamento.validacoes.itemAtendimento.ItemAtendimentoConsultaValidacao;
import moduloFaturamento.validacoes.solicitacaoServico.SolicitacaoServicoConsultaValidacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.Optional;

abstract class CobrancaFaturaValidacao {

    @Autowired
    private CobrancaServicoFaturaRepository cobrancaServicoFaturaRepository;
    @Autowired
    private SolicitacaoServicoConsultaValidacao solicitacaoServicoConsultaValidacao;
    @Autowired
    private ItemAtendimentoConsultaValidacao itemAtendimentoConsultaValidacao;

    protected void gerarExececaoCobrancaFaturaNaoEncontrado(Long id){
        Optional<CobrancaServicoFatura> cobrancaFaturaOptional = this.cobrancaServicoFaturaRepository.findByIdAndDataHoraExclusaoIsNull(id);
        if (cobrancaFaturaOptional.isEmpty()) {
            throw new MsgGenericaPersonalizadaException("Cobrança não encontrado");
        }
    }

    protected void gerarExcecaoReferenciaAFaturarSuperiorATresMesesDaDataVigente(LocalDate referenciaAFaturar){
        LocalDate dataMaximaPermitida = LocalDate.now().plusMonths(3);

        if(referenciaAFaturar != null && referenciaAFaturar.isAfter(dataMaximaPermitida)){
            throw new MsgGenericaPersonalizadaException("Favor informar referência a faturar com no máximo três meses da data vigente.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }


    protected void gerarExcecaoReferenciaAFaturarAnteriorADataDeHoje(LocalDate referenciaAFaturar){
        LocalDate dataMaximaPermitida = LocalDate.now();

        if(referenciaAFaturar != null && referenciaAFaturar.isBefore(dataMaximaPermitida)){
            throw new MsgGenericaPersonalizadaException("Favor informar referência a faturar superior a data de ontem.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    protected void gerarExcecaoTipoSolicitacaoInformadoPoremNumeroAusente(CobrancaFaturaTipoNumeroSolicitacaoEnum tipo, Integer refAtendimento, Integer codAtendimento, Short sequencia){
        if(tipo != null && (refAtendimento == null || codAtendimento == null || sequencia == null)){
            throw new MsgGenericaPersonalizadaException("Quando o tipo de solicitação é informado, o número da solicitação deve ser informado.", HttpStatus.BAD_REQUEST);
        }
    }

    protected void gerarExcecaoTipoSolicitacaoNaoInformadoPoremNumeroInformado(CobrancaFaturaTipoNumeroSolicitacaoEnum tipo, Integer refAtendimento, Integer codAtendimento, Short sequencia){
        if(tipo == null && (refAtendimento != null || codAtendimento != null || sequencia != null)){
            throw new MsgGenericaPersonalizadaException("Quando um número de solicitação é informado, o tipo deve ser informado", HttpStatus.BAD_REQUEST);
        }
    }

    protected void gerarExcecaoSolicitacaoNaoEncontrada(Integer refAtendimento, Integer codAtendimento, Short sequencia, CobrancaFaturaTipoNumeroSolicitacaoEnum tipo){
        if(tipo != null && tipo.getTipo().equals(CobrancaFaturaTipoNumeroSolicitacaoEnum.SOLICITACAO_SERVICO.getTipo())){
            this.gerarExcecaoSSInformadaNaoEncontrado(refAtendimento, codAtendimento, sequencia);
        }else{
            this.gerarExcecaoItemAtendimentoInformadaNaoEncontrado(refAtendimento, codAtendimento, sequencia);
        }
    }

    protected void gerarExcecaoQualquerModificacaoCobrancaComServicoJurosEMultas(Short codigoServico){
        final Short CODIGO_SERVICO_MULTA =  1405;
        final Short CODIGO_SERVICO_JUROS =  1421;
        if(codigoServico.equals(CODIGO_SERVICO_MULTA) || codigoServico.equals(CODIGO_SERVICO_JUROS)){
            throw new MsgGenericaPersonalizadaException("Não é possível modificar ou remover cobrança para esse serviço", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
    private void gerarExcecaoSSInformadaNaoEncontrado(Integer refAtendimento, Integer codAtendimento, Short sequencia){
        if(refAtendimento != null && codAtendimento != null && sequencia != null) {
            this.solicitacaoServicoConsultaValidacao.gerarExcecaoSSNaoEncontrada(refAtendimento, codAtendimento, sequencia);
        }
    }

    private void gerarExcecaoItemAtendimentoInformadaNaoEncontrado(Integer refAtendimento, Integer codAtendimento, Short sequencia){
        if(refAtendimento != null && codAtendimento != null && sequencia != null) {
            this.itemAtendimentoConsultaValidacao.gerarExcecaoItemAtendimentoNaoEncontrada(refAtendimento, codAtendimento, sequencia);
        }
    }


}
