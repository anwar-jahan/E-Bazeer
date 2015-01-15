package ebazer;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
 
public class ProductView extends Stage {
	//Tonmoy Dada
    private TableView<Product> table = new TableView<Product>();
    private final ObservableList<Product> data =
            FXCollections.observableArrayList(
            		//Integer pi, String pn, int qa, double up, String md, Integer ci, String d
            new Product("1", "Messiah Of Dune", "20", "22.20", "12/21/214", "15.00", "This is a test description"),
            new Product("2", "Gone with the Wind", "15", "18.00", "12/21/214", "12.00", "This is a test description"),
            new Product("3", "Garden of Rama", "300", "12.00", "12/21/214", "18.00", "This is a test description"),
            new Product("4", "Pants", "150", "20.00", "12/21/214", "13.00", "This is a test description"),
            new Product("5", "Skirts", "15", "12.00", "12/21/214", "12.00", "This is a test description"),
            new Product("6", "T-Shirts", "15", "16.00", "12/21/214", "15.00", "This is a test description"),
            new Product("7", "HATS", "15", "14.20", "12/21/214", "18.00", "This is a test description")
            
            );
    final HBox hb = new HBox();
 
    EbazaarMainFrame ebazzer;
    Stage stage;
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public ProductView( EbazaarMainFrame ebazzer,Stage stage){
    	this.stage=stage;
    	this.ebazzer=ebazzer;
    	
    	
    	
        Scene scene = new Scene(new Group());
        setTitle("Product Management");
        setWidth(610);
        setHeight(550);
 
        final Label label = new Label("View Product");
        label.setFont(new Font("Arial", 20));
 
        table.setEditable(true);
        
 
        TableColumn idCol = new TableColumn("Id");
        idCol.setMinWidth(100);
        idCol.setCellValueFactory(
            new PropertyValueFactory<Product, String>("productId"));
        idCol.setCellFactory(TextFieldTableCell.forTableColumn());
        idCol.setOnEditCommit(
            new EventHandler<CellEditEvent<Product, String>>() {
                @Override
                public void handle(CellEditEvent<Product, String> t) {
                    ((Product) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setProductId(t.getNewValue());
                }
            }
        );
 
 
        TableColumn productNameCol = new TableColumn("Product Name");
        productNameCol.setMinWidth(250);
        productNameCol.setCellValueFactory(
            new PropertyValueFactory<Product, String>("productName"));
        productNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        productNameCol.setOnEditCommit(
            new EventHandler<CellEditEvent<Product, String>>() {
                @Override
                public void handle(CellEditEvent<Product, String> t) {
                    ((Product) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setProductName(t.getNewValue());
                }
            }
        );
 
        TableColumn unitPriceCol = new TableColumn("Unit Price");
        unitPriceCol.setMinWidth(120);
        unitPriceCol.setCellValueFactory(
            new PropertyValueFactory<Product, String>("unitPrice"));
        unitPriceCol.setCellFactory(TextFieldTableCell.forTableColumn());
        unitPriceCol.setOnEditCommit(
            new EventHandler<CellEditEvent<Product, String>>() {
                @Override
                public void handle(CellEditEvent<Product, String> t) {
                    ((Product) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setUnitPrice(t.getNewValue());
                }
            }
        );       
        
        TableColumn quantityAvailCol = new TableColumn("Quantity");
        quantityAvailCol.setMinWidth(100);
        quantityAvailCol.setCellValueFactory(
            new PropertyValueFactory<Product, String>("quantityAvail"));
        quantityAvailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        quantityAvailCol.setOnEditCommit(
            new EventHandler<CellEditEvent<Product, String>>() {
                @Override
                public void handle(CellEditEvent<Product, String> t) {
                    ((Product) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setQuantityAvail(t.getNewValue());
                }
            }
        );        
        

        table.setItems(data);
        table.getColumns().addAll(idCol, productNameCol, unitPriceCol, quantityAvailCol);
 
        final TextField addId = new TextField();
        addId.setPromptText("Id");
        addId.setMaxWidth(idCol.getPrefWidth());
        
        final TextField addProductName = new TextField();
        addProductName.setMaxWidth(addProductName.getPrefWidth());
        addProductName.setPromptText("Product Name");
        
        final TextField addUnitPrice = new TextField();
        addUnitPrice.setMaxWidth(addUnitPrice.getPrefWidth());
        addUnitPrice.setPromptText("Unit Price");
        
        final TextField addQuantityAvail = new TextField();
        addQuantityAvail.setMaxWidth(addQuantityAvail.getPrefWidth());
        addQuantityAvail.setPromptText("Quantity");
        
        final Button addButton = new Button("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                data.add(new Product(
                        addId.getText(),
                        addProductName.getText(), 
                        addQuantityAvail.getText(),
                        addUnitPrice.getText(),
                        "01/09/2015", 
                        "1",
                        "This is a test description"
                        
                        ));
                addId.clear();
                addProductName.clear();
                addQuantityAvail.clear();
                addUnitPrice.clear();
                
            }
        });
 
        hb.getChildren().addAll(addId, addProductName, addQuantityAvail, addUnitPrice, addButton);
        hb.setSpacing(2);
 
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, hb);
 
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
 
        setScene(scene);
        stage.show();
    }

    public static class Product {

        private String productId;
        private String productName;
        private String quantityAvail;
        private String unitPrice;
        private String mfgDate;
        private String catalogId;
        private String description;
        
        public Product(String pi, String pn, String qa, String up, String md, String ci, String d){
            productId=pi;
            productName = pn;
            quantityAvail = qa;
            unitPrice = up;
            mfgDate = md;
            catalogId = ci;
            description = d;
        }
        //this constructor is used when getting user-entered data in adding a new product
        public Product(String name, String date, String numAvail, String price){
        	this(null, name, numAvail, price, date, null, null);
        }
        /**
         * @return Returns the catalogId.
         */
        public String getCatalogId() {
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
        public String getProductId() {
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
        public String getQuantityAvail() {
            return quantityAvail;
        }
        /**
         * @return Returns the unitPrice.
         */
        public String getUnitPrice() {
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
		public void setProductId(String productId) {
			this.productId = productId;
		}
		public void setProductName(String productName) {
			this.productName = productName;
		}
		public void setUnitPrice(String unitPrice) {
			this.unitPrice = unitPrice;
		}
		public void setQuantityAvail(String quantityAvail) {
			this.quantityAvail = quantityAvail;
		}
		
		
    }

}