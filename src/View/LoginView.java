package View;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu.Separator;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import Controller.LoginController;
import Model.LoginModel;
//import images.*;

@SuppressWarnings("serial")
public class LoginView extends JFrame{//class login view containing code for GUI

    //private JFrame loginFrame;
    private LoginModel loginModel;//login model to be used
    private JTextField jTUsername;
    private JLabel jLUsername;
    private JPasswordField jPPassword;
    private JLabel jLPassword;
    private JButton jBLogin;
    private Container loginContainer;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JSeparator separator;
    private JLabel userImage;
    private JLabel belowLabel;
    private JToggleButton jBPassword;

    public LoginView(){

    }
    //the constructor of loginView containing GUI code
    public LoginView(LoginModel loginModel,LoginController loginController) {

        loginController.addView(this);//sets the view in class variable
        this.loginModel=loginModel;//the login model is set
        setSize(920,700);//	the size of frame is set
        setResizable(false);//set frame is not set re-sizable
        setLocationRelativeTo(null);//the frame is aligned on the center

        loginContainer = getContentPane();//the container of the frame
        loginContainer.setLayout(null);//no layout is used on the container

        leftPanel = new JPanel();
        leftPanel.setLayout(null);

        rightPanel = new JPanel();
        rightPanel.setLayout(null);
        rightPanel.setBackground(Color.white);

        Font font1 = new Font("Sogoe UI", Font.BOLD, 32);
        Font font2 = new Font("Sogoe UI", Font.PLAIN, 20);
        Color newPanelColor = new Color(0, 255, 119);

        leftPanel.setBounds(0, 0, 460, 700);
        Color color2= new Color(33, 89, 66);
        leftPanel.setBackground(color2);

        rightPanel.setBounds(460, 0, 460, 700);

        separator = new JSeparator();
        separator.setBounds(130, 160, 200, 5);
        separator.setPreferredSize(new Dimension(50,5));
        separator.setBackground(new Color(204,204, 204));
        rightPanel.add(separator);

        JLabel cross = new JLabel("x");
        cross.setBounds(424, 0, 36, 43);
        cross.setFont(font1);
        cross.setHorizontalAlignment(JLabel.CENTER);
        cross.setVerticalAlignment(JLabel.CENTER);
        cross.setForeground(new Color(204,204,204));
        cross.setBackground(color2);
        cross.setOpaque(true);
        cross.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cross.addMouseListener(loginController);
        rightPanel.add(cross);


        JLabel jLLogin = new JLabel("Login");
        jLLogin.setFont(font1);
        jLLogin.setHorizontalAlignment(JLabel.CENTER);
        jLLogin.setForeground(new Color(220,220, 220));
        jLLogin.setBackground(new Color(89, 222, 191));
        jLLogin.setOpaque(true);
        jLLogin.setBounds(0, 100, 460, 70);
        rightPanel.add(jLLogin);


        jLUsername= new JLabel("Username");
        jLUsername.setFont(font2);
        Color foreColor = Color.BLACK;
        jLUsername.setForeground(foreColor);
        jLUsername.setBounds(40, 260, 160, 30);
        rightPanel.add(jLUsername);

        jLPassword= new JLabel("Password");
        jLPassword.setFont(font2);
        jLPassword.setForeground(foreColor);
        jLPassword.setBounds(40, 360, 160, 30);
        rightPanel.add(jLPassword);


        jTUsername= new JTextField(30);
        jTUsername.setBounds(40, 300, 300, 40);
        jTUsername.setFont(new Font("Sogoe UI", Font.PLAIN, 18));
        jTUsername.setForeground(new Color(51,51,51));
        jTUsername.setBorder(BorderFactory.createLineBorder(new Color(51,51,51)));
        rightPanel.add(jTUsername);

        jPPassword= new JPasswordField(30);
        jPPassword.setBounds(40, 400, 300, 40);
        jPPassword.setFont(new Font("Sogoe UI", Font.PLAIN, 18));
        jPPassword.setForeground(new Color(51,51,51));
        jPPassword.setBorder(BorderFactory.createLineBorder(new Color(51,51,51)));
        rightPanel.add(jPPassword);

        jBLogin = new JButton("Login", new ImageIcon("src/images/login.png"));
        jBLogin.setFont(new Font("Sogoe UI", Font.PLAIN, 18));
        jBLogin.setBounds(140, 470, 100,50);
        jBLogin.setForeground(new Color(204, 204, 204));
        jBLogin.setBackground(color2);
        jBLogin.addActionListener(loginController);
        jBLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        rightPanel.add(jBLogin);

        jBPassword = new JToggleButton(new ImageIcon("src/images/eye.png"));
        jBPassword.setSelectedIcon(new ImageIcon("src/images/eye.png"));
        jBPassword.setBounds(350, 400, 40, 40);
        jBPassword.setFocusable(false);
        jBPassword.setBorderPainted(false);
        jBPassword.setBackground(Color.white);
        jBPassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jBPassword.setContentAreaFilled(false);
        rightPanel.add(jBPassword);
        jBPassword.addMouseListener(loginController);


        userImage= new JLabel(new ImageIcon("src/images/usericon.png"));
        userImage.setBounds(170, 200, 100, 100);
        leftPanel.add(userImage);

        belowLabel = new JLabel("Humain Resource Manager");
        belowLabel.setFont(new Font("Sogoe UI", Font.BOLD, 26));
        belowLabel.setForeground(new Color(204, 204, 204));
        belowLabel.setBounds(75, 320, 360, 40);
        leftPanel.add(belowLabel);


        loginContainer.add(leftPanel);
        loginContainer.add(rightPanel);
        getRootPane().setDefaultButton(jBLogin);//default listener button for the frame
        setUndecorated(true);//hides the window bar
        setVisible(true); //shows the made frame
        jBLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = getUsername();
                String password = getPassword();
                loginController.authenticateAndRedirect(username, password);
            }
        });
    }

    public JPasswordField getjPPassword() {
        return jPPassword;//returns the password field where value is entered
    }

    public JToggleButton getjBPassword() {//returns the toggle button for eye icon
        return jBPassword;//returns the button
    }

    public String getUsername() {//returns the username entered in text field
        return this.jTUsername.getText();//returns the username
    }

    public String getPassword() {//returns the password entered in text field
        return String.valueOf(this.jPPassword.getPassword());//returns the password
    }

}
