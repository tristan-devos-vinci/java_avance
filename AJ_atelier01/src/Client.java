import java.util.ArrayList;

public class Client {
    private static int numeroSuivant = 1;
    private int numero;
    private String nom;
    private String prenom;
    private String telephone;
    private Commande commandeEnCours;
    private ArrayList<Commande> commandesPassees = new ArrayList<Commande>();


    public Client(String nom, String prenom, String telephone) {
        this.numero = numeroSuivant++;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getNumero() {
        return numeroSuivant;
    }

    public String getTelephone() {
        return telephone;
    }

    public Commande getCommandeEnCours() {
        return commandeEnCours;
    }

    public boolean enregister(Commande commande) {
        if (commandeEnCours != null) return false;
        if (!commande.getClient().equals(this)) return false;
        if (commandesPassees.contains(commande)) return false;
        commandeEnCours = commande;
        return true;
    }

    public boolean cloturerCommandeEnCours() {
        if (commandeEnCours == null) return false;
        this.commandesPassees.add(this.commandeEnCours);
        this.commandeEnCours = null;
        return true;
    }

    @Override
    public String toString() {
        return "client n° " + numero + " (" + prenom  + " " + nom + ", telephone : " + telephone +")";
    }
}
