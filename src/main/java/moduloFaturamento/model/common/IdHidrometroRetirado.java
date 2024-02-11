package moduloFaturamento.model.common;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class IdHidrometroRetirado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "CD_HIDROMETRO")
    private String codHidrometro;

    @Column(name = "MATRICULA_IMOVEL")
    private Integer matriculaImovel;

    @Column(name = "DT_RETIRADA")
    private Integer dataRetirada;

    public String getCodHidrometro() {
        return codHidrometro;
    }

    public void setCodHidrometro(String codHidrometro) {
        this.codHidrometro = codHidrometro;
    }

    public Integer getMatriculaImovel() {
        return matriculaImovel;
    }

    public void setMatriculaImovel(Integer matriculaImovel) {
        this.matriculaImovel = matriculaImovel;
    }

    public Integer getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(Integer dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

}
