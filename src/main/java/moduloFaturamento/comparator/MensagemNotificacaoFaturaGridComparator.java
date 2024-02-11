package moduloFaturamento.comparator;

import moduloFaturamento.dto.mensgemNotificacaoFatura.MensagemNotificacaoFaturaGridRespostaDTO;

public class MensagemNotificacaoFaturaGridComparator extends AbstractDTOComparator<MensagemNotificacaoFaturaGridRespostaDTO> {

	@Override
	public int compare(MensagemNotificacaoFaturaGridRespostaDTO o1, MensagemNotificacaoFaturaGridRespostaDTO o2) {

		switch (getCampo()) {

		case "idNotificacao":
			if ("DESC".equalsIgnoreCase(getOrdem())) {

				if (o2.getIdNotificacao() != null && o1.getIdNotificacao() != null) {

					return o2.getIdNotificacao().compareTo(o1.getIdNotificacao());
				}
				if (o2.getIdNotificacao() == null && o1.getIdNotificacao() != null) {

					return -1;
				}
				if (o2.getIdNotificacao() != null && o1.getIdNotificacao() == null) {

					return 1;
				}
				return 0;
			} else {

				if (o1.getIdNotificacao() != null && o2.getIdNotificacao() != null) {

					return o1.getIdNotificacao().compareTo(o2.getIdNotificacao());
				}
				if (o1.getIdNotificacao() == null && o2.getIdNotificacao() != null) {

					return -1;
				}
				if (o1.getIdNotificacao() != null && o2.getIdNotificacao() == null) {

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

		case "mensagem":
			if ("DESC".equalsIgnoreCase(getOrdem())) {

				if (o2.getMensagem() != null && o1.getMensagem() != null) {

					return o2.getMensagem().compareTo(o1.getMensagem());
				}
				if (o2.getMensagem() == null && o1.getMensagem() != null) {

					return -1;
				}
				if (o2.getMensagem() != null && o1.getMensagem() == null) {

					return 1;
				}
				return 0;
			} else {

				if (o1.getMensagem() != null && o2.getMensagem() != null) {

					return o1.getMensagem().compareTo(o2.getMensagem());
				}
				if (o1.getMensagem() == null && o2.getMensagem() != null) {

					return -1;
				}
				if (o1.getMensagem() != null && o2.getMensagem() == null) {

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
		case "flagMensagemLonga":
			if ("DESC".equalsIgnoreCase(getOrdem())) {

				if (o2.getFlagMensagemLonga() != null && o1.getFlagMensagemLonga() != null) {

					return o2.getFlagMensagemLonga().compareTo(o1.getFlagMensagemLonga());
				}
				if (o2.getFlagMensagemLonga() == null && o1.getFlagMensagemLonga() != null) {

					return -1;
				}
				if (o2.getFlagMensagemLonga() != null && o1.getFlagMensagemLonga() == null) {

					return 1;
				}
				return 0;
			} else {

				if (o1.getFlagMensagemLonga() != null && o2.getFlagMensagemLonga() != null) {

					return o1.getFlagMensagemLonga().compareTo(o2.getFlagMensagemLonga());
				}
				if (o1.getFlagMensagemLonga() == null && o2.getFlagMensagemLonga() != null) {

					return -1;
				}
				if (o1.getFlagMensagemLonga() != null && o2.getFlagMensagemLonga() == null) {

					return 1;
				}
				return 0;
			}
		default:
			return 0;
		}

	}

}
