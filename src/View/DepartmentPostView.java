package View;
import Model.ConnexionDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class DepartmentPostView extends javax.swing.JFrame{
    private HumanResourceView parent;
    /**
     * Creates new form DepartmentPostView
     */
    public DepartmentPostView() {
        initComponents();
        setLocationRelativeTo(parent);
        loadDataDepartments();
        loadDataPosts();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    // ...

    public void loadDataDepartments() {
        try (Connection connection = ConnexionDB.getConnection()) {
            DefaultTableModel tableModel = (DefaultTableModel) jTableDepartments.getModel();
            tableModel.setRowCount(0);
            String query = "SELECT * FROM departement";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    Object[] row = new Object[2]; // Adjust the array size based on the number of columns
                    row[0] = resultSet.getString("nom_departement");
                    row[1] = resultSet.getString("description");
                    tableModel.addRow(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadDataPosts() {
        try (Connection connection = ConnexionDB.getConnection()) {
            DefaultTableModel tableModel = (DefaultTableModel) jTablePosts.getModel();
            tableModel.setRowCount(0);
            String query = "SELECT * FROM poste";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    Object[] row = new Object[5]; // Adjust the array size based on the number of columns
                    row[0] = resultSet.getString("nom_poste");
                    row[1] = resultSet.getString("description");
                    row[2] = resultSet.getDouble("salaire_base");
                    row[3] = resultSet.getString("avantages_sociaux");
                    // Add more rows for additional columns
                    tableModel.addRow(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ... (Autres méthodes)

    private javax.swing.JButton jButtonAddDepartment;
    private javax.swing.JButton jButtonEditDepartment;
    private javax.swing.JButton jButtonDeleteDepartment;
    private javax.swing.JButton jButtonAddPost;
    private javax.swing.JButton jButtonEditPost;
    private javax.swing.JButton jButtonDeletePost;
    private javax.swing.JPanel jPanelDepartments;
    private javax.swing.JPanel jPanelPosts;
    private javax.swing.JScrollPane jScrollPaneDepartments;
    private javax.swing.JScrollPane jScrollPanePosts;
    private javax.swing.JTable jTableDepartments;
    private javax.swing.JTable jTablePosts;
    // ... (Autres composants)
    // End of variables declaration//GEN-END:variables
    @SuppressWarnings("unchecked")
// <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        JSplitPane jSplitPane = new JSplitPane();
        jSplitPane.setDividerLocation(400); // Ajustez la position du séparateur selon vos besoins

        jPanelDepartments = new javax.swing.JPanel();
        jScrollPaneDepartments = new javax.swing.JScrollPane();
        jTableDepartments = new javax.swing.JTable();
        jButtonAddDepartment = new javax.swing.JButton();
        jButtonEditDepartment = new javax.swing.JButton();
        jButtonDeleteDepartment = new javax.swing.JButton();

        jPanelDepartments.setBorder(BorderFactory.createTitledBorder("Departments"));
        jPanelDepartments.setBackground(new Color(200, 200, 240));

        jTableDepartments.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"Department Name", "Description"}
        ) {
            Class[] types = new Class[]{java.lang.String.class, java.lang.String.class};

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });
        jScrollPaneDepartments.setViewportView(jTableDepartments);

        jButtonAddDepartment.setText("Add");
        jButtonEditDepartment.setText("Edit");
        jButtonDeleteDepartment.setText("Delete");

        // Ajoutez des gestionnaires d'événements pour les boutons d'actions

        javax.swing.GroupLayout jPanelDepartmentsLayout = new javax.swing.GroupLayout(jPanelDepartments);
        jPanelDepartments.setLayout(jPanelDepartmentsLayout);
        jPanelDepartmentsLayout.setHorizontalGroup(
                jPanelDepartmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelDepartmentsLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanelDepartmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPaneDepartments, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                                        .addGroup(jPanelDepartmentsLayout.createSequentialGroup()
                                                .addComponent(jButtonAddDepartment)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButtonEditDepartment)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButtonDeleteDepartment)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        jPanelDepartmentsLayout.setVerticalGroup(
                jPanelDepartmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelDepartmentsLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPaneDepartments, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelDepartmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButtonAddDepartment)
                                        .addComponent(jButtonEditDepartment)
                                        .addComponent(jButtonDeleteDepartment))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSplitPane.setLeftComponent(jPanelDepartments);

        jPanelPosts = new javax.swing.JPanel();
        jScrollPanePosts = new javax.swing.JScrollPane();
        jTablePosts = new javax.swing.JTable();
        jButtonAddPost = new javax.swing.JButton();
        jButtonEditPost = new javax.swing.JButton();
        jButtonDeletePost = new javax.swing.JButton();

        jPanelPosts.setBorder(javax.swing.BorderFactory.createTitledBorder("Posts"));
        jPanelPosts.setBackground(new Color(200, 240, 200));

        jTablePosts.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"Post Name", "Description", "Base Salary", "Social Benefits"}
        ) {
            Class[] types = new Class[]{java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class};

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });
        jScrollPanePosts.setViewportView(jTablePosts);

        jButtonAddPost.setText("Add");
        jButtonEditPost.setText("Edit");
        jButtonDeletePost.setText("Delete");

        // Ajoutez des gestionnaires d'événements pour les boutons d'actions

        javax.swing.GroupLayout jPanelPostsLayout = new javax.swing.GroupLayout(jPanelPosts);
        jPanelPosts.setLayout(jPanelPostsLayout);
        jPanelPostsLayout.setHorizontalGroup(
                jPanelPostsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelPostsLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanelPostsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPanePosts, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                                        .addGroup(jPanelPostsLayout.createSequentialGroup()
                                                .addComponent(jButtonAddPost)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButtonEditPost)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButtonDeletePost)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())

        );

        jPanelPostsLayout.setVerticalGroup(
                jPanelPostsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelPostsLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPanePosts, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelPostsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButtonAddPost)
                                        .addComponent(jButtonEditPost)
                                        .addComponent(jButtonDeletePost))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSplitPane.setRightComponent(jPanelPosts);

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
        // Pour les boutons "Edit" et "Delete" des départements
        jButtonEditDepartment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                jButtonEditDepartmentActionPerformed(evt);
            }
        });

        jButtonDeleteDepartment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                jButtonDeleteDepartmentActionPerformed(evt);
            }
        });

// Pour les boutons "Edit" et "Delete" des postes
        jButtonEditPost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                jButtonEditPostActionPerformed(evt);
            }
        });

        jButtonDeletePost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                jButtonDeletePostActionPerformed(evt);
            }
        });
        jButtonAddDepartment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                jButtonAddDepartmentActionPerformed(evt);
            }

            private void jButtonAddDepartmentActionPerformed(ActionEvent evt) {
                String departmentName = JOptionPane.showInputDialog(this, "Enter Department Name:");
                String description = JOptionPane.showInputDialog(this, "Enter Department Description:");

                if (departmentName != null && description != null) {
                    addDepartment(departmentName, description);
                } else {
                    JOptionPane.showMessageDialog(DepartmentPostView.this, "Please enter valid department name and description.");
                }
            }
        });
        jButtonAddPost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                jButtonAddPostActionPerformed(evt);
            }

            private void jButtonAddPostActionPerformed(ActionEvent evt) {
                String postName = JOptionPane.showInputDialog(this, "Enter Post Name:");
                String description = JOptionPane.showInputDialog(this, "Enter Post Description:");
                String baseSalaryStr = JOptionPane.showInputDialog(this, "Enter Base Salary:");
                String socialBenefits = JOptionPane.showInputDialog(this, "Enter Social Benefits:");

                try {
                    double baseSalary = Double.parseDouble(baseSalaryStr);
                    if (postName != null && description != null && baseSalaryStr != null && socialBenefits != null) {
                        addPost(postName, description, baseSalary, socialBenefits);
                    } else {
                        JOptionPane.showMessageDialog(DepartmentPostView.this, "Please enter valid post information.");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(DepartmentPostView.this, "Please enter a valid base salary (numeric value).");
                }
            }
        });
        pack();
    }
    public void addDepartment(String departmentName, String description) {
        try (Connection connection = ConnexionDB.getConnection()) {
            String query = "INSERT INTO departement (nom_departement, description) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, departmentName);
                preparedStatement.setString(2, description);
                preparedStatement.executeUpdate();
            }
            loadDataDepartments();  // Rafraîchir les données après l'ajout
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void editDepartment(String departmentName, String newDescription) {
        try (Connection connection = ConnexionDB.getConnection()) {
            String query = "UPDATE departement SET description = ? WHERE nom_departement = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, newDescription);
                preparedStatement.setString(2, departmentName);
                preparedStatement.executeUpdate();
            }
            loadDataDepartments();  // Rafraîchir les données après la modification
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDepartment(String departmentName) {
        try (Connection connection = ConnexionDB.getConnection()) {
            String query = "DELETE FROM departement WHERE nom_departement = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, departmentName);
                preparedStatement.executeUpdate();
            }
            loadDataDepartments();  // Rafraîchir les données après la suppression
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPost(String postName, String description, double baseSalary, String socialBenefits) {
        try (Connection connection = ConnexionDB.getConnection()) {
            String query = "INSERT INTO poste (nom_poste, description, salaire_base, avantages_sociaux) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, postName);
                preparedStatement.setString(2, description);
                preparedStatement.setDouble(3, baseSalary);
                preparedStatement.setString(4, socialBenefits);
                preparedStatement.executeUpdate();
            }
            loadDataPosts();  // Rafraîchir les données après l'ajout
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editPost(String postName, String newDescription, double newBaseSalary, String newSocialBenefits) {
        try (Connection connection = ConnexionDB.getConnection()) {
            String query = "UPDATE poste SET description = ?, salaire_base = ?, avantages_sociaux = ? WHERE nom_poste = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, newDescription);
                preparedStatement.setDouble(2, newBaseSalary);
                preparedStatement.setString(3, newSocialBenefits);
                preparedStatement.setString(4, postName);
                preparedStatement.executeUpdate();
            }
            loadDataPosts();  // Rafraîchir les données après la modification
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePost(String postName) {
        try (Connection connection = ConnexionDB.getConnection()) {
            String query = "DELETE FROM poste WHERE nom_poste = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, postName);
                preparedStatement.executeUpdate();
            }
            loadDataPosts();  // Rafraîchir les données après la suppression
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void jButtonEditDepartmentActionPerformed(ActionEvent evt) {
        String departmentName = getSelectedDepartmentName();
        if (departmentName != null) {
            String newDescription = JOptionPane.showInputDialog(this, "Enter New Department Description:");
            editDepartment(departmentName, newDescription);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a department to edit.");
        }
    }

    private void jButtonDeleteDepartmentActionPerformed(ActionEvent evt) {
        String departmentName = getSelectedDepartmentName();
        if (departmentName != null) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this department?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                deleteDepartment(departmentName);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a department to delete.");
        }
    }

    private void jButtonEditPostActionPerformed(ActionEvent evt) {
        String postName = getSelectedPostName();
        if (postName != null) {
            String newDescription = JOptionPane.showInputDialog(this, "Enter New Post Description:");
            double newBaseSalary = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter New Base Salary:"));
            String newSocialBenefits = JOptionPane.showInputDialog(this, "Enter New Social Benefits:");
            editPost(postName, newDescription, newBaseSalary, newSocialBenefits);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a post to edit.");
        }
    }

    private void jButtonDeletePostActionPerformed(ActionEvent evt) {
        String postName = getSelectedPostName();
        if (postName != null) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this post?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                deletePost(postName);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a post to delete.");
        }
    }

    // Ajoutez ces méthodes pour obtenir le nom sélectionné dans les tableaux

    private String getSelectedDepartmentName() {
        int selectedRow = jTableDepartments.getSelectedRow();
        if (selectedRow != -1) {
            return (String) jTableDepartments.getValueAt(selectedRow, 0);
        }
        return null;
    }

    private String getSelectedPostName() {
        int selectedRow = jTablePosts.getSelectedRow();
        if (selectedRow != -1) {
            return (String) jTablePosts.getValueAt(selectedRow, 0);
        }
        return null;
    }
}
