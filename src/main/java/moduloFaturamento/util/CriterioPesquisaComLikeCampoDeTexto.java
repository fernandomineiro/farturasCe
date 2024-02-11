package moduloFaturamento.util;

import java.util.HashMap;
import java.util.Map;

public class CriterioPesquisaComLikeCampoDeTexto {

	public static String incluirCriterio(String texto, Integer codigoCriterio) {
		 String criterio=retornaCriterio().get(codigoCriterio);
		 if(criterio.equals("Contendo"))
			return "%"+texto+"%";
		 if(criterio.equals("Iniciado"))
			 return texto+"%";
		 if(criterio.equals("Finalizado"))
			 return "%"+texto;
		 if(criterio.equals("Exato"))
			 return texto;
		return texto;
	}
	
	private static  Map<Integer,String> retornaCriterio(){
		Map<Integer,String> criterioPesquisa= new HashMap<>();
		criterioPesquisa.put(1, "Contendo");
		criterioPesquisa.put(2, "Iniciado");
		criterioPesquisa.put(3,"Finalizado");
		criterioPesquisa.put(4, "Exato");
		return criterioPesquisa;
	}
	
	

}
