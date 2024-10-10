import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

public class PizzaComposable extends Pizza{
    private Client createur;
    private LocalDateTime date;


    PizzaComposable (Client createur){
        super("Pizza composable du client " + createur.getNumero(), "Pzza de " + createur.getPrenom()+ " " + createur.getNom());
        this.createur = createur;
        this.date = LocalDateTime.now();

    }

    public Client getCreateur() {
        return createur;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public String toString() {
        DateTimeFormatter formater = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return super.toString() + "\nPizza créée le " + formater.format(date);
    }
}
