package View;


import Model.ConnexionDB;
import Model.LoginModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class VoirConger extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private HomeEmploye parent;
    public VoirConger() throws SQLException {
        setTitle("Voir Congés");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Création du tableau
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);

        // Ajout des colonnes au modèle de tableau
        tableModel.addColumn("ID Congé");
        tableModel.addColumn("Type Congé");
        tableModel.addColumn("Date Début");
        tableModel.addColumn("Date Fin");
        tableModel.addColumn("Nombre Jours");
        tableModel.addColumn("État Congé");
        // Ajout du tableau à un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        // Ajout du JScrollPane au contenu de la fenêtre
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        // Affichage des congés de l'employé
        afficherCongesEmploye();
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    private void afficherCongesEmploye() throws SQLException {
        Connection connection = ConnexionDB.getConnection();
        String query = "SELECT id_conge, type_conge, date_debut, date_fin, nombre_jours, etat_conge " + "FROM conge WHERE id_employe = 35";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Object[] rowData = {
                            resultSet.getInt("id_conge"),
                            resultSet.getString("type_conge"),
                            resultSet.getString("date_debut"),
                            resultSet.getString("date_fin"),
                            resultSet.getString("nombre_jours"),
                            resultSet.getString("etat_conge")
                    };
                    tableModel.addRow(rowData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
