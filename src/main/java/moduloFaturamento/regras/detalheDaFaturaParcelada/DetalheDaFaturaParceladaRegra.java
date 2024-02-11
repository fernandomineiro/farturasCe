package moduloFaturamento.regras.detalheDaFaturaParcelada;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import moduloFaturamento.model.DetalheDaFaturaParcelada;
import moduloFaturamento.model.Fatura;
import moduloFaturamento.model.IdDetalheDaFaturaParcelada;
import moduloFaturamento.repository.FaturaRepository;

public abstract class DetalheDaFaturaParceladaRegra {

    @Autowired
    private FaturaRepository faturaRepository;

    protected List<DetalheDaFaturaParcelada> cadastrarUmaNovaDetalheDaFaturaParcelada(Integer cdParcelamento, Integer matriculaImovel, Integer cdCliente,
         List<Integer> listaReferencias, BigDecimal valorTotalJuros,  BigDecimal valorTotalMulta, BigDecimal valorTotalDescontoDaFatura, 
         BigDecimal valorTotalDescontoJuros, BigDecimal valorTotaldescontoMulta){

        List<Fatura> listaDeFaturas = faturaRepository.findByIdFaturaMatriculaImovelAndCdClienteAndIdFaturaRefFaturaIn(matriculaImovel, cdCliente, 
            listaReferencias);

        List<DetalheDaFaturaParcelada> listaDetalheDeFaturaParceladas = new ArrayList<>();

        int quantidadeDeReferencia = listaDeFaturas.size();

        boolean veriicacaoPrimeiraParcela = true;

        BigDecimal valorDasParcelasJuros = buscarValorDasParcelas(valorTotalJuros, quantidadeDeReferencia);

        BigDecimal valorDasParcelasMulta = buscarValorDasParcelas(valorTotalMulta, quantidadeDeReferencia);

        BigDecimal valorDasParcelasDescontoDaFatura = buscarValorDasParcelas(valorTotalDescontoDaFatura, quantidadeDeReferencia);

        BigDecimal valorDasParcelasDescontoDoJuros = buscarValorDasParcelas(valorTotalDescontoJuros, quantidadeDeReferencia);

        BigDecimal valorDasParcelasDescontoMulta = buscarValorDasParcelas(valorTotaldescontoMulta, quantidadeDeReferencia);

        for (Fatura fatura : listaDeFaturas) {

            DetalheDaFaturaParcelada detalheDaFaturaParcelada = new DetalheDaFaturaParcelada();

            if (veriicacaoPrimeiraParcela) {

                BigDecimal juros = calculoSeValorDasParcelasEExatamenteOuPrimeiraTemUmaDiferenca(valorTotalJuros, quantidadeDeReferencia, valorDasParcelasJuros);

                BigDecimal multa = calculoSeValorDasParcelasEExatamenteOuPrimeiraTemUmaDiferenca(valorTotalMulta, quantidadeDeReferencia, valorDasParcelasMulta);

                BigDecimal descontoFatura = calculoSeValorDasParcelasEExatamenteOuPrimeiraTemUmaDiferenca(valorTotalDescontoDaFatura, quantidadeDeReferencia, 
                    valorDasParcelasDescontoDaFatura);
                
                BigDecimal descontoJuros = calculoSeValorDasParcelasEExatamenteOuPrimeiraTemUmaDiferenca(valorTotalDescontoJuros, quantidadeDeReferencia, 
                    valorDasParcelasDescontoDoJuros);
                
                BigDecimal descontoMulta = calculoSeValorDasParcelasEExatamenteOuPrimeiraTemUmaDiferenca(valorTotaldescontoMulta, quantidadeDeReferencia, 
                    valorDasParcelasDescontoMulta);

                instanciarValoresParaDetalheDaFaturaParcelada(detalheDaFaturaParcelada, juros, multa, descontoFatura, descontoJuros, descontoMulta, fatura,
                    cdParcelamento);

                listaDetalheDeFaturaParceladas.add(detalheDaFaturaParcelada);
                veriicacaoPrimeiraParcela = false;
                continue;
            }

            instanciarValoresParaDetalheDaFaturaParcelada(detalheDaFaturaParcelada, valorDasParcelasJuros, valorDasParcelasMulta, valorDasParcelasDescontoDaFatura,
                valorDasParcelasDescontoDoJuros, valorDasParcelasDescontoMulta, fatura, cdParcelamento);

            listaDetalheDeFaturaParceladas.add(detalheDaFaturaParcelada);            
        }

        return listaDetalheDeFaturaParceladas;
    }

    /**
     * MÉTDOSO PRIVADOS
     */
    private void instanciarValoresParaDetalheDaFaturaParcelada(DetalheDaFaturaParcelada detalheDaFaturaParcelada, BigDecimal juros, BigDecimal multa,
        BigDecimal descontoFatura, BigDecimal descontoJuros, BigDecimal descontoMulta, Fatura fatura, Integer cdParcelamento) {

        detalheDaFaturaParcelada.setJurosFatura(juros);
        detalheDaFaturaParcelada.setMultaFatura(multa);
        detalheDaFaturaParcelada.setDescontoFatura(descontoFatura);
        detalheDaFaturaParcelada.setDescontoJuros(descontoJuros);
        detalheDaFaturaParcelada.setDescontoMulta(descontoMulta);

        IdDetalheDaFaturaParcelada idDetalheDaFaturaParcelada = new IdDetalheDaFaturaParcelada(cdParcelamento, fatura.getMatriculaImovel(), 
            fatura.getRefFatura(), fatura.getOrigemFatura());

        detalheDaFaturaParcelada.setIdDetalheDaFaturaParcelada(idDetalheDaFaturaParcelada);
    }

    private BigDecimal calculoSeValorDasParcelasEExatamenteOuPrimeiraTemUmaDiferenca(BigDecimal valorTotalJuros, int quantidadeDeReferencia, BigDecimal valor) {

        BigDecimal valorTotalDosJurosProvaReal = valor.multiply(BigDecimal.valueOf(quantidadeDeReferencia));

        if (valorTotalDosJurosProvaReal.compareTo(valorTotalJuros) != 0) {

            BigDecimal valorDaDiferencia = valorTotalJuros.subtract(valorTotalDosJurosProvaReal);
            return valor.add(valorDaDiferencia);

        } else {
            return valor;
        }
    }

    private BigDecimal buscarValorDasParcelas(BigDecimal valor, int quantidadeDeReferencia) {

        return valor.compareTo(BigDecimal.ZERO) != 0 ? 
            valor.divide( BigDecimal.valueOf(quantidadeDeReferencia), 2, RoundingMode.HALF_EVEN) : 
            BigDecimal.ZERO;
    }
    
}
