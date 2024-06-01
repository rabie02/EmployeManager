import Controller.HumanResourceController;
import Controller.LoginController;
import Model.LoginModel;
import Model.Rh;
import View.LoginView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        LoginModel loginModel = new LoginModel();//calling the login model
        LoginController loginController = new LoginController(loginModel);//calling the login controller with parameter for login model
        LoginView loginView = new LoginView(loginModel, loginController);
    }
}
