package scor05;
/**
 * Clase Pokemon que tiene la info parseada del .csv
 * Nombre es satírico :)
 */

import java.util.List;

public class Digimon implements Comparable<Digimon> {
    public String name;
    public int number; // Numero del pokedex
    public String type1;
    public String type2;
    public String classification;
    public float height; 
    public float weight;
    public List<String> abilities;
    public byte generation;
    public boolean legendary;

    public Digimon(String name, int number, String type1, String type2, String classification, float height, float weight, List<String> abilities, byte generation, boolean legendary) {
        this.name = name;
        this.number = number;
        this.type1 = type1;
        this.type2 = type2;
        this.classification = classification;
        this.height = height;
        this.weight = weight;
        this.abilities = abilities;
        this.generation = generation;
        this.legendary = legendary;
    }

    public int compareTo(Digimon d){
        return (this.type1.compareTo(d.type1) < 0) ? -1 : (this.type1.compareTo(d.type1) > 0) ? 1 : 0;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getType1() {
        return this.type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return this.type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public String getClassification() {
        return this.classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public float getHeight() {
        return this.height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return this.weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public List<String> getAbilities() {
        return this.abilities;
    }

    public void setAbilities(List<String> abilities) {
        this.abilities = abilities;
    }

    public byte getGeneration() {
        return this.generation;
    }

    public void setGeneration(byte generation) {
        this.generation = generation;
    }

    public boolean isLegendary() {
        return this.legendary;
    }

    public boolean getLegendary() {
        return this.legendary;
    }

    public void setLegendary(boolean legendary) {
        this.legendary = legendary;
    }

}