package moduloFaturamento.regras.dossieCliente;

import org.springframework.stereotype.Service;

import moduloFaturamento.model.common.DossieCliente;

@Service
public class DossieClienteFaturamentoRegra extends DossieClienteRegra{

    public DossieCliente cadastrarDossieClienteNOFaturamentoFatura(Integer cdCliente, String dcAnotacao, String dcObjeto, String loginUsuario){
        return super.construirDossieImovel(cdCliente, dcAnotacao, dcObjeto, loginUsuario);
    }
    
}
