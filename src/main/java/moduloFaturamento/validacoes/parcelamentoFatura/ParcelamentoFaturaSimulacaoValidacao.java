package moduloFaturamento.validacoes.parcelamentoFatura;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ParcelamentoFaturaSimulacaoValidacao extends ParcelamentoFaturaValidacao{

    public void verificarSeDescontosEMaiorQueDivida(BigDecimal valorFatura, BigDecimal valorJuros, BigDecimal valorMulta,
        BigDecimal descontosAutomaticoFatura, BigDecimal descontosAutomaticoJuros, BigDecimal descontosAutomaticoMulta,
        BigDecimal descontoDaNegociacao) {

        super.gerarExcecaoQuandoDescontoForMaiorQueDebito(valorFatura, valorJuros, valorMulta, descontosAutomaticoFatura, 
        descontosAutomaticoJuros, descontosAutomaticoMulta, descontoDaNegociacao);
    }

    public void verificarSeParcelasEstaoDeAcordoComINcentivoCasoEstejaSendoAplicado(boolean incentivoJuridico, short numeroDeParcelas, 
        List<LocalDate> listaRefencias, Integer matriculaImovel, BigDecimal valorTotalFaturas, BigDecimal valorDaParcelas, BigDecimal valorDaEntrada){
            
        super.gerarExcecaoSeValorDasParcelasForemMenorDoIncentivoCasoTenha(incentivoJuridico, numeroDeParcelas, listaRefencias, matriculaImovel, 
            valorTotalFaturas, valorDaParcelas, valorDaEntrada);
    }

    public void validacaoValorFinaDarDividaEMenorQueAEntradaOuDescontoDaNegociacao(BigDecimal totalANegociar, BigDecimal valorEntrada, BigDecimal descontoDaNegociacao){
        super.gerarExcecaoValorFinalDaDividaEMenorQueAEntradaOuDescontoDaNegociacao(totalANegociar, valorEntrada, descontoDaNegociacao);
    }
    
}
