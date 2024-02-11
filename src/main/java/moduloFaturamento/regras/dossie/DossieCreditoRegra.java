package moduloFaturamento.regras.dossie;

import org.springframework.stereotype.Service;

import moduloFaturamento.model.common.DossieImovel;

@Service
public class DossieCreditoRegra extends DossieImovelRegra{

    public DossieImovel construirDossieImovel(String dcAnotacao, Integer matriculaImovel, Short dv, Integer matriculaCliente, Short dvCliente, String idUsuario) {
        return super.construirDossieImovel((short) 43, matriculaCliente, dcAnotacao, "Concender Crédito", matriculaImovel, dv, dvCliente, idUsuario);
    }

    public DossieImovel atualizarDossieImovel(String dcAnotacao, Integer matriculaImovel, Short dv, Integer matriculaCliente, Short dvCliente, String idUsuario, 
        Integer dataAnotacao) {
        return super.atualizarDossieImovel((short) 43, matriculaCliente, dcAnotacao, "Concender Crédito", matriculaImovel, dv, dvCliente, idUsuario, dataAnotacao);
    }
    
}
