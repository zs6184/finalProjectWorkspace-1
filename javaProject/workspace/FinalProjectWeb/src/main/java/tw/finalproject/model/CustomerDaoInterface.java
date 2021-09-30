package tw.finalproject.model;

import java.util.List;

public interface CustomerDaoInterface {

	// 查詢
	CustomerBean select(int cId);
	List<CustomerBean> selectUsername(CustomerBean username);
	List<CustomerBean> selectAll();

	// 新增
	CustomerBean insert(CustomerBean cBean);

	// 修改
	CustomerBean updateOne(int cId, CustomerBean cBean);
	

	// 刪除
	boolean deletOne(int cId);
}
