package ebazer;

public class OrderItem {
	String pname;
	int quntitys;
	double unitprice;
	double totalPrice;
	
	OrderItem(String pname,int quntity,double unitprice,double totalPrice){
		this.pname=pname;
		this.quntitys=quntity;
		this.unitprice=unitprice;
		this.totalPrice=totalPrice;
	}
	public String getPname(){
		return pname;
	}
	public int getQuantity(){
		return quntitys;
	}
	public double getUnitprice(){
		return unitprice;
	}
	public double getTotalPrice(){
		return totalPrice;
	}
}
