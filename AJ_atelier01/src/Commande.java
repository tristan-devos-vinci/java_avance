import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class Commande {
    private static int numeroSuivant = 1;
    private int numero;
    private Client client;
    private LocalDateTime date;
    private ArrayList<LigneDeCommande> lignesCommande = new ArrayList<>();

    public Commande(Client client) {
        this.client = client;
        if (!client.enregister(this)) {
            throw new IllegalArgumentException("La commande n'a pas pu être enregistrée");
        }
        client.enregister(this);
        this.numero = numeroSuivant++;
        this.date = LocalDateTime.now();
    }

    public int getNumero() {
        return numero;
    }

    public Client getClient() {
        return client;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public boolean ajouter(Pizza pizza, int quantite){
        if (!this.equals(client.getCommandeEnCours()))
            return false;
        for (LigneDeCommande ligne : lignesCommande) {
            if (ligne.getPizza().equals(pizza)) {
                ligne.setQuantite(ligne.getQuantite() + quantite);
                return true;
            }
        }
        lignesCommande.add(new LigneDeCommande(pizza, quantite));
        return true;

    }

    public boolean ajouter(Pizza pizza){
        if (!this.equals(client.getCommandeEnCours()))
            return false;
        for (LigneDeCommande ligne : lignesCommande) {
            if (ligne.getPizza().equals(pizza)) {
                ligne.setQuantite(ligne.getQuantite() + 1);
                return true;
            }
        }
        lignesCommande.add(new LigneDeCommande(pizza, 1));
        return true;
    }

    public double calculerMontantTotal(){
        double montantTotal = 0.0;
        for (LigneDeCommande ligne : lignesCommande) {
            System.out.println(ligne.getPrixUnitaire());
            montantTotal += ligne.getQuantite()*ligne.getPrixUnitaire();
        }
        return montantTotal;
    }

    public String detailler(){
        String detail = "";
        for (LigneDeCommande ligne : lignesCommande) {
            detail  +=    ligne.getPizza()+" x"+ligne.getQuantite()+"\n";
        }
        return detail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commande commande = (Commande) o;
        return numero == commande.numero;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }
    public Iterator<LigneDeCommande> iterator() {
        return lignesCommande.iterator();
    }

    public String toString() {
        DateTimeFormatter formater = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        String encours = "";
        if (client.getCommandeEnCours() == this)
            encours = " (en cours)";
        return "Commande n° " + numero + encours + " du " + client + "\ndate : " + formater.format(date);
    }
}
