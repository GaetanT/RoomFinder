package insa.roomfinder;

/**
 * Created by Gaetan on 09/01/2016.
 */
public class Salle {
    private boolean dispo;
    private String nom;
    private String info;

    public Salle(boolean dispo, String nom, String info) {
        this.dispo = dispo;
        this.nom = nom;
        this.info = info;
    }

    public boolean isDispo() {
        return dispo;
    }

    public void setDispo(boolean dispo) {
        this.dispo = dispo;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
