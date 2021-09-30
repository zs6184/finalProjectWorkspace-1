package tw.finalproject.model;

import java.util.List;

import org.hibernate.Session;

public class CustomerService implements CustomerServiceInterface {

	private CustomerDao cusDao;
	
	//使用建構子取得session並在DAO建立時放入session
	public CustomerService(Session session) {
			this.cusDao = new CustomerDao(session);
	}
	
	@Override
	public CustomerBean select(int cId) {
		CustomerBean theCus = cusDao.select(cId);
		return theCus;
	}
	
	@Override
	public List<CustomerBean> selectUsername(CustomerBean username) {
		List<CustomerBean> theCus = cusDao.selectUsername(username);
		return theCus;
	}
	@Override
	public List<CustomerBean> selectAll() {
		List<CustomerBean> cusAll = cusDao.selectAll();
		return cusAll;
	}

	@Override
	public CustomerBean insert(CustomerBean cBean) {
		CustomerBean oneCus = cusDao.insert(cBean);
		return null;
	}

	@Override
	public CustomerBean updateOne(int cId, CustomerBean cBean) {
		CustomerBean oneCus = cusDao.updateOne(cId, cBean);
		return oneCus;
	}

	@Override
	public boolean deletOne(int cId) {
		boolean boo = cusDao.deletOne(cId);
		return boo;
	}

}
