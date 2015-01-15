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
 
public class CatalogView extends Stage {
 
	//Tonmoy Dada
    private TableView<Catalog> table = new TableView<Catalog>();
    private final ObservableList<Catalog> data =
            FXCollections.observableArrayList( new Catalog("1", "Books"),
                    new Catalog("2", "Clothes"));
    final HBox hb = new HBox();

    EbazaarMainFrame ebazzer;
    Stage stage;
    @SuppressWarnings({ "rawtypes", "unchecked", })
	public CatalogView(EbazaarMainFrame ebazzer,Stage stage) {
    	this.stage=stage;
    	this.ebazzer=ebazzer;
    	
        Scene scene = new Scene(new Group());
        setTitle("Catalog Management");
        setWidth(310);
        setHeight(550);
 
        final Label label = new Label("View Catelog");
        label.setFont(new Font("Arial", 20));
 
        table.setEditable(true);
        
 
        TableColumn idCol = new TableColumn("Id");
        idCol.setMinWidth(100);
        idCol.setCellValueFactory(
            new PropertyValueFactory<Catalog, Integer>("id"));
        idCol.setCellFactory(TextFieldTableCell.forTableColumn());
        idCol.setOnEditCommit(
            new EventHandler<CellEditEvent<Catalog, String>>() {
                @Override
                public void handle(CellEditEvent<Catalog, String> t) {
                    ((Catalog) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setId(t.getNewValue());
                }
            }
        );
 
 
        TableColumn nameCol = new TableColumn("Catalog");
        nameCol.setMinWidth(200);
        nameCol.setCellValueFactory(
            new PropertyValueFactory<Catalog, String>("name"));
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setOnEditCommit(
            new EventHandler<CellEditEvent<Catalog, String>>() {
                @Override
                public void handle(CellEditEvent<Catalog, String> t) {
                    ((Catalog) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setName(t.getNewValue());
                }
            }
        );
 

        table.setItems(data);
        table.getColumns().addAll(idCol, nameCol);
 
        final TextField addId = new TextField();
        addId.setPromptText("Id");
        addId.setMaxWidth(idCol.getPrefWidth());
        final TextField addName = new TextField();
        addName.setMaxWidth(nameCol.getPrefWidth());
        addName.setPromptText("Catelog Name");
        
 
        final Button addButton = new Button("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                data.add(new Catalog(
                        addId.getText(),
                        addName.getText()
                        ));
                addId.clear();
                addName.clear();
                
            }
        });
 
        hb.getChildren().addAll(addId, addName, addButton);
        hb.setSpacing(2);
 
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, hb);
 
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
 
        setScene(scene);
        stage.show();
    }

    public static class Catalog {
    	String id;
    	String name;
    	public String getName() {
    		return name;
    	}
    	public void setName(String name) {
    		this.name = name;
    	}
    	public Catalog(String id, String name) {
    		this.id=id;
    		this.name = name;
    	}
    	public String getId() {
    		return id;
    	}
    	public void setId(String id) {
    		this.id = id;
    	}
    	
    	/*
    	public boolean equals(Object ob) {
    		if(this == ob) return true;
    		if(getClass() != ob.getClass()) return false;
    		Catalog c = (Catalog)ob;
    		return name.equals(c.name);
    	}
    	*/
    	
    	
    }
}