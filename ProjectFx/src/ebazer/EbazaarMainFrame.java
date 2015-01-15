package ebazer;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.controlsfx.dialog.Dialogs;

import ebazer.Catalog;
import ebazer.DefaultData;
import ebazer.FourByTwoGridPane;
import tables.CatalogListWindow;
import tables.Product;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.stage.*;

/*
 *@author Anwar
 */
public class EbazaarMainFrame extends Application {
	private final String EBAZAAR_APP_NAME = "Ebazaar Online Shopping Application";
	private final String CUSTOMER = "Customer";
	private final String ADMINISTRATOR = "Administrator";
	private final String ONLINE_PURCHASE = "Online Purchase";
	private final String REVIEW_ORDERS = "Review Orders";
	private final String EXIT = "Exit";
	private final String RETRIEVE_CART = "Retrieve Saved Cart";
	private final String MAINTAIN_CATALOGUE = "Maintain Product Catalog";
	private final String MAINTAIN_CAT_TYPES = "Maintain Catalog Types";

	// online purchase
	TabPane tabPane;
	Tab purchase, review, retrieve;
	private TableView<Catalog> table = new TableView<Catalog>();
	Catalog selected = null;
	private TableView<Product> table2 = new TableView<Product>();
	Product selectedProduct = null;

	Stage stage;
	GridPane grid, grid2, gridHistory;

	// Card Details

	// constants
	private final boolean USE_DEFAULT_DATA = true;

	private final String ITEM = "Item";
	private final String QUANTITY = "Quantity";
	private final String UNIT_PRICE = "Unit Price";
	private final String TOTAL = "Total Price";
	private final String MAIN_LABEL = "Cart Items";

	// button labels
	private final String PROCEED_BUTN = "Proceed To Checkout";
	private final String CONTINUE = "Continue Shopping";
	private final String SAVE_CART = "Save Cart";
	private final String EXIT_BUTN = "Exit E-Bazaar";

	private TableView<OrderItem> table3 = new TableView<OrderItem>();
	ObservableList<OrderItem> orderlist = FXCollections.observableArrayList();
	List<OrderItem> orderlistAll = new ArrayList<OrderItem>();
	// History
	private TableView<OrderHistory> table4 = new TableView<OrderHistory>();
	ObservableList<OrderHistory> orderHis = FXCollections.observableArrayList();
	public static ObservableMap<OrderHistory, List<OrderItem>> orderHistoryDetails = FXCollections
			.observableHashMap();

	private double totalAmountofOrder;
	private TableView<OrderItem> table5 = new TableView<OrderItem>();

	OrderHistory selectorderdetails = null;

	public EbazaarMainFrame() {

	}

	public static void main(String[] args) {
		launch(args);
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public void start(Stage stage) throws Exception {

		this.stage = stage;
		stage.setTitle(EBAZAAR_APP_NAME);
		Group root = new Group();
		Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
		Scene scene = new Scene(root, visualBounds.getWidth(),
				visualBounds.getHeight() - 25, Color.WHITE);

		SplitPane splitPane = new SplitPane();
		splitPane.prefWidthProperty().bind(scene.widthProperty());
		splitPane.prefHeightProperty().bind(scene.heightProperty());
		VBox leftArea = new VBox(10);

		// Sitemenu
		TreeItem<String> rootItem = new TreeItem<String>(CUSTOMER);
		rootItem.setExpanded(true);
		TreeItem<String> item1 = new TreeItem<String>(ONLINE_PURCHASE);
		// item1.addEventHandler(MouseEvent e, e->EventHandler::NEW);

		TreeItem<String> item2 = new TreeItem<String>(REVIEW_ORDERS);
		TreeItem<String> item3 = new TreeItem<String>(RETRIEVE_CART);
		rootItem.getChildren().addAll(item1, item2, item3);

		TreeView<String> tree = new TreeView<String>(rootItem);
		tree.setId("left-tree");

		leftArea.setSpacing(10);
		leftArea.getChildren().add(tree);
		leftArea.setId("left-side");

		SplitPane splitPane2 = new SplitPane();
		splitPane2.setOrientation(Orientation.VERTICAL);
		splitPane2.prefWidthProperty().bind(scene.widthProperty());
		splitPane2.prefHeightProperty().bind(scene.heightProperty());

		HBox centerArea = new HBox();
		centerArea.setId("topPanelDesign");
		
		Label label1 = new Label();
		Image image = new Image(getClass().getResourceAsStream("5.png"));
		label1.setGraphic(new ImageView(image));
		label1.setTextFill(Color.web("#0076a3"));
		
		Text upperRight = new Text("Welcome to E-Bazaar");
		upperRight.setId("welcome-page");
		centerArea.getChildren().addAll(label1,upperRight);//add(upperRight);

		VBox rightArea = new VBox();

		/* start tab */
		tabPane = new TabPane();
		
		BorderPane borderPane = new BorderPane();
		Tab tab = new Tab();
		tab.setText("Home");
		VBox tabhbox = new VBox();
		tab.setContent(tabhbox);

		purchase = new Tab();
		purchase.setText(ONLINE_PURCHASE);

		review = new Tab();
		review.setText(REVIEW_ORDERS);

		retrieve = new Tab();
		retrieve.setText(RETRIEVE_CART);
		tabPane.getTabs().addAll(tab, purchase, review, retrieve);
		/* End tab */

		// bind to take available space
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());
		borderPane.setCenter(tabPane);
		rightArea.getChildren().add(borderPane);

		splitPane2.getItems().add(centerArea);
		splitPane2.getItems().add(rightArea);
		//splitPane2.setId("topPanelDesign");

		splitPane.getItems().add(leftArea);
		splitPane.getItems().add(splitPane2);

		ObservableList<SplitPane.Divider> dividers = splitPane.getDividers();
		for (int i = 0; i < dividers.size(); i++) {
			dividers.get(i).setPosition((i + 1.0) / 7);
		}

		ObservableList<SplitPane.Divider> dividers2 = splitPane2.getDividers();
		for (int i = 0; i < dividers2.size(); i++) {
			dividers2.get(i).setPosition((i + 1.0) / 7);
		}

		HBox hbox = new HBox();
		hbox.getChildren().add(splitPane);

		// menubar
		MenuBar menuBar = new MenuBar();
		// menu
		Menu menuCustomer = new Menu(CUSTOMER);
		Menu menuAdministrator = new Menu(ADMINISTRATOR);

		MenuItem menuItemPurchaseOnline = new MenuItem(ONLINE_PURCHASE);
		MenuItem menuItemRevOrders = new MenuItem(REVIEW_ORDERS);
		MenuItem menuItemRetrieveSavedCart = new MenuItem(RETRIEVE_CART);
		MenuItem menuItemExit = new MenuItem(EXIT);

		MenuItem menuItemMaintainProduct = new MenuItem(MAINTAIN_CATALOGUE);
		MenuItem menuItemMaintainCatalogTypes = new MenuItem(MAINTAIN_CAT_TYPES);

		/* Menu action start */
		menuItemExit.setOnAction(evt -> Platform.exit());
		menuItemPurchaseOnline.setOnAction(evt -> {
			tabPane.getSelectionModel().select(purchase);
		});

		menuItemRevOrders.setOnAction(evt -> {
			tabPane.getSelectionModel().select(review);
		});

		menuItemRetrieveSavedCart.setOnAction(evt -> {
			tabPane.getSelectionModel().select(retrieve);
		});

		menuItemMaintainProduct.setOnAction(evt -> {
			ProductView pv=new ProductView(this,stage);
			pv.show();
		});

		menuItemMaintainCatalogTypes.setOnAction(evt -> {
			CatalogView cv=new CatalogView(this,stage);
			cv.show();
		});
		/* End Menu action start */

		menuCustomer.getItems().addAll(menuItemPurchaseOnline,
				menuItemRevOrders, menuItemRetrieveSavedCart, menuItemExit);
		menuAdministrator.getItems().addAll(menuItemMaintainProduct,
				menuItemMaintainCatalogTypes);
		menuBar.getMenus().addAll(menuCustomer, menuAdministrator);

		// get All Category
		getProductCategory();
		// purchase.setContent(grid);

		VBox vBox = new VBox();
		vBox.getChildren().add(menuBar);
		vBox.getChildren().add(hbox);
		root.getChildren().add(vBox);

		scene.getStylesheets().add(
				getClass().getResource("ebazeer.css").toExternalForm());

		stage.setScene(scene);
		stage.show();
	}

	@SuppressWarnings("unchecked")
	public void getProductCategory() {

		/* Start purchase Tab All Windoes */

		ObservableList<Catalog> cats = DefaultData.CATALOG_LIST_DATA;
		table.setItems(cats);

		final Label label = new Label("Browse Catalogs");
		label.setFont(new Font("Arial", 16));
		HBox labelHbox = new HBox(10);
		labelHbox.setAlignment(Pos.CENTER);
		labelHbox.getChildren().add(label);

		TableColumn<Catalog, String> catalogNameCol = new TableColumn<>(
				"Catalog");
		catalogNameCol.setMinWidth(250);
		catalogNameCol
				.setCellValueFactory(new PropertyValueFactory<Catalog, String>(
						"name"));
		catalogNameCol.setCellFactory(TextFieldTableCell.forTableColumn());

		table.getColumns().addAll(catalogNameCol);

		Button viewButton = new Button("View Catalog");

		grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(10);
		grid.setHgap(10);
		grid.add(labelHbox, 0, 0);
		grid.add(table, 0, 1);
		HBox btnBox = new HBox(10);
		btnBox.setAlignment(Pos.CENTER);
		btnBox.getChildren().add(viewButton);
		grid.add(btnBox, 0, 3);

		purchase.setContent(grid);
		viewButton.setOnAction(evt -> {
			grid.getChildren().remove(2);
			selected = table.getSelectionModel().getSelectedItem();
			setProductList(selected);

		});

	}

	@SuppressWarnings("unchecked")
	public void setProductList(Catalog selectedd) {
		// product List
		List<Product> aproduct = DefaultData.PRODUCT_LIST_DATA.get(selectedd);
		
		table2.setItems(FXCollections.observableList(aproduct));

		final Label labelProduct = new Label("Product List");
		labelProduct.setFont(new Font("Arial", 16));
		HBox labelHboxproduct = new HBox(10);
		labelHboxproduct.setAlignment(Pos.CENTER);
		labelHboxproduct.getChildren().add(labelProduct);
		grid.add(labelHboxproduct, 5, 0);

		Button viewButton = new Button("Select Product");
		HBox btnBox = new HBox(10);
		btnBox.setAlignment(Pos.CENTER);
		btnBox.getChildren().add(viewButton);

		TableColumn<Product, String> catalogNameCol2 = new TableColumn<>(
				"Product");
		catalogNameCol2.setMinWidth(250);
		catalogNameCol2
				.setCellValueFactory(new PropertyValueFactory<Product, String>(
						"productName"));
		catalogNameCol2.setCellFactory(TextFieldTableCell.forTableColumn());
		table2.getColumns().addAll(catalogNameCol2);
		grid.add(table2, 5, 1);

		grid.add(btnBox, 5, 3);
		viewButton.setOnAction(evt -> {
			selectedProduct = table2.getSelectionModel().getSelectedItem();
			getProductDetails(selectedProduct);

		});

	}

	@SuppressWarnings("deprecation")
	public void getProductDetails(Product selectproduct) {

		// Label label = new Label(String.format("%s: Product Details",
		// selectproduct.getProductName()));
		Label label = new Label(String.format("%s", "Product Details"));

		label.setFont(new Font("Arial", 16));
		HBox labelHbox = new HBox(10);
		labelHbox.setAlignment(Pos.CENTER);
		labelHbox.getChildren().add(label);

		// prepare display grid
		List<String> displayValues = Arrays.asList(
				selectproduct.getProductName(),
				(new Double(selectproduct.getUnitPrice())).toString(),
				(new Integer(selectproduct.getQuantityAvail())).toString(),
				selectproduct.getDescription());
		FourByTwoGridPane dataTable = new FourByTwoGridPane(
				DefaultData.DISPLAY_PRODUCT_FIELDS, displayValues, "darkblue");

		Button addToCartButton = new Button("Add to Cart");

		grid.setAlignment(Pos.CENTER);
		grid.setVgap(10);
		grid.setHgap(10);
		grid.add(labelHbox, 10, 0);
		grid.add(dataTable, 10, 1);
		HBox btnBox = new HBox(10);
		btnBox.setAlignment(Pos.CENTER);
		btnBox.getChildren().add(addToCartButton);
		grid.add(btnBox, 10, 3);

		Label lblTitle = new Label("Enter Quantity :");
		TextField quantity = new TextField();
		Button btnQuantity = new Button("Add Quantity");

		Stage dialogStage = new Stage();
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.setScene(new Scene(VBoxBuilder.create()
				.children(lblTitle, quantity, btnQuantity)
				.alignment(Pos.CENTER).padding(new Insets(5)).build()));

		addToCartButton.setOnAction(evt -> {
			dialogStage.show();

		});

		btnQuantity.setOnAction(evt -> {
			gridRemoveAll();

			// Map Data set with product and Quantity
				dialogStage.hide();
				getProductCategory();
				tabPane.getSelectionModel().select(retrieve);
				table3.getColumns().clear();

				showAllProductInCart(quantity.getText(), selectproduct);
			});
	}

	// Show All product in Cart
	@SuppressWarnings("unchecked")
	public void showAllProductInCart(String qty, Product selcproduct) {

		int quantity = Integer.parseInt(qty);
		double totalPrice = quantity * selcproduct.getUnitPrice();
		OrderItem item1 = new OrderItem(selcproduct.getProductName(), quantity,
				selcproduct.getUnitPrice(), totalPrice);
		System.out.println(selcproduct.getProductName() + ","
				+ selcproduct.getUnitPrice() + "," + quantity);
		//
		addAmount(totalPrice);

		orderlist.add(item1);
		orderlistAll.add(item1);
		table3.setItems(orderlist);

		final Label label = new Label(MAIN_LABEL);
		label.setFont(new Font("Arial", 16));
		HBox labelHbox = new HBox(10);
		labelHbox.setAlignment(Pos.CENTER);
		labelHbox.getChildren().add(label);

		TableColumn<OrderItem, String> cartItemCol = new TableColumn<>(ITEM);
		cartItemCol.setMinWidth(250);
		cartItemCol
				.setCellValueFactory(new PropertyValueFactory<OrderItem, String>(
						"pname"));
		cartItemCol.setCellFactory(TextFieldTableCell.forTableColumn());

		TableColumn<OrderItem, Integer> cartQuantityCol = new TableColumn<>(
				QUANTITY);
		cartQuantityCol.setMinWidth(250);
		cartQuantityCol
				.setCellValueFactory(new PropertyValueFactory<OrderItem, Integer>(
						"quantity"));
		// cartQuantityCol.setCellFactory(TextFieldTableCell.forTableColumn());

		TableColumn<OrderItem, Double> cartUnitPriceCol = new TableColumn<>(
				UNIT_PRICE);
		cartUnitPriceCol.setMinWidth(250);
		cartUnitPriceCol
				.setCellValueFactory(new PropertyValueFactory<OrderItem, Double>(
						"unitprice"));
		// cartUnitPriceCol.setCellFactory(TextFieldTableCell.forTableColumn());

		TableColumn<OrderItem, Double> cartTotalPriceCol = new TableColumn<>(
				TOTAL);
		cartTotalPriceCol.setMinWidth(250);
		cartTotalPriceCol
				.setCellValueFactory(new PropertyValueFactory<OrderItem, Double>(
						"totalPrice"));
		// cartTotalPriceCol.setCellFactory(TextFieldTableCell.forTableColumn());

		table3.getColumns().addAll(cartItemCol, cartQuantityCol,
				cartUnitPriceCol, cartTotalPriceCol); //

		Button btnCheckout = new Button(PROCEED_BUTN);
		Button btnShopping = new Button(CONTINUE);
		Button btnSave = new Button(SAVE_CART);
		// Button btnCheckout = new Button("Proceed to Check0ut");

		grid2 = new GridPane();
		grid2.setAlignment(Pos.CENTER);
		grid2.setVgap(10);
		grid2.setHgap(10);
		grid2.add(labelHbox, 0, 0);
		grid2.add(table3, 0, 1);
		HBox btnBox = new HBox(10);
		btnBox.setAlignment(Pos.CENTER);
		btnBox.getChildren().addAll(btnCheckout, btnShopping, btnSave);
		grid2.add(btnBox, 0, 3);
		retrieve.setContent(grid2);

		btnShopping.setOnAction(evt -> {
			tabPane.getSelectionModel().select(purchase);
		});

		btnCheckout.setOnAction(evt -> {
			ShippingBillingWindow shipAddress = new ShippingBillingWindow(
					stage, this);
			// catalogs.setData(DefaultData.CATALOG_LIST_DATA);
				shipAddress.show();
				// primaryStage.hide();
			});

	}

	// Checkout
	@SuppressWarnings({ "unchecked" })
	public void OderHistory(String rid) {
		table4.getColumns().clear();
		double totalAmounts = getTotalAmountofOrder();
		System.out.println(totalAmounts);

		String sdate = "1/9/2014";
		OrderHistory order1 = new OrderHistory(rid, sdate, totalAmounts);
		orderHis.add(order1);
		table4.setItems(orderHis);
		orderHistoryDetails.put(order1, orderlist);
		setAmountEmplty();

		final Label label = new Label("Order History");
		label.setFont(new Font("Arial", 16));
		HBox labelHbox = new HBox(10);
		labelHbox.setAlignment(Pos.CENTER);
		labelHbox.getChildren().add(label);

		TableColumn<OrderHistory, String> orderIdNameCol = new TableColumn<>(
				"Order Id");
		orderIdNameCol.setMinWidth(100);
		orderIdNameCol
				.setCellValueFactory(new PropertyValueFactory<OrderHistory, String>(
						"orderid"));
		orderIdNameCol.setCellFactory(TextFieldTableCell.forTableColumn());

		TableColumn<OrderHistory, String> catalogNameCol2 = new TableColumn<>(
				"Date");
		catalogNameCol2.setMinWidth(100);
		catalogNameCol2
				.setCellValueFactory(new PropertyValueFactory<OrderHistory, String>(
						"dates"));

		TableColumn<OrderHistory, Double> catalogNameCol3 = new TableColumn<>(
				"Total Amount");
		catalogNameCol3.setMinWidth(100);
		catalogNameCol3
				.setCellValueFactory(new PropertyValueFactory<OrderHistory, Double>(
						"totalAmount"));

		table4.getColumns().addAll(orderIdNameCol, catalogNameCol2,
				catalogNameCol3);
		Button viewButton = new Button("Order Details");

		gridHistory = new GridPane();
		gridHistory.setAlignment(Pos.CENTER);
		gridHistory.setVgap(10);
		gridHistory.setHgap(10);
		gridHistory.add(labelHbox, 0, 0);
		gridHistory.add(table4, 0, 1);
		HBox btnBox = new HBox(10);
		btnBox.setAlignment(Pos.CENTER);
		btnBox.getChildren().add(viewButton);
		gridHistory.add(btnBox, 0, 3);
		review.setContent(gridHistory);

		viewButton.setOnAction(evt -> {

			selectorderdetails = table4.getSelectionModel().getSelectedItem();
			table5.getColumns().clear();
			OrderDetailswithHistory(selectorderdetails);

		});
	}

	@SuppressWarnings("unchecked")
	public void OrderDetailswithHistory(OrderHistory selectorderdetails) {
		List<OrderItem> orderHismain = orderHistoryDetails
				.get(selectorderdetails);
		table5.setItems(FXCollections.observableList(orderHismain));

		final Label label = new Label("Order Details");
		label.setFont(new Font("Arial", 16));
		HBox labelHbox = new HBox(10);
		labelHbox.setAlignment(Pos.CENTER);
		labelHbox.getChildren().add(label);

		TableColumn<OrderItem, String> cartItemCol = new TableColumn<>(ITEM);
		cartItemCol.setMinWidth(100);
		cartItemCol
				.setCellValueFactory(new PropertyValueFactory<OrderItem, String>(
						"pname"));
		cartItemCol.setCellFactory(TextFieldTableCell.forTableColumn());

		TableColumn<OrderItem, Integer> cartQuantityCol = new TableColumn<>(
				QUANTITY);
		cartQuantityCol.setMinWidth(100);
		cartQuantityCol
				.setCellValueFactory(new PropertyValueFactory<OrderItem, Integer>(
						"quantity"));
		// cartQuantityCol.setCellFactory(TextFieldTableCell.forTableColumn());

		TableColumn<OrderItem, Double> cartUnitPriceCol = new TableColumn<>(
				UNIT_PRICE);
		cartUnitPriceCol.setMinWidth(100);
		cartUnitPriceCol
				.setCellValueFactory(new PropertyValueFactory<OrderItem, Double>(
						"unitprice"));
		// cartUnitPriceCol.setCellFactory(TextFieldTableCell.forTableColumn());

		TableColumn<OrderItem, Double> cartTotalPriceCol = new TableColumn<>(
				TOTAL);
		cartTotalPriceCol.setMinWidth(100);
		cartTotalPriceCol
				.setCellValueFactory(new PropertyValueFactory<OrderItem, Double>(
						"totalPrice"));
		// cartTotalPriceCol.setCellFactory(TextFieldTableCell.forTableColumn());

		table5.getColumns().addAll(cartItemCol, cartQuantityCol,
				cartUnitPriceCol, cartTotalPriceCol);
		gridHistory.add(labelHbox, 1, 0);
		gridHistory.add(table5, 1, 1);

	}

	// all remove
	public void gridRemoveAll() {
		grid.getChildren().clear();
		table.getColumns().clear();
		table2.getColumns().clear();
	}

	//
	public void gridRemoveWithCart() {

		orderlist.clear();
		grid2.getChildren().clear();
		table3.getColumns().clear();
	}

	public double getTotalAmountofOrder() {
		return totalAmountofOrder;
	}

	public void addAmount(double value) {
		totalAmountofOrder += value;
	}

	public void setAmountEmplty() {
		totalAmountofOrder = 0.0;
	}

}
