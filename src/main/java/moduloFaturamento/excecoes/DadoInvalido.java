package moduloFaturamento.excecoes;
 
public class DadoInvalido extends Exception{

	private static final long serialVersionUID = 8329695227938273012L;

	public DadoInvalido(String mensagem){
		super(mensagem);
	}
}
