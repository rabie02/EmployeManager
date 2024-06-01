package View;

import Model.ConnexionDB;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddEmp extends JFrame {
    private JTextField nomField;
    private JTextField prenomField;
    private JTextField dateNaissanceField;
    private JTextField adresseField;
    private JTextField codePostalField;
    private JTextField villeField;
    private JTextField paysField;
    private JTextField telephoneField;
    private JTextField emailField;
    private JTextField statutField;
    private JTextField dateEmbaucheField;
    private JTextField posteField;
    private JTextField departementField;
    private JButton createButton;
    private HumanResourceView parent;

    public AddEmp(HumanResourceView parent) {
        this.parent = parent;
        initComponents();
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }


    private void initComponents() {
        // Initialisation des composants

        nomField = new JTextField();
        prenomField = new JTextField();
        dateNaissanceField = new JTextField();
        adresseField = new JTextField();
        codePostalField = new JTextField();
        villeField = new JTextField();
        paysField = new JTextField();
        telephoneField = new JTextField();
        emailField = new JTextField();
        statutField = new JTextField();
        dateEmbaucheField = new JTextField();
        posteField = new JTextField();
        departementField = new JTextField();


        nomField = createStyledTextField();
        prenomField = createStyledTextField();
        dateNaissanceField = createStyledTextField();
        adresseField = createStyledTextField();
        codePostalField = createStyledTextField();
        villeField = createStyledTextField();
        paysField = createStyledTextField();
        telephoneField = createStyledTextField();
        emailField = createStyledTextField();
        statutField = createStyledTextField();
        dateEmbaucheField = createStyledTextField();
        posteField = createStyledTextField();
        departementField = createStyledTextField();

        createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ajouterEmploye();
            }
        });

        // Ajout des composants au conteneur
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new GridLayout(14, 2, 10, 10)); // Espacement entre les composants
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10)); // Marge autour du contenu

        addField(contentPane, "Nom:", nomField);
        addField(contentPane, "Prénom:", prenomField);
        addField(contentPane, "Date de Naissance:", dateNaissanceField);
        addField(contentPane, "Adresse:", adresseField);
        addField(contentPane, "Code Postal:", codePostalField);
        addField(contentPane, "Ville:", villeField);
        addField(contentPane, "Pays:", paysField);
        addField(contentPane, "Téléphone:", telephoneField);
        addField(contentPane, "Email:", emailField);
        addField(contentPane, "Statut:", statutField);
        addField(contentPane, "Date d'Embauche:", dateEmbaucheField);
        addField(contentPane, "Poste:", posteField);
        addField(contentPane, "Département:", departementField);

        contentPane.add(createButton);

        setContentPane(contentPane);
        contentPane.setBackground(new Color(0xAD, 0xD8, 0xE6)); // LightBlue

        pack();
    }
    private void ajouterEmploye() {
        try (Connection connection = ConnexionDB.getConnection()) {
            String query = "INSERT INTO employe (nom, prenom, date_naissance, adresse, code_postal, ville, pays, " +
                    "telephone, email, statut, date_embauche, poste, departement) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                // Paramétrisez la requête avec les valeurs des champs de saisie
                preparedStatement.setString(1, nomField.getText());
                preparedStatement.setString(2, prenomField.getText());
                preparedStatement.setString(3, dateNaissanceField.getText());
                preparedStatement.setString(4, adresseField.getText());
                preparedStatement.setString(5, codePostalField.getText());
                preparedStatement.setString(6, villeField.getText());
                preparedStatement.setString(7, paysField.getText());
                preparedStatement.setString(8, telephoneField.getText());
                preparedStatement.setString(9, emailField.getText());
                preparedStatement.setString(10, statutField.getText());
                preparedStatement.setString(11, dateEmbaucheField.getText());
                preparedStatement.setString(12, posteField.getText());
                preparedStatement.setString(13, departementField.getText());
                // Exécutez la requête
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Employé ajouté avec succès.");
                    // Vous pouvez également fermer cette fenêtre après l'ajout si nécessaire
                    // dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "L'ajout de l'employé a échoué.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    private JTextField createStyledTextField() {
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(200, 25)); // Ajustez la taille
        return textField;
    }

    private void addField(JPanel panel, String label, JTextField textField) {
        JLabel jLabel = new JLabel(label);
        panel.add(jLabel);
        panel.add(textField);
    }
}
