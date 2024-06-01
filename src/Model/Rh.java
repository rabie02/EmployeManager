package Model;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

public class Rh extends Utilisateur {
    private String domaineDeResponsabilite;

    public Rh() {
        // Constructeur par défaut
    }

    public Rh(String nom, String prenom, Date dateNaissance, String adresse, String codePostal,
              String ville, String pays, String telephone, String email, String statut,
              Date dateEmbauche, String domaineDeResponsabilite) {
        super(nom, prenom, dateNaissance, adresse, codePostal, ville, pays, telephone, email, statut, dateEmbauche);
        this.domaineDeResponsabilite = domaineDeResponsabilite;
    }



    // Autres méthodes si nécessaire

    // Getters et setters spécifiques à Rh
    public String getDomaineDeResponsabilite() {
        return domaineDeResponsabilite;
    }

    public void setDomaineDeResponsabilite(String domaineDeResponsabilite) {
        this.domaineDeResponsabilite = domaineDeResponsabilite;
    }


}