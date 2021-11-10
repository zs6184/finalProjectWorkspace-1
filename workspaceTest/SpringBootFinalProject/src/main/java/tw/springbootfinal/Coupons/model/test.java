package tw.springbootfinal.Coupons.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class test {

	public static void main(String[] args) {
		String test= "CustomField1=&CustomField2=&CustomField3=&CustomField4=&HandlingCharge=11&ItemName=potatl　數量:1　小計:125#apple　數量:4　小計:400#&MerchantID=2000132&MerchantTradeNo=28Langji12520&PaymentDate=2021/11/07 22:28:53&PaymentType=Credit_CreditCard&PaymentTypeChargeFee=11&StoreID=&TradeAmt=525&TradeDate=2021/11/07 22:28:27&TradeNo=2111072228273784&TradeStatus=1&CheckMacValue=A9C2ABDA55C45E4C99338E35263EE06BEB341A95FC38B3716BFD025B178A09F3";
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		String[] split = test.split("&");
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
		
		for(Map.Entry<String,Object> entey :hashMap.entrySet()) {
			System.out.println(entey.getKey()+":"+entey.getValue());
		}
		
	}

}
