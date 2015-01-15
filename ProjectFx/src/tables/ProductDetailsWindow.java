package tables;

import java.util.*;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ProductDetailsWindow extends Stage {
	
	Stage primaryStage;
	ProductListWindow productList;
	Product selectedProduct;
	
	@SuppressWarnings("unchecked")
	public ProductDetailsWindow(ProductListWindow list, Product selectedProduct) {
		this.productList = list;
		this.selectedProduct = selectedProduct;
		setTitle("Product Details");
		
		final Label label = new Label(String.format("%s: Product Details", selectedProduct.getProductName()));
        label.setFont(new Font("Arial", 16));
        HBox labelHbox = new HBox(10);
        labelHbox.setAlignment(Pos.CENTER);
        labelHbox.getChildren().add(label);
		
		//prepare display grid
        List<String> displayValues = Arrays.asList(selectedProduct.getProductName(),
		         (new Double(selectedProduct.getUnitPrice())).toString(),
		        (new Integer(selectedProduct.getQuantityAvail())).toString(),
		        selectedProduct.getDescription());
		FourByTwoGridPane dataTable 
		   = new FourByTwoGridPane(DefaultData.DISPLAY_PRODUCT_FIELDS,
				   displayValues, "darkblue");
						        
		
		Button addToCartButton = new Button("Add to Cart");
		Button backButton = new Button("Back to Product List");
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(10); 
		grid.setHgap(10);
		grid.add(labelHbox, 0, 0);
		grid.add(dataTable, 0, 1);
		HBox btnBox = new HBox(10);
		btnBox.setAlignment(Pos.CENTER);
		btnBox.getChildren().add(addToCartButton);
		btnBox.getChildren().add(backButton);
		grid.add(btnBox,0,3);
		
        
		backButton.setOnAction(evt -> {
			productList.show();
			hide();
		});
	

        
		addToCartButton.setOnAction(evt -> {
			System.out.println(selectedProduct.getProductName() + " has been added to your cart!");
		});
        
        
   
        Scene scene = new Scene(grid,300, 250);  
		setScene(scene);
	}
}
