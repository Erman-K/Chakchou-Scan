package fr.atlasdev.chakchouscan;

public class Ingredient {
    private String nom;
    private double quantite;
    private String nutriscore;
    private int novascore;
    private String img_url;

    public Ingredient(){
        this.nom = "";
        this.quantite = 0;
        this.nutriscore = "";
        this.novascore = 0;
        this.img_url = "";
    }


    public Ingredient(String nom, double qt, String ns, int nova, String url){
        this.nom = nom;
        this.quantite = qt;
        this.novascore = nova;
        this.nutriscore = ns;
        this.img_url = url;
    }

    public String getNom() {
        return nom;
    }

    public String getScoreAsString() {
        return String.valueOf(nutriscore);
    }

    public int getNovascore() {
        return novascore;
    }

    public String getUrlAsString() {
        return String.valueOf(img_url);
    }

    public double getQuantiteAsDouble() {
        return quantite;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public void setNutriscore(String nutriscore) {
        this.nutriscore = nutriscore;
    }

    public void setNovascore(int novascore) {
        this.novascore = novascore;
    }

    @Override
    public String toString(){
        return nom + ": " + quantite + "nutriscore :" + nutriscore + "img_url :" + img_url + "\n";
    }
}
