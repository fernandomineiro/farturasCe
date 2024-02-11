package moduloFaturamento.regras.dossie;

import org.springframework.stereotype.Service;

import moduloFaturamento.model.common.DossieImovel;

@Service
public class DossieImovelAtualizarCiclo extends DossieImovelRegra{

    public DossieImovel construirDossieImovel(String dcAnotacao, Integer matriculaImovel, Short dv, Integer matriculaCliente, Short dvCliente, String idUsuario) {
        return super.construirDossieImovel((short) 0, matriculaCliente, dcAnotacao, "Concender Crédito", matriculaImovel, dv, dvCliente, idUsuario);
    }
}
