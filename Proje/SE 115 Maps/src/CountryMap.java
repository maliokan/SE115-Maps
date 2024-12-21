import java.nio.file.Paths;
import java.util.Scanner;

public class CountryMap {
    City[] cities;
    int[][] travelTimes;
    int cityCount;
    int startCity;
    int endCity;


    public CountryMap(int numberCities) {
        cities = new City[numberCities];
        travelTimes = new int[numberCities][numberCities];
    }


    private static int findCityIndex(City[] cities, String name) {
        for (int i = 0; i < cities.length; i++) {
            if (cities[i].name.equals(name)) return i;
        } return -1;
    }


    public void addCity(City city, int index) {
        cities[index] = city;
    }


    public void setRoute(int from, int to, int travelTime) {
        travelTimes[from][to] = travelTime;
    }


    public static CountryMap inputReader(String input) {
        Scanner reader = null;
        try {
            reader = new Scanner(Paths.get(input));

            CountryMap countryMAP;
            int numberOfCities;
            try {
                numberOfCities = Integer.parseInt(reader.nextLine());
                countryMAP = new CountryMap(numberOfCities);
                System.out.println("Number of Cities: "+numberOfCities);
            }catch (NumberFormatException ex){
                System.err.println("Input is not an integer value in line 1!");
                throw ex;
            }






            try {
                String cities="";  //just to show in output.
                String[] countryNames = reader.nextLine().split(" ");
                for (int i = 0; i < numberOfCities; i++) {
                    countryMAP.addCity(new City(countryNames[i]), i);
                    cities += countryNames[i]+ " ";
                }
                System.out.println("Cities are --> " + cities);
                countryMAP.cityCount = countryNames.length;
            }catch (IndexOutOfBoundsException ex){
                System.err.println("less city then expected in line 2!");
                throw ex;
            }






            int routeCount;
            try{
                routeCount = Integer.parseInt(reader.nextLine());
                System.out.println("Number of the routes: "+ routeCount);
            }catch (NumberFormatException ex) {
                System.err.println("Input is not an integer value in line 3!");
                throw ex;
            }

            int i=0;
            try {
                while (i<routeCount) {
                    i++;
                    String[] route = new String[3];
                    String[] temporaryRoute = reader.nextLine().split(" ");   //to catch the problem!,
                    System.arraycopy(temporaryRoute, 0, route, 0, temporaryRoute.length);

                    int start_city = findCityIndex(countryMAP.cities, route[0]);     //start_city = 0;
                    int finish_city = findCityIndex(countryMAP.cities, route[1]);    //finish_city = 1;
                    int time_paste = Integer.parseInt(route[2]);                       // time_paste = 30;
                    countryMAP.setRoute(start_city, finish_city, time_paste);
                    System.out.println("Route "+ i +" --> "+ route[0] +" "+route[1]+" "+time_paste);
                }
            }catch (ArrayIndexOutOfBoundsException ex){
                System.err.println("Not only 2 cities or 1 time in one route in line "+(i+3));
                throw ex;
            }catch (NumberFormatException e) {
                System.err.println("Time value is not an integer value in line "+(i+3));
                throw e;
            }

            try {
                String[] startEnd = new String[2];
                String[] temporary = reader.nextLine().split(" ");
                System.arraycopy(temporary, 0, startEnd, 0, temporary.length);
                countryMAP.startCity = findCityIndex(countryMAP.cities, startEnd[0]);
                countryMAP.endCity = findCityIndex(countryMAP.cities, startEnd[1]);
                System.out.println("Starting city: " + startEnd[0] + "\nEnding city: " + startEnd[1]);
                return countryMAP;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.err.println("More/less city thn expected in line: "+(i+1));
                throw e;
            }

        } catch (Exception e) {
            return null;
        } finally {
            if(reader != null) {
                reader.close();
            }
        }
    }
}