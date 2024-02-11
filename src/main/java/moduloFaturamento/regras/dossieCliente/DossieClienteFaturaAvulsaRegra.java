package moduloFaturamento.regras.dossieCliente;

import org.springframework.stereotype.Service;

import moduloFaturamento.model.common.DossieCliente;

@Service
public class DossieClienteFaturaAvulsaRegra extends DossieClienteRegra{

    public DossieCliente cadastrarDosseiClienteEmfaturaAvulsa(Integer cdCliente, String dcAnotacao, String dcObjeto, String loginUsuario){
        return super.construirDossieImovel(cdCliente, dcAnotacao, dcObjeto, loginUsuario);
    }
    
}
