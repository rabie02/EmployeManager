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

public class PerformanceRapportView extends javax.swing.JFrame {
    private HumanResourceView parent;
     private JTable jTablePerformance;
    private JTable jTableRapport ;


    public PerformanceRapportView() {
        initComponents();
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loadRapport();
        loadPerformanceData();

    }

    public void initComponents() {

        JSplitPane jSplitPane = new JSplitPane();
        jSplitPane.setDividerLocation(400);

        JPanel jPanelPerformance = new javax.swing.JPanel();
        JScrollPane jScrollPanePerformance = new javax.swing.JScrollPane();
        JButton jButtonSuiviPerformance = new javax.swing.JButton();

        jPanelPerformance.setBorder(BorderFactory.createTitledBorder("Suivi des Performances"));
        jPanelPerformance.setBackground(new Color(200, 200, 240));
        jTablePerformance = new javax.swing.JTable();
        jTablePerformance.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"Employee ID", "Critère", "Note"}
        ) {
            Class[] types = new Class[]{java.lang.String.class, java.lang.String.class, java.lang.Double.class};

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });
        jScrollPanePerformance.setViewportView(jTablePerformance);

        jButtonSuiviPerformance.setText("Évaluation des Performances");

        // Ajoutez des gestionnaires d'événements pour les boutons d'actions

        javax.swing.GroupLayout jPanelPerformanceLayout = new javax.swing.GroupLayout(jPanelPerformance);
        jPanelPerformance.setLayout(jPanelPerformanceLayout);
        jPanelPerformanceLayout.setHorizontalGroup(
                jPanelPerformanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelPerformanceLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanelPerformanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPanePerformance, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                                        .addGroup(jPanelPerformanceLayout.createSequentialGroup()
                                                .addComponent(jButtonSuiviPerformance)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        jPanelPerformanceLayout.setVerticalGroup(
                jPanelPerformanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelPerformanceLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPanePerformance, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonSuiviPerformance)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JPanel jPanelRapport = new javax.swing.JPanel();
        JScrollPane jScrollPaneRapport = new javax.swing.JScrollPane();
        JButton jButtonGenerationRapport = new javax.swing.JButton();
        jPanelRapport.setBorder(javax.swing.BorderFactory.createTitledBorder("Rapport"));
        jPanelRapport.setBackground(new Color(200, 240, 200));
        this.jTableRapport = new javax.swing.JTable();
        jTableRapport.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"Employee ID", "Rapport Texte", "Rapport Date"}
        ) {
            Class[] types = new Class[]{java.lang.String.class, java.lang.String.class, java.lang.String.class};
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });
        jScrollPaneRapport.setViewportView(jTableRapport);
        jButtonSuiviPerformance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                loadPerformanceData();
            }
        });

        // ... (autres configurations du bouton et des composants)

        jButtonGenerationRapport.setText("Génération de Rapports");

        // Ajoutez le gestionnaire d'événements pour le bouton "Génération de Rapports"
        jButtonGenerationRapport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ajouterRapport();
            }
        });

        // Ajoutez des gestionnaires d'événements pour les boutons d'actions
        javax.swing.GroupLayout jPanelRapportLayout = new javax.swing.GroupLayout(jPanelRapport);
        jPanelRapport.setLayout(jPanelRapportLayout);
        jPanelRapportLayout.setHorizontalGroup(
                jPanelRapportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelRapportLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanelRapportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPaneRapport, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                                        .addGroup(jPanelRapportLayout.createSequentialGroup()
                                                .addComponent(jButtonGenerationRapport)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        jPanelRapportLayout.setVerticalGroup(
                jPanelRapportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelRapportLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPaneRapport, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonGenerationRapport)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSplitPane.setLeftComponent(jPanelPerformance);
        jSplitPane.setRightComponent(jPanelRapport);

        // ... (autres configurations de la fenêtre)

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                                .addContainerGap())
        );

        // Ajoutez le gestionnaire d'événements pour le bouton "Génération de Rapports"

        // ... (autres configurations du bouton et des composants)
        jButtonGenerationRapport = new javax.swing.JButton(); // Initialize jButtonGenerationRapport

        jButtonGenerationRapport.setText("Génération de Rapports");

        // Ajoutez le gestionnaire d'événements pour le bouton "Génération de Rapports"
        jButtonGenerationRapport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ajouterRapport();
            }
        });

        pack();

    }




    public void ajouterPerformance() {
        // Créer une boîte de dialogue pour l'ajout de performances
        JDialog dialog = new JDialog(this, "Ajouter Performance", true);
        dialog.setSize(400, 200);
        dialog.setLayout(new BorderLayout());

        // Ajouter les composants nécessaires au formulaire d'ajout de performances
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JLabel labelEmployeeId = new JLabel("Employee ID:");
        JTextField textFieldEmployeeId = new JTextField();
        JLabel labelCriteria = new JLabel("Critère:");
        JTextField textFieldCriteria = new JTextField();
        JLabel labelScore = new JLabel("Note:");
        JTextField textFieldScore = new JTextField();
        JButton addButton = new JButton("Ajouter");

        // Ajouter le gestionnaire d'événements pour le bouton "Ajouter"
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupérer les données saisies
                String employeeId = textFieldEmployeeId.getText();
                String criteria = textFieldCriteria.getText();
                double score = Double.parseDouble(textFieldScore.getText());

                // Ajouter le code pour l'ajout dans la base de données ou autre traitement nécessaire
                // ...
                // Fermer la boîte de dialogue après l'ajout
                dialog.dispose();
            }
        });

        // Ajouter les composants au panneau
        panel.add(labelEmployeeId);
        panel.add(textFieldEmployeeId);
        panel.add(labelCriteria);
        panel.add(textFieldCriteria);
        panel.add(labelScore);
        panel.add(textFieldScore);

        // Ajouter le panneau à la boîte de dialogue
        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(addButton, BorderLayout.SOUTH);

        // Afficher la boîte de dialogue
        dialog.setVisible(true);


    }
    public void ajouterRapport() {
        // Ajoutez le code pour afficher le formulaire d'ajout de rapports
        AddPerformanceRapportDialog dialog = new AddPerformanceRapportDialog(PerformanceRapportView.this, "Ajouter Rapport");
        dialog.setVisible(true);
    }



    public void loadRapport() {
            try (Connection connection = ConnexionDB.getConnection()) {
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
    // Méthode pour charger les données de performance depuis la base de données
    public void loadPerformanceData() {
        try (Connection connection = ConnexionDB.getConnection()) {
            DefaultTableModel performanceTableModel = (DefaultTableModel) jTablePerformance.getModel();
            performanceTableModel.setRowCount(0); // Efface les lignes existantes
            String query = "SELECT employee_id, evaluation_criteria, performance_score FROM performance";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Object[] row = new Object[3]; // 3 au lieu de 2 pour inclure employee_id
                    row[0] = resultSet.getString("employee_id");
                    row[1] = resultSet.getString("evaluation_criteria");
                    row[2] = resultSet.getDouble("performance_score");
                    performanceTableModel.addRow(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
