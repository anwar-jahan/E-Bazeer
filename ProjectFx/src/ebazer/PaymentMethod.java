package ebazer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PaymentMethod extends Stage {
	EbazaarMainFrame ebazzer;
	ShippingBillingWindow shippaddress;
	private final String MAIN_LABEL = "Payment Method";
	private final String PROCEED_BUTN = "Proceed With Checkout";
	private final String BACK_TO_CART_BUTN = "Back To Cart";

	private final String NAME_ON_CARD = "Name on Card";
	private final String CARD_NUMBER = "Card Number";
	private final String CARD_TYPE = "Card Type";
	private final String EXPIRATION = "Expiration Date";

	Stage stage;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PaymentMethod(Stage stage,ShippingBillingWindow shippaddress,EbazaarMainFrame ebazzer) {
		this.ebazzer=ebazzer;
		this.stage = stage;
		this.shippaddress=shippaddress;
		setTitle(MAIN_LABEL);

		Label lblTitle = new Label(MAIN_LABEL);
		lblTitle.setId("shipLabel2");
		GridPane top = new GridPane();
		top.setAlignment(Pos.CENTER);
		top.setVgap(10);
		top.setHgap(10);
		top.setPadding(new Insets(25, 25, 25, 25));
		top.add(lblTitle, 0, 1);

		Label lblCardname = new Label(NAME_ON_CARD);
		TextField txtCardName = new TextField();

		Label lblCardNumber = new Label(CARD_NUMBER);
		TextField txtCardnumber = new TextField();

		Label lblCardtype = new Label(CARD_TYPE);
		ComboBox comboBoxtxtCardtype = new ComboBox();
		comboBoxtxtCardtype.getItems().addAll("Visa", "MasterCard", "Discover");
		comboBoxtxtCardtype.setPromptText("Please Select..");
		Label lblCardExpire = new Label(EXPIRATION);
		TextField txtCardExpire = new TextField();

		GridPane midd = new GridPane();
		midd.setAlignment(Pos.CENTER);
		midd.setVgap(10);
		midd.setHgap(10);
		midd.setPadding(new Insets(25, 25, 25, 25));

		midd.add(lblCardname, 0, 1);
		midd.add(txtCardName, 1, 1);
		midd.add(lblCardNumber, 0, 2);
		midd.add(txtCardnumber, 1, 2);
		midd.add(lblCardtype, 0, 3);
		midd.add(comboBoxtxtCardtype, 1, 3);
		midd.add(lblCardExpire, 0, 4);
		midd.add(txtCardExpire, 1, 4);

		Button btnproceed = new Button(PROCEED_BUTN);
		Button btnCancel = new Button(BACK_TO_CART_BUTN);

		GridPane buttom = new GridPane();
		buttom.setAlignment(Pos.CENTER);
		buttom.setVgap(10);
		buttom.setHgap(10);
		buttom.setPadding(new Insets(25, 25, 25, 25));

		buttom.add(btnproceed, 0, 1);
		buttom.add(btnCancel, 1, 1);

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(10);
		grid.setHgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		grid.add(top, 0, 1);
		grid.add(midd, 0, 2);
		grid.add(buttom, 0, 3);

		btnproceed.setOnAction(evt -> {
			TermsAndConditions temscondition=new TermsAndConditions(stage,this,ebazzer);
			temscondition.show();
			hide();
		});

		btnCancel.setOnAction(evt -> {
			shippaddress.show();
			hide();
		});

		Scene scene = new Scene(grid, 420, 420);
		scene.getStylesheets().add(
				getClass().getResource("ebazeer.css").toExternalForm());
		setScene(scene);
		stage.show();
	}
}
