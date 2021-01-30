package Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static Model.Inventory.*;
/** This class manages navigation to sub-pages and search/delete
 * functions on table views */
public class MainMenuController implements Initializable {


    Stage stage;
    Parent scene;


    @FXML
    private TextField partsSearchTextField;

    @FXML
    private TextField productsSearchField;

    @FXML
    private Button productSearchBtn;

    @FXML
    private Button partSearchBtn;

    @FXML
    private AnchorPane mainMenu;

    @FXML
    private AnchorPane partsPane;

    @FXML
    private Button addPartsButton;

    @FXML
    private Button modifyPartsButton;

    @FXML
    private Button deletePartsButton;

    @FXML
    private TextField partsTextField;

    @FXML
    private AnchorPane productsPane;

    @FXML
    private Button deleteProductsButton;

    @FXML
    private Button modifyProductsButton;

    @FXML
    private Button addProductsButton;

    @FXML
    private Button exitMainMenuButton;

    @FXML
    private TableView<Part> partsTableView;

    @FXML
    private TableColumn<Part, Integer> partIDCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TableColumn<Part, Integer> partStockCol;

    @FXML
    private TableColumn<Part, Double> partPriceCpuCol;

    @FXML
    private TableView<Product> productsTableView;

    @FXML
    private TableColumn<Product, Integer> productIdCol;

    @FXML
    private TableColumn<Product, String> productNameCol;

    @FXML
    private TableColumn<Product, Integer> productStockCol;

    @FXML
    private TableColumn<Product, Double> productPriceCpuCol;


    /** This is the add parts button click event handler.
     * Method for adding parts screen.
     * @param event  The arguments mouse event.
     * @return void. */
    @FXML
    void addPartsButtonClick(MouseEvent event) throws IOException {
        try {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View/AddPart.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (IOException e) {
        }
    }

    /** This is the add products button click event handler.
     * Method for adding products screen.
     * @param event  The arguments mouse event.
     * @return void. */
    @FXML
    void addProductsButtonClick(MouseEvent event) throws IOException {
        try {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View/AddProduct.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (IOException e) {
        }
    }

    /** This is the delete part button click event handler.
     * Method for deleting selected part.
     * @param event  The arguments mouse event.
     * @return void. */
    @FXML
    private void deletePartsButtonClick(MouseEvent event) {
        Part selectedPart = partsTableView.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            Alert alertDeleted = new Alert(Alert.AlertType.CONFIRMATION);
            alertDeleted.setTitle("Part Delete Notification");
            alertDeleted.setHeaderText("Select part to delete");
            alertDeleted.setContentText("Select part to delete");
            alertDeleted.showAndWait();
        }
        else if (selectedPart != null) {
            Inventory.deletePart(selectedPart);
            Alert alertDeleted = new Alert(Alert.AlertType.CONFIRMATION);
            alertDeleted.setTitle("Part Delete Notification");
            alertDeleted.setHeaderText("Part was deleted");
            alertDeleted.setContentText("Part was deleted from list");
            alertDeleted.showAndWait();
        }
         else {
                Alert alertNull = new Alert(Alert.AlertType.ERROR);
                alertNull.setTitle("Part Delete Error");
                alertNull.setHeaderText("Part not deleted");
                alertNull.setContentText("Part not deleted from list");
                alertNull.showAndWait();
            }
    }


    /** This is the delete products button click event handler.
     * Method for deleting selected product.
     * @param event  The arguments mouse event.
     * @return void. */
    @FXML
    void deleteProductsButtonClick(MouseEvent event) {
        Product selectedProduct = productsTableView.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            Alert alertDeleted = new Alert(Alert.AlertType.CONFIRMATION);
            alertDeleted.setTitle("Product Delete Notification");
            alertDeleted.setHeaderText("Select product to delete");
            alertDeleted.setContentText("Select product to delete");
            alertDeleted.showAndWait();
        } else if (!selectedProduct.getProductParts().isEmpty()) {
            Alert alertDeleted = new Alert(Alert.AlertType.WARNING);
            alertDeleted.setTitle("Product Delete Notification");
            alertDeleted.setHeaderText("Product was not deleted");
            alertDeleted.setContentText("Product contains at least one part");
            alertDeleted.showAndWait();
        } else {
            Inventory.deleteProduct(selectedProduct);
            Alert alertDeleted = new Alert(Alert.AlertType.CONFIRMATION);
            alertDeleted.setTitle("Product Delete Notification");
            alertDeleted.setHeaderText("Product was deleted");
            alertDeleted.setContentText("Product was deleted from list");
            alertDeleted.showAndWait();
        }
    }

    /** This is the updatePartsTableView method.
     * Method to retrieve all parts and set them into table view.
     * @return partsTableView. */
    @FXML
    public void updatePartsTableView () {
        partsTableView.setItems(getAllParts());
    }


    /** This is the updateProductsTableView method.
     * Method for retrieve all products and set them into table view.
     * @return productsTableView. */
    @FXML
    public void updateProductsTableView () {
        productsTableView.setItems(getAllProducts());
    }


        /** This is the exit main menu button click event handler.
         * Method for exiting program.
         * @param event  The arguments mouse event.
         * @return void. */
        @FXML
        void exitMainMenuButtonClick (MouseEvent event){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exit program");
            alert.setHeaderText("Are you sure you want to exit?");
            alert.setContentText("Press OK to exit. Press Cancel to stay");
            alert.showAndWait();

            if (alert.getResult() == ButtonType.OK) {
                System.exit(0);
            }
            else {
                alert.close();
            }
        }


    private static Part modifyPart;
    private static int modifyPartIndex;

    /** This is the partToModifyIndex method.
     * Method for selected parts to modify.
     * @return modifyPartIndex. */
    public static int partToModifyIndex() {
        return modifyPartIndex;
    }

    /** This is the modify parts button click event handler.
     * Method to navigate to modify parts sub menu.
     * @param event  The arguments mouse event.
     * @return void. */
    @FXML
        void modifyPartsButtonClick(MouseEvent event) throws IOException {
            modifyPart = partsTableView.getSelectionModel().getSelectedItem();
            modifyPartIndex = getAllParts().indexOf(modifyPart);


            if (modifyPart == null) {
                Alert alertNull = new Alert(Alert.AlertType.ERROR);
                alertNull.setTitle("Part Modify Error");
                alertNull.setHeaderText("Part not modifiable");
                alertNull.setContentText("No Part modifiable");
                alertNull.showAndWait();
            } else {
                try {
                    System.out.println("On modify parts clicked");
                    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("/View/ModifyPart.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.show();
                }
                catch (IOException e) {}
            }
        }


    private static Product modifySelProduct;
    private static int modifySelProductIndex;

    /** This is the productToModifyIndex method.
     * Method for selected products to modify.
     * @return modifySelProductIndex. */
    public static int productToModifyIndex() {
        return modifySelProductIndex;
    }

        /** This is the clicking modify button click event handler.
         * Method to navigate to modify products sub menu.
         * @param event  The arguments mouse event.
         * @return void. */
        @FXML
        void modifyProductsButtonClick (MouseEvent event) throws IOException {
            modifySelProduct = productsTableView.getSelectionModel().getSelectedItem();
            modifySelProductIndex = getAllProducts().indexOf(modifySelProduct);


            if (modifySelProduct == null) {
                Alert nullAlert = new Alert(Alert.AlertType.ERROR);
                nullAlert.setTitle("Product Modify Error");
                nullAlert.setHeaderText("Product not modifiable");
                nullAlert.setContentText("No Product modifiable");
                nullAlert.showAndWait();
            } else {
                try {
                    System.out.println("On modify products clicked");
                    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("/View/ModifyProduct.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.show();
                } catch (IOException e) {}
            }
        }

        /** This is the part search button click event handler.
         * Method to search parts for ID or name.
         * @param event  The arguments mouse event.
         * @return void. */
        public void clickPartSearchBtn (MouseEvent event) throws IOException {
            String searchPartIDString = partsSearchTextField.getText();
            String searchPartNameLC = searchPartIDString.toLowerCase();
            Part searchPartName = Inventory.lookupPartString(searchPartNameLC);
            ObservableList<Part> searchedPartsNameList = FXCollections.observableArrayList();
            searchedPartsNameList.add(searchPartName);
            partsTableView.setItems(searchedPartsNameList);

            if (searchPartIDString.equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Product search warning");
                alert.setHeaderText("No products entered");
                alert.setContentText("Please enter product");
                alert.showAndWait();
                partsTableView.setItems(getAllParts());
            } else if (searchPartIDString != null) {
                Part searchPartID = Inventory.lookupPart(Integer.parseInt(searchPartIDString));
                ObservableList<Part> searchedPartsIdList = FXCollections.observableArrayList();
                searchedPartsIdList.add(searchPartID);
                partsTableView.setItems(searchedPartsIdList);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Part search warning");
                alert.setHeaderText("No parts entered match");
                alert.setContentText("Searched term does not match part ID or name");
                alert.showAndWait();
                partsTableView.setItems(getAllParts());
            }
        }

        /** This is the product search button click event handler.
         * Method to search products for ID or name.
         * @param event  The arguments mouse event.
         * @return void. */
        public void clickProductSearchBtn (MouseEvent event){
            String searchProductIDString = productsSearchField.getText();
            String searchProductNameLC = searchProductIDString.toLowerCase();
            Product searchProductName = Inventory.lookupProductString(searchProductNameLC);
            ObservableList<Product> searchedProductsNameList = FXCollections.observableArrayList();
            searchedProductsNameList.add(searchProductName);
            productsTableView.setItems(searchedProductsNameList);

            if (searchProductIDString.equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Product search warning");
                alert.setHeaderText("No products entered");
                alert.setContentText("Please enter product");
                alert.showAndWait();
                productsTableView.setItems(getAllProducts());
            } else if (searchProductIDString != null) {
                Product searchProductID = Inventory.lookupProduct(Integer.parseInt(searchProductIDString));
                ObservableList<Product> searchedProductIdList = FXCollections.observableArrayList();
                searchedProductIdList.add(searchProductID);
                productsTableView.setItems(searchedProductIdList);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Product search warning");
                alert.setHeaderText("No products entered match");
                alert.setContentText("Searched term does not match product ID or name");
                alert.showAndWait();
                productsTableView.setItems(getAllProducts());
            }
        }



    private static Part selectedPart;

    private static int selectedPartIndex;

    /** This is the getSelectedPart method.
     * @return selectedPart */
    public static Part getSelectedPart () {
        return selectedPart;
    }

    /** This is the getSelectedPartIndex method.
     * @return selectedPartIndex */
    public static int getSelectedPartIndex () {
        return selectedPartIndex;
    }

    private static Product selectedProduct;

    private static int selectedProductIndex;

    /** This is the getSelectedProduct method.
     * @return selectedProduct */
    public static Product getSelectedProduct() {
        return selectedProduct;
    }

    /** This is the getSelectedProductIndex method.
     * @return selectedProductIndex */
    public static int getSelectedProductIndex() {
        return selectedProductIndex;
    }

    /** This is the productsSearch event handler.
     * @param actionEvent  The arguments mouse event.
     * @return void. */
    public void productsSearch(ActionEvent actionEvent) {
    }

    /** This is the partsSearch event handler.
     * @param actionEvent  The arguments mouse event.
     * @return void. */
    public void partsSearch(ActionEvent actionEvent) {
    }


    /** This is the initialize method for screen.
     * Method to set table views contents with columns, matches table views with columns.
     * @param url Pass url.
     * @param rb Pass rb.
     * @return void. */
    @Override
    public void initialize (URL url, ResourceBundle rb){
        partIDCol.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCpuCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        updatePartsTableView();
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCpuCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        updateProductsTableView();
    }
}










