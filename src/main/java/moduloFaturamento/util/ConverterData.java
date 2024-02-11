package moduloFaturamento.util;

import java.time.LocalDate;

public final class ConverterData {

	public static LocalDate stringEmLocalDateFormatoBR(String date) {

		return integerEmLocalDateFormatoBR(removerSeparadores(date));
	}

	public static LocalDate stringEmLocalDateDataFormatoBR(String date) {

		return integerEmLocalDateDataFormatoBR(removerSeparadores(date));
	}

	public static LocalDate stringEmLocalDateReferenciaFormatoBR(String date) {

		return integerEmLocalDateReferenciaFormatoBR(removerSeparadores(date));
	}

	public static LocalDate integerEmLocalDateFormatoBR(Integer date) {

		LocalDate result = null;

		result = integerEmLocalDateDataFormatoBR(date);

		if (result == null) {

			result = integerEmLocalDateReferenciaFormatoBR(date);
		}

		return result;
	}

	public static LocalDate integerEmLocalDateDataFormatoBR(Integer date) {

		try {
			String dateStr = date.toString();
			int dia = Integer.parseInt(dateStr.substring(6, 8));
			int mes = Integer.parseInt(dateStr.substring(4, 6));
			int ano = Integer.parseInt(dateStr.substring(0, 4));

			return LocalDate.of(ano, mes, dia);
		} catch (Exception e) {

			return null;
		}
	}

	public static LocalDate integerEmLocalDateReferenciaFormatoBR(Integer date) {

		try {

			int ano = date % 10000;
			int mes = date / 10000;

			return LocalDate.of(ano, mes, 1);
		} catch (Exception e) {

			return null;
		}
	}

	public static LocalDate integerEmLocalDateFormatoDB(Integer date) {

		LocalDate result = null;

		result = integerEmLocalDateDataFormatoDB(date);

		if (result == null) {

			result = integerEmLocalDateReferenciaFormatoDB(date);
		}

		return result;
	}

	public static LocalDate integerEmLocalDateDataFormatoDB(Integer date) {

		if (date == null || date.toString().length() != 8) {

			return null;
		}

		try {

			int ano = date / 10000;
			int mes = (date % 10000) / 100;
			int dia = date % 100;

			return LocalDate.of(ano, mes, dia);
		} catch (Exception e) {

			return null;
		}
	}

	public static LocalDate integerEmLocalDateReferenciaFormatoDB(Integer date) {

		if (date == null || date.toString().length() != 6) {

			return null;
		}

		try {

			int ano = date / 100;
			int mes = date % 100;

			return LocalDate.of(ano, mes, 1);
		} catch (Exception e) {

			return null;
		}
	}

	public static Integer localDateEmIntegerDataFormatoDB(LocalDate date) {

		return date.getYear() * 10000 + (date.getMonthValue() * 100) + date.getDayOfMonth();
	}

	public static Integer localDateEmIntegerReferenciaFormatoDB(LocalDate date) {

		return date.getYear() * 100 + date.getMonthValue();
	}

	public static Integer localDateEmIntegerDataFormatoDBNullPossivel(LocalDate date) {
		
		return date == null ? null : localDateEmIntegerDataFormatoDB(date);
		
	}

	public static Integer localDateEmIntegerReferenciaFormatoDBNullPossivel(LocalDate date) {

		return date == null ? null : localDateEmIntegerReferenciaFormatoDB(date);
	}

	private static Integer removerSeparadores(String date) {

		try {

			return Integer.valueOf(date.replace("-", "").replace("/", ""));
		} catch (Exception e) {

			return null;
		}
	}

	public static String localDateParaReferenciaApresentacao(LocalDate localDate) {
		
		return localDate.getMonthValue() + "/" + localDate.getYear();
	}

}
