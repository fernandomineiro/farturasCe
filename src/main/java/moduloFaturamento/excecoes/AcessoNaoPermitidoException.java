package moduloFaturamento.excecoes;

public class AcessoNaoPermitidoException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public AcessoNaoPermitidoException(String mensagem){
		super(mensagem);
	}
}
