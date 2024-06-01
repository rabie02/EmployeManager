package View;

import Model.ConnexionDB;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class AddPerformanceRapportDialog extends JDialog {
    public AddPerformanceRapportDialog(JFrame parent, String title) {
        super(parent, title, true);
        initComponents();
        loadRapport();
    }


    private void initComponents() {
        // Ajoutez les composants nécessaires au formulaire
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JLabel labelEmployeeId = new JLabel("Employee ID:");
        JTextField textFieldEmployeeId = new JTextField();
        JLabel labelRapportTexte = new JLabel("Texte du rapport:");
        JTextField textFieldRapportTexte = new JTextField();
        JLabel labelRapportDate = new JLabel("Date du rapport:");
        JTextField textFieldRapportDate = new JTextField();
        JButton addButton = new JButton("Ajouter");

        // Ajoutez le gestionnaire d'événements pour le bouton "Ajouter"
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupérer les données saisies
                String employeeId = textFieldEmployeeId.getText();
                String rapportTexte = textFieldRapportTexte.getText();
                String rapportDate = textFieldRapportDate.getText();

                // Ajouter le code pour l'ajout dans la base de données
                ajouterRapportDansBaseDeDonnees(employeeId, rapportTexte, rapportDate);
                // Fermer la boîte de dialogue après l'ajout
                dispose();
            }
        });

        // Ajoutez les composants au panneau
        panel.add(labelEmployeeId);
        panel.add(textFieldEmployeeId);
        panel.add(labelRapportTexte);
        panel.add(textFieldRapportTexte);
        panel.add(labelRapportDate);
        panel.add(textFieldRapportDate);

        // Ajoutez le panneau à la boîte de dialogue
        add(panel, BorderLayout.CENTER);
        add(addButton, BorderLayout.SOUTH);
        setLocationRelativeTo(null);

        // Rendre la boîte de dialogue visible après avoir ajouté les composants
        pack();
        setVisible(true);
    }
    private void ajouterRapportDansBaseDeDonnees(String employeeId, String rapportTexte, String rapportDate) {
        try (Connection connection = ConnexionDB.getConnection()) {
            String query = "INSERT INTO rapport (employee_id, rapport_texte, rapport_date) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                // Paramétrisez la requête avec les valeurs
                preparedStatement.setString(1, employeeId);
                preparedStatement.setString(2, rapportTexte);
                preparedStatement.setString(3, rapportDate);
                // Exécutez la requête
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Rapport ajouté avec succès.");
                    // Vous pouvez également fermer cette fenêtre après l'ajout si nécessaire
                    // dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "L'ajout du rapport a échoué.");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void loadRapport() {
        try (Connection connection = ConnexionDB.getConnection()) {
            JTable jTableRapport=new JTable();
            DefaultTableModel rapportTableModel = (DefaultTableModel) jTableRapport.getModel();
            rapportTableModel.setRowCount(0); // Efface les lignes existantes
            String query = "SELECT employee_id, rapport_texte, rapport_date FROM rapport";
            System.out.println("Executing query: " + query); // Ajout de message de débogage
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    while (resultSet.next()) {
                        Object[] row = new Object[3];
                        row[0] = resultSet.getString("employee_id");
                        row[1] = resultSet.getString("rapport_texte");

                        // Convertir la date en format lisible
                        java.sql.Date rapportDate = resultSet.getDate("rapport_date");
                        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                        String rapportDateString = sdf.format(rapportDate);

                        row[2] = rapportDateString;

                        System.out.println("Row data: " + Arrays.toString(row)); // Ajout de message de débogage
                        rapportTableModel.addRow(row);
                        System.out.println("Row added to the table.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
