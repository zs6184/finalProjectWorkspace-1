package tw.finalproject.model;

import java.util.List;

public interface CustomerServiceInterface {
	//service負責處理商業邏輯
	
	// 查詢
	public CustomerBean select(int cId);
	public List<CustomerBean> selectUsername(CustomerBean cBean);
	public List<CustomerBean> selectAll();

	// 新增
	public CustomerBean insert(CustomerBean cBean);

	// 修改
	public CustomerBean updateOne(int cId, CustomerBean mBean);

	// 刪除
	public boolean deletOne(int cId);
	
}
