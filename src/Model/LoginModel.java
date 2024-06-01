package Model;
import View.LoginView;

import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class LoginModel {
    private String username;
    private String password;
    private int employeeId;
    public int getEmployeeId() {
        return employeeId;
    }

    public void setUsername(String username) {
        this.username=username;
    }
    public String getUsername(){return username;}
    public void setPassword(String password){
        this.password=password;
    }
    @Override
    public String toString() {
        return "LoginModel{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
    @SuppressWarnings("unchecked")
    public void checkRh(LoginView loginView) {

    }

}
