package fr.atlasdev.chakchouscan;

import java.util.ArrayList;

public class  ListeCourses {
    private ArrayList<Ingredient> liste;

    public ListeCourses(){
        liste = new ArrayList<Ingredient>();
    }

    public void add(Ingredient item) {
        liste.add(item);
    }

    public void addRaw(String nom, double quantite, String ns, String es, int nova, String url){
        liste.add(new Ingredient(nom, quantite, ns, es,nova, url));
    }

    @Override
    public String toString(){
        String str = "";
        for (Ingredient i : liste){
            str += i.toString();
        }
        return str;
    }

}
