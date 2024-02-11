package moduloFaturamento.regras.solicitacaoServico;

import moduloFaturamento.util.Formulas;

abstract class SolicitacaoServicoRegra {

    protected static String formatarSS(Integer refAtendimento, Integer codAtendimento, Short seqSS) {
        Formulas formulas = new Formulas();
        if(refAtendimento != null && codAtendimento != null && seqSS != null) {
            return refAtendimento.toString().substring(4, 6) + "/" + refAtendimento.toString().substring(2, 4) +
                    "-" + formulas.preencherZero(6, codAtendimento.toString().length()) + codAtendimento +
                    "-" + formulas.preencherZero(2, seqSS.toString().length()) + seqSS;
        }else{
            return null;
        }
    }
}
