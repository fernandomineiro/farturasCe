package moduloFaturamento.regras.faturamentoParcelamentoRegra;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import moduloFaturamento.model.FaturamentoParcelamento;

@Service
public class FaturamentoParcelamentoCrudRegra extends FaturamentoParcelamentoRegra{

    public FaturamentoParcelamento cadastrarNovaParcela(Integer cdCliente, Integer matriculaImovel, String token, float porcentagemDaCorrecaoMonetaria, 
        Integer quantidadeDeParcelas,  BigDecimal descontoDaNegociacao, Short diaPagamentoDasParcelas, BigDecimal valorEntrada, BigDecimal totalAgua, BigDecimal totalEsgoto, 
        BigDecimal totalOutrosServicos, BigDecimal totalJuros, BigDecimal totalMulta, BigDecimal descontoAutomaticoPrincipal, BigDecimal descontoAutomaticoEncargosFinanceiros, 
        BigDecimal descontoCreditoCliente, BigDecimal correcaoMonetaria){

        return super.cadastrarParcelamento(cdCliente, matriculaImovel, token, porcentagemDaCorrecaoMonetaria, quantidadeDeParcelas, descontoDaNegociacao, 
            diaPagamentoDasParcelas, valorEntrada, totalAgua, totalEsgoto, totalOutrosServicos, totalJuros, totalMulta, descontoAutomaticoPrincipal, 
            descontoAutomaticoEncargosFinanceiros, descontoCreditoCliente, correcaoMonetaria);
    }
    
}
