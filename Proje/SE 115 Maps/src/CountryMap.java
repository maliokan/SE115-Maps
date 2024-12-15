import java.io.*;
public class CountryMap {
    City[] countries;
    int[][] travelTimes;
    int countryCount;



    public static CountryMap fromInput(String input) {
        try {
            BufferedReader reader = new BufferedReader(new StringReader(input));  // Creates a reader obj that will read the "input".

            CountryMap wayMap;   //pre-defined because of the try-catch block!
            try {
                int maxCountries = Integer.parseInt(reader.readLine());
                wayMap = new CountryMap(maxCountries);
            }catch (NumberFormatException ex){
                System.err.println("Line 1, Input is not an integer value!");  // If the number of the countries is given
                throw ex;                                                               // as a character not number then the error will occur!
            }






            try {
                String[] countryNames = reader.readLine().split(" ");
                for (int i = 0; i < countryNames.length; i++) {
                    wayMap.addCountry(new City(countryNames[i]), i);
                }
                wayMap.countryCount = countryNames.length;
            }catch (ArrayIndexOutOfBoundsException ex){
                System.err.println("Line 2, more/less city then expected!");
                throw ex;
            }catch (NullPointerException e){
                System.err.println("Some cities are missing!");
                throw e;
            }






            int routeCount;
            try{
                routeCount = Integer.parseInt(reader.readLine());           // 3.line will give number of routes!
            }catch (NumberFormatException ex) {
                System.err.println("Line 3, Input is not an integer value!");
                throw ex;
            }









            for (int i = 0; i < routeCount; i++) {
                String[] route = reader.readLine().split(" ");               // "route" will contain values of current line as in route = {A, B, 30};
                int start_city = findCountryIndex(wayMap.countries, route[0]);     //start_city = 0;
                int finish_city = findCountryIndex(wayMap.countries, route[1]);    //finish_city = 1;
                int time_paste = Integer.parseInt(route[2]);                       // time_paste = 30;
                wayMap.setRoute(start_city, finish_city, time_paste);
            }






            // Son satır: Başlangıç ve bitiş ülkeleri
            String[] startEnd = reader.readLine().split(" ");
            wayMap.startCountry = findCountryIndex(wayMap.countries, startEnd[0].trim());
            wayMap.endCountry = findCountryIndex(wayMap.countries, startEnd[1].trim());

            return wayMap;
        } catch (Exception e) {
            System.out.println("Input processing error: " + e.getMessage());
            return null;
        }
    }



    private static int findCountryIndex(City[] countries, String name) {
        for (int i = 0; i < countries.length; i++) {
            if (countries[i] != null && countries[i].name.equals(name)) {
                return i;
            }
        }
        return -1;
    }

    int startCountry;
    int endCountry;

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