package model;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CountryList{
    static String folder = "data";
    static String path = "data/data.txt";

    ArrayList<Country> countries;

    public CountryList() {
        countries = new ArrayList<>();
    }

    public ArrayList<Country> getCountries() {
        return countries;
    }

    public void setCountries(ArrayList<Country> countries) {
        this.countries = countries;
    }

    public int searchCountryPos(String name){
        int pos = -1;
        boolean found = false;
        for(int i = 0; i < countries.size() && !found; i++){
            if(countries.get(i).getName().toLowerCase().equals(name.toLowerCase())){
                pos = i;
                found = true;
            }
        }
        return pos;
    }

    public Country getCountry(int pos){
        return countries.get(pos);
    }

    public String getCountriesMedals(){
        for(int i=0 ; i<countries.size()-1 ; i++) {
            for (int j = 1; j < countries.size() - i; j++) {
                if (countries.get(j-1).compareTo(countries.get(j)) > 0) {
                    Country previous = countries.get(j-1);
                    Country current = countries.get(j);
                    countries.set(j-1, current);
                    countries.set(j, previous);
                }
            }
        }
        String msj = "";
        for(Country c : countries){
            msj += c.getName() + "= oro: " + c.getGold() + " plata: " + c.getSilver() + " bronce: " + c.getBronze() + "\n";
        }
        return msj;
    }


    public String getCountriesTotalMedals() {
        CompareMedals compareMedals = new CompareMedals();
        for(int i=0 ; i<countries.size()-1 ; i++) {
            for (int j = 1; j < countries.size() - i; j++) {
                if (compareMedals.compare(countries.get(j-1),countries.get(j)) > 0) {
                    Country previous = countries.get(j-1);
                    Country current = countries.get(j);
                    countries.set(j-1, current);
                    countries.set(j, previous);
                }
            }
        }
        String msj = "";
        for(Country c : countries){
            msj += c.getName() + ": " + c.getTotalMedals() + "\n";
        }
        return msj;
    }

    public String getCountriesAlphabetically() {
        for(int i=0 ; i<countries.size()-1 ; i++) {
            for (int j = 1; j < countries.size() - i; j++) {
                if (countries.get(j - 1).getName().compareTo(countries.get(j).getName()) > 0) {
                    Country previous = countries.get(j-1);
                    Country current = countries.get(j);
                    countries.set(j-1, current);
                    countries.set(j, previous);
                }
            }
        }
        String msj = "";
        for(Country c : countries){
            msj += c.getName() + "\n";
        }
        return msj;
    }

    //---------Persistencia--------

    public void save() throws IOException {
        File file = new File(path);
        FileOutputStream fos = new FileOutputStream(file);
        String data = "";
        for (int i = 0; i < countries.size(); i++) {
            data += countries.get(i).getName() + ":" + countries.get(i).getGold() + ":" + countries.get(i).getSilver() + ":" + countries.get(i).getBronze() + "\n";
        }
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
        writer.write(data);
        writer.flush();
        fos.close();
    }

    public void load() throws IOException {
        File file = new File(path);
        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line = "";
            while ((line = reader.readLine()) != null) {

                System.out.println(line);
                String[] arr = line.split(":");
                System.out.println(Arrays.toString(arr));
                countries.add(
                        new Country(arr[0], Integer.parseInt(arr[1]), Integer.parseInt(arr[2]), Integer.parseInt(arr[3]))
                );
            }
            reader.close();
        }else{
            File f = new File(folder);
            if(!f.exists()){
                f.mkdirs();
            }
            file.createNewFile();
        }
    }
}
