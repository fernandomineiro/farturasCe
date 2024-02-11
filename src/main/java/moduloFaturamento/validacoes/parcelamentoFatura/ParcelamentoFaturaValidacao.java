package moduloFaturamento.validacoes.parcelamentoFatura;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import moduloFaturamento.excecoes.MsgGenericaPersonalizadaException;
import moduloFaturamento.model.IncentivoClienteDetalhe;
import moduloFaturamento.model.common.Cliente;
import moduloFaturamento.repository.ClienteRepository;
import moduloFaturamento.service.impl.ParcelamentoFaturaServiceImpl;
import moduloFaturamento.util.ConverterData;
import moduloFaturamento.validacoes.common.CommonUtilValidacoes;

public abstract class ParcelamentoFaturaValidacao {

    @Autowired
    private CommonUtilValidacoes commonUtilValidacoes;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ParcelamentoFaturaServiceImpl parcelamentoFaturaServiceImpl;

    protected void gerarExcecaoValidarCdCliente(Integer cdCliente, Short dvCliente, String cpfOuCnpj) {

        if (cpfOuCnpj == null) {
            commonUtilValidacoes.gerarExcecaoValidarCdCliente(cdCliente, dvCliente);
        } else {

            Optional<List<Cliente>> clientes = clienteRepository.findByCpfCnpj(cpfOuCnpj);

            if (!clientes.isPresent()) {
                throw new MsgGenericaPersonalizadaException("CPF ou CNJP não foram cliente cadastrado");
            }

            if (clientes.get().size() > 1) {
                throw new MsgGenericaPersonalizadaException("Existe CPF/CNPJ dupliado");
            }
        }

    }

    protected void gerarExcecaoQuandoDescontoForMaiorQueDebito(BigDecimal valorFatura, BigDecimal valorJuros, BigDecimal valorMulta,
        BigDecimal descontosAutomaticoFatura, BigDecimal descontosAutomaticoJuros, BigDecimal descontosAutomaticoMulta, BigDecimal descontoDaNegociacao){

        BigDecimal somatorioDaDivida = valorFatura.add(valorJuros.add(valorMulta));

        BigDecimal valorValidadoDoDescontoFatura = descontosAutomaticoFatura == null ? BigDecimal.ZERO : descontosAutomaticoFatura;
        BigDecimal valorValidadoDoDescontoJuros = descontosAutomaticoJuros == null ? BigDecimal.ZERO : descontosAutomaticoJuros;
        BigDecimal valorValidadoDoDescontoMulta = descontosAutomaticoMulta == null ? BigDecimal.ZERO : descontosAutomaticoMulta;

        BigDecimal valorDescontoDaNegociacao = descontoDaNegociacao == null ? BigDecimal.ZERO : descontoDaNegociacao;

        BigDecimal somatorioDosDescontos = valorValidadoDoDescontoFatura.add(valorValidadoDoDescontoJuros.add(valorValidadoDoDescontoMulta
            .add(valorDescontoDaNegociacao)));

        if (somatorioDaDivida.compareTo(somatorioDosDescontos) < 0) {
            throw new MsgGenericaPersonalizadaException("Valor do desconto e maior que valor Principal!");
        }
    }

    protected void gerarExcecaoSeValorDasParcelasForemMenorDoIncentivoCasoTenha(boolean incentivoJuridico, short numeroDeParcelas, 
        List<LocalDate> listaRefencias, Integer matriculaImovel, BigDecimal valorTotalFaturas, BigDecimal valorDaParcelas, BigDecimal valorEntrada){

        BigDecimal porcentagemDaEntrada;
        Integer ultimaRefenciaDaFaturaEmAberto;
        Integer quantidadeDeFaturaEmAberto;

        if (listaRefencias != null) {

            porcentagemDaEntrada = valorEntrada == null ? BigDecimal.ZERO : valorEntrada.divide(valorTotalFaturas, 2, RoundingMode.HALF_EVEN);
            quantidadeDeFaturaEmAberto = listaRefencias.size();

            LocalDate ultimaRefrenciaEmLocalDate = listaRefencias.stream().max(LocalDate::compareTo).get();
            ultimaRefenciaDaFaturaEmAberto = ConverterData.localDateEmIntegerReferenciaFormatoDB(ultimaRefrenciaEmLocalDate);

            IncentivoClienteDetalhe existeIncentivoDetalhadoCliente = parcelamentoFaturaServiceImpl.verificarSeExisteIncentivoDetalhadoCliente(incentivoJuridico, 
                porcentagemDaEntrada, numeroDeParcelas, quantidadeDeFaturaEmAberto, matriculaImovel, valorTotalFaturas, ultimaRefenciaDaFaturaEmAberto);

            if (existeIncentivoDetalhadoCliente != null && 
                existeIncentivoDetalhadoCliente.getValorMinimoParcela().compareTo(BigDecimal.ZERO) != 0 &&
                existeIncentivoDetalhadoCliente.getValorMinimoParcela().compareTo(valorDaParcelas) < 0 ) {

                throw new MsgGenericaPersonalizadaException("Valor das parcelas é menor do que Incentivo permite");                
            }
        }
    }

    protected void gerarExcecaoValorFinalDaDividaEMenorQueAEntradaOuDescontoDaNegociacao(BigDecimal totalANegociar, BigDecimal valorEntrada, BigDecimal descontoDaNegociacao){

        if (valorEntrada.compareTo(totalANegociar) > 0) {
            throw new MsgGenericaPersonalizadaException("Valor da entrada é maior que a dívida");
        }

        if (descontoDaNegociacao.compareTo(totalANegociar) > 0) {
            throw new MsgGenericaPersonalizadaException("Valor do desconto da Negocição é maior que a dívida");
        }
    }
}
