package View;

import Model.Conge;
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

public class GestionConge extends JFrame {
    private Conge congeDAO;
    private HumanResourceView parent;
    private JTable congeTable;

    private JTextField idEmployeField;
    private JTextField typeCongeField;
    private JTextField dateDebutField;
    private JTextField dateFinField;
    private JTextField nombreJoursField;
    JTable jTable1 = new JTable();
    private JButton supprimerCongeButton;
    private JButton validerCongeButton;
    private JButton nonValiderCongeButton;
    private JPanel buttonPanel;

    public GestionConge() {
        setTitle("Gestion des Congés");
        setSize(800, 400);
        this.parent = parent;
        initComponents();
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // Chargez les données au démarrage de l'application
        loadData();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel for form components

        JButton demanderCongeButton = new JButton("Demander Congé");
        demanderCongeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                demanderConge();
            }
        });

        JButton approbationRejetButton = new JButton("Approbation/Rejet Congé");
        approbationRejetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                approbationRejetConge();
            }
        });
        JPanel tablePanel = new JPanel(new BorderLayout());
        // Create a table model with additional column for "État de Congé"


        // Panel for table

        // Create a table model with additional column for "État de Congé"
        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make cells non-editable for better control
                return false;
            }
        };
        tableModel.addColumn("ID Employé");
        tableModel.addColumn("Type de Congé");
        tableModel.addColumn("Date de Début");
        tableModel.addColumn("Date de Fin");
        tableModel.addColumn("Nombre de Jours");
        tableModel.addColumn("État de Congé");

        congeTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(congeTable);
        tablePanel.add(tableScrollPane, BorderLayout.CENTER);

        mainPanel.add(tablePanel, BorderLayout.CENTER);


        add(mainPanel);
        buttonPanel = new JPanel();
        supprimerCongeButton = new JButton("Supprimer Congé");
        supprimerCongeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                supprimerConge();
            }
        });
        buttonPanel.add(supprimerCongeButton);

        validerCongeButton = new JButton("Valider Congé");
        validerCongeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validerConge(true);
            }
        });
        buttonPanel.add(validerCongeButton);

        nonValiderCongeButton = new JButton("Non Valider Congé");
        nonValiderCongeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validerConge(false);
            }
        });
        buttonPanel.add(nonValiderCongeButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }
    private void demanderConge() {
        // Récupérer les données du formulaire
        int idEmploye = Integer.parseInt(idEmployeField.getText());
        String typeConge = typeCongeField.getText();
        String dateDebut = dateDebutField.getText();
        String dateFin = dateFinField.getText();
        int nombreJours = Integer.parseInt(nombreJoursField.getText());

        // Enregistrer la demande de congé dans le tableau
        DefaultTableModel tableModel = (DefaultTableModel) congeTable.getModel();
        Object[] rowData = {idEmploye, typeConge, dateDebut, dateFin, nombreJours, "En attente"};
        tableModel.addRow(rowData);

        // Réinitialiser les champs du formulaire après la demande
        idEmployeField.setText("");
        typeCongeField.setText("");
        dateDebutField.setText("");
        dateFinField.setText("");
        nombreJoursField.setText(null);

        JOptionPane.showMessageDialog(this, "Demande de congé ajoutée au tableau.");
    }

    private void approbationRejetConge() {
        // Obtenir la ligne sélectionnée dans la table
        int selectedRow = congeTable.getSelectedRow();
        if (selectedRow != -1) {
            // Modifier l'état du congé dans la colonne "État de Congé"
            DefaultTableModel tableModel = (DefaultTableModel) congeTable.getModel();
            tableModel.setValueAt("Validé", selectedRow, 5);

            JOptionPane.showMessageDialog(this, "Congé validé avec succès.");
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une ligne dans le tableau.");
        }
    }
    public void loadData() {
        try (Connection connection = ConnexionDB.getConnection()) {
            DefaultTableModel tableModel = (DefaultTableModel) congeTable.getModel();
            tableModel.setRowCount(0);
            String query = "SELECT id_employe, type_conge, date_debut, date_fin, nombre_jours, etat_conge FROM conge";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Object[] row = new Object[6];
                    row[0] = resultSet.getInt("id_employe");
                    row[1] = resultSet.getString("type_conge");
                    row[2] = resultSet.getString("date_debut");
                    row[3] = resultSet.getString("date_fin");
                    row[4] = resultSet.getInt("nombre_jours");
                    row[5] = resultSet.getString("etat_conge");
                    tableModel.addRow(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void supprimerConge() {
        int selectedRow = congeTable.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel tableModel = (DefaultTableModel) congeTable.getModel();
            int idConge = (int) tableModel.getValueAt(selectedRow, 0);
            // Supprimer le congé de la base de données
            supprimerCongeDeLaBase(idConge);
            // Supprimer la ligne de la table
            tableModel.removeRow(selectedRow);
            JOptionPane.showMessageDialog(this, "Congé supprimé avec succès.");
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une ligne dans le tableau.");
        }
    }

    private void supprimerCongeDeLaBase(int idConge) {
        try (Connection connection = ConnexionDB.getConnection()) {
            // Utilisez une requête DELETE avec l'ID du congé
            String deleteQuery = "DELETE FROM conge WHERE id_employe = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setInt(1, idConge);

                // Exécutez la requête DELETE
                int rowsAffected = preparedStatement.executeUpdate();

                // Vérifiez le nombre de lignes affectées
                if (rowsAffected > 0) {
                    // Suppression réussie
                    JOptionPane.showMessageDialog(this, "Congé supprimé de la base de données.");
                } else {
                    // Aucune ligne supprimée
                    JOptionPane.showMessageDialog(this, "Aucun congé supprimé. Vérifiez l'ID de l'employé.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors de la suppression du congé de la base de données.");
        }
    }


    private void validerConge(boolean isValide) {
        int selectedRow = congeTable.getSelectedRow();

        if (selectedRow != -1) {
            int idEmploye = (int) congeTable.getValueAt(selectedRow, 0);
            String etatConge = isValide ? "Valide" : "Non Valide";

            // Mettre à jour l'état du congé dans la base de données
            try (Connection connection = ConnexionDB.getConnection()) {
                String updateQuery = "UPDATE conge SET etat_conge = ? WHERE id_employe = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                    preparedStatement.setString(1, etatConge);
                    preparedStatement.setInt(2, idEmploye);

                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        // Mise à jour réussie
                        JOptionPane.showMessageDialog(this, "État du congé mis à jour avec succès.");
                        // Mettre à jour l'affichage dans le tableau
                        DefaultTableModel tableModel = (DefaultTableModel) congeTable.getModel();
                        tableModel.setValueAt(etatConge, selectedRow, 5);
                    } else {
                        // Aucune ligne mise à jour
                        JOptionPane.showMessageDialog(this, "État du congé non mis à jour. Vérifiez l'ID de l'employé.");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erreur lors de la mise à jour de l'état du congé dans la base de données.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une ligne dans le tableau.");
        }
    }
}