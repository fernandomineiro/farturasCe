package moduloFaturamento.model.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "CDACL")
public class Cliente implements Serializable {
    @Id
    @Column(name = "CD_CLIENTE")
    private Integer cdCliente;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "CLIENTE_ESPECIAL")
    private String clienteEspecial;

    @Column(name = "TP_CLIENTE")
    private Short tipoCliente;

    @Column(name = "DV")
    private Short dvCliente;

    @Column(name = "CPF_CNPJ")
    private String cpfCnpj;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "RETER_IMPOSTOS")
    private String reterImpostos;

    public String getReterImpostos() {
        return reterImpostos;
    }

    public void setReterImpostos(String reterImpostos) {
        this.reterImpostos = reterImpostos;
    }

    public Integer getCdCliente() {
        return cdCliente;
    }

    public void setCdCliente(Integer cdCliente) {
        this.cdCliente = cdCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Short getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(Short tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public Short getDvCliente() {
        return dvCliente;
    }

    public void setDvCliente(Short dvCliente) {
        this.dvCliente = dvCliente;
    }

    public String getClienteEspecial() {
        return clienteEspecial;
    }

    public void setClienteEspecial(String clienteEspecial) {
        this.clienteEspecial = clienteEspecial;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
