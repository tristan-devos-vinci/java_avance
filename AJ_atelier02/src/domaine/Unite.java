package domaine;

public enum Unite {
    GRAMME("gr"),
    KILOGRAMME("kg"),
    LITRE("l"),
    MILLILITRE("ml"),
    CENTILITRE("cl"),
    DECILITRE("dl"),
    CUILLERE_A_CAFE("cc"),
    CUILLERE_A_THE("ct"),
    CUILLERE_A_DESSERT("cd"),
    CUILLERE_A_SOUPE("cs"),
    PINCEE("pincée"),
    UN_PEU("peu"),
    NEANT("");
    private final String unite;


    Unite(String unite) {
        this.unite = unite;
    }

    @Override
    public String toString() {
        return unite;
    }
}
