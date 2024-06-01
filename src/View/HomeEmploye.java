package View;

import Controller.LoginController;
import Model.ConnexionDB;
import Model.Employee;
import Model.LoginModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HomeEmploye extends JFrame {
    private LoginModel loggedInUser;
    private JTable jTable1;
    private JButton logoutButton;
    private JButton demandeCongeButton;
    private JButton voirCongeButton;

    public HomeEmploye(LoginModel loggeinUser) {
        this.loggedInUser=loggeinUser;
        initComponents();
        loadData();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setTitle("Home Employé");
    }

    private void initComponents() {
        jTable1 = new JTable();
        logoutButton = new JButton("Logout");
        demandeCongeButton = new JButton("Demander Congé");
        voirCongeButton = new JButton("Voir Congé");

        logoutButton.addActionListener(this::logoutButtonActionPerformed);
        demandeCongeButton.addActionListener(this::demandeCongeButtonActionPerformed);
        voirCongeButton.addActionListener(evt -> {
            try {
                voirCongeButtonActionPerformed(evt);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        JScrollPane jScrollPane = new JScrollPane(jTable1);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(jScrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(logoutButton);
        buttonPanel.add(demandeCongeButton);
        buttonPanel.add(voirCongeButton);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadData() {
        try (Connection connection = ConnexionDB.getConnection()) {
            DefaultTableModel tableModel = new DefaultTableModel(
                    new Object[][]{},
                    new String[]{"Nom", "Prenom", "Adresse", "Numero", "Email", "Date de Naissance", "Statuts", "Poste"}
            );

            // Assuming you have a valid loggedInUser instance after login
            if (this.loggedInUser != null) {
                String query = "SELECT e.nom, e.prenom, e.adresse, e.telephone, e.email, e.date_naissance, e.statut, e.poste " +
                        "FROM employe e " +
                        "JOIN user u ON e.id_employe = u.employe_id " +
                        "WHERE e.id_employe = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setInt(1, 35); // Assuming 35 is the employe_id you want to filter
                    System.out.println("Executing query: " + query);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
                            Object[] row = new Object[8];
                            row[0] = resultSet.getString("nom");
                            row[1] = resultSet.getString("prenom");
                            row[2] = resultSet.getString("adresse");
                            row[3] = resultSet.getString("telephone");
                            row[4] = resultSet.getString("email");
                            row[5] = resultSet.getString("date_naissance");
                            row[6] = resultSet.getString("statut");
                            row[7] = resultSet.getString("poste");
                            tableModel.addRow(row);
                        }
                    }
                }
                jTable1.setModel(tableModel);
            } else {
                // Handle the case where loggedInUser is null
                System.out.println("LoggedInUser is null. Cannot load data.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void logoutButtonActionPerformed(ActionEvent evt) {
        int confirmation = JOptionPane.showConfirmDialog(this,
                "Êtes-vous sûr de vouloir vous déconnecter?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            // Add your logout logic here
            dispose();
            // Example: Return to the login view
            LoginModel loginModel = new LoginModel();
            LoginController loginController = new LoginController(loginModel);
            LoginView loginView = new LoginView(loginModel, loginController);
            loginView.setVisible(true);
        }
    }
    private void demandeCongeButtonActionPerformed(ActionEvent evt) {
        // Open the Leave Request Form when the "Demander Congé" button is clicked
        DemandeCongeForm demandeCongeForm = new DemandeCongeForm();
        demandeCongeForm.setVisible(true);
    }

    private void voirCongeButtonActionPerformed(ActionEvent evt) throws SQLException {
        VoirConger c= new VoirConger();
        c.setVisible(true);
    }


}