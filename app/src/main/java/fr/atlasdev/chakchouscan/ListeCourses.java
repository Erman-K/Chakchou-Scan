package fr.atlasdev.chakchouscan;

import java.util.ArrayList;

public class ListeCourses {
    private ArrayList<Ingredient> liste;

    public ListeCourses(){
        liste = new ArrayList<Ingredient>();
    }

    public void add(Ingredient item) {
        liste.add(item);
    }

    public void addRaw(String nom, double quantite, String unite){
        liste.add(new Ingredient(nom, quantite, unite));
    }

    @Override
    public String toString(){
        String str = "";
        for (Ingredient i : liste){
            str += i.toString();
        }
        return str;
    }

    public static void main(String [] args){
        ListeCourses test = new ListeCourses();
        test.addRaw("Mais",400,"g");
        test.addRaw("Lait", 2, "L");
        test.addRaw("Jus de citron", 20, "mL");
        System.out.print(test);
    }
}
