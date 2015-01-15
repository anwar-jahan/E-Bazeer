package tables;

public class Product {

    private Integer productId;
    private String productName;
    private int quantityAvail;
    private double unitPrice;
    private String mfgDate;
    private Integer catalogId;
    private String description;
    
    public Product(Integer pi, String pn, int qa, double up, String md, Integer ci, String d){
        productId=pi;
        productName = pn;
        quantityAvail = qa;
        unitPrice = up;
        mfgDate = md;
        catalogId = ci;
        description = d;
    }
    //this constructor is used when getting user-entered data in adding a new product
    public Product(String name, String date, int numAvail, double price){
    	this(null, name, numAvail, price, date, null, null);
    }
    /**
     * @return Returns the catalogId.
     */
    public Integer getCatalogId() {
        return catalogId;
    }
    /**
     * @return Returns the mfgDate.
     */
    public String getMfgDate() {
        return mfgDate;
    }
    /**
     * @return Returns the productId.
     */
    public Integer getProductId() {
        return productId;
    }
    /**
     * @return Returns the productName.
     */
    public String getProductName() {
        return productName;
    }
    /**
     * @return Returns the quantityAvail.
     */
    public int getQuantityAvail() {
        return quantityAvail;
    }
    /**
     * @return Returns the unitPrice.
     */
    public double getUnitPrice() {
        return unitPrice;
    }
    /**
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }
	public void setDescription(String description) {
		this.description = description;
	}
}
