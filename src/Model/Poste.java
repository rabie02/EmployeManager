package Model;

public class Poste {
    private String nomPoste;
    private String description;
    private float salaireBase;
    private String avantagesSociaux;

    @Override
    public String toString() {
        return "Poste{" +
                "nomPoste='" + nomPoste + '\'' +
                ", description='" + description + '\'' +
                ", salaireBase=" + salaireBase +
                ", avantagesSociaux='" + avantagesSociaux + '\'' +
                '}';
    }
}
