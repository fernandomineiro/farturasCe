package moduloFaturamento.dto.leituraConsumoImovel.projection;

import moduloFaturamento.dto.classificacaoImobiliaria.LeituraConsumoImovelCabecalhoClassificacaoImovelDTO;
import moduloFaturamento.dto.imovelArrecadacao.ArrecadacaoImovelDTO;
import moduloFaturamento.model.Fatura;
import moduloFaturamento.model.common.HidrometroRetirado;
import moduloFaturamento.util.ConverterData;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class LeituraConsumoImovelCabecalhoDTO {

    private String nomeCliente;
    private Boolean clienteEspecial;
    private Boolean orgaoPublico;
    private String criterioDecisaoJudicial;
    private String siglaRua;
    private String nomeRua;
    private String bairro;
    private String cidade;
    private String complemento;
    private Integer numeroEndereco;
    private Short ciclo;
    private BigDecimal sequencia;
    private String situacaoLigacaoAgua;
    private String situacaoLigacaoEsgoto;

    private String tipoMatriculaImovel;
    private Integer matriculaMacroNumero;
    private Short   matriculaMacroDv;

    private List<LeituraConsumoImovelCabecalhoClassificacaoImovelDTO> listaDeClassificacaoImobiliaria;
    private Integer totalEconomiasClassificacoImovel;

    private String hidrometroInstaladoCodigo;
    private LocalDate hidrometroInstaladoDataInstalacao;
    private Integer hidrometroInstaladoLeitura;

    private Short hidrometroPonteiro;

    private String ultimoHidrometroRetiradoCodigo;
    private LocalDate ultimoHidrometroRetiradoDataRetirada;
    private Integer ultimoHidrometroRetiradoLeitura;

    private String arrecadacaoImovelNumeroDebitoContaCorrente;
    private Short arrecadacaoImovelCodigoAgenciaArrecadora;
    private Short arrecadacaoImovelCodigoAgenteArrecador;

    private LocalDate ultimoFaturamentoReferencia;
    private BigDecimal ultimoFaturamentoValorFatura;

    private LocalDate dataReativacaoAgua;


    public LeituraConsumoImovelCabecalhoDTO() {
    }

    public LeituraConsumoImovelCabecalhoDTO(String nomeCliente, String clienteEspecial, Short tipoCliente, String siglaRua, String nomeRua, String bairro, String cidade, String complemento, Integer numeroEndereco, Short ciclo,
                                            BigDecimal sequencia, String situacaoLigacaoAgua, String situacaoLigacaoEsgoto, String hidrometroInstaladoCodigo,
                                            Integer hidrometroInstaladoDataInstalacao, Integer hidrometroInstaladoLeitura, Integer dataReativacaoAgua, Short hidrometroPonteiro, Short tipoMatriculaImovel, Integer matriculaMacro,
                                            String criterioFaturamentoDecisaoJudicial) {
        this.nomeCliente = (nomeCliente != null ? nomeCliente.trim() : null);
        this.clienteEspecial = (clienteEspecial != null && clienteEspecial.equalsIgnoreCase("S"));
        this.setOrgaoPublico(tipoCliente);
        this.siglaRua = (siglaRua != null ? siglaRua.trim() : null);
        this.nomeRua = (nomeRua != null ? nomeRua.trim() : null);
        this.bairro = (bairro != null ? bairro.trim() : null);
        this.cidade = (cidade != null ? cidade.trim() : null);
        this.complemento = (complemento != null ? complemento.trim() : null);
        this.numeroEndereco = numeroEndereco;
        this.ciclo = ciclo;
        this.sequencia = sequencia;
        this.situacaoLigacaoAgua = situacaoLigacaoAgua;
        this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
        this.hidrometroInstaladoCodigo = (hidrometroInstaladoCodigo != null ? hidrometroInstaladoCodigo.trim() : null);
        this.hidrometroInstaladoDataInstalacao = ConverterData.integerEmLocalDateDataFormatoDB(hidrometroInstaladoDataInstalacao);
        this.hidrometroInstaladoLeitura = hidrometroInstaladoLeitura;
        this.dataReativacaoAgua = ConverterData.integerEmLocalDateDataFormatoDB(dataReativacaoAgua);
        this.hidrometroPonteiro = hidrometroPonteiro;
        this.tipoMatriculaImovel = (tipoMatriculaImovel != null ? (tipoMatriculaImovel == 1 ? "Macro" : "Micro") : "");
        this.criterioDecisaoJudicial = criterioFaturamentoDecisaoJudicial;
        this.matriculaMacroNumero = matriculaMacro;
    }




    public void setHidrometroRetirado(HidrometroRetirado hidrometroRetirado){
        if(hidrometroRetirado != null){
            this.ultimoHidrometroRetiradoCodigo = (hidrometroRetirado.getIdHidrometroRetirado().getCodHidrometro() != null ? hidrometroRetirado.getIdHidrometroRetirado().getCodHidrometro().trim() : null);
            this.ultimoHidrometroRetiradoDataRetirada = ConverterData.integerEmLocalDateDataFormatoDB(hidrometroRetirado.getIdHidrometroRetirado().getDataRetirada());
            this.ultimoHidrometroRetiradoLeitura = hidrometroRetirado.getLeituraRetirada();
        }
    }


    public void setNumeroDebitoContaCorrente(ArrecadacaoImovelDTO dto) {
        if (dto != null) {
            this.arrecadacaoImovelCodigoAgenteArrecador = dto.getAgenteArrecadador();
            this.arrecadacaoImovelCodigoAgenciaArrecadora = dto.getAgenciaArrecadadora();
            this.arrecadacaoImovelNumeroDebitoContaCorrente = dto.getContaCorrente();
        }
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getNomeRua() {
        return nomeRua;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getComplemento() {
        return complemento;
    }

    public Integer getNumeroEndereco() {
        return numeroEndereco;
    }

    public Short getCiclo() {
        return ciclo;
    }

    public String getSiglaRua() {
        return siglaRua;
    }

    public BigDecimal getSequencia() {
        return sequencia;
    }

    public String getSituacaoLigacaoAgua() {
        return situacaoLigacaoAgua;
    }

    public String getSituacaoLigacaoEsgoto() {
        return situacaoLigacaoEsgoto;
    }

    public List<LeituraConsumoImovelCabecalhoClassificacaoImovelDTO> getListaDeClassificacaoImobiliaria() {
        return listaDeClassificacaoImobiliaria;
    }

    public Integer getTotalEconomiasClassificacoImovel() {
        return totalEconomiasClassificacoImovel;
    }

    public void setListaDeClassificacaoImobiliariaETotalEconomias(List<LeituraConsumoImovelCabecalhoClassificacaoImovelDTO> listaDeClassificacaoImobiliaria) {
        this.listaDeClassificacaoImobiliaria = listaDeClassificacaoImobiliaria;

        this.totalEconomiasClassificacoImovel = 0;
        for(LeituraConsumoImovelCabecalhoClassificacaoImovelDTO leituraConsumoImovelCabecalhoClassificacaoImovelDTO : listaDeClassificacaoImobiliaria){
            this.totalEconomiasClassificacoImovel += leituraConsumoImovelCabecalhoClassificacaoImovelDTO.getEconomias();
        }

    }

    public void setUltimoFaturamento(Fatura fatura){
        if(fatura != null){
            this.ultimoFaturamentoReferencia = ConverterData.integerEmLocalDateReferenciaFormatoDB(fatura.getRefFatura());
            this.ultimoFaturamentoValorFatura = fatura.getValorFatura();
        }

    }

    public String getHidrometroInstaladoCodigo() {
        return hidrometroInstaladoCodigo;
    }

    public LocalDate getHidrometroInstaladoDataInstalacao() {
        return hidrometroInstaladoDataInstalacao;
    }

    public Integer getHidrometroInstaladoLeitura() {
        return hidrometroInstaladoLeitura;
    }

    public String getUltimoHidrometroRetiradoCodigo() {
        return ultimoHidrometroRetiradoCodigo;
    }

    public LocalDate getUltimoHidrometroRetiradoDataRetirada() {
        return ultimoHidrometroRetiradoDataRetirada;
    }

    public Integer getUltimoHidrometroRetiradoLeitura() {
        return ultimoHidrometroRetiradoLeitura;
    }

    public String getArrecadacaoImovelNumeroDebitoContaCorrente() {
        return arrecadacaoImovelNumeroDebitoContaCorrente;
    }

    public Short getArrecadacaoImovelCodigoAgenciaArrecadora() {
        return arrecadacaoImovelCodigoAgenciaArrecadora;
    }

    public Short getArrecadacaoImovelCodigoAgenteArrecador() {
        return arrecadacaoImovelCodigoAgenteArrecador;
    }

    public LocalDate getUltimoFaturamentoReferencia() {
        return ultimoFaturamentoReferencia;
    }

    public BigDecimal getUltimoFaturamentoValorFatura() {
        return ultimoFaturamentoValorFatura;
    }

    public LocalDate getDataReativacaoAgua() {
        return dataReativacaoAgua;
    }

    public Short getHidrometroPonteiro() {
        return hidrometroPonteiro;
    }

    public Boolean getClienteEspecial() {
        return clienteEspecial;
    }

    public String getTipoMatriculaImovel() {
        return tipoMatriculaImovel;
    }

    public Boolean getOrgaoPublico() {
        return orgaoPublico;
    }

    private void setOrgaoPublico(Short tipoCliente) {
        switch (tipoCliente){
            case 1: this.orgaoPublico = true; break;
            case 2: this.orgaoPublico = true; break;
            case 3: this.orgaoPublico = true; break;
            default: this.orgaoPublico = false;
        }
    }

    public String getCriterioDecisaoJudicial() {
        return criterioDecisaoJudicial;
    }

    public Integer getMatriculaMacroNumero() {
        return matriculaMacroNumero;
    }

    public Short getMatriculaMacroDv() {
        return matriculaMacroDv;
    }

    public void setMatriculaMacroDv(Short matriculaMacroDv) {
        this.matriculaMacroDv = Objects.requireNonNullElse(matriculaMacroDv, (short)0);
    }
}
