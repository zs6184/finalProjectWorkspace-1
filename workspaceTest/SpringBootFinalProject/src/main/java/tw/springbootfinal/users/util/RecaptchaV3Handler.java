package tw.springbootfinal.users.util;

public class RecaptchaV3Handler {
	private String recaptchaSecret = "6Lcnq94cAAAAAEhdIjBGvTDx9qFyH2hSU8_4BnIJ";
	private String recaptchaServerURL = "https://www.google.com/recaptcha/api/siteverify";
	
	public void verify(String recaptchaFormResponse) {
		System.out.println("ReCaptchaV3 Handler called.");
		System.out.println("g-recaptcha-response: "+recaptchaFormResponse);
	}
}
