package iii.homework.service;

import java.util.List;

import iii.homework.model.CustomerBean;

public interface CustomerServiceInterface {
	//service負責處理商業邏輯
	
	// 查詢
	public CustomerBean select(int cId);
	public List<CustomerBean> selectAll();
	//會員登入判斷是否存在及是否正確
	public String selectUsername(CustomerBean cBean);//回傳pass或fail
	//會員註冊時判斷帳號是否存在
	public String selectCreateCusUsername(CustomerBean cBean);
	//會員註冊時判斷電話號碼是否存在
	public String selectPhone(CustomerBean cBean);
	//會員註冊時判斷Email是否存在
	public String selectEmail(CustomerBean cBean);

	// 新增
	public CustomerBean insert(CustomerBean cBean);

	// 修改
	public CustomerBean updateOne(int cId, CustomerBean mBean);

	// 刪除
	public boolean deletOne(int cId);
	
}
