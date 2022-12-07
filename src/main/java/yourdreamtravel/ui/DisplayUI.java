package yourdreamtravel.ui;

import javafx.beans.binding.IntegerBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import yourdreamtravel.application.AgenceService;
import yourdreamtravel.domain.Client;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DisplayUI {
    private final AgenceService agenceService;



    public DisplayUI() {
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

    private void selectionClient(){
        Stage newWindow = new Stage();
        try {
            Pane root = new Pane();
            Scene scene = new Scene(root,400,400);
            //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            Map<String, Client> clients = agenceService.getClientsMap();
            List<String> clientNames = new ArrayList<>(clients.keySet());

            ListView<String> listView = new ListView<String>();
            ObservableList<String> list = FXCollections.observableArrayList();
            listView.setItems(list);
            for(int i=0; i<clientNames.size(); i++) {
                list.add(clients.get(clientNames.get(i)).getNom());
            }

            listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            listView.setOnMouseClicked(new EventHandler<Event>() {
                @Override
                public void handle(Event event) {
                    ObservableList<String> selectedItems =  listView.getSelectionModel().getSelectedItems();
                    for(String s : selectedItems){
                        for(int i =0; i<clients.size(); i++){
                            if(clients.get(clientNames.get(i)).getNom().equals(s)){
                                agenceService.setClientActif(clients.get(clientNames.get(i)));
                                System.out.println("selected item " + s);
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setContentText(s+" est selectionné");
                                alert.show();
                                newWindow.close();
                            }
                        }
                    }
                }
            });
            root.getChildren().add(listView);
            newWindow.setScene(scene);
            newWindow.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
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
                selectionClient();
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
