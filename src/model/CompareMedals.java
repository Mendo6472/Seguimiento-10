package model;

import java.util.Comparator;

public class CompareMedals implements Comparator<Country>{

    @Override
    public int compare(Country country1, Country country2){
        return country2.getTotalMedals() - country1.getTotalMedals();
    }

}
