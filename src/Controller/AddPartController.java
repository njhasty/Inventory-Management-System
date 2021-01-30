package Controller;

import Model.InHousePart;
import Model.Inventory;
import Model.OutSourcedPart;
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

import static Model.Inventory.getPartIdListCount;
/** This class manages adding a part to parts list */
public class AddPartController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private Text machineIdText;

    @FXML
    private ToggleGroup addPartTG;

    @FXML
    private TextField partIDField;

    @FXML
    private TextField partNameField;

    @FXML
    private TextField partInvLevelField;

    @FXML
    private TextField partFieldMax;

    @FXML
    private TextField partFieldMin;

    @FXML
    private TextField partFieldPrice;

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

    @FXML
    private int partID;

    @FXML
    private boolean outSourcedPart;

    @FXML
    private boolean inHousePart;

    /** This is the cancelButton event handler.
     * Method to exit to main menu.
     * @param event  The arguments mouse event.
     * @return void. */
    @FXML
    void cancelButtonClick(MouseEvent event) throws IOException {
        try {
            Alert alertExit = new Alert(Alert.AlertType.CONFIRMATION);
            alertExit.setTitle("Exit to Main Screen");
            alertExit.setHeaderText("Are you sure you want to cancel add part?");
            alertExit.setContentText("Press OK to exit to the main screen. Press cancel to stay on this screen.");
            alertExit.showAndWait();

            if (alertExit.getResult() == ButtonType.OK) {
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            } else {
                alertExit.close();
            }
        } catch (IOException e) {
        }
    }

    /** This is the radio button select event handler.
     * Method to select in house radio button.
     * @param event  The arguments mouse event.
     * @return void. */
    @FXML
    void inHouseRadioClick(MouseEvent event) {
        machineIdText.setText("Machine ID");
        machineIDField.setPromptText("Machine ID");
    }

    /** This is the radio button select event handler.
     * Method to select outsourced radio button.
     * @param event  The arguments mouse event.
     * @return void. */
    @FXML
    void outsourcedRadioClick(MouseEvent event) {
        machineIdText.setText("Company Name");
        machineIDField.setPromptText("Company Name");
    }

    /** This is the save button click handler.
     * Method to save added part, return to main menu.
     * Logical Error: Input errors but part still added and return to main screen.
     * Solution was to add logic that validates for inverse of above if statements.
     * If inverse logic statement does not validate, part is not added.
     * @param event  The arguments mouse event.
     * @return void. */
    @FXML
    void saveButtonClick(MouseEvent event) throws IOException {
        int partID = Inventory.getPartIdListCount();
        String partName = partNameField.getText();
        int stock = Integer.parseInt(partInvLevelField.getText());
        int max = Integer.parseInt(partFieldMax.getText());
        int min = Integer.parseInt(partFieldMin.getText());
        double price = Double.parseDouble(partFieldPrice.getText());
        int machineID = Integer.parseInt(machineIDField.getText());
        String companyName = machineIDField.getText();

            if (partName.equals("") || partName.equals(null)) {
                Alert alertNameNull = new Alert(Alert.AlertType.WARNING);
                alertNameNull.setTitle("Add Part Error");
                alertNameNull.setHeaderText("Part Name cannot be empty");
                alertNameNull.setContentText("Please type name of Part");
                alertNameNull.showAndWait();
            }  if (stock < min || stock > max) {
                Alert alertStockMaxMin = new Alert(Alert.AlertType.WARNING);
                alertStockMaxMin.setTitle("Add Part Error");
                alertStockMaxMin.setHeaderText("Inv must be greater than min, less than max");
                alertStockMaxMin.setContentText("Please type correct amount for Inventory");
                alertStockMaxMin.showAndWait();
            } if (max < min) {
                Alert alertStockMaxMin = new Alert(Alert.AlertType.WARNING);
                alertStockMaxMin.setTitle("Add Part Error");
                alertStockMaxMin.setHeaderText("Max must be greater than min");
                alertStockMaxMin.setContentText("Please type correct amount for max.");
                alertStockMaxMin.showAndWait();
            } if (min > max) {
                Alert alertStockMaxMin = new Alert(Alert.AlertType.WARNING);
                alertStockMaxMin.setTitle("Modify Part Error");
                alertStockMaxMin.setHeaderText("Min must be less than Max");
                alertStockMaxMin.setContentText("Please type correct amount for min");
                alertStockMaxMin.showAndWait();
            } if (price <= 0.00) {
                Alert alertPrice = new Alert(Alert.AlertType.WARNING);
                alertPrice.setTitle("Add Part Error");
                alertPrice.setHeaderText("Price must be greater than 0.00");
                alertPrice.setContentText("Please type correct amount for price");
                alertPrice.showAndWait();
            } else if(!partName.contains("") && !partName.contains(null) && stock > min && stock < max && max > min && min < max && price >=0.00) {
                if (inHouseRadio.isSelected()) {
                InHousePart inHousePart = new InHousePart(partID, partName, price, stock, min, max, machineID);
                Inventory.addPart(inHousePart);
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            } else if (outsourcedRadio.isSelected()) {
                OutSourcedPart outSourcedPart = new OutSourcedPart(partID, partName, price, stock, min, max, companyName);
                Inventory.addPart(outSourcedPart);
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }
        }
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

    /** This is the textFieldPartInvLevel event handler.
     * @param actionEvent  The arguments mouse event.
     * @return void. */
    public void textFieldPartInvLevel(ActionEvent actionEvent) {
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

    /** This is the textFieldPartPrice event handler.
     * @param actionEvent  The arguments mouse event.
     * @return void. */
    public void textFieldPartPrice(ActionEvent actionEvent) {
    }

    /** This is the textFieldmachineId event handler.
     * @param actionEvent  The arguments mouse event.
     * @return void. */
    public void textFieldmachineId(ActionEvent actionEvent) {
    }

    /** This is the addPartTG event handler.
     * @param actionEvent  The arguments mouse event.
     * @return void. */
    public void addPartTG(ActionEvent actionEvent) {
    }

    /** This is the initialize method for screen.
     * Method to set partIDField to disabled.
     * @param url Pass url.
     * @param rb Pass rb.
     * @return void. */
    @Override
    public void initialize (URL url, ResourceBundle rb){
        partIDField.setText("Auto Gen - Disabled");
    }
}
