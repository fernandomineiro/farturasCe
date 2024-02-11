package moduloFaturamento.validacoes.credito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import moduloFaturamento.excecoes.MsgGenericaPersonalizadaException;
import moduloFaturamento.model.Credito;
import moduloFaturamento.model.Fatura;
import moduloFaturamento.model.FaturamentoParcelamentoParcela;
import moduloFaturamento.model.IdFatura;
import moduloFaturamento.model.common.Imovel;
import moduloFaturamento.repository.CreditoRepository;
import moduloFaturamento.repository.FaturaRepository;
import moduloFaturamento.repository.FaturamentoParcelamentoParcelaRepository;
import moduloFaturamento.repository.common.ImovelRepository;
import moduloFaturamento.util.ConverterData;
import moduloFaturamento.validacoes.common.CommonUtilValidacoes;
import moduloFaturamento.validacoes.solicitacaoServico.SolicitacaoServicoConsultaValidacao;

public abstract class CreditoValidacao {

    @Autowired
    private CreditoRepository repository;
    @Autowired
    private ImovelRepository imovelRepository;
    @Autowired
    private FaturamentoParcelamentoParcelaRepository faturamentoParcelamentoParcelaRepository;
    @Autowired
    private SolicitacaoServicoConsultaValidacao solicitacaoServicoConsultaValidacao;
    @Autowired
    private FaturaRepository faturaRepository;
    @Autowired
    private CommonUtilValidacoes commonUtilValidacoes;

    protected void gerarExcessaoSeMatriculaExiste(Integer matricula) {

        Optional<Imovel> verifacandoSeexisteMatricula = imovelRepository.findById(matricula);

        if (verifacandoSeexisteMatricula.isEmpty()) {
            throw new MsgGenericaPersonalizadaException("Não existe Matricula com esses números.");
        }

	}

    protected void gerarExcessaoSeExiteCreditoLancado(Integer matricula, short credito) {

        Integer verificarSeExistePeloMenosUmCredito = repository.verificarSeExisteCredito(matricula, credito);

        if (verificarSeExistePeloMenosUmCredito == null) {
            throw new MsgGenericaPersonalizadaException("Não existe crédido.");
        }

	}

    protected Integer gerarExcessaoSeCampoValorSeraEditado(BigDecimal valorCreditoDTO, BigDecimal valorCreditoDoBD){
        if (!valorCreditoDTO.equals(valorCreditoDoBD)) return 1;
        return 0;
    }

    protected Integer gerarExcessaoSeIraModificarSS( Integer refAtendimento, Integer refAtendimentoCredito,
                                                        Integer cdAtendimento, Integer cdAtendimentoCredito,
                                                        Short seqSS, Short seqSSCredito){
        
        // Se Usuario não preencher nada os valores da Tela serão nulas, mas no banco entendo que Zero é valor nullo
        if (refAtendimento == null && cdAtendimento == null && seqSS == null) {
            refAtendimento = 0;
            cdAtendimento = 0;
            seqSS = 0;
        }

        if (refAtendimento != refAtendimentoCredito  && cdAtendimento != cdAtendimentoCredito && seqSS != seqSSCredito) return 1;

        return 0;
    }

    protected Integer gerarExcessaoSeModificaraParcela(Integer cdParcelamento, Integer numeroParcela, FaturamentoParcelamentoParcela parcela){

        if (parcela != null) {
                                                    
            if(parcela.getIdFaturamentoParcelamentoParcela().getCdParcelamento() != cdParcelamento && 
                    parcela.getIdFaturamentoParcelamentoParcela().getNumeroParcela() != numeroParcela) return 1;

            return 0;  
                                                                
            } else {

                if(Optional.ofNullable(cdParcelamento).orElse(0) != 0|| Optional.ofNullable(numeroParcela).orElse(0) != 0 )return 1;

                return 0;
            }
    }

    protected Integer gerarExcessaoSeIraEditarFatura(LocalDate referenciaFatura, Integer referenciaFaturaCredito, Short origemFatura, Short origemFaturaCredito){

        if (referenciaFaturaCredito != null) {

            LocalDate dataEmLocalDate = ConverterData.integerEmLocalDateReferenciaFormatoDB(referenciaFaturaCredito);

            if (referenciaFatura != dataEmLocalDate && origemFatura != origemFaturaCredito) return 1;

            return 0;            
        }

        if (referenciaFatura != null && origemFatura != origemFaturaCredito || origemFatura != null) return 1;
        
        return 0;
    }

    protected Integer gerarExcessaoSeJustificativaSeraAlterada(String cdAnotacao, String cdAnotacaoCredito){

        if (!cdAnotacao.equalsIgnoreCase(cdAnotacaoCredito) ) return 1;
        
        return 0;
    }

    protected void gerarExcessaoSeTentarAtualizarSemModificar(Integer cont){

        if (cont == 0) {
            throw new MsgGenericaPersonalizadaException("Não será atualizado porque nenhum campo foi alterado.");
        }

    }

    protected void gerarExcessaoSeExisteCdParcelamentoENumeroParcela(Integer cdParcelamento, Integer numeroParcela) {

        if (Optional.ofNullable(cdParcelamento).orElse(0) != 0) {

            Integer verificarSeExisteParcelasDoParcelamento = faturamentoParcelamentoParcelaRepository.buscarSeExisteUMRegistro(cdParcelamento, numeroParcela);

            if (verificarSeExisteParcelasDoParcelamento == null) {
                throw new MsgGenericaPersonalizadaException("Dados incorretos para inserir uma Parcela.");
            }
        }
	}

    protected void gerarExcessaoSeExisteSSValida(Integer refAtendimento, Integer cdAtendimento, Short seqSS) {
        if (Optional.ofNullable(refAtendimento).orElse(0) != 0) {
            solicitacaoServicoConsultaValidacao.gerarExcecaoSSNaoEncontrada(refAtendimento, cdAtendimento, seqSS);
        }        
	}

    protected void gerarExcessaoSeRefFaturaEValido(LocalDate refFatura) {
        if(refFatura != null) {
            commonUtilValidacoes.gerarExcessaoReferenciaInvalidaFormatoDB(refFatura);
        }        
	}

    protected void gerarExcessaoSeExisteFaturaCadastrada(Integer matricula, Short origemFatura ,LocalDate refFatura) {

        if (refFatura != null) {

            Integer conversaoData = ConverterData.localDateEmIntegerReferenciaFormatoDB(refFatura);

            Optional<Fatura> buscandoSeExisteUmaFatura = faturaRepository.findById(new IdFatura(matricula, origemFatura, conversaoData));
    
            if (buscandoSeExisteUmaFatura.isEmpty()) {
                throw new MsgGenericaPersonalizadaException("Não existe fatura com essa a matricula ou dados estão incorretos");
            }

        }        
	}  
    
    protected void gerarExcessaoSeAlterarCreditoForMelhorQueSaldo(BigDecimal valorCreditoTela, BigDecimal valorCreditoBD, BigDecimal valorsaldo){

        BigDecimal valorJaPago = valorCreditoBD.subtract(valorsaldo).setScale(2);

        BigDecimal valorSubtracaoParaVerDiferencaDosCreditos = valorCreditoTela.subtract(valorJaPago).setScale(2);

        if (valorSubtracaoParaVerDiferencaDosCreditos.compareTo(BigDecimal.ZERO) < 0 ) {

            throw new MsgGenericaPersonalizadaException("Não é possivel alterar Crédito, pois valor do saldo é maior");          
        }

    }

    protected void gerarExcessaoSeCampoJustificativaEstaSendoModificado(Long id, String dcAnotacaoUsuario){

        Optional<Credito> buscarCreditoPeloAtributoId = repository.findById(id);

        if (buscarCreditoPeloAtributoId.isPresent()) {

            if (buscarCreditoPeloAtributoId.get().getDcAnotacao().equalsIgnoreCase(dcAnotacaoUsuario)  ){

                throw new MsgGenericaPersonalizadaException("Campo não pode ser igual ao anterior.");            }
        }else{

            throw new MsgGenericaPersonalizadaException("Crédito não encontrado.");
        }

    }
}
