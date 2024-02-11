package moduloFaturamento.regras.dossie;

import org.springframework.stereotype.Service;

import moduloFaturamento.model.common.DossieImovel;

@Service
public class DossieImovelParcelamentofatura extends DossieImovelRegra{

    public DossieImovel construirDossieImovelParaParcelamentoFatura(String dcAnotacao, Integer matriculaImovel, Short dv, Integer cdcliente, 
        Short dvCliente, String idUsuario) {
        return super.construirDossieImovel((short) 0, cdcliente, dcAnotacao, "Parcelamento", matriculaImovel, dv, dvCliente, idUsuario);
    }
    
}
