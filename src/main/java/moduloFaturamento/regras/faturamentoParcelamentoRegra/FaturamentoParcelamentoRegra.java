package moduloFaturamento.regras.faturamentoParcelamentoRegra;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import moduloFaturamento.model.FaturamentoParcelamento;
import moduloFaturamento.model.common.Imovel;
import moduloFaturamento.repository.common.ImovelRepository;
import moduloFaturamento.util.ConverterData;

public abstract class FaturamentoParcelamentoRegra {

    @Autowired
    private ImovelRepository imovelRepository;

    protected FaturamentoParcelamento cadastrarParcelamento(Integer cdCliente, Integer matriculaImovel, String loginUsuario, float porcentagemDaCorrecaoMonetaria, 
        Integer quantidadeDeParcelas,  BigDecimal descontoDaNegociacao, Short diaPagamentoDasParcelas, BigDecimal valorEntrada, BigDecimal totalAgua, BigDecimal totalEsgoto, 
        BigDecimal totalOutrosServicos, BigDecimal totalJuros, BigDecimal totalMulta, BigDecimal descontoAutomaticoPrincipal, BigDecimal descontoAutomaticoEncargosFinanceiros, 
        BigDecimal descontoCreditoCliente, BigDecimal correcaoMonetaria){

        Imovel imovel = imovelRepository.findByMatriculaImovelAndDataHoraExclusaoIsNull(matriculaImovel);
        Integer dataParcelamento = ConverterData.localDateEmIntegerDataFormatoDB(LocalDate.now());
        Integer horaEmInteger = buscarHoraEmInteger();

        FaturamentoParcelamento faturamentoParcelamento = new FaturamentoParcelamento();

        faturamentoParcelamento.setCdCliente(cdCliente);
        faturamentoParcelamento.setImovel(imovel);

        faturamentoParcelamento.setLoginUsuario(loginUsuario);

        faturamentoParcelamento.setDataParcelamento(dataParcelamento);
        faturamentoParcelamento.setHoraParcelamento(horaEmInteger);

        faturamentoParcelamento.setIndiceReajuste(BigDecimal.valueOf(porcentagemDaCorrecaoMonetaria).setScale(2));

        faturamentoParcelamento.setStatusParcelamento((short) 0);

        faturamentoParcelamento.setQuantidadeParcelas(quantidadeDeParcelas.shortValue());

        faturamentoParcelamento.setOrigemFaturaParcelamento((short) 7);

        faturamentoParcelamento.setValorEntrada(valorEntrada);

        faturamentoParcelamento.setValorDescontoNegociacao(descontoDaNegociacao);

        faturamentoParcelamento.setDiaVencimento(diaPagamentoDasParcelas);

        faturamentoParcelamento.setCsCortar("S");
        faturamentoParcelamento.setCsEmviarCobranca("S");

        faturamentoParcelamento.setValorAgua(totalAgua);
        faturamentoParcelamento.setValorEsgoto(totalEsgoto);
        faturamentoParcelamento.setValorOutrosServicos(totalOutrosServicos);

        faturamentoParcelamento.setValorJuros(totalJuros);
        faturamentoParcelamento.setValorMulta(totalMulta);

        faturamentoParcelamento.setDescontroAutomaticosDoPrincipal(descontoAutomaticoPrincipal);
        faturamentoParcelamento.setDescontosAutomaticosDoFinanceiros(descontoAutomaticoEncargosFinanceiros);

        faturamentoParcelamento.setDescontrosCreditoDoCliente(descontoCreditoCliente);

        faturamentoParcelamento.setValorCorrecaoMonetraria(correcaoMonetaria);

        return faturamentoParcelamento;
    }

    private Integer buscarHoraEmInteger() {

        SimpleDateFormat formatado = new SimpleDateFormat("HH.mm.ss.mmm");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String tempoEmString = formatado.format(timestamp).replaceAll("\\.", "");
        return Integer.valueOf(tempoEmString);
    }
}
