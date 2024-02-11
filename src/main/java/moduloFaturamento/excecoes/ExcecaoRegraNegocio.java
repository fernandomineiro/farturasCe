package moduloFaturamento.excecoes;
 
public class ExcecaoRegraNegocio  extends RuntimeException{

	private static final long serialVersionUID = 7642853318090492786L;

	public ExcecaoRegraNegocio(String mensagem){
		super(mensagem);
	}
}
