package Controller;

import Model.InHousePart;
import Model.Inventory;
import Model.OutSourcedPart;
import Model.Part;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static Controller.MainMenuController.*;
import static Model.Inventory.*;
/** This class manages modifying selected part of parts list */
public class ModifyPartController implements Initializable {

    private int partIndex = partToModifyIndex();
    Stage stage;
    Parent scene;

    @FXML
    private TextField modMachineIDField;

    @FXML
    private Text partMachineID;

    @FXML
    private ToggleGroup modifyPartTG;

    @FXML
    private TextField modPartIdField;

    @FXML
    private TextField modPartNameField;

    @FXML
    private TextField modPartStockField;

    @FXML
    private TextField modPartMaxField;

    @FXML
    private TextField modPartMinField;

    @FXML
    private TextField modPartPriceCostField;

    @FXML
    private RadioButton inHouseRadio;

    @FXML
    private RadioButton outsourcedRadio;

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField invField;

    @FXML
    private TextField maxField;

    @FXML
    private TextField minField;

    @FXML
    private TextField priceCostField;

    @FXML
    private TextField machineIDField;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    private int partID;


    /** This is the cancel button click event handler.
     * Method to cancel modified part, return to main screen.
     * @param event  The arguments mouse event.
     * @return void. */
    @FXML
    void cancelButtonClick(MouseEvent event) throws IOException {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exit to Main Screen");
            alert.setHeaderText("Are you sure you want to cancel modify part?");
            alert.setContentText("Press OK to exit to the main screen. Press cancel to stay on this screen.");
            alert.showAndWait();

            if (alert.getResult() == ButtonType.OK) {
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            } else {
                alert.close();
            }
        } catch (IOException e) {
        }
    }

    /** This is the save button click event handler.
     * Method to save modified part, return to main screen.
     * @param event  The arguments mouse event.
     * @return void. */
    @FXML
    void saveButtonClick(MouseEvent event) throws IOException {
        int partID = getAllParts().get(partIndex).getPartID();
        String partName = modPartNameField.getText();
        int stock = Integer.parseInt(modPartStockField.getText());
        int max = Integer.parseInt(modPartMaxField.getText());
        int min = Integer.parseInt(modPartMinField.getText());
        double price = Double.parseDouble(modPartPriceCostField.getText());
        int machineID = Integer.parseInt(modMachineIDField.getText());
        String companyName = modMachineIDField.getText();


            if (partName.equals("") || partName.equals(null)) {
                Alert alertNameNull = new Alert(Alert.AlertType.WARNING);
                alertNameNull.setTitle("Modify Part Error");
                alertNameNull.setHeaderText("Part Name cannot be empty");
                alertNameNull.setContentText("Please type name of Part");
                alertNameNull.showAndWait();
            }
            if (stock < min || stock > max) {
                Alert alertStockMaxMin = new Alert(Alert.AlertType.WARNING);
                alertStockMaxMin.setTitle("Modify Part Error");
                alertStockMaxMin.setHeaderText("Inv must be greater than min, less than max");
                alertStockMaxMin.setContentText("Please type correct amount for stock");
                alertStockMaxMin.showAndWait();
            }
            if (max < min) {
                Alert alertStockMaxMin = new Alert(Alert.AlertType.WARNING);
                alertStockMaxMin.setTitle("Modify Part Error");
                alertStockMaxMin.setHeaderText("Max must be greater than min");
                alertStockMaxMin.setContentText("Please type correct amount for max");
                alertStockMaxMin.showAndWait();
            }
            if (min > max) {
                Alert alertStockMaxMin = new Alert(Alert.AlertType.WARNING);
                alertStockMaxMin.setTitle("Modify Part Error");
                alertStockMaxMin.setHeaderText("Min must be less than Max");
                alertStockMaxMin.setContentText("Please type correct amount for min");
                alertStockMaxMin.showAndWait();
            }
            if (price <= 0.00) {
                Alert alertPrice = new Alert(Alert.AlertType.WARNING);
                alertPrice.setTitle("Modify Part Error");
                alertPrice.setHeaderText("Price cannot be less than or equal to 0.00");
                alertPrice.setContentText("Please type correct amount for price");
                alertPrice.showAndWait();
            } else if (!partName.contains("") && !partName.contains(null) && stock > min && stock < max && max > min && min < max && price >=0.00) {
                    if (inHouseRadio.isSelected()) {
                        InHousePart inHousePart = new InHousePart(partID, partName, price, stock, min, max, machineID);
                        Inventory.updatePart(partIndex, inHousePart);
                        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                        scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
                        stage.setScene(new Scene(scene));
                        stage.show();
                    } else if (outsourcedRadio.isSelected()) {
                        OutSourcedPart outSourcedPart = new OutSourcedPart(partID, partName, price, stock, min, max, companyName);
                        Inventory.updatePart(partIndex, outSourcedPart);
                        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                        scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
                        stage.setScene(new Scene(scene));
                        stage.show();
                    }
                }
            }


    /** This is the toggle group for in house and outsourced radio buttons.
     * Toggle group for in house or outsourced radio buttons.
     * @param actionEvent  The arguments mouse event.
     * @return void. */
    public void setModifyPartTG(ActionEvent actionEvent) {
        this.modifyPartTG = modifyPartTG;
        inHouseRadio.setToggleGroup(modifyPartTG);
        outsourcedRadio.setToggleGroup(modifyPartTG);
    }

    /** This is the getModifyPartTG event handler.
     * @param actionEvent  The arguments mouse event.
     * @return void. */
    public void getModifyPartTG(ActionEvent actionEvent) throws Exception {
    }


    /** This is the in house radio button click event handler.
     * Method to select radio button, update text and field.
     * @param event  The arguments mouse event.
     * @return void. */
    public void inHouseRadioClick(MouseEvent event) {
        partMachineID.setText("Machine ID");
        machineIDField.setPromptText("Machine ID");
    }

    /** This is the outsourced radio button click event handler.
     * Method to select radio button, update text and field.
     * @param event  The arguments mouse event.
     * @return void. */
    public void outsourcedRadioClick(MouseEvent event) {
        partMachineID.setText("Company Name");
        machineIDField.setPromptText("Company Name");
    }

    /** This is the textFieldPartID event handler.
     * @param actionEvent  The arguments mouse event.
     * @return void. */
    public void textFieldPartID(ActionEvent actionEvent) {
    }

    /** This is the textFieldPartName event handler.
     * @param actionEvent  The arguments mouse event.
     * @return void. */
    public void textFieldPartName(ActionEvent actionEvent) {
    }

    /** This is the textFieldPartStock event handler.
     * @param actionEvent  The arguments mouse event.
     * @return void. */
    public void textFieldPartStock(ActionEvent actionEvent) {
    }

    /** This is the textFieldPartMax event handler.
     * @param actionEvent  The arguments mouse event.
     * @return void. */
    public void textFieldPartMax(ActionEvent actionEvent) {
    }

    /** This is the textFieldPartMin event handler.
     * @param actionEvent  The arguments mouse event.
     * @return void. */
    public void textFieldPartMin(ActionEvent actionEvent) {
    }

    /** This is the textFieldPartPriceCostField event handler.
     * @param actionEvent  The arguments mouse event.
     * @return void. */
    public void textFieldPartPriceCostField(ActionEvent actionEvent) {
    }

    /** This is the textFieldMachineID event handler.
     * @param actionEvent  The arguments mouse event.
     * @return void. */
    public void textFieldMachineID(ActionEvent actionEvent) {
    }

    /** This is the initialize method for screen.
     * Method to opens screen that contains previously selected part to modify including if part is in house or outsourced.
     * @param url Pass url.
     * @param rb Pass rb.
     * @return void. */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Part part = getAllParts().get(partIndex);
        modPartIdField.setText("Auto Gen - Disabled ");
        modPartNameField.setText(part.getName());
        modPartStockField.setText(Integer.toString(part.getStock()));
        modPartPriceCostField.setText(Double.toString(part.getPrice()));
        modPartMaxField.setText(Integer.toString(part.getMax()));
        modPartMinField.setText(Integer.toString(part.getMin()));
        if (part instanceof InHousePart) {
            modMachineIDField.setText("Machine ID");
            modMachineIDField.setText(Integer.toString(((InHousePart) getAllParts().get(partIndex)).getMachineID()));
            inHouseRadio.setSelected(true);
        } else {
            modMachineIDField.setText("Company Name");
            modMachineIDField.setText(((OutSourcedPart) getAllParts().get(partIndex)).getCompanyName());
            outsourcedRadio.setSelected(true);

        }

    }
}
