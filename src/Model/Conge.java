package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class Conge {
    private String typeConge;
    private Date dateDebut;
    private Date dateFin;
    public Conge(String typeConge,Date dateDebut,Date dateFin){
        this.typeConge=typeConge;
        this.dateDebut=dateDebut;
        this.dateFin=dateFin;
    }

    @Override
    public String toString() {
        return "Conge{" +
                "typeConge='" + typeConge + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                '}';
    }

    public void demanderConge(int idEmploye, String typeConge, String dateDebut, String dateFin, int nombreJours) {
        try (Connection connection = ConnexionDB.getConnection()) {
            String query = "INSERT INTO conge (id_employe, type_conge, date_debut, date_fin, nombre_jours) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, idEmploye);
                preparedStatement.setString(2, typeConge);
                preparedStatement.setString(3, dateDebut);
                preparedStatement.setString(4, dateFin);
                preparedStatement.setInt(5, nombreJours);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Demande de congé enregistrée avec succès.");
                } else {
                    System.out.println("Échec de l'enregistrement de la demande de congé.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

