package iii.homework.model;

import java.util.List;

public interface CustomerDaoInterface {

	// 查詢
	CustomerBean select(int cId);
	List<CustomerBean> selectAll();
	//搜尋會員帳號
	List<CustomerBean> selectUsername(CustomerBean username);
	//會員註冊時搜尋電話號碼
	List<CustomerBean> selectPhone(CustomerBean phoneNumber);
	//會員註冊時搜尋Email
	List<CustomerBean> selectEmail(CustomerBean email);

	// 新增
	CustomerBean insert(CustomerBean cBean);

	// 修改
	CustomerBean updateOne(int cId, CustomerBean cBean);
	

	// 刪除
	boolean deletOne(int cId);
}
