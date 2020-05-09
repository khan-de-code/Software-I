package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductController implements Initializable {
    @FXML
    private Text pageTitle;

    @FXML
    private TextField idTF;
    @FXML
    private TextField nameTF;
    @FXML
    private TextField invTF;
    @FXML
    private TextField priceTF;
    @FXML
    private TextField maxTF;
    @FXML
    private TextField minTF;

    @FXML
    private Button searchBtn;
    @FXML
    private TextField searchTF;

    @FXML
    private Button addBtn;
    @FXML
    private Button deleteBtn;

    @FXML
    private Button saveBtn;
    @FXML
    private Button cancelBtn;

    @FXML
    private TableView<Part> partsTable;
    @FXML
    private TableColumn<Part, Integer> partIDTC;
    @FXML
    private TableColumn<Part, String> partNameTC;
    @FXML
    private TableColumn<Part, Integer> partInventoryTC;
    @FXML
    private TableColumn<Part, Double> partPriceTC;

    @FXML
    private TableView<Part> partsInProductTable;
    @FXML
    private TableColumn<Part, Integer> partInProductIDTC;
    @FXML
    private TableColumn<Part, String> partInProductNameTC;
    @FXML
    private TableColumn<Part, Integer> partInProductInventoryTC;
    @FXML
    private TableColumn<Part, Double> partInProductPriceTC;

    private ObservableList<Part> buildingProductPartList = FXCollections.observableArrayList();
    private static Product returnProduct;
    private static Inventory currentInventory;
    private static ObservableList<Part> modifyInventory = FXCollections.observableArrayList();
    private static Product modifyProduct;

    private ObservableList<Part> modifyInventorySearch = FXCollections.observableArrayList();
    private ObservableList<Part> currentInventorySearch = FXCollections.observableArrayList();

    public static Product start(Inventory inventory, Product product) throws IOException {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ProductController.class.getResource("product.fxml"));

        currentInventory = inventory;
        modifyProduct = product;

        Parent productViewParent = loader.load();

        ProductController productController = loader.getController();
        productController.modifyInventory = FXCollections.observableArrayList();
        if (product != null) {
            for (Part part : inventory.getAllParts()) {
                Boolean partInProduct = false;
                for (Part productPart : product.getAssociatedParts()) {
                    if (part.equals(productPart)) {
                        partInProduct = true;
                        productController.buildingProductPartList.add(part);
                        break;
                    }
                }
                if (!partInProduct) {
                    productController.modifyInventory.add(part);
                }
            }
            productController.setModifyValues();
            productController.pageTitle.setText("Modify Product");
        }

        Scene partViewScene = new Scene(productViewParent);

        window.setScene(partViewScene);
        window.showAndWait();

        return returnProduct;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Sets column widths evenly
        partIDTC.prefWidthProperty().bind(partsTable.widthProperty().divide(4));
        partNameTC.prefWidthProperty().bind(partsTable.widthProperty().divide(4));
        partInventoryTC.prefWidthProperty().bind(partsTable.widthProperty().divide(4));
        partPriceTC.prefWidthProperty().bind(partsTable.widthProperty().divide(4));

        partInProductIDTC.prefWidthProperty().bind(partsInProductTable.widthProperty().divide(4));
        partInProductNameTC.prefWidthProperty().bind(partsInProductTable.widthProperty().divide(4));
        partInProductInventoryTC.prefWidthProperty().bind(partsInProductTable.widthProperty().divide(4));
        partInProductPriceTC.prefWidthProperty().bind(partsInProductTable.widthProperty().divide(4));

        //Initialize column values
        partIDTC.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        partNameTC.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        partInventoryTC.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        partPriceTC.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));

        partInProductIDTC.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        partInProductNameTC.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        partInProductInventoryTC.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        partInProductPriceTC.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));

        partsTable.setItems(currentInventory.getAllParts());
        partsInProductTable.setItems(buildingProductPartList);

        //Add input restrictions
        invTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    invTF.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        maxTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    maxTF.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        minTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    minTF.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        priceTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*[\\.]{0,1}\\d*")) {
                    String temp = "";
                    Boolean firstDot = false;
                    for (int i = 0; i < newValue.length(); i++) {
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
                    priceTF.setText(temp);
                }
            }
        });

        idTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    idTF.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    private void setModifyValues() {
        idTF.setText(modifyProduct.getId() + "");
        nameTF.setText(modifyProduct.getName());
        invTF.setText(modifyProduct.getStock() + "");
        priceTF.setText(Double.toString(modifyProduct.getPrice()));
        maxTF.setText(modifyProduct.getMax() + "");
        minTF.setText(modifyProduct.getMin() + "");

        partsTable.setItems(modifyInventory);
    }

    public void searchBtnPushed() {
        currentInventorySearch = FXCollections.observableArrayList();
        modifyInventorySearch = FXCollections.observableArrayList();
        String searchText = searchTF.getText();
        if (searchText.equals("")) {
            if (modifyProduct == null) {
                partsTable.setItems(currentInventory.getAllParts());
            } else {
                partsTable.setItems(modifyInventory);
            }
        } else {
            if (modifyProduct == null) {
                for (Part part : currentInventory.getAllParts()) {
                    if (part.getName().equals(searchText)) {
                        currentInventorySearch.add(part);
                    }
                    partsTable.setItems(currentInventorySearch);
                }
            } else {
                for (Part part : modifyInventory) {
                    if (part.getName().equals(searchText)) {
                        modifyInventorySearch.add(part);
                    }
                    partsTable.setItems(modifyInventorySearch);
                }
            }
        }
    }

    private int getID() {
        int id = 0;
        for (Product product : currentInventory.getAllProducts()) {
            if (product.getId() >= id) {
                id = product.getId() + 1;
            }
        }

        return id;
    }

    public void addBtnPushed() {
        if (partsTable.getSelectionModel().getSelectedItem() != null) {
            Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
            buildingProductPartList.add(selectedPart);
        }
    }

    public void deleteBtnPushed() {
        if (partsInProductTable.getSelectionModel().getSelectedItem() != null) {
            Part selectedPart = partsInProductTable.getSelectionModel().getSelectedItem();
            buildingProductPartList.remove(selectedPart);
        }
    }

    public void saveBtnPushed() {
        if (buildingProductPartList.size() <= 0) {
            return;
        }

        int id = getID();

        if (nameTF.getText().equals("") || invTF.getText().equals("") || priceTF.getText().equals("")
                || maxTF.getText().equals("") || minTF.getText().equals("") || buildingProductPartList.size() == 0) {
            return;
        }

        if (Integer.parseInt(invTF.getText()) > Integer.parseInt(maxTF.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setHeaderText("Inventory Value Conflict");
            alert.setContentText("You have entered an inventory value greater than your designated maximum inventory" +
                    " amount for this product.");

            alert.showAndWait();
            return;
        }

        int sumOfPartsPrice = 0;
        for (Part part : buildingProductPartList) {
            sumOfPartsPrice += part.getPrice();
        }
        double test = Double.parseDouble(priceTF.getText());
        if (sumOfPartsPrice > Double.parseDouble(priceTF.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setHeaderText("Product Price Conflict");
            alert.setContentText("The designated price for this product can not be less than the the sum of the" +
                    " prices for the parts that make up this product.");

            alert.showAndWait();
            return;
        }

        Product tempProduct;
        if (modifyProduct == null) {
            tempProduct = new Product(buildingProductPartList, id, nameTF.getText(), Double.parseDouble(priceTF.getText()),
                    Integer.parseInt(invTF.getText()), Integer.parseInt(minTF.getText()), Integer.parseInt(maxTF.getText()));
        } else {
            tempProduct = new Product(buildingProductPartList, Integer.parseInt(idTF.getText()), nameTF.getText(), Double.parseDouble(priceTF.getText()),
                    Integer.parseInt(invTF.getText()), Integer.parseInt(minTF.getText()), Integer.parseInt(maxTF.getText()));
        }

        if (modifyProduct != null && !modifyProduct.equals(tempProduct)) {
            returnProduct = modifyProduct;
        }

        returnProduct = tempProduct;

        Stage stage = (Stage) nameTF.getScene().getWindow();
        stage.close();
    }

    public void cancelBtnPushed() {
        Stage stage = (Stage) nameTF.getScene().getWindow();
        stage.close();
    }
}
