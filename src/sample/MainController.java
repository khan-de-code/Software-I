package sample;

import com.sun.scenario.effect.impl.prism.PrDrawable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private Button addPartBtn;
    @FXML
    private Button modifyPartBtn;
    @FXML
    private Button deletePartBtn;

    @FXML
    private TableColumn<Part, Integer> partIDTC;
    @FXML
    private TableColumn<Part, String> partNameTC;
    @FXML
    private TableColumn<Part, Integer> partInventoryTC;
    @FXML
    private TableColumn<Part, Double> partPriceTC;
    @FXML
    private TableView<Part> partsTable;
    @FXML
    private TableColumn<Product, Integer> prodIDTC;
    @FXML
    private TableColumn<Product, String> prodNameTC;
    @FXML
    private TableColumn<Product, Integer> prodInventoryTC;
    @FXML
    private TableColumn<Product, Double> prodPriceTC;
    @FXML
    private TableView<Product> productsTable;

    ObservableList<Part> partList = FXCollections.observableArrayList();
    ObservableList<Product> productList = FXCollections.observableArrayList();
    public Inventory inventory = new Inventory(partList, productList);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Sets column widths evenly
        partIDTC.prefWidthProperty().bind(partsTable.widthProperty().divide(4));
        partNameTC.prefWidthProperty().bind(partsTable.widthProperty().divide(4));
        partInventoryTC.prefWidthProperty().bind(partsTable.widthProperty().divide(4));
        partPriceTC.prefWidthProperty().bind(partsTable.widthProperty().divide(4));

        prodIDTC.prefWidthProperty().bind(productsTable.widthProperty().divide(4));
        prodNameTC.prefWidthProperty().bind(productsTable.widthProperty().divide(4));
        prodInventoryTC.prefWidthProperty().bind(productsTable.widthProperty().divide(4));
        prodPriceTC.prefWidthProperty().bind(productsTable.widthProperty().divide(4));

        //Initialize column values
        partIDTC.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        partNameTC.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        partInventoryTC.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        partPriceTC.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));

        prodIDTC.setCellValueFactory(new PropertyValueFactory<Product, Integer>("prodIDTC"));
        prodNameTC.setCellValueFactory(new PropertyValueFactory<Product, String>("prodNameTC"));
        prodInventoryTC.setCellValueFactory(new PropertyValueFactory<Product, Integer>("prodInventoryTC"));
        prodPriceTC.setCellValueFactory(new PropertyValueFactory<Product, Double>("prodPriceTC"));

        partsTable.setItems(inventory.getAllParts());
        productsTable.setItems(inventory.getAllProducts());
    }

    public void addPartBtnPushed(ActionEvent event) throws IOException {
        inventory.addPart(PartController.start(inventory, null));
    }

    public void modifyPartBtnPushed(ActionEvent event) throws IOException {
        if (partsTable.getSelectionModel().getSelectedItem() != null) {
            Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
            Part modifiedPart = PartController.start(inventory, selectedPart);

            int selectedPartIndex = inventory.getAllParts().indexOf(selectedPart);
            inventory.updatePart(selectedPartIndex, modifiedPart);
        }
    }

    public void deletePartBtnPushed(ActionEvent event) {
        if (partsTable.getSelectionModel().getSelectedItem() != null) {
            Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
            inventory.deletePart(selectedPart);
        }
    }

}
