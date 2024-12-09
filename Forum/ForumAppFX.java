import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;


public class ForumAppFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Titre de la fenêtre
        primaryStage.setTitle("Forum Simple");

        // Création des éléments de l'interface
        TextArea messageArea = new TextArea();
        messageArea.setEditable(false); // Zone de discussion en lecture seule
        messageArea.setWrapText(true); // Permet le retour à la ligne automatique

        TextField inputField = new TextField();
        inputField.setPromptText("Entrez votre message ici...");
        Button sendButton = new Button("Envoyer");

        // Gestion de l'envoi de messages
        sendButton.setOnAction(e -> {
            String message = inputField.getText().trim();
            if (!message.isEmpty()) {
                messageArea.appendText(message + "\n"); // Ajoute le message à la zone de discussion
                inputField.clear(); // Vide le champ de saisie
            }
        });

        // Organisation de l'interface
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(
            new Label("Zone de discussion :"),
            messageArea,
            new Label("Écrire un message :"),
            new HBox(10, inputField, sendButton) // Saisie et bouton sur la même ligne
        );

        // Affichage de la scène
        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
