package moduloFaturamento.anexos.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "FAT_PARAMETRO_INCENTIVO_CLIENTE")
public class ParametroIncentivoClienteAnexo {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
    private Long id;

    @Column(name = "DATA_HORA_INCLUSAO")
    private LocalDateTime dataHoraInclusao;

    @Column(name = "ID_PARAMETRO_INCENTIVO_CLIENTE")
    private Integer idParametroIncentivoCliente;

    @Column(name = "ARQUIVO")
    private byte[] arquivo;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "USUARIO")
    private String usuario;

    @Column(name = "NOME_ARQUIVO")
    private String nomeArquivo;

    @Column(name = "TAMANHO_ARQUIVO")
    private int tamanhoArquivo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataHoraInclusao() {
        return dataHoraInclusao;
    }

    public void setDataHoraInclusao(LocalDateTime dataHoraInclusao) {
        this.dataHoraInclusao = dataHoraInclusao;
    }



    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public int getTamanhoArquivo() {
        return tamanhoArquivo;
    }

    public void setTamanhoArquivo(int tamanhoArquivo) {
        this.tamanhoArquivo = tamanhoArquivo;
    }


    public Integer getIdParametroIncentivoCliente() {
        return idParametroIncentivoCliente;
    }

    public void setIdParametroIncentivoCliente(Integer parametroIncentivoClienteId) {
        this.idParametroIncentivoCliente = parametroIncentivoClienteId;
    }
}
