package domaine;

import util.Util;

import java.time.Duration;
import java.util.*;

public class Plat {

    public enum Difficulte{
        X,XX,XXX,XXXX,XXXXX;
        @Override
        public String toString() {
            return super.toString().replace("X","*");
        }
    }

    public enum Cout{
        $,$$,$$$,$$$$,$$$$$;
        @Override
        public String toString() {
            return super.toString().replace("$","€");
        }
    }

    public enum Type{
        ENTREE, PLAT, DESSERT;
    }

    private final String nom;
    private int nbPersonnes;
    private Difficulte niveauDeDifficulte;
    private Cout cout;
    private Duration dureeEnMinutes = Duration.ofMinutes(0);
    private List<Instruction> recette = new ArrayList<Instruction>();
    private Set<IngredientQuantifie> ingredients = new HashSet<IngredientQuantifie>();
    private Type type;
    public Plat(String nom, int nbPersonnes, Difficulte niveauDeDifficulte, Cout cout, Type type) {
        Util.checkString(nom);
        Util.checkStrictlyPositive(nbPersonnes);
        Util.checkObject(niveauDeDifficulte);
        Util.checkObject(cout);
        this.nom = nom;
        this.nbPersonnes = nbPersonnes;
        this.niveauDeDifficulte = niveauDeDifficulte;
        this.cout = cout;
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public int getNbPersonnes() {
        return nbPersonnes;
    }

    public Difficulte getNiveauDeDifficulte() {
        return niveauDeDifficulte;
    }

    public Cout getCout() {
        return cout;
    }

    public Duration getDureeEnMinutes() {
        return dureeEnMinutes;
    }

    // gestion de la recette et de la dureeEnMinutes


    public Type getType() {
        return type;
    }

    /** Cette méthode insère l'instruction à la position précisée (la position commence à 1)
     * @param position la position à laquelle l'instruction doit être insérée
     * @param instruction l'instruction à insérer
     * @throws IllegalArgumentException en cas de position invalide ou d'instruction null
     */
    public void insererInstruction(int position, Instruction instruction){
        Util.checkStrictlyPositive(position);
        Util.checkObject(instruction);
        if (position > recette.size() + 1) throw new IllegalArgumentException();
        recette.add(position-1,instruction);
        dureeEnMinutes = dureeEnMinutes.plus(instruction.getDureeEnMinutes());
    }

    /** Cette méthode ajoute l'instruction en fin de la liste
     * @param instruction l'instruction à ajouter
     * @throws IllegalArgumentException en cas d'instruction null
     */
    public void ajouterInstruction (Instruction instruction){
        Util.checkObject(instruction);
        recette.add(instruction);
        dureeEnMinutes = dureeEnMinutes.plus(instruction.getDureeEnMinutes());
    }

    /**
     * Cette méthode remplace l’instruction de la position précisée par celle en paramètre (la position commence à 1).
     * @param position la position de l'instruction à remplacer
     * @param instruction la nouvelle instruction
     * @return l'instruction remplacée
     * @throws IllegalArgumentException en cas de position invalide ou d'instruction null
     */
    public Instruction remplacerInstruction (int position, Instruction instruction){
        Util.checkStrictlyPositive(position);
        Util.checkObject(instruction);
        if (position > recette.size()) throw new IllegalArgumentException();
        Instruction instructionRemplacee = recette.set(position-1,instruction);
        dureeEnMinutes = dureeEnMinutes.minus(instructionRemplacee.getDureeEnMinutes());
        dureeEnMinutes = dureeEnMinutes.plus(instruction.getDureeEnMinutes());
        return instructionRemplacee;
    }

    /**
     * Cette méthode supprime l’instruction qui se trouve à la position précisée en paramètre (la position commence à 1).
     * @param position la position de l'instruction à supprimer
     * @return l'instuction supprimée
     * @throws IllegalArgumentException en cas de position invalide
     */
    public Instruction supprimerInstruction (int position){
        Util.checkStrictlyPositive(position);
        if (position > recette.size() ) throw new IllegalArgumentException();
        Instruction instructionSupprimee = recette.remove(position-1);
        dureeEnMinutes = dureeEnMinutes.minus(instructionSupprimee.getDureeEnMinutes());
        return instructionSupprimee;
    }

    public List<Instruction> instructions(){
        return Collections.unmodifiableList(recette);
    }


    public boolean ajouterIngredient(Ingredient ingredient, int quantite, Unite unite){
        //crée un IngrédientQuantifie et l’ajoute si l’ingrédient n’est pas encore
        //présent. Cela renvoie false si l’ingrédient est déjà présent.
        IngredientQuantifie ingredientQuantifie = new IngredientQuantifie(ingredient, quantite, unite);
        if(ingredients.contains(ingredientQuantifie)){
            return false;
        }
        ingredients.add(ingredientQuantifie);
        return true;
    }
    public boolean ajouterIngredient(Ingredient ingredient, int quantite){
        //crée un IngrédientQuantifie et l’ajoute si l’ingrédient n’est pas encore
        //présent. Cela renvoie false si l’ingrédient est déjà présent.
        IngredientQuantifie ingredientQuantifie = new IngredientQuantifie(ingredient, quantite, Unite.NEANT);
        if(ingredients.contains(ingredientQuantifie)){
            return false;
        }
        ingredients.add(ingredientQuantifie);
        return true;
    }

    public boolean modifierIngredient(Ingredient ingredient, int quantite, Unite unite){
        //modifie la quantité et l’unité de mesure de l’ingrédient si celui-ci est
        //présent. Cela renvoie false si l’ingrédient n’est pas présent.
        IngredientQuantifie ingredientQuantifie = new IngredientQuantifie(ingredient, quantite, unite);
        if(ingredients.contains(ingredientQuantifie)){
            ingredients.remove(ingredientQuantifie);
            ingredients.add(ingredientQuantifie);
            return true;
        }
        return false;
    }

    public boolean supprimerIngredient(Ingredient ingredient){
        //supprime l’ingrédient s’il est présent. Cela renvoie false si l’ingrédient
        //n’est pas présent.
        IngredientQuantifie ingredientQuantifie = new IngredientQuantifie(ingredient, 0, Unite.NEANT);
        if(ingredients.contains(ingredientQuantifie)){
            ingredients.remove(ingredientQuantifie);
            return true;
        }
        return false;
    }

    public IngredientQuantifie trouverIngredientQuantifie(Ingredient ingredient){
        //retourne l’ingrédient quantifié correspondant à l’ingrédient passé en
        //paramètre. Cela renvoie null si l’ingrédient n’est pas présent.
        for(IngredientQuantifie ingredientQuantifie : ingredients){
            if(ingredientQuantifie.getIngredient().equals(ingredient)){
                return ingredientQuantifie;
            }
        }
        return null;
    }













    @Override
    public String toString() {
        String hms = String.format("%d h %02d m", dureeEnMinutes.toHours(), dureeEnMinutes.toMinutes()%60);
        String res = this.nom + "\n\n";
        res += "Pour " + this.nbPersonnes + " personnes\n";
        res += "Difficulté : " + this.niveauDeDifficulte + "\n";
        res += "Coût : " + this.cout + "\n";
        res += "Durée : " + hms + " \n\n";
        res += "Ingrédients :\n";
        for (IngredientQuantifie ing : this.ingredients) {
            res += ing + "\n";
        }
        int i = 1;
        res += "\n";
        for (Instruction instruction : this.recette) {
            res += i++ + ". " + instruction + "\n";
        }
        return res;
    }

}
