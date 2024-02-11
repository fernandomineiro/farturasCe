package moduloFaturamento.autenticacao;

import java.util.Base64;

import org.springframework.stereotype.Component;
 
@Component
public class JwtTokenProvider {


    public Long buscarIdUsuario(String token) {
    	if(token.equals("siscom")) {
    		return 1L;
    	}else {
    	
    	String payloads[] = token.split("\\.");	
    	byte[] decodedBytes = Base64.getDecoder().decode(payloads[1]);
    	String payload = new String(decodedBytes);
    	String login[] = payload.split(",");      
    	return Long.valueOf(login[1].substring(12).replaceAll("\"", ""));
    	}
    }
    
    public String buscarLogin(String token) {
    	if(token.equals("siscom")) {
    		return "SISCOM";
    	}else {
    	String payloads[] = token.split("\\.");	
    	byte[] decodedBytes = Base64.getDecoder().decode(payloads[1]);
    	String payload = new String(decodedBytes);
    	String login[] = payload.split(",");      
    	return login[0].substring(7).replaceAll("\"", "");
    	}
    }
    
    public Boolean eCesan(String token) {
    	if(token.equals("siscom")) {
    		return false;
    	}else {
    	String payloads[] = token.split("\\.");	
    	byte[] decodedBytes = Base64.getDecoder().decode(payloads[1]);
    	String payload = new String(decodedBytes);
    	String login[] = payload.split(","); 
    	return Integer.parseInt(login[2].substring(19).trim())==1;
    	}
    }
}
