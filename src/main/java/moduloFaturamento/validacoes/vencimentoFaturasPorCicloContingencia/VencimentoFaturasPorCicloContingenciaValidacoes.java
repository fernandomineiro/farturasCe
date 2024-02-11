package moduloFaturamento.validacoes.vencimentoFaturasPorCicloContingencia;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import moduloFaturamento.excecoes.MsgGenericaPersonalizadaException;
import moduloFaturamento.util.ConverterData;

public abstract class VencimentoFaturasPorCicloContingenciaValidacoes {

	protected void gerarExcecaoLimiteNovoVencimentoAnteriorDataAtual(String novoVencimento) {
		
		LocalDate vencimento = ConverterData.stringEmLocalDateFormatoBR(novoVencimento);
		LocalDate now = LocalDate.now();

		if (ChronoUnit.DAYS.between(now, vencimento) <= 0) {

			throw new MsgGenericaPersonalizadaException(String.format("Data do novo vencimento %s anterior à data atual.", novoVencimento));
		}

	}
	
	protected void gerarExcecaoNovoVencimentoAnteriorAReferencia(Integer referencia, String novoVencimento) {

		LocalDate vencimento = ConverterData.stringEmLocalDateFormatoBR(novoVencimento);
		LocalDate ref = ConverterData.integerEmLocalDateReferenciaFormatoDB(referencia);

		if (ChronoUnit.MONTHS.between(ref, vencimento) < 0) {

			throw new MsgGenericaPersonalizadaException(String.format("Data do novo vencimento %s é anterior à referência informada.", novoVencimento));
		}
		
	}

	protected void gerarExcecaoLimiteMaximoNovoVencimento(String novoVencimento) {
		
		LocalDate vencimento = ConverterData.stringEmLocalDateFormatoBR(novoVencimento);
		LocalDate now = LocalDate.now();

		if (ChronoUnit.DAYS.between(now, vencimento) > 60) {

			throw new MsgGenericaPersonalizadaException(String.format("Data do novo vencimento %s superior a 60 dias da data atual.", novoVencimento));
		}

	}

	protected void gerarExcecaoFasesFaturaAceitas(List<Short> fases) {

		if(fases == null) {
			throw new MsgGenericaPersonalizadaException(String.format("Pelo menos uma fase tem que ser informada para esta operação"));
		}
		
		for (Short fase : fases) {

			if (!(fase == 4 || fase == 5)) {

				throw new MsgGenericaPersonalizadaException(String.format("Fase %d não permitida para esta operação", fase));
			}
		}
	}
}
