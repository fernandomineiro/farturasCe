package moduloFaturamento.infra.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "config.recaptcha")
public class CaptchaConfig {

	private String proxyCesan;
	private String siteKey;
	private String secret;

	public String getProxyCesan() {
		return proxyCesan;
	}

	public void setProxyCesan(String proxyCesan) {
		this.proxyCesan = proxyCesan;
	}

	public String getSiteKey() {
		return siteKey;
	}

	public void setSiteKey(String siteKey) {
		this.siteKey = siteKey;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

}
