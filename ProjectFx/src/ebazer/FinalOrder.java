package ebazer;

import java.security.SecureRandom;
import java.util.stream.Stream;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FinalOrder extends Stage {
	EbazaarMainFrame ebazzer;
	private final String MAIN_LABEL = "Final Order";
	private final String SUBMIT_BUTN = "Submit Order";
	private final String CANCEL_BUTN = "Cancel";
	
	private final String ITEM = "Item";
	private final String QUANTITY = "Quantity";
	private final String UNIT_PRICE = "Unit Price";
	private final String TOTAL = "Total Price";
	
	
	private TableView<OrderItem> table3 = new TableView<OrderItem>();
	Stage stage;
	TermsAndConditions condition;
	
	@SuppressWarnings("unchecked")
	public FinalOrder(Stage stage,TermsAndConditions condition,EbazaarMainFrame ebazzer){
		this.ebazzer=ebazzer;
		this.stage=stage;
		this.condition=condition;
		setTitle(MAIN_LABEL);
		
		ObservableList<OrderItem> orders=ebazzer.orderlist;
		table3.setItems(orders);
		
		final Label label = new Label(MAIN_LABEL);
		label.setId("shipTop");
		HBox labelHbox = new HBox(10);
		labelHbox.setAlignment(Pos.CENTER);
		labelHbox.getChildren().add(label);
		
	
		TableColumn<OrderItem, String> cartItemCol=new TableColumn<>(ITEM);
		cartItemCol.setMinWidth(150);
		cartItemCol.setCellValueFactory(new PropertyValueFactory<OrderItem, String>("pname"));
		cartItemCol.setCellFactory(TextFieldTableCell.forTableColumn());
		
		
		TableColumn<OrderItem, Integer> cartQuantityCol=new TableColumn<>(QUANTITY);
		cartQuantityCol.setMinWidth(150);
		cartQuantityCol.setCellValueFactory(new PropertyValueFactory<OrderItem, Integer>("quantity"));
		//cartQuantityCol.setCellFactory(TextFieldTableCell.forTableColumn());
		
		TableColumn<OrderItem, Double> cartUnitPriceCol=new TableColumn<>(UNIT_PRICE);
		cartUnitPriceCol.setMinWidth(150);
		cartUnitPriceCol.setCellValueFactory(new PropertyValueFactory<OrderItem, Double>("unitprice"));
		//cartUnitPriceCol.setCellFactory(TextFieldTableCell.forTableColumn());
		
		TableColumn<OrderItem, Double> cartTotalPriceCol=new TableColumn<>(TOTAL);
		cartTotalPriceCol.setMinWidth(150);
		cartTotalPriceCol.setCellValueFactory(new PropertyValueFactory<OrderItem, Double>("totalPrice"));
		//cartTotalPriceCol.setCellFactory(TextFieldTableCell.forTableColumn());
		
		table3.getColumns().addAll(cartItemCol,cartQuantityCol,cartUnitPriceCol,cartTotalPriceCol); //
		

		Button btnSubmit = new Button(SUBMIT_BUTN);
		Button btnCancel = new Button(CANCEL_BUTN);
		
		GridPane gridButtom = new GridPane();
		gridButtom.setAlignment(Pos.CENTER);
		gridButtom.setVgap(10);
		gridButtom.setHgap(10);
		gridButtom.setPadding(new Insets(25, 25, 25, 25));
		gridButtom.add(btnSubmit, 0, 1);
		gridButtom.add(btnCancel, 1, 1);
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(10);
		grid.setHgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		grid.add(labelHbox, 0, 1);
		grid.add(table3, 0, 2);
		grid.add(gridButtom, 0, 3);
		
		btnCancel.setOnAction(evt->{
			condition.show();
			hide();
		});
		
		String msg = "Thank You for Shopping at the Ebazaar. "+
	             "We guarantee satisfaction and quality for our product.";
		
		Label lblTitle=new Label(msg);
		Button btnQuantityss=new Button("Ok");
		Stage dialogStage = new Stage();
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.setScene(new Scene(VBoxBuilder.create()
				.children(lblTitle,btnQuantityss)
				.alignment(Pos.CENTER).padding(new Insets(5)).build()));

		btnQuantityss.setOnAction(evt->{
			dialogStage.hide();
			SecureRandom random = new SecureRandom();
			int ran=random.nextInt(10000);
			String randomNumber="rx"+ran;
			//total Price
			ebazzer.gridRemoveWithCart();
			
			ebazzer.OderHistory(randomNumber);
			ebazzer.tabPane.getSelectionModel().select(ebazzer.review);
			hide();
		});
	
		btnSubmit.setOnAction(evt->{
			dialogStage.show();
			//Removie all Item From Cart and All Order History
			
		});
		
		Scene scene = new Scene(grid, 720, 520);
		scene.getStylesheets().add(
				getClass().getResource("ebazeer.css").toExternalForm());
		setScene(scene);
		stage.show();
		
	}

	public void alertBox(){
		
	}

}
