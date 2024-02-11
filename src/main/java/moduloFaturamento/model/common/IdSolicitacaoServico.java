package moduloFaturamento.model.common;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class IdSolicitacaoServico implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "CD_ATENDIMENTO")
	private Integer codAtendimento;

	@Column(name = "REF_ATENDIMENTO")
	private Integer refAtendimento;

	@Column(name = "SEQ_SS")
	private Short seqSS;

	public IdSolicitacaoServico() {

	}

	public IdSolicitacaoServico(Integer codAtendimento, Integer refAtendimento, Short seqSS) {
		super();
		this.codAtendimento = codAtendimento;
		this.refAtendimento = refAtendimento;
		this.seqSS = seqSS;
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

	public Short getSeqSS() {
		return seqSS;
	}

	public void setSeqSS(Short seqSS) {
		this.seqSS = seqSS;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codAtendimento == null) ? 0 : codAtendimento.hashCode());
		result = prime * result + ((refAtendimento == null) ? 0 : refAtendimento.hashCode());
		result = prime * result + ((seqSS == null) ? 0 : seqSS.hashCode());
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
		IdSolicitacaoServico other = (IdSolicitacaoServico) obj;
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
		if (seqSS == null) {
			return other.seqSS == null;
		} else return seqSS.equals(other.seqSS);
	}

	@Override
	public String toString() {
		return "IdSolicitacaoServico{" +
				"codAtendimento=" + codAtendimento +
				", refAtendimento=" + refAtendimento +
				", seqSS=" + seqSS +
				'}';
	}
}
