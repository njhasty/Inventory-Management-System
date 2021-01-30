package Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import static Controller.MainMenuController.*;
import static Model.Inventory.getAllParts;
import static Model.Inventory.getAllProducts;
/** This class manages modifying selected product of product list */
public class ModifyProductController implements Initializable {

    private ObservableList<Part> currentParts = FXCollections.observableArrayList();
    private int productIndex = productToModifyIndex();
    Stage stage;
    Parent scene;
    private int productID;


    @FXML
    private TextField productSearchIDField;


    @FXML
    private Button modifyProductSearchBtn;

    @FXML
    private TextField modifyProductIDField;

    @FXML
    private TextField modifyProductNameField;

    @FXML
    private TextField modifyStockField;

    @FXML
    private TextField modifyMaxField;

    @FXML
    private TextField modifyMinField;

    @FXML
    private TextField modifyPriceCostField;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button removeAssocPartButton;

    @FXML
    private Button addButton;

    @FXML
    private TableView<Part> partsInStockTableView;

    @FXML
    private TableColumn<Part, Integer> partsInStockPartIdCol;

    @FXML
    private TableColumn<Part, String> partsInStockPartNameCol;

    @FXML
    private TableColumn<Part, Integer> partsInStockStockCol;

    @FXML
    private TableColumn<Part, Double> partsInStockPriceCpuCol;

    @FXML
    private TableView<Part> groupedPartsTableView;

    @FXML
    private TableColumn<Part, Integer> groupedPartsPartIdCol;

    @FXML
    private TableColumn<Part, String> groupedPartsPartNameCol;

    @FXML
    private TableColumn<Part, Integer> groupedPartsStockCol;

    @FXML
    private TableColumn<Part, Double> groupedPartsPriceCpuCol;


    /** This is the add button event handler.
     * Method to add parts to groupedPartsTableView view.
     * @param event  The arguments mouse event.
     * @return void. */
    @FXML
    void addButtonClick(MouseEvent event) {
        Part addedPart = partsInStockTableView.getSelectionModel().getSelectedItem();
        currentParts.add(addedPart);
        updateGroupedPartsTableView();
    }

    /** This is the cancel button click event handler.
     * Method to cancel modify products, return to main menu.
     * @param event  The arguments mouse event.
     * @return void. */
    @FXML
    void cancelButtonClick(MouseEvent event) throws IOException {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exit to Main Screen");
            alert.setHeaderText("Are you sure you want to cancel?");
            alert.setContentText("Press OK to exit to the Main screen. Press Cancel to stay on this screen");
            alert.showAndWait();

            if (alert.getResult() == ButtonType.OK) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/View/MainMenu.fxml"));
                loader.load();
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Parent scene = loader.getRoot();
                stage.setScene(new Scene(scene));
                stage.showAndWait();
            } else {
                alert.close();
            }
        } catch (IOException e) {}
    }

    /** This is the remove associated part button click event handler.
     * Method to remove part from products list.
     * @param event  The arguments mouse event.
     * @return void. */
    @FXML
    void removeAssocPartButtonClick(MouseEvent event) {
        Part partToDelete = groupedPartsTableView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Part Deletion");
        alert.setHeaderText("Are you sure you want to delete part?");
        alert.setContentText("Are you sure you want to delete this part?");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            currentParts.remove(partToDelete);
        } else {
            Alert alertCancelDelete = new Alert(Alert.AlertType.CONFIRMATION);
            alertCancelDelete.setTitle("Confirm Part Deletion");
            alertCancelDelete.setHeaderText("Part not deleted");
            alertCancelDelete.setContentText("Part deletion cancelled");
            alertCancelDelete.showAndWait();
        }
    }

    /** This is the save button click event handler.
     * Method to save modified product, return to main menu.
     * @param event  The arguments mouse event.
     * @return void. */
    @FXML
    void saveButtonClick(MouseEvent event) throws IOException {
        int productID = Inventory.getAllProducts().get(productIndex).getProductID();
        String productName = modifyProductNameField.getText();
        int stock = Integer.parseInt(modifyStockField.getText());
        int max = Integer.parseInt(modifyMaxField.getText());
        int min = Integer.parseInt(modifyMinField.getText());
        double price = Double.parseDouble(modifyPriceCostField.getText());

        if (productName.equals("")) {
            Alert alertNameNull = new Alert(Alert.AlertType.WARNING);
            alertNameNull.setTitle("Modify Product Error");
            alertNameNull.setHeaderText("Product Name cannot be empty");
            alertNameNull.setContentText("Please type name of Product");
            alertNameNull.showAndWait();
        }
        if (stock < 1) {
            Alert alertNameNull = new Alert(Alert.AlertType.WARNING);
            alertNameNull.setTitle("Modify Product Error");
            alertNameNull.setHeaderText("Product stock must be greater than 1");
            alertNameNull.setContentText("Please type correct stock number");
            alertNameNull.showAndWait();
        }
        if (price <= 0.00) {
            Alert alertNameNull = new Alert(Alert.AlertType.WARNING);
            alertNameNull.setTitle("Modify Product Error");
            alertNameNull.setHeaderText("Product price cannot be less than or equal to 0.00");
            alertNameNull.setContentText("Please type correct price");
            alertNameNull.showAndWait();
        }
        if (max < min) {
            Alert alertNameNull = new Alert(Alert.AlertType.WARNING);
            alertNameNull.setTitle("Modify Product Error");
            alertNameNull.setHeaderText("Product max cannot be less than min");
            alertNameNull.setContentText("Please type correct max and min");
            alertNameNull.showAndWait();
        }
        if (stock < min || stock > max) {
            Alert alertNameNull = new Alert(Alert.AlertType.WARNING);
            alertNameNull.setTitle("Modify Product Error");
            alertNameNull.setHeaderText("Product stock cannot be less than min or greater than max");
            alertNameNull.setContentText("Please type correct stock number");
            alertNameNull.showAndWait();
        } else {
            Product newProduct = new Product(productID, productName, price, stock, min, max);
            newProduct.setProductID(productID);
            newProduct.setName(productName);
            newProduct.setStock(stock);
            newProduct.setMax(max);
            newProduct.setMin(min);
            newProduct.setPrice(price);
            Inventory.updateProduct(productIndex, newProduct);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View/MainMenu.fxml"));
            loader.load();
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.showAndWait();
        }
    }

    /** This is the product search button click event handler.
     * Method to search partsInStockTableView for ID or name.
     * @param event  The arguments mouse event.
     * @return void. */
    public void clickModifyProductSearchBtn(MouseEvent event) throws IOException {
        String searchPartIDString = productSearchIDField.getText();
        String searchPartNameLC = searchPartIDString.toLowerCase();
        Part searchPartName = Inventory.lookupPartString(searchPartNameLC);
        ObservableList<Part> searchedPartsNameList = FXCollections.observableArrayList();
        searchedPartsNameList.add(searchPartName);
        partsInStockTableView.setItems(searchedPartsNameList);

        if (searchPartIDString.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Product search warning");
            alert.setHeaderText("No products entered");
            alert.setContentText("Please enter product");
            alert.showAndWait();
            partsInStockTableView.setItems(getAllParts());
        } else if (searchPartIDString != null) {
            Part searchPartID = Inventory.lookupPart(Integer.parseInt(searchPartIDString));
            ObservableList<Part> searchedPartsIdList = FXCollections.observableArrayList();
            searchedPartsIdList.add(searchPartID);
            partsInStockTableView.setItems(searchedPartsIdList);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Product search warning");
            alert.setHeaderText("No products entered match");
            alert.setContentText("Searched term does not match product ID or name");
            alert.showAndWait();
            partsInStockTableView.setItems(getAllParts());
        }
    }


    /** This updates updatePartsInStockTableView.
     * Method to update PartsInStockTableView.
     * @return void. */
    public void updatePartsInStockTableView() {
        partsInStockTableView.setItems(getAllParts());
    }

    /** This updates updateGroupedPartsTableView.
     * Method to update GroupedPartsTableView.
     * @return void. */
    public void updateGroupedPartsTableView() {
        groupedPartsTableView.setItems(currentParts);
    }


    /** This is the initialize method for screen.
     * Method to match table views contents with columns, sets product ID field to Auto Gen - Disabled.
     * Sets contents of modify product fields to previously selected product.
     * @param url Pass url.
     * @param rb Pass rb.
     * @return void. */
    @Override
    public void initialize (URL url, ResourceBundle rb){
        Product product = getAllProducts().get(productIndex);
        modifyProductIDField.setText("Auto Gen - Disabled");
        modifyProductNameField.setText(product.getName());
        modifyStockField.setText(Integer.toString(product.getStock()));
        modifyPriceCostField.setText(Double.toString(product.getPrice()));
        modifyMaxField.setText(Integer.toString(product.getMax()));
        modifyMinField.setText(Integer.toString(product.getMin()));
        currentParts = product.getProductParts();


        partsInStockPartIdCol.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partsInStockPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partsInStockStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partsInStockPriceCpuCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        groupedPartsPartIdCol.setCellValueFactory(new PropertyValueFactory<>("partID"));
        groupedPartsPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        groupedPartsStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        groupedPartsPriceCpuCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        updatePartsInStockTableView();
        updateGroupedPartsTableView();

    }
}
