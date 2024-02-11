package moduloFaturamento.validacoes.credito;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import moduloFaturamento.dto.credito.CreditoAtualizarDTO;
import moduloFaturamento.model.Credito;

@Service
public class CreditoValidacaoCrud extends CreditoValidacao {

    public void gerenciarValidacaoVerificarAtualizar(Credito credito, CreditoAtualizarDTO atualizarDTO) {

        Integer verificarSeAlgumCampoSeraAlterado = 0;

        verificarSeAlgumCampoSeraAlterado += super.gerarExcessaoSeCampoValorSeraEditado(atualizarDTO.getValorCredito().setScale(2), credito.getValorCredito());

        verificarSeAlgumCampoSeraAlterado += super.gerarExcessaoSeIraModificarSS(atualizarDTO.getRefAtendimento(), credito.getRefAtendimento(),
                                                    atualizarDTO.getCodAtendimento(), credito.getCdAtendimento(), 
                                                    atualizarDTO.getSeqSS(),  credito.getSeqSS());

        verificarSeAlgumCampoSeraAlterado += super.gerarExcessaoSeModificaraParcela(atualizarDTO.getCdParcelamento(), atualizarDTO.getNumeroParcela(), credito.getFaturamentoParcelamentoParcela());

        verificarSeAlgumCampoSeraAlterado += super.gerarExcessaoSeIraEditarFatura(atualizarDTO.getReferenciaFatura(), credito.getRefFatura(), 
                                                    atualizarDTO.getOrigemFatura(), credito.getOrigemFatura());

        verificarSeAlgumCampoSeraAlterado += super.gerarExcessaoSeJustificativaSeraAlterada(atualizarDTO.getCampoJustificativa(), credito.getDcAnotacao());                                    

        super.gerarExcessaoSeTentarAtualizarSemModificar(verificarSeAlgumCampoSeraAlterado);
        super.gerarExcessaoSeAlterarCreditoForMelhorQueSaldo(atualizarDTO.getValorCredito(), credito.getValorCredito(), credito.getValorSaldo());
	}

    public void gerenciarValidacaoParaCadstrar(Integer matricula, Integer cdParcelamento, Integer numeroParcela,Short origemFatura, LocalDate refFatura, 
                                            Integer refAtendimento, Integer codAtendimento, Short seqSS ){
        super.gerarExcessaoSeMatriculaExiste(matricula);
        super.gerarExcessaoSeExisteCdParcelamentoENumeroParcela(cdParcelamento, numeroParcela);
        super.gerarExcessaoSeExisteSSValida(refAtendimento, codAtendimento, seqSS);
        super.gerarExcessaoSeRefFaturaEValido(refFatura);
        super.gerarExcessaoSeExisteFaturaCadastrada(matricula, origemFatura, refFatura);
      }

    public void gerenciarValidacaoParaEncerrarCredito(Long id, String dcAnotacao){

        super.gerarExcessaoSeCampoJustificativaEstaSendoModificado(id, dcAnotacao);
    }      
    
}
