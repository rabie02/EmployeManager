package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Departement {
    private String nomDepartement;
    private String description;
    public Departement(String nomDepartement,String description){
        this.nomDepartement=nomDepartement;
        this.description=description;
    }

    @Override
    public String toString() {
        return "Departement{" +
                "nomDepartement='" + nomDepartement + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
    public static List<Departement> getDepartements() {
        List<Departement> departements = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Établir la connexion à la base de données
            connection = ConnexionDB.getConnection();

            // Préparer la requête SQL
            String query = "SELECT nom_departement, description FROM departement";
            preparedStatement = connection.prepareStatement(query);

            // Exécuter la requête
            resultSet = preparedStatement.executeQuery();

            // Parcourir les résultats et créer des objets Departement
            while (resultSet.next()) {
                String nomDepartement = resultSet.getString("nom_departement");
                String description = resultSet.getString("description");
                Departement departement = new Departement(nomDepartement, description);
                departements.add(departement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermer les ressources (ResultSet, PreparedStatement, Connection)
            ConnexionDB.close(resultSet);
            ConnexionDB.close(preparedStatement);
            ConnexionDB.close(connection);
        }

        return departements;
    }
    public String getDepartmentName() {
        return nomDepartement;
    }
    public String getDescription(){
        return description;
    }
}
