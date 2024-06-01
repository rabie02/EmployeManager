package Controller;

import Model.ConnexionDB;
import Model.Employee;
import Model.LoginModel;
import View.HomeEmploye;
import View.HumanResourceView;
import View.LoginView;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController implements ActionListener, KeyListener, MouseListener {
    LoginView loginView;
    LoginModel loginModel;
    Employee emp;


    public LoginController (LoginModel loginModel){
         this.loginModel=loginModel;
     }


    public void authenticateAndRedirect(String username, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnexionDB.getConnection();
            String query = "SELECT * FROM user WHERE username = ? AND password = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();

            // If a matching user is found in the database
            if (resultSet.next()) {
                String role = resultSet.getString("role");

                if ("employe".equals(role)) {
                    redirectToHomeEmploye();
                } else if ("RH".equals(role)) {
                    redirectToHumanResourceView();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid role");// Handle invalid role
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnexionDB.close(resultSet);
            ConnexionDB.close(preparedStatement);
            ConnexionDB.close(connection);
        }
    }

    private void redirectToHomeEmploye() {
        HomeEmploye homeEmploye = new HomeEmploye(loginModel);
        homeEmploye.setVisible(true);

        // Close the login view if needed
        loginView.dispose();

    }

    private void redirectToHumanResourceView() {
        // Redirect to HR view
        HumanResourceView hrView = new HumanResourceView();
        hrView.setVisible(true);

        // Close the login view if needed
        loginView.dispose();
    }

    public void addView(Object view) {
        this.loginView=(LoginView) view;//class variable is set
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() instanceof JToggleButton) {
            loginView.getjPPassword().setEchoChar((char)0);//changes the icon of eye image and shows the password
            loginView.getjBPassword().setSelectedIcon(new ImageIcon("src/images/eye-dark.png"));
        }

    }
    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() instanceof JToggleButton) {
            loginView.getjPPassword().setEchoChar(('�'));//changes the icon of eye image and hides the password
            loginView.getjBPassword().setSelectedIcon(new ImageIcon("src/images/eye.png"));
        }

    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
