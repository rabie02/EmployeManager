package View;

import Model.ConnexionDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeModifier extends JFrame {
    private JTextField nomField;
    private JTextField prenomField;
    private JTextField adresseField;
    private JTextField numField;
    private JTextField emailField;
    private JTextField datenField;
    private JTextField nbrheuretrvField;
    private JTextField posteField;
    private String telephone;
    private String idEmploye;
    private HumanResourceView parent;
    public EmployeeModifier(HumanResourceView parent, String nom, String prenom, String adresse, String num, String email, String daten, String nbrheuretrv, String poste) {

        setTitle("Modifier Employé");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);

        // Initialisation des composants du formulaire
        nomField = new JTextField(nom);
        prenomField = new JTextField(prenom);
        adresseField = new JTextField(adresse);
        numField = new JTextField(num);
        emailField = new JTextField(email);
        datenField = new JTextField(daten);
        nbrheuretrvField = new JTextField(nbrheuretrv);
        posteField = new JTextField(poste);



        JButton saveButton = new JButton("Update");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateEmployeeInDatabase();
                dispose(); // Fermer la fenêtre après la mise à jour
            }
        });
        setSize(600, 400); // Ajustez la taille selon vos préférences
        getContentPane().setBackground(new Color(89, 222, 218));
        setLayout(new GridLayout(9, 2));
        setBackground(new Color(0, 255, 217));
        saveButton.setBackground(new Color(70, 130, 180));

        // Disposition des composants dans le formulaire
        setLayout(new GridLayout(9, 2));
        add(new JLabel("Nom:"));
        add(nomField);
        add(new JLabel("Prénom:"));
        add(prenomField);
        add(new JLabel("Adresse:"));
        add(adresseField);
        add(new JLabel("Numéro:"));
        add(numField);
        add(new JLabel("Email:"));
        add(emailField);
        add(new JLabel("Date de Naissance:"));
        add(datenField);
        add(new JLabel("Statut:"));
        add(nbrheuretrvField);
        add(new JLabel("Poste:"));
        add(posteField);
        add(new JLabel("")); // Espace vide pour la disposition
        add(saveButton);

    }
    private void updateEmployeeInDatabase() {
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String adresse = adresseField.getText();
        String num = numField.getText();
        String email = emailField.getText();
        String daten = datenField.getText();
        String nbrheuretrv = nbrheuretrvField.getText();
        String poste = posteField.getText();
        String telephone = numField.getText(); // Utilisez le champ numField pour obtenir le numéro de téléphone
        try (Connection connection = ConnexionDB.getConnection()) {
            String query = "UPDATE employe SET nom=?, prenom=?, adresse=?, telephone=?, email=?, date_naissance=?, statut=?, poste=? WHERE telephone=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, nom);
                preparedStatement.setString(2, prenom);
                preparedStatement.setString(3, adresse);
                preparedStatement.setString(4, num);
                preparedStatement.setString(5, email);
                preparedStatement.setString(6, daten);
                preparedStatement.setString(7, nbrheuretrv);
                preparedStatement.setString(8, poste);
                preparedStatement.setString(9, telephone);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(EmployeeModifier.this, "Employé mis à jour avec succès.");
                    // Ajoutez ici le code pour recharger les données si nécessaire dans la classe parent (HumanResourceView)

                } else {
                    JOptionPane.showMessageDialog(EmployeeModifier.this, "La mise à jour de l'employé a échoué.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }



}
