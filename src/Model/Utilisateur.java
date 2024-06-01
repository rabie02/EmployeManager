package Model;

import java.util.Date;

    public class Utilisateur {

        private String nom;
        private String prenom;
        private Date dateNaissance;
        private String adresse;
        private String codePostal;
        private String ville;
        private String pays;
        private String telephone;
        private String email;
        private String statut;
        private Date dateEmbauche;
        private String deparetment;

        // Constructors

        public Utilisateur() {
            // Default constructor
        }

        public Utilisateur(String nom, String prenom, Date dateNaissance, String adresse, String codePostal,
                           String ville, String pays, String telephone, String email, String statut,
                           Date dateEmbauche) {
            this.nom = nom;
            this.prenom = prenom;
            this.dateNaissance = dateNaissance;
            this.adresse = adresse;
            this.codePostal = codePostal;
            this.ville = ville;
            this.pays = pays;
            this.telephone = telephone;
            this.email = email;
            this.statut = statut;
            this.dateEmbauche = dateEmbauche;
        }

        // Getters and Setters

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getPrenom() {
            return prenom;
        }

        public void setPrenom(String prenom) {
            this.prenom = prenom;
        }

        // Repeat the pattern for other fields

        // Additional Methods

        @Override
        public String toString() {
            return "Utilisateur{" +

                    ", nom='" + nom + '\'' +
                    ", prenom='" + prenom + '\'' +
                    ", dateNaissance=" + dateNaissance +
                    ", adresse='" + adresse + '\'' +
                    ", codePostal='" + codePostal + '\'' +
                    ", ville='" + ville + '\'' +
                    ", pays='" + pays + '\'' +
                    ", telephone='" + telephone + '\'' +
                    ", email='" + email + '\'' +
                    ", statut='" + statut + '\'' +
                    ", dateEmbauche=" + dateEmbauche +
                    '}';
        }

        protected void setTelephone(String telephone) {
            this.telephone=telephone;
        }
        protected void setDateNaissance(Date dateNaissance){
            this.dateNaissance=dateNaissance;
        }
        protected void setDateEmbauche(Date dateEmbauche){
            this.dateEmbauche=dateEmbauche;
        }

        protected void setAdresse(String adresse) {
            this.adresse=adresse;
        }

        protected void setCodePostal(String codePostal) {
            this.codePostal=codePostal;
        }

        protected void setVille(String ville) {
            this.ville=ville;
        }

        protected void setPays(String pays) {
            this.ville=ville;
        }

        protected void setEmail(String email) {
            this.email=email;
        }

        protected void setStatut(String statut) {
            this.statut=statut;
        }

        protected void setDepartement(String departement) {
            this.deparetment=departement;

        }

        public String getAdresse() {
            return  adresse;
        }

        public String getTelephone() {
            return telephone;
        }

        public String getEmail() {
            return email;
        }

        public Date getDateNaissance() {
            return dateNaissance;
        }

        public String getVille() {
            return ville;
        }

        public String getPays() {
            return pays;
        }

        public  String getStatut() {
            return statut;
        }

        public Date getDateEmbauche() {
            return dateEmbauche;
        }
    }


