package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Employee extends Utilisateur {
    private int nbrHeuresTravail;
    private String poste;

    // Constructeurs, getters, setters...

    public Employee() {
        // Constructeur par défaut
    }

    public Employee(String nom, String prenom, Date dateNaissance, String adresse, String codePostal,
                    String ville, String pays, String telephone, String email, String statut,
                    Date dateEmbauche, int nbrHeuresTravail, String poste) {
        super(nom, prenom, dateNaissance, adresse, codePostal, ville, pays, telephone, email, statut, dateEmbauche);
        this.nbrHeuresTravail = nbrHeuresTravail;
        this.poste = poste;
    }

    // Autres méthodes si nécessaire

    // Getters et setters spécifiques à Employee
    public int getNbrHeuresTravail() {
        return nbrHeuresTravail;
    }

    public void setNbrHeuresTravail(int nbrHeuresTravail) {
        this.nbrHeuresTravail = nbrHeuresTravail;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }



    @Override
    public String toString() {
        super.toString();
        return "Employee{" +
                "nbrHeuresTravail=" + nbrHeuresTravail +
                ", poste='" + poste + '\'' +
                '}';
    }


}
