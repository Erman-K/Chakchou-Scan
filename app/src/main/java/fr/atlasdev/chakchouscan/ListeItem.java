package fr.atlasdev.chakchouscan;

import java.util.ArrayList;

public class ListeItem {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<String> getDirections() {
        return directions;
    }

    public void setDirections(ArrayList<String> directions) {
        this.directions = directions;
    }

    public String title;
    public ArrayList<String> ingredients;
    public ArrayList<String> directions;

    public ListeItem(){

    }

    public ListeItem(String nom){
        this.title = nom;
    }
}
