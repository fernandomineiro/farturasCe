package moduloFaturamento.dto;

public class GenericoWrapperDTO<T> {

	protected T dados;
	protected long totalRegistro;


	public T getDados() {
		return dados;
	}

	public void setDados(T dados) {
		this.dados = dados;
	}

	public long getTotalRegistro() {
		return totalRegistro;
	}

	public void setTotalRegistro(long totalRegistro) {
		this.totalRegistro = totalRegistro;
	}

}
