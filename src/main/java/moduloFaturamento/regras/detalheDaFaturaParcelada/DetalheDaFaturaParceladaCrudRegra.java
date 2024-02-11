package moduloFaturamento.regras.detalheDaFaturaParcelada;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import moduloFaturamento.model.DetalheDaFaturaParcelada;

@Service
public class DetalheDaFaturaParceladaCrudRegra extends DetalheDaFaturaParceladaRegra{

    public List<DetalheDaFaturaParcelada> cadastrarDetalheDaFaturaParcelada (Integer cdParcelamento, Integer matriculaImovel, Integer cdCliente, 
        List<Integer> listaReferencias, BigDecimal valorTotalJuros,  BigDecimal valorTotalMulta, BigDecimal valorTotalDescontoDaFatura, 
        BigDecimal valorTotalDescontoJuros, BigDecimal valorTotaldescontoMulta){

        return super.cadastrarUmaNovaDetalheDaFaturaParcelada(cdParcelamento, matriculaImovel, cdCliente, listaReferencias, valorTotalJuros, valorTotalMulta, 
            valorTotalDescontoDaFatura, valorTotalDescontoJuros, valorTotaldescontoMulta);
    }
    
}
