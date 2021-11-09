package iii;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ecpay.payment.integration.AllInOne;

//本Servlet用以接收從用戶端(付款者)在付款成功後，以POST方法回傳的付款者的付款結果。
@WebServlet(urlPatterns="/ECPayServer3", loadOnStartup=3)
public class ECPayServer3 extends HttpServlet {
	private static final long serialVersionUID = 1L; 
	public static AllInOne all;
	
	public void init() {
		all = new AllInOne("");
	}
	
	//【ECPayServer.java】obj.setClientBackURL("http://localhost:8080/ecpay/ECPayServer3");
	//綠界付款成功畫面會增加「返回商店」按鈕，消費者點選此按鈕後，會將頁面導回到此設定的網址
	//(注意：導回時不會帶付款結果到此網址，只是將頁面(以GET方法)導回而已。)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("<h1>恭喜您付款成功。</h1>");
	}
	
	//【ECPayServer.java】obj.setOrderResultURL("http://localhost:8080/ecpay/ECPayServer3"); 
	//當消費者付款完成後，綠界會將付款結果參數以幕前(Client POST)回傳到該網址。
	//若此參數設定網址未使用 https 時，部份瀏覽器可能會出現警告訊息提醒。		
	//若與[ClientBackURL]同時設定，將會以此參數為主。
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		System.out.printf("【ECPayServer3.java】用戶端付款成功後回傳「付款結果」通知給伺服端的POST請求網址=%s\n", request.getRequestURL() );
		//輸出範例：
		//【ECPayServer3.java】用戶端付款成功後回傳「付款結果」通知給伺服端的POST請求網址=http://220.133.103.95:8080/ecpay/ECPayServer3
		
		Hashtable<String, String> dict = new Hashtable<String, String>();
		Enumeration<String> enumeration = request.getParameterNames();
		while(enumeration.hasMoreElements()) {
			String paramName = enumeration.nextElement();
			String paramValue = request.getParameter(paramName);
			dict.put(paramName, paramValue);			
		}
		System.out.printf("【ECPayServer3.java】用戶端付款成功後回傳「付款結果」通知給伺服端的參數們：\n%s\n", dict.toString());
		//輸出範例：
		//【ECPayServer3.java】用戶端付款成功後回傳「付款結果」通知給伺服端的參數們：
		//{CheckMacValue=028D288F5CB566EB1FA5E204FA46B6FC68AB3ED68EC12AE17E561A6A9AF885F5, 
		//TradeDate=2021/08/31 11:09:08, TradeNo=2108311109087900, MerchantID=2000132, 
		//PaymentTypeChargeFee=21, PaymentType=Credit_CreditCard, TradeAmt=1050, RtnMsg=Succeeded, StoreID=, CustomField4=, 
		//MerchantTradeNo=III1630379348465, CustomField3=, 
		//PaymentDate=2021/08/31 11:10:23, SimulatePaid=0, CustomField2=, CustomField1=, RtnCode=1}//RtnCode=1付款成功

		boolean checkStatus = all.compareCheckMacValue(dict); //true：表示資料未被竄改
		//消費者付款成功且檢查碼正確的時候：	(RtnCode:交易狀態(1:成功，其餘為失敗))	
		if ("1".equals(dict.get("RtnCode")) && checkStatus==true ){
			//付款成功後的工作
			 //---------------------------//
			 //在此撰寫你的處理邏輯
			 //---------------------------//
			 //回應用戶端(付款者)
			 out.print("<h1>伺服端已接收到從用戶端(付款者)送出的「付款成功」通知。</h1>");
			 
			 //變更訂單付款狀態
			 //update ***** where orderid = dict.get("MerchantTradeNo")
		}
		else
			out.print("<h1>伺服端已接收到從用戶端(付款者)送出的付款通知(但付款資料有誤！)。</h1>");	
	}	
}


