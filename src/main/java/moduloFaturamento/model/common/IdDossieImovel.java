package moduloFaturamento.model.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class IdDossieImovel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "SEQ_ANOTACAO")
	private Short seqAnotacao;
	@Column(name = "MATRICULA_IMOVEL")
	private Integer matriculaImovel;
	@Column(name = "DT_ANOTACAO")
	private Integer dtAnotacao;

	public IdDossieImovel() {

	}

	public IdDossieImovel(Short seqAnotacao, Integer matriculaImovel, Integer dtAnotacao) {

		this.seqAnotacao = seqAnotacao;
		this.matriculaImovel = matriculaImovel;
		this.dtAnotacao = dtAnotacao;
	}

	public Short getSeqAnotacao() {

		return seqAnotacao;
	}

	public void setSeqAnotacao(Short seqAnotacao) {

		this.seqAnotacao = seqAnotacao;
	}

	public Integer getMatriculaImovel() {

		return matriculaImovel;
	}

	public void setMatriculaImovel(Integer matriculaImovel) {

		this.matriculaImovel = matriculaImovel;
	}

	public Integer getDtAnotacao() {

		return dtAnotacao;
	}

	public void setDtAnotacao(Integer dtAnotacao) {

		this.dtAnotacao = dtAnotacao;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((dtAnotacao == null) ? 0 : dtAnotacao.hashCode());
		result = prime * result + ((matriculaImovel == null) ? 0 : matriculaImovel.hashCode());
		result = prime * result + ((seqAnotacao == null) ? 0 : seqAnotacao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IdDossieImovel other = (IdDossieImovel) obj;
		if (dtAnotacao == null) {

			if (other.dtAnotacao != null)
				return false;
		} else if (!dtAnotacao.equals(other.dtAnotacao))
			return false;
		if (matriculaImovel == null) {

			if (other.matriculaImovel != null)
				return false;
		} else if (!matriculaImovel.equals(other.matriculaImovel))
			return false;
		if (seqAnotacao == null) {

			if (other.seqAnotacao != null)
				return false;
		} else if (!seqAnotacao.equals(other.seqAnotacao))
			return false;
		return true;
	}

    public void setSequeciaAnotacao(Short buscarSequenciaPorDataEMatricula) {
    }

}