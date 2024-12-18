import java.nio.file.Paths;
import java.util.Scanner;

public class CountryMap {
    City[] countries;
    int[][] travelTimes;
    int cityCount;
    int startCity;
    int endCity;



    public static CountryMap inputReader(String input) {
        Scanner reader = null;
        try {
            reader = new Scanner(Paths.get(input));

            CountryMap wayMap;
            try {
                int numberofCities = Integer.parseInt(reader.nextLine());
                wayMap = new CountryMap(numberofCities);
                System.out.println("Number of Cities: "+numberofCities);
            }catch (NumberFormatException ex){
                System.err.println("Input is not an integer value in line 1!");
                throw ex;
            }






            try {
                String cities="";  //just to show in output.
                String[] countryNames = reader.nextLine().split(" ");
                for (int i = 0; i < countryNames.length; i++) {
                    wayMap.addCountry(new City(countryNames[i]), i);
                    cities += countryNames[i]+ " ";
                }
                System.out.println("Cities are --> " + cities);
                wayMap.cityCount = countryNames.length;
            }catch (ArrayIndexOutOfBoundsException ex){
                System.err.println("more/less city then expected in line 2!");
                throw ex;
            }catch (NullPointerException e){
                System.err.println("Some cities are missing in line 2!");
                throw e;
            }






            int routeCount;
            try{
                routeCount = Integer.parseInt(reader.nextLine());
                System.out.println("Number of the routes: "+ routeCount);
            }catch (NumberFormatException ex) {
                System.err.println("Input is not an integer value in line 3!");
                throw ex;
            }









            for (int i = 0; i < routeCount; i++) {
                String[] route = reader.nextLine().split(" ");               // "route" will contain values of current line as in route = {A, B, 30};
                int start_city = findCountryIndex(wayMap.countries, route[0]);     //start_city = 0;
                int finish_city = findCountryIndex(wayMap.countries, route[1]);    //finish_city = 1;
                int time_paste = Integer.parseInt(route[2]);                       // time_paste = 30;
                wayMap.setRoute(start_city, finish_city, time_paste);
                System.out.println("Route "+ (i+1) +" --> "+ route[0] +" "+route[1]+" "+time_paste);
            }






            String[] startEnd = reader.nextLine().split(" ");
            wayMap.startCity = findCountryIndex(wayMap.countries, startEnd[0]);
            wayMap.endCity = findCountryIndex(wayMap.countries, startEnd[1]);
            System.out.println("Starting city: "+startEnd[0]+"\nEnding city: "+startEnd[1]);

            return wayMap;
        } catch (Exception e) {
            System.err.println("Input processing error: " + e.getMessage());
            return null;
        } finally {
            if(reader != null) {
                reader.close();
            }
        }
    }



    private static int findCountryIndex(City[] countries, String name) {
        for (int i = 0; i < countries.length; i++) {
            if (countries[i] != null && countries[i].name.equals(name)) return i;
        } return -1;
    }


    public CountryMap(int maxCountries) {
        countries = new City[maxCountries];
        travelTimes = new int[maxCountries][maxCountries];
    }

    public void addCountry(City country, int index) {
        countries[index] = country;
    }

    public void setRoute(int from, int to, int travelTime) {
        travelTimes[from][to] = travelTime;
        travelTimes[to][from] = travelTime; // İki yönlü rota
    }
}