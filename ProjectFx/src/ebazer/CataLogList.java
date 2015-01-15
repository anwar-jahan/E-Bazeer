package ebazer;

import javafx.collections.ObservableList;
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
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ebazer.Catalog;

public class CataLogList extends Stage {
	private TableView<Catalog> table = new TableView<Catalog>();
	Stage stage2;
	Catalog selected;

	public void setData(ObservableList<Catalog> cats) {
		table.setItems(cats);
	}

	@SuppressWarnings("unchecked")
	public CataLogList(Stage stage2) {
		this.stage2 = stage2;
		setTitle("Catalog List");
		final Label label = new Label("Browse Catalogs");
		label.setFont(new Font("Arial", 16));
		HBox labelHbox = new HBox(10);
		labelHbox.setAlignment(Pos.CENTER);
		labelHbox.getChildren().add(label);
		
		TableColumn<Catalog, String> catalogNameCol = new TableColumn<>("Catalog");
		catalogNameCol.setMinWidth(250);
		catalogNameCol.setCellValueFactory(
            new PropertyValueFactory<Catalog, String>("name"));
		catalogNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		
		table.getColumns().addAll(catalogNameCol);
		
		Button viewButton = new Button("View Catalog");
		Button backButton = new Button("Back to Start");
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(10); 
		grid.setHgap(10);
		grid.add(labelHbox, 0, 0);
		grid.add(table, 0, 1);
		HBox btnBox = new HBox(10);
		btnBox.setAlignment(Pos.CENTER);
		btnBox.getChildren().add(viewButton);
		btnBox.getChildren().add(backButton);
		grid.add(btnBox,0,3);
		
		backButton.setOnAction(evt -> {
			stage2.show();
			hide();
		});
		
		
		 Scene scene = new Scene(grid,300, 250);  
			setScene(scene);

	}

}
