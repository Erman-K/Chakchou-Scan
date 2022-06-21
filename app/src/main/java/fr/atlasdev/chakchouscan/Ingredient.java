package fr.atlasdev.chakchouscan;

public class Ingredient {
    private String nom;
    private String unite;
    private double quantite;

    public Ingredient(String nom, double qt, String u){
        this.nom = nom;
        this.quantite = qt;
        this.unite = u;
    }

    public String getNom() {
        return nom;
    }

    public String getQuantiteAsString() {
        return String.valueOf(quantite) + unite;
    }

    public double getQuantiteAsDouble() {
        return quantite;
    }

    @Override
    public String toString(){
        return nom + ": " + quantite + unite + "\n";
    }
}
