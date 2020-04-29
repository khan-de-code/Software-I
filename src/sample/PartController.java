package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PartController implements Initializable {
    @FXML private Text companyNameText;
    @FXML private Text machineIDText;
    @FXML private TextField companyNameTF;
    @FXML private TextField machineIDTF;

    @FXML private RadioButton inHouse;
    @FXML private RadioButton outsourced;
    @FXML private ToggleGroup toggleGroup1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void radioButtonChange() {
        if (this.toggleGroup1.getSelectedToggle().equals(inHouse)) {
            companyNameText.setVisible(true);
            companyNameTF.setVisible(true);
            machineIDText.setVisible(false);
            machineIDTF.setVisible(false);
        } else if (this.toggleGroup1.getSelectedToggle().equals(outsourced)) {
            machineIDText.setVisible(true);
            machineIDTF.setVisible(true);
            companyNameText.setVisible(false);
            companyNameTF.setVisible(false);
        }
    }

    public void addCancelBtnPushed(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("main.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setMinHeight(750);
        window.setMinWidth(1150);
        window.setScene(tableViewScene);
        window.show();
    }
}
