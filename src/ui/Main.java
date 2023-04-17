import java.io.IOException;
import java.util.Scanner;
import model.*;

public class Main {

    static CountryList countryList = new CountryList();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        countryList.load();
        while(true){
            showMenu();
        }

    }

    public static void showMenu() throws IOException{
        System.out.println("1.Añadir pais\n2.Mostrar medalleria\n3.Mostrar total de medallas\n4.Mostrar paises\n5.Salir\n");
        int option = Integer.parseInt(scanner.nextLine());
        handleOption(option);
    }

    public static void handleOption(int option) throws IOException{
        switch (option){
            case 1 -> addCountry();
            case 2 -> showMedals();
            case 3 -> showTotalMedals();
            case 4 -> showCountries();
            case 5 -> System.exit(0);
        }
    }

    public static void addCountry() throws IOException{
        System.out.println("Escriba la entrada con el formato Pais::medalla::numeroMedalla (ej: Colombia::oro::3)");
        String input = scanner.nextLine();
        String[] data = input.split("\\:\\:");
        int countryPos = countryList.searchCountryPos(data[0]);
        switch (data[1]) {
            case "oro":
                if(countryPos == -1){
                    countryList.getCountries().add(
                        new Country(data[0], Integer.parseInt(data[2]), 0, 0)
                    );
                } else {
                    countryList.getCountry(countryPos).addGold(Integer.parseInt(data[2]));
                }
                break;

            case "plata":
                if(countryPos == -1){
                    countryList.getCountries().add(
                        new Country(data[0], 0, Integer.parseInt(data[2]), 0)
                    );
                } else {
                    countryList.getCountry(countryPos).addSilver(Integer.parseInt(data[2]));
                }
                break;

            case "bronce":
                if(countryPos == -1){
                    countryList.getCountries().add(
                        new Country(data[0], 0, 0, Integer.parseInt(data[2]))
                    );
                } else {
                    countryList.getCountry(countryPos).addBronze(Integer.parseInt(data[2]));
                }
                break;
                
            default:
                System.out.println("Medalla incorrecta.");
                break;
        }
        countryList.save();
    }

    public static void showMedals(){
        System.out.println(countryList.getCountriesMedals());
    }

    public static void showTotalMedals(){
        System.out.println(countryList.getCountriesTotalMedals());
    }

    public static void showCountries(){
        System.out.println(countryList.getCountriesAlphabetically());
    }
}