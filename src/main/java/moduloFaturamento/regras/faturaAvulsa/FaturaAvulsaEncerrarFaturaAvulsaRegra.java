package moduloFaturamento.regras.faturaAvulsa;

import org.springframework.stereotype.Service;

import moduloFaturamento.model.FaturaAvulsa;

@Service
public class FaturaAvulsaEncerrarFaturaAvulsaRegra extends FaturaAvulsaRegra{

    @Override
    public FaturaAvulsa encerrarFaturaAvulsa(Long id){
                
        return super.encerrarFaturaAvulsa(id);
    }    
}
