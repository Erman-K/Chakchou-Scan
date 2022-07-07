package fr.atlasdev.chakchouscan;

public class Ingredient {
    private String nom;
    private double quantite;
    private String nutriscore;
    private String ecoscore;
    private int novascore;
    private String img_url;

    public Ingredient(){
        this.nom = "No information";
        this.quantite = 0;
        this.nutriscore = "No information";
        this.ecoscore = "No information";
        this.novascore = 0;
        this.img_url = "No information";
    }




    public Ingredient(String nom, double qt, String ns, String es, int nova, String url){
        this.nom = nom;
        this.quantite = qt;
        this.novascore = nova;
        this.nutriscore = ns;
        this.ecoscore = es;
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

    public String getEcoscore() {
        return ecoscore;
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

    public void setEcoscore(String ecoscore) {
        this.ecoscore = ecoscore;
    }

    @Override
    public String toString(){
        if (quantite <= 0 & novascore <= 0 & novascore > 4) return nom + "\nQuantité : NO INFORMATION"  + "\nNutriscore : " + nutriscore.toUpperCase() + "\nEcoscore : " + ecoscore.toUpperCase() +"\nNovascore : NO INFORMATION";
        if (quantite <= 0 & novascore > 0 & novascore <= 4) return nom + "\nQuantité : NO INFORMATION"  + "\nNutriscore : " + nutriscore.toUpperCase() + "\nEcoscore : " + ecoscore.toUpperCase() +"\nNovascore : " + novascore;
        if (quantite != 0 & novascore <= 0 & novascore > 4) return nom + "\nQuantité : " + quantite  + "\nNutriscore : " + nutriscore.toUpperCase() + "\nEcoscore : " + ecoscore.toUpperCase() +"\nNovascore : NO INFORMATION ";

        return nom + "\nQuantité : " + quantite + "\nNutriscore : " + nutriscore.toUpperCase() + "\nEcoscore : " + ecoscore.toUpperCase() +"\nNovascore : " + novascore;
    }
}
