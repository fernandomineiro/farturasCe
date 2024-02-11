package moduloFaturamento.anexos.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FAT_LEITURA_ANEXO")
public class LeituraAnexo {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
    private Long id;

    @Column(name = "DATA_HORA_INCLUSAO")
    private LocalDateTime dataHoraInclusao;

    @Column(name = "FAT_LEITURA_ANEXO_ID")
    private Long leituraId;

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

    @Column(name = "ACESSO_RESTRITO")
    private String acessoRestrito;

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

    public Long getLeituraId() {
        return leituraId;
    }

    public void setLeituraId(Long leituraId) {
        this.leituraId = leituraId;
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

    public String getAcessoRestrito() {
        return acessoRestrito;
    }

    public void setAcessoRestrito(String acessoRestrito) {
        this.acessoRestrito = acessoRestrito;
    }

    
    
}
