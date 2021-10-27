package tw.springbootfinal.users.model;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class RecaptchaService {
	
	public Recaptcha ReCAPTCHA(String token) {
		System.out.println("前端回傳的token:"+token);
		//密鑰
		String secretKey = "6Lcnq94cAAAAAEhdIjBGvTDx9qFyH2hSU8_4BnIJ";
		//發送http請求給google
		String url = "https://www.google.com/recaptcha/api/siteverify";//google驗證網址
		
		//設定標頭
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);//指的就是只接受表單類型的資料格式
	    
	    //LinkedMultiValueMap特殊功能:1個key可以儲存多組value，而且不會覆蓋
	    //可應用在formData(表單數據)，同個key有可能有多組value的時候
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("secret", secretKey);
		map.add("response", token);
		
		//建立http請求，放入map及標頭
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		
		//傳送http請求的物件
		RestTemplate restTemplate = new RestTemplate();
		
		//用post方法送請求物件給google驗證網址，並取得回應，放入bean物件
		Recaptcha response = restTemplate.postForObject(url, request, Recaptcha.class);
		
		System.out.println("response: "+response);

		return response;
	}
	
}
