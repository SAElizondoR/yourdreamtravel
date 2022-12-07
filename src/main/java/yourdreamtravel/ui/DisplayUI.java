package yourdreamtravel.ui;

import javafx.beans.binding.IntegerBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import yourdreamtravel.application.AgenceService;

import java.io.*;

public class DisplayUI {
    private final AgenceService agenceService;
    private final BufferedReader buff;

    @FXML
    private TextField ClientNametxt;



    public DisplayUI() {
        buff = new BufferedReader(new InputStreamReader(System.in));
        agenceService = new AgenceService();
    }

    private void creerClient() {
        VBox root = new VBox();
        root.setPadding(new Insets(25));
        root.setAlignment(Pos.CENTER);
        Label label = new Label("Quel est votre nom ? ");
        TextField name = new TextField("");
        Scene secondScene = new Scene(root, 400, 400);
        Stage newWindow = new Stage();

        Button buttonValide = new Button();
        buttonValide.setText("valider");
        buttonValide.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                agenceService.addClient(name.textProperty().getValue());
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText(name.textProperty().getValue()+" est un client");
                alert.show();
                newWindow.close();
            }
        });

        root.getChildren().addAll(label, name, buttonValide);

        newWindow.setTitle("Creer un client");
        newWindow.setScene(secondScene);

        // Set position of second window, related to primary window.
        newWindow.setX(newWindow.getX() + 100);
        newWindow.setY(newWindow.getY() + 100);

        newWindow.show();
    }

    public void run(Stage primaryStage) throws FileNotFoundException {
        InputStream stream = new FileInputStream("src/main/java/yourdreamtravel/fond.jpg");
        Image image = new Image(stream);
        ImageView imageView = new ImageView();
        imageView.setImage(image);

        Button buttonCreerClient = new Button();
        Button buttonSelectClient = new Button();
        buttonCreerClient.setText("Creer un client");
        buttonSelectClient.setText("Selectionner un client");


        buttonCreerClient.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                creerClient();
            }
        });

        buttonSelectClient.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //Label secondLabel = new Label("selectionner un client");

                StackPane secondaryLayout = new StackPane();
                //secondaryLayout.getChildren().add(secondLabel);

                Scene secondScene = new Scene(secondaryLayout, 400, 400);

                // New window (Stage)
                Stage newWindow = new Stage();
                newWindow.setTitle("selectionner un client");
                newWindow.setScene(secondScene);

                // Set position of second window, related to primary window.
                newWindow.setX(primaryStage.getX() + 100);
                newWindow.setY(primaryStage.getY() + 100);

                newWindow.show();
            }
        });


        buttonCreerClient.setLayoutX(150);
        buttonCreerClient.setLayoutY(100);
        buttonSelectClient.setLayoutX(325);
        buttonSelectClient.setLayoutY(100);
        buttonCreerClient.setStyle("-fx-font: bold italic 10pt \"Arial\";\n" +"    -fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0 );");
        buttonSelectClient.setStyle("-fx-font: bold italic 10pt \"Arial\";\n" +"    -fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0 );");

        Group root = new Group();
        root.getChildren().addAll(imageView);
        root.getChildren().addAll(buttonCreerClient, buttonSelectClient);
        //root.getChildren().add(buttonSelectClient);

        Scene scene = new Scene(root, 600, 400);


        primaryStage.setTitle("YOUR DREAM TRAVEL");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }



}
