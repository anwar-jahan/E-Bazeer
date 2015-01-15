package ebazer;

public class OrderHistory {
	String orderid;
	String dates;
	double totalAmount;
	public OrderHistory(String orderid,String dates,double totalAmount){
		this.orderid=orderid;
		this.dates=dates;
		this.totalAmount=totalAmount;
	}
	public String getOrderid() {
		return orderid;
	}
	public String getDates() {
		return dates;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	
	

}
