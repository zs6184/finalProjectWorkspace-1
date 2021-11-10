package tw.springbootfinal.ecpay;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;
import ecpay.payment.integration.domain.QueryTradeInfoObj;
import tw.springbootfinal.order.model.orderBean;
import tw.springbootfinal.order.model.orderDetailsBean;
import tw.springbootfinal.order.model.orderServic;
import tw.springbootfinal.users.model.CustomerBean;
import tw.springbootfinal.users.model.CustomerService;

@Controller
@RequestMapping("/ec")
public class ecpayController {
	private static final long serialVersionUID = 1L;

	@Autowired
	orderServic oService;
	
	@Autowired
	private CustomerService cService;
	
	// @PostMapping("/ECPayServer")
	@GetMapping("/ECPayServer/{cid}")
	public void ecpay(HttpServletRequest request, HttpServletResponse response, @PathVariable("cid") int orderid)
			throws IOException {
		System.out.println("我是訂單id" + orderid);
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		String form = genAioCheckOutALL(orderid);
		System.out.printf("ECPayServer.java 產生了讓消費者付款的表單：\n%s\n", form);
		// 輸出內容請參考最下面區段。
		out.print(form);
	}
	//生成訂單
	private String genAioCheckOutALL(int orderid) {
		AioCheckOutALL obj = new AioCheckOutALL();
		orderBean order = oService.selectbyid(orderid);
		String.valueOf(orderid);
		obj.setMerchantTradeNo(String.format("%dLangji1%d", new Date().getMinutes(), orderid)); // 特店交易編號均為唯一值，不可重複使用。英數字大小寫混合。
		obj.setMerchantTradeDate(String.format("%tY/%<tm/%<td %<tH:%<tM:%<tS", new Date())); // 特店交易時間。格式為：yyyy/MM/dd
																								// HH:mm:ss。
		// 訂單明細
		String ordedeatil = "";
		Set<orderDetailsBean> orderDetails = order.getOrderDetails();
		for (orderDetailsBean od : orderDetails) {
			ordedeatil += od.getProduct().getName() + "　" + "數量:" + od.getNum() + "　" + "小計:"
					+ String.valueOf(od.getProduct().getPrice() * od.getNum() + "#");
		}
		// 判斷折扣碼
		if (order.getCoupons() != null) {
			ordedeatil = ordedeatil.concat(order.getCoupons().getCouponName() + "折扣:-"
					+ String.valueOf(order.getCoupons().getCouponDiscount()));
		}

		obj.setTotalAmount(String.valueOf(order.getTotal())); // 交易金額。請帶整數，不可有小數點。僅限新台幣。
		obj.setTradeDesc("浪跡餐飲"); // 交易描述。請勿帶入特殊字元。
		obj.setItemName(ordedeatil); // 商品名稱

		// 不知道幹嘛
		obj.setNeedExtraPaidInfo("N");

		// ***付款結果通知我們伺服端的方式(可二選一)***//
		// A.以Server端(綠界)方式直接回傳付款結果通知
		obj.setReturnURL("https://220.133.103.95/ecpay/ECPayServer2");
		// 當消費者付款完成後，綠界會將付款結果參數以幕後(Server POST)回傳到該網址(該網址須是一個固定IP且使用https協定)。
		/*
		 * 請添加下列區段到Tomcat's「server.xml」，使之支援HTTPS。
		 * -----------------------------------------------------------------------------
		 * - <Connector protocol="org.apache.coyote.http11.Http11NioProtocol" port="443"
		 * maxThreads="200" scheme="https" secure="true" SSLEnabled="true"
		 * keystoreFile="C:\_jsp-ide\_JSSE(Java Secure Socket Extension)/mykeystore.jks"
		 * keystorePass="123456" clientAuth="false" sslProtocol="TLS"/>
		 * -----------------------------------------------------------------------------
		 * - server.xml所在位置： 生產環境：Tomcat安裝路徑>conf>server.xml 開發環境：Servers>「Tomcat v9.0
		 * Server at localhost-config」>server.xml
		 */

		// B.以Client端(消費者)方式回傳付款結果通知
		obj.setOrderResultURL("http://localhost:8080/ec/ECPayServer3");
		// 當消費者付款完成後，綠界會將付款結果參數以幕前(Client POST)回傳到該網址。
		// 若此參數設定網址未使用 https 時，部份瀏覽器可能會出現警告訊息提醒。(註：chrome對於localhost網址不會出現警告訊息提醒)
		// 若與下面[ClientBackURL]同時設定，將會以此參數為主。

		// ********************************//

		// obj.setClientBackURL("http://localhost:8080/ecpay/ECPayServer3");
		// 綠界付款成功畫面會增加「返回商店」按鈕，消費者點選此按鈕後，會將頁面導回到此設定的網址
		// (注意：導回時不會帶付款結果到此網址，只是將頁面(以GET方法)導回而已。)
		// 產生訂單Html form的方法 //AioCheckOutALL
		AllInOne all = new AllInOne("");
		String form = all.aioCheckOut(obj, null);
		return form;
		// 綠界付款表單的基本要求：
		// 1.action="https://payment-stage.ecpay.com.tw/Cashier/AioCheckOut/V5"
		// (設定於~.config.EcpayPayment.xml)
		// 2.enctype="application/x-www-form-urlencoded"
		// 3.method="POST"
	}
	//當付款完成
	@PostMapping("/ECPayServer3")
	public String payresoult(HttpServletRequest request, HttpServletResponse response,RedirectAttributes attr) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		System.out.printf("【ECPayServer3.java】用戶端付款成功後回傳「付款結果」通知給伺服端的POST請求網址=%s\n", request.getRequestURL());
		// 輸出範例：
		// 【ECPayServer3.java】用戶端付款成功後回傳「付款結果」通知給伺服端的POST請求網址=http://220.133.103.95:8080/ecpay/ECPayServer3

		Hashtable<String, String> dict = new Hashtable<String, String>();
		Enumeration<String> enumeration = request.getParameterNames();
		while (enumeration.hasMoreElements()) {
			String paramName = enumeration.nextElement();
			String paramValue = request.getParameter(paramName);
			dict.put(paramName, paramValue);
		}
		System.out.printf("【ECPayServer3.java】用戶端付款成功後回傳「付款結果」通知給伺服端的參數們：\n%s\n", dict.toString());
		// 輸出範例：
		// 【ECPayServer3.java】用戶端付款成功後回傳「付款結果」通知給伺服端的參數們：
		// {CheckMacValue=028D288F5CB566EB1FA5E204FA46B6FC68AB3ED68EC12AE17E561A6A9AF885F5,
		// TradeDate=2021/08/31 11:09:08, TradeNo=2108311109087900, MerchantID=2000132,
		// PaymentTypeChargeFee=21, PaymentType=Credit_CreditCard, TradeAmt=1050,
		// RtnMsg=Succeeded, StoreID=, CustomField4=,
		// MerchantTradeNo=III1630379348465, CustomField3=,
		// PaymentDate=2021/08/31 11:10:23, SimulatePaid=0, CustomField2=,
		// CustomField1=, RtnCode=1}//RtnCode=1付款成功
		AllInOne all = new AllInOne("");
		boolean checkStatus = all.compareCheckMacValue(dict); // true：表示資料未被竄改
		// 消費者付款成功且檢查碼正確的時候： (RtnCode:交易狀態(1:成功，其餘為失敗))
		if ("1".equals(dict.get("RtnCode")) && checkStatus == true) {
			// 付款成功後的工作
			// ---------------------------//
			// 在此撰寫你的處理邏輯
			// ---------------------------//
			// 回應用戶端(付款者)
			//out.print("<h1>伺服端已接收到從用戶端(付款者)送出的「付款成功」通知"+dict.get("MerchantTradeNo")+"。</h1>");
			
			
			//attr.addAttribute("merchantTradeNo", dict.get("MerchantTradeNo"));
			//attr.addAttribute("paymentDate", dict.get("PaymentDate"));
			//attr.addAttribute("tradeAmt", dict.get("TradeAmt"));
			System.out.println("MacValue:"+dict.get("CheckMacValue"));
			System.out.println("MerchantID:"+dict.get("MerchantID"));
			return "redirect:/ec/ecpaysuccess?merchantTradeNo="+dict.get("MerchantTradeNo")+"&paymentDate="+dict.get("PaymentDate")+"&tradeAmt="+dict.get("TradeAmt");
			// 變更訂單付款狀態
			// update ***** where orderid = dict.get("MerchantTradeNo")
		} else {
			//out.print("<h1>伺服端已接收到從用戶端(付款者)送出的付款通知(但付款資料有誤！)。</h1>");
			return null;
		}
	}
	
	//測試跳轉
	@RequestMapping("/ECPayServertest")
	@ResponseBody
	public String Stringtestss() {
		AllInOne all = new AllInOne("");
		QueryTradeInfoObj query = new QueryTradeInfoObj();
		query.setMerchantTradeNo("28Langji12520");
		String queryTradeInfo = all.queryTradeInfo(query);
		System.out.println("回傳結果:"+queryTradeInfo);
		return "ok";
	}
	
	
	//訂單完成
	@RequestMapping(path = "/ecpaysuccess", produces = "text/plain;charset=utf-8")
	public String ecpaysuccess(Model m,HttpServletRequest request,Principal p,RedirectAttributes attr,HttpSession session,
			@RequestParam("merchantTradeNo")String merchantTradeNo,
			@RequestParam("paymentDate")String paymentDate,
			@RequestParam("tradeAmt")String tradeAmt
			) {
		//取信用卡內容
		System.out.println("訂單編號:"+merchantTradeNo);
		//session使用者
		CustomerBean user = cService.getLoginCustomerBean(session);
		//訂單ID
		int orderid = Integer.parseInt(merchantTradeNo.substring(merchantTradeNo.length()-4));
		//變更已付款
		orderBean order = oService.selectbyid(orderid);
		order.setPaystatus(1);
		order.setMerchantTradeNo(merchantTradeNo);
		oService.saveOrder(order);
		System.out.println("訂單id:"+orderid+"付款完成");
		//傳參
		m.addAttribute("orderid",orderid);
		m.addAttribute("user",user.getCusUsername());
		m.addAttribute("merchantTradeNo",merchantTradeNo);
		m.addAttribute("paymentDate",paymentDate);
		m.addAttribute("tradeAmt",tradeAmt);
		return "ecpaysuccess";
	}
	
	//綠界訂單檢視
	@RequestMapping("/ecdetail")
	public String pcdetail(HttpSession session,Model m) {
		CustomerBean loginCustomerBean = cService.getLoginCustomerBean(session);
		//取的取用者訂單
		Set<orderBean> orders = loginCustomerBean.getOrderBean();;
		AllInOne all = new AllInOne("");
		QueryTradeInfoObj queryObj = new QueryTradeInfoObj();
		//準備ArrayList接綠界返回結果
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		//取得綠界訂單
		for(orderBean order:orders) {
			if(order.getMerchantTradeNo() != null) {
				queryObj.setMerchantTradeNo(order.getMerchantTradeNo());
				String str = all.queryTradeInfo(queryObj);
				HashMap<String, Object> ecdorder = ecfindorder(str);
				list.add(ecdorder);
			}
			
		}
		Collections.reverse(list);
		m.addAttribute("orders",list);
		return "ecdetail";
	}
	
	//綠界查詢訂單api
	public HashMap<String, Object> ecfindorder(String tradeNo) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		String[] split = tradeNo.split("&");
		for(String s:split) {
			String[] split2 = s.split("=");
			if(split2.length !=1) {
				//判斷商品加入陣列
				if(split2[0].equals("ItemName")) {
					String[] split3 = split2[1].split("#");
					ArrayList<String> arrayList = new ArrayList<String>();
					for(String split4:split3) {
						arrayList.add(split4);
					}
					//將商品加入陣列
					hashMap.put(split2[0], arrayList);
				}else{
					hashMap.put(split2[0], split2[1]);
				}
				
			}			
		}
		//檢查內容
		for(Map.Entry<String,Object> entey :hashMap.entrySet()) {
			System.out.println(entey.getKey()+":"+entey.getValue());
		}
		return hashMap;
	}
	

}
