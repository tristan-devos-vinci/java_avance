import java.util.ArrayList;

public abstract class Pizza {
    public static final double PRIX_BASE = 5.0;
    private String titre;
    private String description;
    private ArrayList<Ingredient> ingredients = new ArrayList<>();

    public Pizza(String titre, String description) {
        this.titre = titre;
        this.description = description;
    }

    public Pizza(String titre, String description, ArrayList<Ingredient> ingredients) {
        this(titre, description);
        for (Ingredient i : ingredients) {
            if (this.ingredients.contains(i))
                throw new IllegalArgumentException("Il ne peut pas y avoir deux fois le même ingrédient dans une pizza.");
            this.ingredients.add(i);
        }
    }


    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public boolean ajouter(Ingredient ingredient){
        if (ingredients.contains(ingredient))
            return false;
        ingredients.add(ingredient);
        return true;
    }

    public boolean supprimer(Ingredients ingredient){
        if (!ingredients.contains(ingredient))
            return false;
        ingredients.remove(ingredient);
        return true;
    }

    public double calculerPrix (){
        double prixRetour  = PRIX_BASE;
        for (Ingredient i: ingredients) {
            prixRetour += i.getPrix();
        }
        return prixRetour;
    }

    @Override
    public String toString() {
        String infos = titre + "\n" + description + "\nIngrédients : ";
        for (Ingredient ingredient : ingredients){
            infos +="\n" + ingredient.getNom();
        }
        infos +="\nprix : " + calculerPrix() + " euros";
        return infos;
    }
}
