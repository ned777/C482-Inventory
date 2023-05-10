package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;

/**
 *  The AddPartController is responsible for managing the part inventory..
 */
public class AddPartController {

    @FXML private Label MachineIDorCompany;

    @FXML private RadioButton PartInHouseRadio;
    @FXML private RadioButton PartOutsourcedRadio;
    @FXML private RadioButton addPartOutsourced;
    @FXML private Button addPartSaveButton;
    @FXML private Button addPartCancelButton;
    @FXML private TextField addPartName;
    @FXML private TextField addPartInventory;
    @FXML private TextField addPartPrice;
    @FXML private TextField addPartMax;
    @FXML private TextField addPartMin;
    @FXML private TextField addPartMachineID;

    /**
     * When the button is pressed, update the text to display the Company Name.
     *
     * @param event
     */
    @FXML
    void onActionAddPartOutsourced(ActionEvent event) {

        MachineIDorCompany.setText("Company Name");
    }

    /**
     * When the button is pressed, update the text to display the machine ID.
     * @param event
     */
    @FXML
    void onActionAddPartInHouse(ActionEvent event) {

        MachineIDorCompany.setText("Machine ID");
    }

    /**
     * When the button is pressed, navigate to the main screen.
     * @param event
     */

    @FXML
    public void addPartCancelAction (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../views/MainScreen.fxml"));
        Scene scene = new Scene(root);
        Stage MainScreenReturn = (Stage)((Node)event.getSource()).getScene().getWindow();
        MainScreenReturn.setScene(scene);
        MainScreenReturn.show();

    }

    /**
     * To save the part, handle the button press event.
     * A runtime error was occurring, specifically a NumberFormatException caused by the input string "test
     * To resolve the error, I wrapped the problematic section of the method inside a try-catch block.
     * Since the error was a NumberFormatException, I instructed the catch block to handle that specific error.
     * It's important to note that try-catch blocks may not be the most efficient solution for larger
     *  programs due to potential memory and CPU constraints.
     * @param event
     */
    @FXML
    void addPartSaveButton(ActionEvent event) throws IOException {
        try {

            // Create random number and multiply by 100, this will keep it from overlapping with products.
            int uniqueID = (int) (Math.random() * 100);

            String name = addPartName.getText();
            int inStock = Integer.parseInt(addPartInventory.getText());
            double price = Double.parseDouble(addPartPrice.getText());
            int min = Integer.parseInt(addPartMin.getText());
            int max = Integer.parseInt(addPartMax.getText());

            int machineID = 0;
            String companyName;

            //Min should be less than max.
            if (max < min) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Maximum must be greater than minimum.");
                alert.showAndWait();
                return;
            }
            //Inventory should be between the min and max values.
            else if (inStock < min || max < inStock) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory must be within min and max.");
                alert.showAndWait();
                return;
            }

            // Print to console for troubleshooting.
            System.out.println(PartOutsourcedRadio.isSelected() + " outsourced");
            System.out.println(PartInHouseRadio.isSelected() + " inhouse");
            if (PartOutsourcedRadio.isSelected()) {
                companyName = addPartMachineID.getText();
                Outsourced addPart = new Outsourced(uniqueID, name, price, inStock, min, max, companyName);
                Inventory.addPart(addPart);
            }
            if (PartInHouseRadio.isSelected()) {
                machineID = Integer.parseInt(addPartMachineID.getText());
                InHouse addPart = new InHouse(uniqueID, name, price, inStock, min, max, machineID);
                Inventory.addPart(addPart);
            }
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Object scene = FXMLLoader.load(getClass().getResource("../views/MainScreen.fxml"));
            stage.setScene(new Scene((Parent) scene));
            stage.show();

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setContentText("Incorrect value");
            alert.showAndWait();
            return;
        }
    }

}
