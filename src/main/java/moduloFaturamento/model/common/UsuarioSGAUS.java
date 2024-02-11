package moduloFaturamento.model.common;

import javax.persistence.*;

@Entity
@Table(name="SGAUS")
public class UsuarioSGAUS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_USUARIO",length=15)
    private String idUsuario;
    @Column(name="ATENDENTE")
    private Integer atendente;
    @Column(name="CD_LOTACAO")
    private Short cdLotacao;
    @Column(name="CD_PERFIL")
    private Short cdPerfil;
    @Column(name="CRED_CIDADE",length=1)
    private char credCidade;
    @Column(name="DT_BLOQUEIO")
    private Integer dtBloqueio;
    @Column(name="DT_CREDENCIAMENTO")
    private Integer dtCredenciamento;
    @Column(name="DT_INCLUSAO")
    private Integer dtInclusao;
    @Column(name="DT_LOGIN")
    private Integer dtLogin;
    @Column(name="DT_SENHA")
    private Integer dtSenha;
    @Column(name="DT_ULTIMO_ACESSO")
    private Integer dtUltimoAcesso;
    @Column(name="MAINT",length=1)
    private char maint;
    @Column(name="MOT_BLOQUEIO")
    private Integer motBloqueio;
    @Column(name="NOME",length=30)
    private String nome;
    @Column(name="NV_HIERARQUICO")
    private Integer nvHierarquico;
    @Column(name="SENHA",length=20)
    private String senha;
    @Column(name="TP_USUARIO")
    private Integer tpUsuario;
    @Column(name="GUICHE_ATENDIMENTO")
    private Integer guicheAtendimento;
    @Column(name="MATRICULA_FUNC",length=12)
    private String matriculaFunc;
    @Column(name="ID_USUARIO_AD",length=250)
    private String idUsuarioAd;
    @Column(name="ID_RESP_INCLUSAO",length=15)
    private String idRespInclusao;

    public Integer getAtendente() {
        return atendente;
    }
    public void setAtendente(Integer atendente) {
        this.atendente = atendente;
    }
    public Short getCdLotacao() {
        return cdLotacao;
    }
    public void setCdLotacao(Short cdLotacao) {
        this.cdLotacao = cdLotacao;
    }
    public Short getCdPerfil() {
        return cdPerfil;
    }
    public void setCdPerfil(Short cdPerfil) {
        this.cdPerfil = cdPerfil;
    }
    public char getCredCidade() {
        return credCidade;
    }
    public void setCredCidade(char credCidade) {
        this.credCidade = credCidade;
    }
    public Integer getDtBloqueio() {
        return dtBloqueio;
    }
    public void setDtBloqueio(Integer dtBloqueio) {
        this.dtBloqueio = dtBloqueio;
    }
    public Integer getDtCredenciamento() {
        return dtCredenciamento;
    }
    public void setDtCredenciamento(Integer dtCredenciamento) {
        this.dtCredenciamento = dtCredenciamento;
    }
    public Integer getDtInclusao() {
        return dtInclusao;
    }
    public void setDtInclusao(Integer dtInclusao) {
        this.dtInclusao = dtInclusao;
    }
    public Integer getDtLogin() {
        return dtLogin;
    }
    public void setDtLogin(Integer dtLogin) {
        this.dtLogin = dtLogin;
    }
    public Integer getDtSenha() {
        return dtSenha;
    }
    public void setDtSenha(Integer dtSenha) {
        this.dtSenha = dtSenha;
    }
    public Integer getDtUltimoAcesso() {
        return dtUltimoAcesso;
    }
    public void setDtUltimoAcesso(Integer dtUltimoAcesso) {
        this.dtUltimoAcesso = dtUltimoAcesso;
    }
    public String getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
    public char getMaint() {
        return maint;
    }
    public void setMaint(char maint) {
        this.maint = maint;
    }
    public Integer getMotBloqueio() {
        return motBloqueio;
    }
    public void setMotBloqueio(Integer motBloqueio) {
        this.motBloqueio = motBloqueio;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Integer getNvHierarquico() {
        return nvHierarquico;
    }
    public void setNvHierarquico(Integer nvHierarquico) {
        this.nvHierarquico = nvHierarquico;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public Integer getTpUsuario() {
        return tpUsuario;
    }
    public void setTpUsuario(Integer tpUsuario) {
        this.tpUsuario = tpUsuario;
    }
    public Integer getGuicheAtendimento() {
        return guicheAtendimento;
    }
    public void setGuicheAtendimento(Integer guicheAtendimento) {
        this.guicheAtendimento = guicheAtendimento;
    }
    public String getMatriculaFunc() {
        return matriculaFunc;
    }
    public void setMatriculaFunc(String matriculaFunc) {
        this.matriculaFunc = matriculaFunc;
    }
    public String getIdUsuarioAd() {
        return idUsuarioAd;
    }
    public void setIdUsuarioAd(String idUsuarioAd) {
        this.idUsuarioAd = idUsuarioAd;
    }
    public String getIdRespInclusao() {
        return idRespInclusao;
    }
    public void setIdRespInclusao(String idRespInclusao) {
        this.idRespInclusao = idRespInclusao;
    }

}

