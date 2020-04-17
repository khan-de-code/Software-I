package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.format.TextStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Text title2 = new Text("Inventory Management System");
        Text title3 = new Text("Inventory Management System3");


        BorderPane root = new BorderPane();
        Text title = new Text("Inventory Management System");
        title.setId("main-screen-title");
        root.setTop(title);

            HBox innerRoot = new HBox(30);
            innerRoot.setId("inner-root");
            innerRoot.autosize();
                BorderPane parts = new BorderPane();
                    parts.setId("parts-products-box");
                    HBox partsHBoxTop = new HBox(15);
                        partsHBoxTop.setId("parts-products-top");
                        Text partsTitle = new Text("Parts");
                            partsTitle.setId("parts-products-title");
                        Pane spacerPartsTop = new Pane();
                        Button partsSearchBtn = new Button("Search");
                        TextField partsSearchField = new TextField();
                            partsSearchField.setId("search-field");
                        partsHBoxTop.getChildren().addAll(partsTitle, spacerPartsTop, partsSearchBtn, partsSearchField);
                        partsHBoxTop.setHgrow(spacerPartsTop, Priority.ALWAYS);
                    parts.setTop(partsHBoxTop);

                    TableView partsTableView = new TableView();
// TODO:                   TableColumn<String, Part> column1 = new TableColumn<>("First Name");
                    parts.setCenter(partsTableView);

                    HBox partsHBoxBottom = new HBox(15);
                        Pane spacerPartsBotLeft = new Pane();
                        Button partsAddBtn = new Button("Add");
                        Button partsModifyBtn = new Button("Modify");
                        Button partsDeleteBtn = new Button("Delete");
                        Pane spacerPartsBotRight = new Pane();
                        partsHBoxBottom.setHgrow(spacerPartsBotLeft, Priority.ALWAYS);
                        partsHBoxBottom.setHgrow(spacerPartsBotRight, Priority.ALWAYS);
                        partsHBoxBottom.getChildren().addAll(spacerPartsBotLeft, partsAddBtn, partsModifyBtn, partsDeleteBtn, spacerPartsBotRight);
                    parts.setBottom(partsHBoxBottom);
            innerRoot.setHgrow(parts, Priority.ALWAYS);
                    
                BorderPane products = new BorderPane();
                    products.setId("parts-products-box");
                    HBox productsHBoxTop = new HBox(15);
                        productsHBoxTop.setId("parts-products-top");
                        Text productsTitle = new Text("Products");
                            productsTitle.setId("parts-products-title");
                        Pane spacerProductsTop = new Pane();
                        Button productsSearchBtn = new Button("Search");
                        TextField productsSearchField = new TextField();
                        productsHBoxTop.getChildren().addAll(productsTitle,spacerProductsTop, productsSearchBtn, productsSearchField);
                    products.setTop(productsHBoxTop);
                    partsHBoxTop.setHgrow(spacerProductsTop, Priority.ALWAYS);

                    TableView productsTableView = new TableView();
// TODO: implement table columns after part class is created
                    products.setCenter(productsTableView);

                    HBox productsHBoxBottom = new HBox(15);
                        Pane spacerProdBotLeft = new Pane();
                        Button productsAddBtn = new Button("Add");
                        Button productsModifyBtn = new Button("Modify");
                        Button productsDeleteBtn = new Button("Delete");
                        Pane spacerProdBotRight = new Pane();
                        productsHBoxBottom.setHgrow(spacerProdBotLeft, Priority.ALWAYS);
                        productsHBoxBottom.setHgrow(spacerProdBotRight, Priority.ALWAYS);
                        productsHBoxBottom.getChildren().addAll(spacerProdBotLeft, productsAddBtn, productsModifyBtn, productsDeleteBtn, spacerProdBotRight);
                    products.setBottom(productsHBoxBottom);
            innerRoot.setHgrow(products, Priority.ALWAYS);

            innerRoot.getChildren().addAll(parts, products);
        root.setCenter(innerRoot);

        HBox bottomRootHBox = new HBox();
            Pane spacerExitBtn = new Pane();
            Button exitBtn = new Button("Exit");
            bottomRootHBox.setHgrow(spacerExitBtn, Priority.ALWAYS);
            bottomRootHBox.getChildren().addAll(spacerExitBtn, exitBtn);
        root.setBottom(bottomRootHBox);


        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root, 1150, 750);
        scene.getStylesheets().add("sample/controlStyles.css");

        primaryStage.setTitle("Software I");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
