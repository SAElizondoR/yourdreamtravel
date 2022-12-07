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
import yourdreamtravel.domain.Lieu;
import yourdreamtravel.domain.Vol;

import java.io.*;
import java.util.*;

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

    private void selectionClient() throws FileNotFoundException {
        InputStream stream = new FileInputStream("src/main/java/yourdreamtravel/fond.jpg");
        Image image = new Image(stream);
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        Stage newWindow = new Stage();
        try {
            Pane root = new Pane();
            Scene scene = new Scene(root,400,400);
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
                                root.getChildren().clear();
                                root.getChildren().add(imageView);
                                faireReservationVoyage(root);
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

    private Lieu lieuDeDepart(Pane root, List<String> destinationNames, Map<String, Lieu> destinations){
        final Lieu[] depart = new Lieu[1];
        ComboBox<String> myComboBox1 = new ComboBox<String>();
        Label label1 = new Label("Lieu de depart : ");
        label1.setLayoutX(0);
        label1.setLayoutY(0);
        label1.setStyle("-fx-font-size: 18");
        myComboBox1.setLayoutX(130);
        myComboBox1.setLayoutY(2);

        for(int i=0; i<destinationNames.size(); i++) {
            myComboBox1.getItems().add(destinations.get(destinationNames.get(i)).getNom());
        }
        myComboBox1.setEditable(true);
        myComboBox1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                for(int i =0; i<destinationNames.size(); i++){
                    if(destinations.get(destinationNames.get(i)).getNom().equals(myComboBox1.getValue())){
                        depart[0] = destinations.get(destinationNames.get(i));
                        //System.out.println(destinations.get(destinationNames.get(i)).getNom());
                        destinationNames.remove(i);
                    }
                }
            }
        });
        root.getChildren().addAll(myComboBox1, label1);
        return depart[0];
    }

    private Lieu lieuDestination(Pane root, List<String> destinationNames, Map<String, Lieu> destinations) {
        final Lieu[] destination = new Lieu[1];
        ComboBox<String> myComboBox2 = new ComboBox<String>();
        Label label2 = new Label("Lieu d'arriver : ");
        label2.setLayoutX(0);
        label2.setLayoutY(50);
        label2.setStyle("-fx-font-size: 18");
        myComboBox2.setLayoutX(130);
        myComboBox2.setLayoutY(50);

        for(int i=0; i<destinationNames.size(); i++) {
            myComboBox2.getItems().add(destinations.get(destinationNames.get(i)).getNom());
        }
        myComboBox2.setEditable(true);
        myComboBox2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                for(int i =0; i<destinationNames.size(); i++){
                    if(destinations.get(destinationNames.get(i)).getNom().equals(myComboBox2.getValue())){
                        destination[0] = destinations.get(destinationNames.get(i));
                        //System.out.println(destinations.get(destinationNames.get(i)).getNom());
                        destinationNames.remove(i);
                    }
                }
            }
        });
        root.getChildren().addAll(myComboBox2, label2);
        return destination[0];
    }

    private void faireReservationVoyage(Pane root) {
        Map<String, Lieu> destinations = agenceService.getDestinationMap();
        List<String> destinationNames = new ArrayList<>(destinations.keySet());
        Lieu depart = lieuDeDepart(root, destinationNames, destinations);
        Lieu destination = lieuDestination(root, destinationNames, destinations);

        Map<String, Vol> itineraire = agenceService.proposerItineraire(depart, destination);
        List<String> itineraireString = new ArrayList<>(itineraire.keySet());
        System.out.printf("Itinéraire proposé:\n%s\n", String.join("\n",
            itineraireString));
        List<Vol> itineraireVols = new ArrayList<>(itineraire.values());



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

        Group root = new Group();


        buttonCreerClient.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                creerClient();
            }
        });

        buttonSelectClient.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    selectionClient();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });


        buttonCreerClient.setLayoutX(150);
        buttonCreerClient.setLayoutY(100);
        buttonSelectClient.setLayoutX(325);
        buttonSelectClient.setLayoutY(100);
        buttonCreerClient.setStyle("-fx-font: bold italic 10pt \"Arial\";\n" +"    -fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0 );");
        buttonSelectClient.setStyle("-fx-font: bold italic 10pt \"Arial\";\n" +"    -fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0 );");


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
