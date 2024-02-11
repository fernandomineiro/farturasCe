package moduloFaturamento.comparator;

import moduloFaturamento.dto.notificaoFatura.NotificacaoFaturaCriticaGridRespostaDTO;

public class NotificacaoFaturaCriticaGridComparator extends AbstractDTOComparator<NotificacaoFaturaCriticaGridRespostaDTO> {

	@Override
	public int compare(NotificacaoFaturaCriticaGridRespostaDTO o1, NotificacaoFaturaCriticaGridRespostaDTO o2) {

		switch (getCampo()) {

		case "cdCidade":
			if ("DESC".equalsIgnoreCase(getOrdem())) {

				if (o2.getCdCidade() != null && o1.getCdCidade() != null) {

					return o2.getCdCidade().compareTo(o1.getCdCidade());
				}
				if (o2.getCdCidade() == null && o1.getCdCidade() != null) {

					return -1;
				}
				if (o2.getCdCidade() != null && o1.getCdCidade() == null) {

					return 1;
				}
				return 0;
			} else {

				if (o1.getCdCidade() != null && o2.getCdCidade() != null) {

					return o1.getCdCidade().compareTo(o2.getCdCidade());
				}
				if (o1.getCdCidade() == null && o2.getCdCidade() != null) {

					return -1;
				}
				if (o1.getCdCidade() != null && o2.getCdCidade() == null) {

					return 1;
				}
				return 0;
			}

		case "dcCidade":
			if ("DESC".equalsIgnoreCase(getOrdem())) {

				if (o2.getDcCidade() != null && o1.getDcCidade() != null) {

					return o2.getDcCidade().compareTo(o1.getDcCidade());
				}
				if (o2.getDcCidade() == null && o1.getDcCidade() != null) {

					return -1;
				}
				if (o2.getDcCidade() != null && o1.getDcCidade() == null) {

					return 1;
				}
				return 0;
			} else {

				if (o1.getDcCidade() != null && o2.getDcCidade() != null) {

					return o1.getDcCidade().compareTo(o2.getDcCidade());
				}
				if (o1.getDcCidade() == null && o2.getDcCidade() != null) {

					return -1;
				}
				if (o1.getDcCidade() != null && o2.getDcCidade() == null) {

					return 1;
				}
				return 0;
			}
		case "ciclo":
			if ("DESC".equalsIgnoreCase(getOrdem())) {

				if (o2.getCiclo() != null && o1.getCiclo() != null) {

					return o2.getCiclo().compareTo(o1.getCiclo());
				}
				if (o2.getCiclo() == null && o1.getCiclo() != null) {

					return -1;
				}
				if (o2.getCiclo() != null && o1.getCiclo() == null) {

					return 1;
				}
				return 0;
			} else {

				if (o1.getCiclo() != null && o2.getCiclo() != null) {

					return o1.getCiclo().compareTo(o2.getCiclo());
				}
				if (o1.getCiclo() == null && o2.getCiclo() != null) {

					return -1;
				}
				if (o1.getCiclo() != null && o2.getCiclo() == null) {

					return 1;
				}
				return 0;
			}

		case "refCronograma":
			if ("DESC".equalsIgnoreCase(getOrdem())) {

				if (o2.getRefCronograma() != null && o1.getRefCronograma() != null) {

					return o2.getRefCronograma().compareTo(o1.getRefCronograma());
				}
				if (o2.getRefCronograma() == null && o1.getRefCronograma() != null) {

					return -1;
				}
				if (o2.getRefCronograma() != null && o1.getRefCronograma() == null) {

					return 1;
				}
				return 0;
			} else {

				if (o1.getRefCronograma() != null && o2.getRefCronograma() != null) {

					return o1.getRefCronograma().compareTo(o2.getRefCronograma());
				}
				if (o1.getRefCronograma() == null && o2.getRefCronograma() != null) {

					return -1;
				}
				if (o1.getRefCronograma() != null && o2.getRefCronograma() == null) {

					return 1;
				}
				return 0;
			}
		case "matricula":
			if ("DESC".equalsIgnoreCase(getOrdem())) {

				if (o2.getMatricula() != null && o1.getMatricula() != null) {

					return o2.getMatricula().compareTo(o1.getMatricula());
				}
				if (o2.getMatricula() == null && o1.getMatricula() != null) {

					return -1;
				}
				if (o2.getMatricula() != null && o1.getMatricula() == null) {

					return 1;
				}
				return 0;
			} else {

				if (o1.getMatricula() != null && o2.getMatricula() != null) {

					return o1.getMatricula().compareTo(o2.getMatricula());
				}
				if (o1.getMatricula() == null && o2.getMatricula() != null) {

					return -1;
				}
				if (o1.getMatricula() != null && o2.getMatricula() == null) {

					return 1;
				}
				return 0;
			}
		case "matriculaDv":
			if ("DESC".equalsIgnoreCase(getOrdem())) {

				if (o2.getMatriculaDv() != null && o1.getMatriculaDv() != null) {

					return o2.getMatriculaDv().compareTo(o1.getMatriculaDv());
				}
				if (o2.getMatriculaDv() == null && o1.getMatriculaDv() != null) {

					return -1;
				}
				if (o2.getMatriculaDv() != null && o1.getMatriculaDv() == null) {

					return 1;
				}
				return 0;
			} else {

				if (o1.getMatriculaDv() != null && o2.getMatriculaDv() != null) {

					return o1.getMatriculaDv().compareTo(o2.getMatriculaDv());
				}
				if (o1.getMatriculaDv() == null && o2.getMatriculaDv() != null) {

					return -1;
				}
				if (o1.getMatriculaDv() != null && o2.getMatriculaDv() == null) {

					return 1;
				}
				return 0;
			}
		case "cicloFechado":
			if ("DESC".equalsIgnoreCase(getOrdem())) {

				if (o2.getCicloFechado() != null && o1.getCicloFechado() != null) {

					return o2.getCicloFechado().compareTo(o1.getCicloFechado());
				}
				if (o2.getCicloFechado() == null && o1.getCicloFechado() != null) {

					return -1;
				}
				if (o2.getCicloFechado() != null && o1.getCicloFechado() == null) {

					return 1;
				}
				return 0;
			} else {

				if (o1.getCicloFechado() != null && o2.getCicloFechado() != null) {

					return o1.getCicloFechado().compareTo(o2.getCicloFechado());
				}
				if (o1.getCicloFechado() == null && o2.getCicloFechado() != null) {

					return -1;
				}
				if (o1.getCicloFechado() != null && o2.getCicloFechado() == null) {

					return 1;
				}
				return 0;
			}
		case "existeOutraNotificacao":
			if ("DESC".equalsIgnoreCase(getOrdem())) {

				if (o2.getExisteOutraNotificacao() != null && o1.getExisteOutraNotificacao() != null) {

					return o2.getExisteOutraNotificacao().compareTo(o1.getExisteOutraNotificacao());
				}
				if (o2.getExisteOutraNotificacao() == null && o1.getExisteOutraNotificacao() != null) {

					return -1;
				}
				if (o2.getExisteOutraNotificacao() != null && o1.getExisteOutraNotificacao() == null) {

					return 1;
				}
				return 0;
			} else {

				if (o1.getExisteOutraNotificacao() != null && o2.getExisteOutraNotificacao() != null) {

					return o1.getExisteOutraNotificacao().compareTo(o2.getExisteOutraNotificacao());
				}
				if (o1.getExisteOutraNotificacao() == null && o2.getExisteOutraNotificacao() != null) {

					return -1;
				}
				if (o1.getExisteOutraNotificacao() != null && o2.getExisteOutraNotificacao() == null) {

					return 1;
				}
				return 0;
			}
			default: 
				return 0;
		}

	}

}
