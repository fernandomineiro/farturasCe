
package moduloFaturamento.util;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Formulas {

	private String padLeft(String text, String padChar, Integer size) {
		String format = "%" + padChar + size + "d";
		return String.format(format, Integer.parseInt(text));
	}

	public Integer calculaDV(Integer numero) {
		String SDICODDV;
		Integer SDINUMERO;
		Integer SDIRESULTADO;
		Integer SDIDV;
		Integer SDISOMA;
		Integer SDIMULT;
		Integer SDIRESTO;
		Integer SDII;
		Integer DVI;

		SDICODDV = padLeft(String.valueOf(numero), "0", 14);
		SDII = 13;
		SDIMULT = 2;
		SDISOMA = 0;

		while (true) {
			SDINUMERO = Integer.valueOf(SDICODDV.substring(SDII, SDII + 1));
			SDIRESULTADO = SDINUMERO * SDIMULT;
			SDISOMA = SDISOMA + SDIRESULTADO;
			SDII = SDII - 1;

			if (SDII == 0) {
				break;
			}

			SDIMULT = SDIMULT + 1;

			if (SDIMULT == 10) {
				SDIMULT = 2;
			}
		}

		SDIRESTO = SDISOMA % 11;
		SDIDV = SDIRESTO - 11;

		if (SDIDV < 0) {
			SDIDV = SDIDV * -1;
		}

		if (SDIDV > 9) {
			SDIDV = 0;
		}

		DVI = SDIDV;

		return DVI;
	}

	public String preencherZero(int tamanho, int tamanhoSemZero) {
		int numerosZeros = tamanho - tamanhoSemZero;
		String zeros = "";
		int count = 1;

		while (count <= numerosZeros) {
			zeros = zeros + "0";
			count++;
		}
		return zeros;

	}
	
	public String preencherVazio(int tamanho, int tamanhoSemZero) {
		int numerosZeros = tamanho - tamanhoSemZero;
		String zeros = "";
		int count = 1;

		while (count <= numerosZeros) {
			zeros = zeros + " ";
			count++;
		}
		return zeros;

	}
	
	public String adaptarTexto( String texto, int maximo) {
	      if(texto.length()>maximo)	
		return texto.substring(0,maximo);
	      else
	    return  texto+""+this.preencherVazio(maximo, texto.length());  

	   
	}
	
	
	public String complementarTexto(String texto,int posicaoInicial, int posicaoFinal) {
		if(texto.length()>posicaoInicial) {
			if(texto.length()>=posicaoFinal)
			return 	texto.substring(posicaoInicial,posicaoInicial);
			else
			return 	texto.substring(posicaoInicial, texto.length());
		}
		return "";
	}
	
    
	public String complementarTextoLinha(String texto,int posicaoInicial,  int maximoLinha) {
	     if(texto.length()<posicaoInicial)
	    	 return "";
	     else if(texto.length()>posicaoInicial&&texto.length()<maximoLinha)
	    	 return texto.trim().substring(posicaoInicial,texto.length());
	     else
		return texto.trim().substring(posicaoInicial, maximoLinha);
		
	}
	
	
	
	public  boolean isValidEmailAddressRegex(String email) {
        boolean isEmailIdValid = false;
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                isEmailIdValid = true;
            }
        
        return isEmailIdValid;
    }

	public boolean compararCampos(Integer campo1,Integer campo2) {	
		campo1=campo1==null?0:campo1;
		campo2=campo2==null?0:campo2;
		return campo1.intValue()==campo2.intValue();	
	}
	
	public boolean compararCampos(Short campo1,Short campo2) {	
		campo1=campo1==null?0:campo1;
		campo2=campo2==null?0:campo2;
		return campo1.intValue()==campo2.intValue();	
	}
	
	
	public boolean compararCampos(Long campo1,Long campo2) {	
		campo1=campo1==null?0:campo1;
		campo2=campo2==null?0:campo2;
		return campo1.longValue()==campo2.longValue();	
	}

	public boolean compararCampos(String campo1,String campo2) {
		campo1=campo1==null?"":campo1.trim();
		campo2=campo2==null?"":campo2.trim();
		return campo1.equalsIgnoreCase(campo2);	
	}
	
	
}

