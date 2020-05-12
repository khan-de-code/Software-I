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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private Text mainTitle;

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

    @FXML
    private TextField searchPartsTF;
    @FXML
    private TextField searchProductsTF;
    ObservableList<Part> filteredPartList = FXCollections.observableArrayList();
    ObservableList<Product> filteredProductList = FXCollections.observableArrayList();

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

        prodIDTC.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        prodNameTC.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        prodInventoryTC.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stock"));
        prodPriceTC.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));

        partsTable.setItems(inventory.getAllParts());
        productsTable.setItems(inventory.getAllProducts());
    }

    public void addPartBtnPushed(ActionEvent event) throws IOException {
        Part returnedPart = PartController.start(inventory, null);
        if (returnedPart != null) {
            inventory.addPart(returnedPart);
        }

    }

    public void modifyPartBtnPushed(ActionEvent event) throws IOException {
        if (partsTable.getSelectionModel().getSelectedItem() != null) {
            Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
            Part modifiedPart = PartController.start(inventory, selectedPart);
            if (modifiedPart == null) {
                return;
            }

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

    public void addProductBtnPushed(ActionEvent event) throws IOException {
        if (inventory.getAllParts().size() > 0) {
            Product returnedProduct = ProductController.start(inventory, null);
            if (returnedProduct != null) {
                inventory.addProduct(returnedProduct);
            }
        }
    }

    public void setModifyProductBtnPushed(ActionEvent event) throws IOException {
        if (productsTable.getSelectionModel().getSelectedItem() != null) {
            Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();
            Product modifiedProduct = ProductController.start(inventory, selectedProduct);
            if (modifiedProduct == null) {
                return;
            }

            int selectedProductIndex = inventory.getAllProducts().indexOf(selectedProduct);
            inventory.updateProduct(selectedProductIndex, modifiedProduct);
        }
    }

    public void setDeleteProductBtnPushed(ActionEvent event) {
        if (productsTable.getSelectionModel().getSelectedItem() != null) {
            Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();
            inventory.deleteProduct(selectedProduct);
        }
    }

    public void searchPartsMain() {
        filteredProductList = FXCollections.observableArrayList();
        filteredPartList = FXCollections.observableArrayList();
        if (searchPartsTF.getText().equals("")) {
            partsTable.setItems(inventory.getAllParts());
            return;
        }

        for (Part part : inventory.getAllParts()) {
            if (part.getName().equals(searchPartsTF.getText())) {
                filteredPartList.add(part);
            }
        }
        partsTable.setItems(filteredPartList);
    }

    public void searchProductsMain() {
        if (searchProductsTF.getText().equals("")) {
            productsTable.setItems(inventory.getAllProducts());
            return;
        }

        for (Product product : inventory.getAllProducts()) {
            if (product.getName().equals(searchProductsTF.getText())) {
                filteredProductList.add(product);
            }
        }
        productsTable.setItems(filteredProductList);
    }

    public void exitMain() {
        Stage stage = (Stage) searchProductsTF.getScene().getWindow();
        stage.close();
    }

}
