package moduloFaturamento.regras.dossie;

import moduloFaturamento.model.common.DossieImovel;
import org.springframework.stereotype.Service;

@Service
public class DossieImovelCobrancaFaturaRegra extends DossieImovelRegra{

    public DossieImovel construirDossieImovel(String dcAnotacao, Integer matriculaImovel, Short dv, Integer matriculaCliente, Short dvCliente, String idUsuario) {
        return super.construirDossieImovel((short) 30, matriculaCliente, dcAnotacao, "Cobrar Serviços", matriculaImovel, dv, dvCliente, idUsuario);
    }
}
