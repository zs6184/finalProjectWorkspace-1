package tw.finalspring.model;

import org.springframework.stereotype.Component;

@Component
public class RecaptchaObject {
	private String secret;
	private String url;

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
