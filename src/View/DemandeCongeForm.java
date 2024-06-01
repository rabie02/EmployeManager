package View;

import Model.ConnexionDB;
import Model.Employee;
import Model.LoginModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DemandeCongeForm extends JFrame {
    private JTextField id_employee;
    private JTextField typeCongeField;
    private JTextField dateDebutField;
    private JTextField dateFinField;
    private JTextField nombreJoursField;



    public DemandeCongeForm() {
        initComponents();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fermer cette fenêtre sans fermer l'application principale
        setSize(400, 200);
        setLocationRelativeTo(null);
        setTitle("Demande de Congé");

    }

    private void initComponents() {
        id_employee = new JTextField(30);
        typeCongeField = new JTextField(20);
        dateDebutField = new JTextField(10);
        dateFinField = new JTextField(10);
        nombreJoursField = new JTextField(5);

        JButton validerButton = new JButton("Valider");
        validerButton.addActionListener(this::validerButtonActionPerformed);

        JPanel panel = new JPanel(new GridLayout(6, 2)); // Increased rows to accommodate the new ID Employé field
        panel.add(new JLabel("ID Employé:"));
        panel.add(id_employee);
        panel.add(new JLabel("Type de Congé:"));
        panel.add(typeCongeField);
        panel.add(new JLabel("Date de Début (YYYY-MM-DD):"));
        panel.add(dateDebutField);
        panel.add(new JLabel("Date de Fin (YYYY-MM-DD):"));
        panel.add(dateFinField);
        panel.add(new JLabel("Nombre de Jours:"));
        panel.add(nombreJoursField);
        panel.add(new JLabel(""));
        panel.add(validerButton);

        getContentPane().add(panel, BorderLayout.CENTER);
    }

    private void validerButtonActionPerformed(ActionEvent evt) {

        try {
            // Get input values
            int idEmploye = Integer.parseInt(id_employee.getText());
            String typeConge = typeCongeField.getText();
            String dateDebut = dateDebutField.getText();
            String dateFin = dateFinField.getText();
            String nombreJours = nombreJoursField.getText();

            // Validate input (you can add more validation as needed)
            if (idEmploye <= 0 || typeConge.isEmpty() || dateDebut.isEmpty() || dateFin.isEmpty() || nombreJours.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Save the leave request
            enregistrerDemandeConge(idEmploye, typeConge, dateDebut, dateFin, nombreJours);

            // Optionally, display a success message
            JOptionPane.showMessageDialog(this, "Demande de congé enregistrée avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);

            // You may choose to close the form or reset the input fields after successful validation
            // For example: dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Veuillez saisir un ID valide", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void enregistrerDemandeConge(int idEmploye, String typeConge, String dateDebut, String dateFin, String nombreJours) {
        // Add your logic here to save the leave request to the database
        try (Connection connection = ConnexionDB.getConnection()) {
            // Create a PreparedStatement and execute the SQL query to insert the leave request
            String query = "INSERT INTO conge (id_employe, type_conge, date_debut, date_fin, nombre_jours, etat_conge) VALUES (?, ?, ?, ?, ?, 'non valide')";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, idEmploye);
                preparedStatement.setString(2, typeConge);
                preparedStatement.setString(3, dateDebut);
                preparedStatement.setString(4, dateFin);
                preparedStatement.setString(5, nombreJours);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "conger ajouté avec succès.");
                    // Vous pouvez également fermer cette fenêtre après l'ajout si nécessaire
                    // dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "L'ajout de conger a échoué.");
                }
            }
        } catch (SQLException e) {
            // Handle database-related exceptions
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors de l'enregistrement de la demande de congé", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
