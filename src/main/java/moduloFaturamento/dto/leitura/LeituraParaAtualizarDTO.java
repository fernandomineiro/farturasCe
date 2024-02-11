package moduloFaturamento.dto.leitura;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class LeituraParaAtualizarDTO {

    @NotNull
	private Integer matricula;

    @NotNull
	private Integer refFatura;
    
    private Integer agente;

    private Integer leitura;

    private Short idOcorrencia;

    private Short idOcorrencia2;

    private Short idOcorrencia3;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataLeitura;

    @NotNull
    private Integer idCidade;

    @NotNull
    private Short ciclo;

    private List<LeituraAnexoDTO> anexos;

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public Integer getRefFatura() {
        return refFatura;
    }

    public void setRefFatura(Integer refFatura) {
        this.refFatura = refFatura;
    }

    public Integer getAgente() {
        return agente;
    }

    public void setAgente(Integer agente) {
        this.agente = agente;
    }

    public Integer getLeitura() {
        return leitura;
    }

    public void setLeitura(Integer leitura) {
        this.leitura = leitura;
    }

    public Short getIdOcorrencia() {
        return idOcorrencia;
    }

    public void setIdOcorrencia(Short idOcorrencia) {
        this.idOcorrencia = idOcorrencia;
    }

    public Short getIdOcorrencia2() {
        return idOcorrencia2;
    }

    public void setIdOcorrencia2(Short idOcorrencia2) {
        this.idOcorrencia2 = idOcorrencia2;
    }

    public Short getIdOcorrencia3() {
        return idOcorrencia3;
    }

    public void setIdOcorrencia3(Short idOcorrencia3) {
        this.idOcorrencia3 = idOcorrencia3;
    }

    public LocalDate getDataLeitura() {
        return dataLeitura;
    }

    public void setDataLeitura(LocalDate dataLeitura) {
        this.dataLeitura = dataLeitura;
    }

    public Integer getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(Integer idCidade) {
        this.idCidade = idCidade;
    }

    public Short getCiclo() {
        return ciclo;
    }

    public void setCiclo(Short ciclo) {
        this.ciclo = ciclo;
    }

    public List<LeituraAnexoDTO> getAnexos() {
        return anexos;
    }

    public void setAnexos(List<LeituraAnexoDTO> anexos) {
        this.anexos = anexos;
    }

}
