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
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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

            HBox innerRoot = new HBox(100);
            innerRoot.setId("inner-root");
                BorderPane parts = new BorderPane();
                parts.setId("parts-products-box");
                    HBox partsHBoxTop = new HBox(15);
                        Text partsTitle = new Text("Parts");
                        Button partsSearchBtn = new Button("Search");
                        TextField partsSearchField = new TextField();
                        partsHBoxTop.getChildren().addAll(partsTitle, partsSearchBtn, partsSearchField);
                    parts.setTop(partsHBoxTop);

                    TableView partsTableView = new TableView();
// TODO:                   TableColumn<String, Part> column1 = new TableColumn<>("First Name");
                    parts.setCenter(partsTableView);

                    HBox partsHBoxBottom = new HBox(15);
                        Button partsAddBtn = new Button("Add");
                        Button partsModifyBtn = new Button("Modify");
                        Button partsDeleteBtn = new Button("Delete");
                        partsHBoxBottom.getChildren().addAll(partsAddBtn, partsModifyBtn, partsDeleteBtn);
                    parts.setBottom(partsHBoxBottom);
                    
                BorderPane products = new BorderPane();
                products.setId("parts-products-box");
                    HBox productsHBoxTop = new HBox(15);
                        Text productsTitle = new Text("Products");
                        Button productsSearchBtn = new Button("Search");
                        TextField productsSearchField = new TextField();
                        productsHBoxTop.getChildren().addAll(productsTitle, productsSearchBtn, productsSearchField);
                    products.setTop(productsHBoxTop);

                    TableView productsTableView = new TableView();
// TODO: implement table columns after part class is created
                    products.setCenter(productsTableView);

                    HBox productsHBoxBottom = new HBox(15);
                        Button productsAddBtn = new Button("Add");
                        Button productsModifyBtn = new Button("Modify");
                        Button productsDeleteBtn = new Button("Delete");
                        productsHBoxBottom.getChildren().addAll(productsAddBtn, productsModifyBtn, productsDeleteBtn);
                    products.setBottom(productsHBoxBottom);

            innerRoot.getChildren().addAll(parts, products);
        root.setCenter(innerRoot);

        Button exitBtn = new Button("Exit");
        root.setBottom(exitBtn);


        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root, 1150, 750);
        scene.getStylesheets().add("sample/controlStyles.css");

        primaryStage.setTitle("Software I");
        primaryStage.setScene(scene);
        primaryStage.show();
//        // Create the Text Nodes
//        Text centerText = new Text("Center");
//        Text topText = new Text("Top");
//        Text rightText = new Text("Right");
//        Text bottomText = new Text("Bottom");
//        Text leftText = new Text("Left");
//
//        // Set the alignment of the Top Text to Center
//        BorderPane.setAlignment(topText,Pos.TOP_CENTER);
//        // Set the alignment of the Bottom Text to Center
//        BorderPane.setAlignment(bottomText,Pos.BOTTOM_CENTER);
//        // Set the alignment of the Left Text to Center
//        BorderPane.setAlignment(leftText,Pos.CENTER_LEFT);
//        // Set the alignment of the Right Text to Center
//        BorderPane.setAlignment(rightText, Pos.CENTER_RIGHT);
//
//        // Create a BorderPane with a Text node in each of the five regions
//        BorderPane root = new BorderPane(centerText, topText, rightText, bottomText, leftText);
//
//        // Set the Size of the VBox
//        root.setPrefSize(400, 400);
//        // Set the Style-properties of the BorderPane
//        root.setStyle("-fx-padding: 10;" +
//                "-fx-border-style: solid inside;" +
//                "-fx-border-width: 2;" +
//                "-fx-border-insets: 5;" +
//                "-fx-border-radius: 5;" +
//                "-fx-border-color: blue;");
//
//        // Create the Scene
//        Scene scene = new Scene(root);
//        // Add the scene to the Stage
//        primaryStage.setScene(scene);
//        // Set the title of the Stage
//        primaryStage.setTitle("A simple BorderPane Example");
//        // Display the Stage
//        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
