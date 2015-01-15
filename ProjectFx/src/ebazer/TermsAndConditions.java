package ebazer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class TermsAndConditions extends Stage {
	EbazaarMainFrame ebazzer;
	private final String MAIN_LABEL = "Terms and Conditions";
	private final String PROCEED_BUTN = "Accept Terms And Conditions";
	private final String EXIT_BUTN = "Exit E-Bazaar";
	private final String TERMS_MESSAGE = "Any Items purchased from this site adhere to the terms and "
			+ "conditions depicted in this document. You will have to accecpt "
			+ "the Terms and Conditions depicted here inorder to purchase "
			+ "anything from this site.";

	Stage stage;
	PaymentMethod paymethod;

	public TermsAndConditions(Stage stage, PaymentMethod paymethod,EbazaarMainFrame ebazzer) {
		this.ebazzer=ebazzer;
		this.stage = stage;
		this.paymethod = paymethod;
		setTitle("Terms and Conditions");

		Label lblTitle = new Label(MAIN_LABEL);
		lblTitle.setId("shipLabel2");
		GridPane top = new GridPane();
		top.setAlignment(Pos.CENTER);
		top.setVgap(10);
		top.setHgap(10);
		top.setPadding(new Insets(25, 25, 25, 25));
		top.add(lblTitle, 0, 1);

		TextFlow textFlow = new TextFlow();
		Text text1 = new Text(10, 20, TERMS_MESSAGE);
		textFlow.getChildren().add(text1);
		GridPane midde = new GridPane();
		midde.setAlignment(Pos.CENTER);
		midde.setVgap(10);
		midde.setHgap(10);
		midde.setPadding(new Insets(25, 25, 25, 25));
		midde.add(textFlow, 0, 1);

		Button btnaccept = new Button(PROCEED_BUTN);
		Button exitbtn = new Button(EXIT_BUTN);

		GridPane buttom = new GridPane();
		buttom.setAlignment(Pos.CENTER);
		buttom.setVgap(10);
		buttom.setHgap(10);
		buttom.setPadding(new Insets(25, 25, 25, 25));
		buttom.add(btnaccept, 0, 1);
		buttom.add(exitbtn, 1, 1);

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(10);
		grid.setHgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		grid.add(top, 0, 1);
		grid.add(midde, 0, 2);
		grid.add(buttom, 0, 3);

		btnaccept.setOnAction(evt -> {
			FinalOrder forder=new FinalOrder(stage,this,ebazzer);
			forder.show();
			hide();
			
		});

		exitbtn.setOnAction(evt -> {
			paymethod.show();
			hide();
		});

		Scene scene = new Scene(grid, 420, 320);
		scene.getStylesheets().add(
				getClass().getResource("ebazeer.css").toExternalForm());
		setScene(scene);
		stage.show();

	}
}
