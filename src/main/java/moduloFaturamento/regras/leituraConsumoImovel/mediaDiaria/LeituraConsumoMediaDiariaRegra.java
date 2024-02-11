package moduloFaturamento.regras.leituraConsumoImovel.mediaDiaria;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moduloFaturamento.model.Leitura;
import moduloFaturamento.model.common.HidrometroInstalado;
import moduloFaturamento.regras.leituraConsumoImovel.LeituraConsumoRegra;
import moduloFaturamento.repository.HidrometroInstaladoRepository;
import moduloFaturamento.repository.LeituraRepository;
import moduloFaturamento.service.common.ClassificacaoImobiliariaService;
import moduloFaturamento.service.common.HidrometroMovimentacaoInstalacaoRetiradaService;
import moduloFaturamento.service.common.ImovelService;
import moduloFaturamento.util.ConverterData;

@Service
public abstract class LeituraConsumoMediaDiariaRegra extends LeituraConsumoRegra {

    @Autowired
    private LeituraRepository leituraRepository;
    @Autowired
    private HidrometroInstaladoRepository hidrometroInstaladoRepository;
    @Autowired
    private ImovelService imovelService;
    @Autowired
    private HidrometroMovimentacaoInstalacaoRetiradaService hidrometroMovimentacaoInstalacaoRetiradaService;
    @Autowired
    private ClassificacaoImobiliariaService classificacaoImobiliariaService;

    protected BigDecimal buscarValorCalculadoMediaDiaria(Integer matriculaImovel, LocalDate refFatura) {

        List<Leitura> leituras = this.leituraRepository.buscarUltimasDozeLeiturasQueNaoPossuemExcluirCalculoMedia(matriculaImovel, ConverterData.localDateEmIntegerReferenciaFormatoDB(refFatura));

        leituras = this.retornarLeiturasComLimiteMaximo24Meses(leituras,refFatura);

        Boolean imovelEstaSomenteNoServicoColetaDeEsgoto = this.imovelService.retornarSeImovelEstaSomenteNoServicoColetaDeEsgoto(matriculaImovel);
        Boolean houveHistoricoMovimentacaoHidrometroParaEsteImovel = this.hidrometroMovimentacaoInstalacaoRetiradaService.retornarSeHouveHistoricoInstalacaoRetiradaHidrometroPorMatricula(matriculaImovel);

        if(houveHistoricoMovimentacaoHidrometroParaEsteImovel){
            if(refFatura.isAfter(LocalDate.of(2021,9,1))){
                return this.calculoParaReferenciaMaiorQueSetembro2021(matriculaImovel, refFatura, leituras, imovelEstaSomenteNoServicoColetaDeEsgoto);
            }else{
                return this.calculoParaReferenciaMenorQueSetembro2021(matriculaImovel, refFatura, leituras, imovelEstaSomenteNoServicoColetaDeEsgoto);
            }
        }else{
            return BigDecimal.ZERO;
        }
    }


    private List<Leitura> retornarLeiturasComLimiteMaximo24Meses(List<Leitura> leituras, LocalDate MesVigente){
        List<Leitura> leiturasLimite24Meses = new ArrayList<>();
        if(leituras != null && leituras.size() > 0) {
            LocalDate mesMaisAntigo = ConverterData.integerEmLocalDateReferenciaFormatoDB(leituras.get(leituras.size()-1).getIdLeituraFaturamento().getRefFatura());
            LocalDate mesLimiteParaRegraDe24Meses = MesVigente.minusMonths(24);

            for(Leitura leitura : leituras){
                LocalDate dataRef = ConverterData.integerEmLocalDateReferenciaFormatoDB(leitura.getIdLeituraFaturamento().getRefFatura());
                if (dataRef.isAfter(mesLimiteParaRegraDe24Meses)) {
                    leiturasLimite24Meses.add(leitura);
                }
            }
        }
        return leiturasLimite24Meses;
    }



    private BigDecimal calculoParaReferenciaMenorQueSetembro2021(Integer matriculaImovel, LocalDate refFatura, List<Leitura> leituras, Boolean imovelEstaSomenteNoServicoColetaDeEsgoto){

        BigDecimal somatorioConsumoFaturado = BigDecimal.ZERO;
        BigDecimal somatorioConsumoMedido = BigDecimal.ZERO;
        BigDecimal somatorioDiasVenda = BigDecimal.ZERO;
        BigDecimal somatorioDiasConsumo = BigDecimal.ZERO;

        for (Leitura leitura : leituras){
            if(leitura.getMedido() > 0){
                somatorioConsumoMedido = somatorioConsumoMedido.add(new BigDecimal(leitura.getMedido()));
                somatorioDiasConsumo = somatorioDiasConsumo.add(new BigDecimal(leitura.getDiasConsumo()));
            }else{
                somatorioConsumoFaturado = this.obterResultadoSomaConsumoFaturado(imovelEstaSomenteNoServicoColetaDeEsgoto, somatorioConsumoFaturado, leitura);
                somatorioDiasVenda = somatorioDiasVenda.add(new BigDecimal(leitura.getDiasVenda()));
            }
        }

        HidrometroInstalado hidrometroInstalado = this.hidrometroInstaladoRepository.buscarUltimoHidrometroInstaladoPelaMatriculaImovel(matriculaImovel);
        if(hidrometroInstalado == null) {
            return this.obterCalculoMediaQuandoHidrometroNaoEstaInstalado(matriculaImovel);
        }else {
            return this.obterResultadoDaMediaDiariaPeloSomatorioDosDados(somatorioConsumoFaturado, somatorioConsumoMedido, somatorioDiasVenda, somatorioDiasConsumo);
        }


    }

    private BigDecimal obterCalculoMediaQuandoHidrometroNaoEstaInstalado(Integer matriculaImovel) {
        Integer basico = this.obterValorBasicogrupoConsumoDoImovel(matriculaImovel);
        Integer totalEconomia = this.classificacaoImobiliariaService.buscarTotalEconomias(matriculaImovel);
        if(totalEconomia > 1){
            return new BigDecimal((basico * totalEconomia)/30);
        }else{
            return new BigDecimal(basico/30);
        }
    }

    private BigDecimal calculoParaReferenciaMaiorQueSetembro2021(Integer matriculaImovel, LocalDate refFatura, List<Leitura> leituras, Boolean imovelEstaSomenteNoServicoColetaDeEsgoto){
        BigDecimal somatorioConsumoFaturado = BigDecimal.ZERO;
        BigDecimal somatorioConsumoMedido = BigDecimal.ZERO;
        BigDecimal somatorioDiasVenda = BigDecimal.ZERO;
        BigDecimal somatorioDiasConsumo = BigDecimal.ZERO;

        for (Leitura leitura : leituras){
            boolean possuiOcorrenciaGrave = (leitura.getOcorrencia() != null && leitura.getOcorrencia() != 0);
            if(possuiOcorrenciaGrave){
                somatorioConsumoFaturado = this.obterResultadoSomaConsumoFaturado(imovelEstaSomenteNoServicoColetaDeEsgoto, somatorioConsumoFaturado, leitura);
                somatorioDiasVenda = somatorioDiasVenda.add(new BigDecimal(leitura.getDiasVenda()));
            }else{
                somatorioConsumoMedido = somatorioConsumoMedido.add(new BigDecimal(leitura.getMedido()));
                somatorioDiasConsumo = somatorioDiasConsumo.add(new BigDecimal(leitura.getDiasConsumo()));
            }
        }

        HidrometroInstalado hidrometroInstalado = this.hidrometroInstaladoRepository.buscarUltimoHidrometroInstaladoPelaMatriculaImovel(matriculaImovel);
        if(hidrometroInstalado == null) {
            return BigDecimal.ZERO;
        }else{
            return obterResultadoDaMediaDiariaPeloSomatorioDosDados(somatorioConsumoFaturado, somatorioConsumoMedido, somatorioDiasVenda, somatorioDiasConsumo);
        }

    }

    private BigDecimal obterResultadoSomaConsumoFaturado(Boolean imovelEstaSomenteNoServicoColetaDeEsgoto, BigDecimal somatorioConsumoFaturado, Leitura leitura) {
        if (imovelEstaSomenteNoServicoColetaDeEsgoto) {
            somatorioConsumoFaturado = somatorioConsumoFaturado.add(leitura.getConsumoFaturarEsgoto());
        } else {
            somatorioConsumoFaturado = somatorioConsumoFaturado.add(leitura.getConsumoFaturarAgua());
        }
        return somatorioConsumoFaturado;
    }

    private BigDecimal obterResultadoDaMediaDiariaPeloSomatorioDosDados(BigDecimal somatorioConsumoFaturado, BigDecimal somatorioConsumoMedido, BigDecimal somatorioDiasVenda, BigDecimal somatorioDiasConsumo) {
        BigDecimal somatorioConsumoMedidoEFaturado;
        BigDecimal somatorioDiasConsumoEVenda;

        somatorioConsumoMedidoEFaturado = somatorioConsumoFaturado.add(somatorioConsumoMedido);
        somatorioDiasConsumoEVenda = somatorioDiasVenda.add(somatorioDiasConsumo);

        if (!somatorioConsumoMedidoEFaturado.equals(BigDecimal.ZERO) && !somatorioDiasConsumoEVenda.equals(BigDecimal.ZERO)) {
            BigDecimal teste = somatorioConsumoMedidoEFaturado.divide(somatorioDiasConsumoEVenda, 3, RoundingMode.FLOOR);
            return somatorioConsumoMedidoEFaturado.divide(somatorioDiasConsumoEVenda, 3, RoundingMode.FLOOR);
        } else {
            return BigDecimal.ZERO.setScale(3, RoundingMode.FLOOR);
        }
    }
}
