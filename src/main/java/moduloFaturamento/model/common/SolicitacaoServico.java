package moduloFaturamento.model.common;

import moduloFaturamento.util.customAnnotation.JsonCesanNotSerializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ATASS")
public class SolicitacaoServico {

    @EmbeddedId
    @JsonCesanNotSerializable
    private IdSolicitacaoServico idSolicitacaoServico;

    public SolicitacaoServico() {
    }

    public SolicitacaoServico(IdSolicitacaoServico idSolicitacaoServico) {
        this.idSolicitacaoServico = idSolicitacaoServico;
    }

    public IdSolicitacaoServico getIdSolicitacaoServico() {
        return idSolicitacaoServico;
    }

    public void setIdSolicitacaoServico(IdSolicitacaoServico idSolicitacaoServico) {
        this.idSolicitacaoServico = idSolicitacaoServico;
    }

    @Override
    public String toString() {
        return "SolicitacaoServico{" +
                "idSolicitacaoServico=" + idSolicitacaoServico +
                '}';
    }
}
