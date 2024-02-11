package moduloFaturamento.comparator;

import java.util.Comparator;

public abstract class AbstractDTOComparator<T> implements Comparator<T> {

	public static final String DESC = "DESC";

	private String campo;
	private String ordem;

	public AbstractDTOComparator() {

	}

	public String getCampo() {

		return campo;
	}

	public void setCampo(String campo) {

		this.campo = campo;
	}

	public String getOrdem() {

		return ordem;
	}

	public void setOrdem(String ordem) {

		this.ordem = ordem;
	}

	/**
	 * É requerido que os parâmetros sejam passados em ordem DIRETA. Object 1, Object 2
	 */
	protected <C extends Comparable<C>> int compareSafeForNull(C one, C two) {

		boolean direct = !DESC.equalsIgnoreCase(getOrdem());

		C first = direct ? one : two;
		C second = direct ? two : one;

		int res = 0;

		if (first != null && second != null) {

			res = first.compareTo(second);
		}

		if (first == null && second == null) {

			res = 0;
		}

		if (first == null) {

			res = -1;
		}
		if (second == null) {

			res = 1;
		}

		return res;
	}
}
