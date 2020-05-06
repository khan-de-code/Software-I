package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.scene.control.*;
import javafx.stage.Modality;
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

    private static Part returnPart;
    static Inventory currentInventory;
    static Part modifyPart;

    public static Part start(Inventory inventory, Part part) throws IOException {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(PartController.class.getResource("part.fxml"));
        Parent partViewParent = loader.load();

        PartController partController = loader.getController();
        currentInventory = inventory;
        modifyPart = part;
        if (part != null) {
            partController.setModifyValues();
        }

        Scene partViewScene = new Scene(partViewParent);

        window.setScene(partViewScene);
        window.showAndWait();

        return returnPart;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partInventory.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    partInventory.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        partMax.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    partMax.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        partMin.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    partMin.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        partPrice.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*[\\.]{0,1}\\d*")) {
                    String temp = "";
                    Boolean firstDot = false;
                    for (int i = 0; i < newValue.length(); i++) {
                        Boolean breakLoop = false;
                        char currentChar = newValue.charAt(i);

                        switch (currentChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                temp += currentChar;
                                break;
                            case '.':
                                if (firstDot) {
                                    break;
                                } else {
                                    temp += currentChar;
                                }
                            default:
                                break;
                        }

                        if (!firstDot && currentChar == '.') {
                            firstDot = true;
                        }
                    }
                    partPrice.setText(temp);
                }
            }
        });

        machineIDTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    machineIDTF.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    public void setModifyValues() {
        partName.setText(modifyPart.getName());
        partInventory.setText(modifyPart.getStock() + "");
        System.out.println(String.valueOf(modifyPart.getPrice()));
        partPrice.setText(String.valueOf(modifyPart.getPrice()));
        System.out.println(partPrice.getText());
        partMin.setText(modifyPart.getMax() + "");
        partMax.setText(modifyPart.getMax() + "");
        if (modifyPart instanceof InHouse) {
            inHouse.setSelected(true);
            machineIDTF.setText(((InHouse) modifyPart).getMachineID() + "");
        } else if (modifyPart instanceof Outsourced) {
            outsourced.setSelected(true);
            companyNameTF.setText(((Outsourced) modifyPart).getCompanyName() + "");
        }

        radioButtonChange();
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
        Stage stage = (Stage) inHouse.getScene().getWindow();
        stage.close();
    }

    private int getID(MainController mainController) {
        int id = 0;
        for (Part part : currentInventory.getAllParts()) {
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

        MainController mainController = loader.getController();
        int id = getID(mainController);

        if (toggleGroup1.getSelectedToggle().equals(inHouse)) {
            if (partName.getText().equals("") || partPrice.getText().equals("") || partInventory.getText().equals("")
                    || partMax.getText().equals("") || partMin.getText().equals("") || machineIDTF.getText().equals("")) {
                return;
            }

            returnPart = new InHouse(id, partName.getText(), Double.parseDouble(partPrice.getText()),
                    Integer.parseInt(partInventory.getText()), Integer.parseInt(partMin.getText()),
                    Integer.parseInt(partMax.getText()), Integer.parseInt(machineIDTF.getText()));
        }

        if (toggleGroup1.getSelectedToggle().equals(outsourced)) {
            if (partName.getText().equals("") || partPrice.getText().equals("") || partInventory.getText().equals("")
                    || partMax.getText().equals("") || partMin.getText().equals("") || companyNameTF.getText().equals("")) {
                return;
            }

            returnPart = new Outsourced(id, partName.getText(), Double.parseDouble(partPrice.getText()),
                    Integer.parseInt(partInventory.getText()), Integer.parseInt(partMin.getText()),
                    Integer.parseInt(partMax.getText()), companyNameTF.getText());
        }
        Stage stage = (Stage) inHouse.getScene().getWindow();
        stage.close();
    }
}
