package ebazer;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ShippingBillingWindow extends Stage {
	// constants
	EbazaarMainFrame ebazzer;
	private final String MAIN_LABEL = "Shipping And Billing Information";
	private final String SHIP_LABEL = "Shipping Address";
	private final String BILL_LABEL = "Billing Address";
	private final String SHIP_METHOD_LABEL = "Shipping Method";
	private final String NAME = "Name";
	private final String ADDRESS_1 = "Address 1";
	private final String ADDRESS_2 = "Address 2";
	private final String CITY = "City";
	private final String STATE = "State";
	private final String ZIP = "Zip";

	// button labels
	private final String SELECT_SHIP_ADDR = "Select Shipping Address";
	private final String PROCEED_WITH_CHECKOUT = "Proceed With Checkout";
	private final String BACK_TO_CART = "Back To Cart";
	private final String GROUND = "Pigeon-carrier Ground";
	private final String AIR = "Pigeon-carrier Air";
	private final String OVERNIGHT = "Pigeon-carrier Overnight";
	private final String CHECK_IF_SAME = "Check if Billing Address is Same as Shipping";

	Stage stage;

	public ShippingBillingWindow(Stage stage,EbazaarMainFrame ebazzer) {
		this.ebazzer=ebazzer;
		this.stage = stage;
		setTitle(MAIN_LABEL);

		Label lblTitle = new Label(MAIN_LABEL);
		lblTitle.setId("shipTop");
		
		GridPane gridTopTitle = new GridPane();
		gridTopTitle.setAlignment(Pos.CENTER);
		gridTopTitle.setVgap(10);
		gridTopTitle.setHgap(10);
		gridTopTitle.add(lblTitle, 0, 1);

		Label lblname = new Label(NAME);
		TextField txtName = new TextField();

		Label lbladdress = new Label(ADDRESS_1);
		TextField txtaddress = new TextField();

		Label lblcity = new Label(CITY);
		TextField txtcity = new TextField();

		Label lblstate = new Label(STATE);
		TextField txtstate = new TextField();

		Label lblzip = new Label(ZIP);
		TextField txtzip = new TextField();

		GridPane gridTopLeft = new GridPane();
		gridTopLeft.setAlignment(Pos.CENTER);
		gridTopLeft.setVgap(10);
		gridTopLeft.setHgap(10);
		gridTopLeft.setPadding(new Insets(25, 25, 25, 25));
		gridTopLeft.add(lblname, 0, 1);
		gridTopLeft.add(txtName, 1, 1);
		gridTopLeft.add(lbladdress, 0, 2);
		gridTopLeft.add(txtaddress, 1, 2);
		gridTopLeft.add(lblcity, 0, 3);
		gridTopLeft.add(txtcity, 1, 3);
		gridTopLeft.add(lblstate, 0, 4);
		gridTopLeft.add(txtstate, 1, 4);
		gridTopLeft.add(lblzip, 0, 5);
		gridTopLeft.add(txtzip, 1, 5);

		Label lblname2 = new Label(NAME);
		TextField txtName2 = new TextField();

		Label lbladdress2 = new Label(ADDRESS_2);
		TextField txtaddress2 = new TextField();

		Label lblcity2 = new Label(CITY);
		TextField txtcity2 = new TextField();

		Label lblstate2 = new Label(STATE);
		TextField txtstate2 = new TextField();

		Label lblzip2 = new Label(ZIP);
		TextField txtzip2 = new TextField();

		GridPane gridTopRight = new GridPane();
		gridTopRight.setAlignment(Pos.CENTER);
		gridTopRight.setVgap(10);
		gridTopRight.setHgap(10);
		gridTopRight.add(lblname2, 0, 1);
		gridTopRight.add(txtName2, 1, 1);
		gridTopRight.add(lbladdress2, 0, 2);
		gridTopRight.add(txtaddress2, 1, 2);
		gridTopRight.add(lblcity2, 0, 3);
		gridTopRight.add(txtcity2, 1, 3);
		gridTopRight.add(lblstate2, 0, 4);
		gridTopRight.add(txtstate2, 1, 4);
		gridTopRight.add(lblzip2, 0, 5);
		gridTopRight.add(txtzip2, 1, 5);
		gridTopRight.setPadding(new Insets(25, 25, 25, 25));

		CheckBox cb1 = new CheckBox();
		cb1.setText(CHECK_IF_SAME);

		GridPane gridMid1 = new GridPane();
		gridMid1.setAlignment(Pos.CENTER);
		gridMid1.setVgap(10);
		gridMid1.setHgap(10);
		gridMid1.add(cb1, 0, 1);

		Label lblMethod = new Label(SHIP_METHOD_LABEL);
		lblMethod.setId("shipLabel2");
		final ToggleGroup group = new ToggleGroup();
		RadioButton rb1 = new RadioButton();
		rb1.setToggleGroup(group);
		rb1.setSelected(true);
		rb1.setText(GROUND);
		RadioButton rb2 = new RadioButton();
		rb2.setToggleGroup(group);
		rb2.setText(AIR);
		RadioButton rb3 = new RadioButton();
		rb3.setToggleGroup(group);
		rb3.setText(OVERNIGHT);

		GridPane gridMid2 = new GridPane();
		gridMid2.setAlignment(Pos.CENTER);
		gridMid2.setVgap(10);
		gridMid2.setHgap(10);
		gridMid2.add(lblMethod, 0, 1);
		gridMid2.add(rb1, 0, 2);
		gridMid2.add(rb2, 0, 3);
		gridMid2.add(rb3, 0, 4);

		Button btnShippingAddress = new Button(SELECT_SHIP_ADDR);
		Button btnProceedCheckout = new Button(PROCEED_WITH_CHECKOUT);
		Button btnBacktoCart = new Button(BACK_TO_CART);

		GridPane gridButtom = new GridPane();
		gridButtom.setAlignment(Pos.CENTER);
		gridButtom.setVgap(10);
		gridButtom.setHgap(10);
		gridButtom.setPadding(new Insets(25, 25, 25, 25));
		gridButtom.add(btnShippingAddress, 0, 1);
		gridButtom.add(btnProceedCheckout, 1, 1);
		gridButtom.add(btnBacktoCart, 2, 1);

		Label leftTitle = new Label(SHIP_LABEL);
		leftTitle.setId("shipLabel2");
		GridPane gridTopleftTitle = new GridPane();
		gridTopleftTitle.setAlignment(Pos.CENTER);
		gridTopleftTitle.setVgap(10);
		gridTopleftTitle.setHgap(10);
		gridTopleftTitle.add(leftTitle, 0, 1);

		Label rightTitle = new Label(BILL_LABEL);
		rightTitle.setId("shipLabel2");
		GridPane gridToprightTitle = new GridPane();
		gridToprightTitle.setAlignment(Pos.CENTER);
		gridToprightTitle.setVgap(10);
		gridToprightTitle.setHgap(10);
		gridToprightTitle.add(rightTitle, 0, 1);

		GridPane gridTop = new GridPane();
		gridTop.add(gridTopleftTitle, 0, 1);
		gridTop.add(gridToprightTitle, 1, 1);
		gridTop.setPadding(new Insets(25, 25, 25, 25));

		gridTop.add(gridTopLeft, 0, 2);
		gridTop.add(gridTopRight, 1, 2);

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(10);
		grid.setHgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		grid.add(gridTopTitle, 0, 1);
		grid.add(gridTop, 0, 2);
		grid.add(gridMid1, 0, 3);
		grid.add(gridMid2, 0, 4);
		grid.add(gridButtom, 0, 5);

		btnShippingAddress.setOnAction(evt -> {

		});
		btnProceedCheckout.setOnAction(evt -> {
			PaymentMethod paymentwindows = new PaymentMethod(stage,this,ebazzer); 
			paymentwindows.show();
			hide();
		});

		btnBacktoCart.setOnAction(evt -> this.close());

		Scene scene = new Scene(grid, 720, 620);
		scene.getStylesheets().add(
				getClass().getResource("ebazeer.css").toExternalForm());
		setScene(scene);
		stage.show();
	}

}
