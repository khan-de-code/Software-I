package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PartController implements Initializable {
    @FXML
    private Text companyNameText;
    @FXML
    private Text machineIDText;
    @FXML
    private TextField companyNameTF;
    @FXML
    private TextField machineIDTF;
    @FXML
    private RadioButton inHouse;
    @FXML
    private RadioButton outsourced;
    @FXML
    private ToggleGroup toggleGroup1;

    @FXML
    private TextField partName;
    @FXML
    private TextField partInventory;
    @FXML
    private TextField partPrice;
    @FXML
    private TextField partMax;
    @FXML
    private TextField partMin;

    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {
        partInventory.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    partInventory.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        partMax.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    partMax.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        partMin.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    partMin.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        partPrice.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    partPrice.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        machineIDTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    machineIDTF.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

    }

    public void radioButtonChange() {
        if (this.toggleGroup1.getSelectedToggle().equals(inHouse)) {
            machineIDText.setVisible(true);
            machineIDTF.setVisible(true);
            companyNameText.setVisible(false);
            companyNameTF.setVisible(false);
        } else if (this.toggleGroup1.getSelectedToggle().equals(outsourced)) {
            companyNameText.setVisible(true);
            companyNameTF.setVisible(true);
            machineIDText.setVisible(false);
            machineIDTF.setVisible(false);
        }
    }

    public void addCancelBtnPushed(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("main.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setMinHeight(750);
        window.setMinWidth(1150);
        window.setScene(tableViewScene);
        window.show();
    }

    private int getID(MainController mainController) {
        int id = 0;
        ObservableList<Part> temp = mainController.inventory.getAllParts();

        for (Part part : temp) {
            if (part.getId() >= id) {
                id = part.getId() + 1;
            }
        }

        return id;
    }

    public void addSaveBtnPushed(ActionEvent event) throws IOException {
        if (toggleGroup1.getSelectedToggle() == null) {
            return;
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("main.fxml"));
        Parent tableViewParent = loader.load();

        MainController mainController = loader.getController();
        int id = getID(mainController);

        if (toggleGroup1.getSelectedToggle().equals(inHouse)) {
            if (partName.getText().equals("") || partPrice.getText().equals("") || partInventory.getText().equals("") || partMax.getText().equals("") || partMin.getText().equals("") || machineIDTF.getText().equals("")) {
                return;
            }

            Integer.parseInt(partMax.getText());
            InHouse inHousePart = new InHouse(id, partName.getText(), Double.parseDouble(partPrice.getText()), Integer.parseInt(partInventory.getText()), Integer.parseInt(partMin.getText()), Integer.parseInt(partMax.getText()), Integer.parseInt(machineIDTF.getText()));
            mainController.updatePartsInventory(inHousePart);
        }

        if (toggleGroup1.getSelectedToggle().equals(outsourced)) {
            if (partName.getText().equals("") || partPrice.getText().equals("") || partInventory.getText().equals("") || partMax.getText().equals("") || partMin.getText().equals("") || companyNameTF.getText().equals("")) {
                return;
            }

            Outsourced outsourced = new Outsourced(id, partName.getText(), Double.parseDouble(partPrice.getText()), Integer.parseInt(partInventory.getText()), Integer.parseInt(partMin.getText()), Integer.parseInt(partMax.getText()), companyNameTF.getText());
            mainController.updatePartsInventory(outsourced);
        }

        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setMinHeight(750);
        window.setMinWidth(1150);
        window.setScene(tableViewScene);
        window.show();
    }
}
