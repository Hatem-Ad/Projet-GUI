package Controllers;

import Entite.Challenge;
import Service.ChallengeService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.sql.SQLException;

public class GestionChallengesController {
    @FXML
    private TableView<Challenge> tableChallenges;
    @FXML
    private TableColumn<Challenge, Integer> colId;
    @FXML
    private TableColumn<Challenge, String> colName;
    @FXML
    private TableColumn<Challenge, String> colDescription;
    @FXML
    private TableColumn<Challenge, Integer> colCategoryId;
    @FXML
    private TextField txtName, txtDescription, txtCategoryId, txtSearch;
    @FXML
    private Button btnAdd, btnSearch, btnReset, btnDelete;

    private final ChallengeService challengeService = new ChallengeService();
    private ObservableList<Challenge> challengeList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        setupTableView();
        loadChallenges();

        // Ajouter un challenge
        btnAdd.setOnAction(e -> addChallenge());

        // Supprimer le challenge sélectionné
        btnDelete.setOnAction(e -> deleteSelectedChallenge());

        // Rechercher un challenge par nom
        btnSearch.setOnAction(e -> searchChallengesByName(txtSearch.getText()));

        // Réinitialiser la recherche
        btnReset.setOnAction(e -> loadChallenges());
    }

    private void setupTableView() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colCategoryId.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
    }

    private void loadChallenges() {
        try {
            challengeList = FXCollections.observableArrayList(challengeService.getAllChallenges());
            tableChallenges.setItems(challengeList);
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible de charger les challenges.");
        }
    }

    private void addChallenge() {
        try {
            Challenge challenge = new Challenge(
                    txtName.getText(),
                    txtDescription.getText(),
                    Integer.parseInt(txtCategoryId.getText())
            );
            challengeService.ajouter(challenge);
            loadChallenges();
            clearFields(txtName, txtDescription, txtCategoryId);
            showAlert(Alert.AlertType.INFORMATION, "Succès", "Challenge ajouté avec succès !");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible d'ajouter le challenge.");
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.WARNING, "Erreur de saisie", "L'ID de la catégorie doit être un nombre.");
        }
    }

    private void deleteSelectedChallenge() {
        Challenge selectedChallenge = tableChallenges.getSelectionModel().getSelectedItem();
        if (selectedChallenge != null) {
            try {
                challengeService.deleteChallenge(selectedChallenge);
                loadChallenges();
                showAlert(Alert.AlertType.INFORMATION, "Succès", "Challenge supprimé avec succès !");
            } catch (SQLException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible de supprimer le challenge.");
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Aucun challenge sélectionné", "Veuillez sélectionner un challenge à supprimer.");
        }
    }

    private void searchChallengesByName(String name) {
        try {
            challengeList = FXCollections.observableArrayList(challengeService.searchChallengeByName(name));
            tableChallenges.setItems(challengeList);
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible de rechercher les challenges.");
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields(TextField... fields) {
        for (TextField field : fields) {
            field.clear();
        }
    }
}
