package model;

public class Country implements Comparable<Country>{
    private String name;
    private int gold = 0;
    private int silver = 0;
    private int bronze = 0;

    public Country(String name, int gold, int silver, int bronze) {
        this.name = name;
        this.gold = gold;
        this.silver = silver;
        this.bronze = bronze;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getGold() {
        return gold;
    }
    public void setGold(int gold) {
        this.gold = gold;
    }
    public int getSilver() {
        return silver;
    }
    public void setSilver(int silver) {
        this.silver = silver;
    }
    public int getBronze() {
        return bronze;
    }
    public int getTotalMedals() {
        return gold + silver + bronze;
    }

    public void setBronze(int bronze) {
        this.bronze = bronze;
    }

    public void addGold(int ammount){
        this.gold += ammount;
    }

    public void addSilver(int ammount){
        this.silver += ammount;
    }

    public void addBronze(int ammount){
        this.bronze += ammount;
    }

    @Override
    public int compareTo(Country country){
        if(this.gold > country.getGold()){
            return -1;
        }
        if(this.gold < country.getGold()){
            return 1;
        }
        if(this.silver > country.getSilver()){
            return -1;
        }
        if(this.silver < country.getSilver()){
            return 1;
        }
        if(this.bronze > country.getBronze()){
            return -1;
        }
        if(this.bronze < country.getBronze()){
            return 1;
        }
        return 0;
    }
}
