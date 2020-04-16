package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Text title = new Text("Inventory Management System");
        Text title2 = new Text("Inventory Management System");
        Text title3 = new Text("Inventory Management System3");


        BorderPane root = new BorderPane();
            HBox innerRoot = new HBox(100);
                BorderPane parts = new BorderPane();
                    parts.setTop(new Text("Parts"));
                    
                BorderPane products = new BorderPane();

            innerRoot.getChildren().addAll(parts, products);

        root.setTop(title);
        root.setCenter(innerRoot);


        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root, 1150, 750);
        scene.getStylesheets().add("../controlStyles.css");

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
