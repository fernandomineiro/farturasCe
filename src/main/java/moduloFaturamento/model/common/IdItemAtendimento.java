package moduloFaturamento.model.common;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class IdItemAtendimento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "CD_ATENDIMENTO")
	private Integer codAtendimento;

	@Column(name = "REF_ATENDIMENTO")
	private Integer refAtendimento;

	@Column(name = "SEQ_ATEND")
	private Short seqAtendimento;

	public IdItemAtendimento() {

	}

	public IdItemAtendimento(Integer codAtendimento, Integer refAtendimento, Short seqAtendimento) {
		super();
		this.codAtendimento = codAtendimento;
		this.refAtendimento = refAtendimento;
		this.seqAtendimento = seqAtendimento;
	}

	public Integer getCodAtendimento() {
		return codAtendimento;
	}

	public void setCodAtendimento(Integer codAtendimento) {
		this.codAtendimento = codAtendimento;
	}

	public Integer getRefAtendimento() {
		return refAtendimento;
	}

	public void setRefAtendimento(Integer refAtendimento) {
		this.refAtendimento = refAtendimento;
	}

	public Short getSeqAtendimento() {
		return seqAtendimento;
	}

	public void setSeqAtendimento(Short seqAtendimento) {
		this.seqAtendimento = seqAtendimento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codAtendimento == null) ? 0 : codAtendimento.hashCode());
		result = prime * result + ((refAtendimento == null) ? 0 : refAtendimento.hashCode());
		result = prime * result + ((seqAtendimento == null) ? 0 : seqAtendimento.hashCode());
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
		IdItemAtendimento other = (IdItemAtendimento) obj;
		if (codAtendimento == null) {
			if (other.codAtendimento != null)
				return false;
		} else if (!codAtendimento.equals(other.codAtendimento))
			return false;
		if (refAtendimento == null) {
			if (other.refAtendimento != null)
				return false;
		} else if (!refAtendimento.equals(other.refAtendimento))
			return false;
		if (seqAtendimento == null) {
			return other.seqAtendimento == null;
		} else return seqAtendimento.equals(other.seqAtendimento);
	}

	@Override
	public String toString() {
		return "IdItemAtendimento{" +
				"codAtendimento=" + codAtendimento +
				", refAtendimento=" + refAtendimento +
				", seqAtendimento=" + seqAtendimento +
				'}';
	}
}
