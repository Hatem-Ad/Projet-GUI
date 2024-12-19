package Service;

import Entite.Challenge;
//import Entite.Participation;
import Utils.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChallengeService implements ChallengeServiceInterface<Challenge>{
    private Connection con = Test.getInstance().getCon();

    // Ajouter un challenge
    public boolean ajouter(Challenge challenge) throws SQLException {
        String query = "INSERT INTO challenges (name, description, category_id) VALUES (?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, challenge.getName());
            ps.setString(2, challenge.getDescription());
            ps.setInt(3, challenge.getCategoryId()); // Lier au category_id
            return ps.executeUpdate() > 0;
        }
    }

    // Supprimer un challenge
    public boolean deleteChallenge(Challenge challenge) throws SQLException {
        String query = "DELETE FROM challenges WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, challenge.getId());
            return ps.executeUpdate() > 0;
        }
    }

    // Mettre à jour un challenge
    public boolean update(Challenge challenge) throws SQLException {
        String query = "UPDATE challenges SET name = ?, description = ?, category_id = ? WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, challenge.getName());
            ps.setString(2, challenge.getDescription());
            ps.setInt(3, challenge.getCategoryId()); // Mettre à jour le category_id
            ps.setInt(4, challenge.getId());
            return ps.executeUpdate() > 0;
        }
    }

    // Trouver un challenge par ID
    public Challenge findById(int id) throws SQLException {
        String query = "SELECT id, name, description, category_id FROM challenges WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Challenge(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getInt("category_id")
                    );
                }
            }
        }
        return null;
    }

    // Récupérer tous les challenges
    public List<Challenge> getAllChallenges() throws SQLException {
        List<Challenge> challenges = new ArrayList<>();
        String query = "SELECT id, name, description, category_id FROM challenges";
        try (PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                challenges.add(new Challenge(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("category_id")
                ));
            }
        }
        return challenges;
    }

    // Récupérer les challenges d'une catégorie spécifique
    public List<Challenge> getChallengesByCategory(int categoryId) throws SQLException {
        List<Challenge> challenges = new ArrayList<>();
        String query = "SELECT id, name, description, category_id FROM challenges WHERE category_id = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, categoryId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    challenges.add(new Challenge(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getInt("category_id")
                    ));
                }
            }
        }
        return challenges;
    }

    // Rechercher des challenges par nom
    public List<Challenge> searchChallengeByName(String name) throws SQLException {
        List<Challenge> challenges = new ArrayList<>();
        String query = "SELECT id, name, description, category_id FROM challenges WHERE name LIKE ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, "%" + name + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    challenges.add(new Challenge(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getInt("category_id")
                    ));
                }
            }
        }
        return challenges;
    }

   /* // Méthode pour enregistrer une participation dans la base de données
    public boolean ParticiperChallenge(Participation participation) throws SQLException {
       PreparedStatement pre = con.prepareStatement("INSERT INTO participations (user_id, challenge_id, progression) VALUES (1,?,0)");
       // pre.setInt(1, participation.getUserId());
        pre.setInt(1, participation.getChallengeId());
        int res = pre.executeUpdate();
        if (res > 0) {
            return true;
        }
        return false;

    }
   // Méthode pour participer à un challenge
   public boolean ParticiperChallenge(Participation participation) throws SQLException {
       // Vérifier si l'utilisateur existe
       String checkUserQuery = "SELECT id FROM users WHERE id = ?";
       try (PreparedStatement ps = con.prepareStatement(checkUserQuery)) {
           ps.setInt(1, participation.getUserId());
           try (ResultSet rs = ps.executeQuery()) {
               if (!rs.next()) {
                   System.out.println("Utilisateur avec ID " + participation.getUserId() + " n'existe pas.");
                   return false; // L'utilisateur n'existe pas
               }
           }
       }

       // Si l'utilisateur existe, procéder à l'insertion de la participation
       String insertParticipationQuery = "INSERT INTO participations (user_id, challenge_id) VALUES (?, ?)";
       try (PreparedStatement ps = con.prepareStatement(insertParticipationQuery)) {
           ps.setInt(1, participation.getUserId());
           ps.setInt(2, participation.getChallengeId());
           return ps.executeUpdate() > 0;
       }
   }

    //Méthode pour récuperer les étapes pour chaque challenge selon la catégorie choisi
    public List<String> getEtapes(Challenge challenge) throws SQLException {
       List<String> etapes = new ArrayList<>();
       String sql = "SELECT description FROM challenges WHERE category_id = ? and name = ?";
       PreparedStatement stmt = con.prepareStatement(sql) ;
      // stmt.setInt(1, challenge.getCategory().getId());
       stmt.setString(2, challenge.getName());
       ResultSet rs = stmt.executeQuery();
       while (rs.next()) {
           etapes.add(rs.getString("description"));
       }
       return etapes;
   }
    public boolean updateProgression(Participation participation) throws SQLException {
       int nouvelleProgression = participation.getProgression();
       // Utilisation de la progression calculée dans l'objet
        // Mise à jour de la progression dans la base de données
        String sql = "UPDATE participations SET progression = ? WHERE user_id = ? AND challenge_id = ?";
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setInt(1, nouvelleProgression);
        pre.setInt(2, participation.getUserId());
        pre.setInt(3, participation.getChallengeId());
        int res = pre.executeUpdate();    return res > 0;
        // Retourne vrai si la mise à jour a réussi
        }*/
}
