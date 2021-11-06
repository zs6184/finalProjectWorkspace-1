package tw.springbootfinal.booking.model;

/**
 * @author
 **/
public class BookingsDTO {

    private String name;
    private String phone;
    private String peopleNum;
    private String orderDate;
    private String time;
    private Integer cusid;
    
    

	

	

	public Integer getCusid() {
		return cusid;
	}

	public void setCusid(Integer cusid) {
		this.cusid = cusid;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(String peopleNum) {
        this.peopleNum = peopleNum;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
