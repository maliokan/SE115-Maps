import java.util.Scanner;
import java.io.*;

public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String filePath = sc.nextLine();
        City[] cities;
        CountryMap[] maps;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;


            line = reader.readLine();                       //first line (number of cities)!!!
            int numberOfCities = Integer.parseInt(line);
            cities = new City[numberOfCities];


            line = reader.readLine();                      //This will read line 2 (cities' names)!!!
            String[] cityNames = line.split(" ");


            for (int i = 0; i < cityNames.length; i++) {
                cities[i] = new City(cityNames[i]);
            }



            System.out.println("Cities:");
            for (City city : cities) {
                System.out.println(city.name);
            }

            line = reader.readLine();                     //This will read the third line (number of maps)!!!
            int numberOfMaps = Integer.parseInt(line);
            maps = new CountryMap[numberOfMaps];


            for (int i=0;i<maps.length;i++) {
                line = reader.readLine();
                String[] mapData = line.split(" ");
                maps[i] = new CountryMap();
                maps[i].startCity = new City(mapData[0]);
                maps[i].finishCity = new City(mapData[1]);
                maps[i].timePast = Integer.parseInt(mapData[2]);
            }

            line = reader.readLine();
            for (City city : cities) {
                if (city.name.equals(line.split(" ")[0])) {
                    city.firstCity = true;
                }
                if (city.name.equals(line.split(" ")[1])) {
                    city.lastCity = true;
                }
            }


            for (City city : cities) {
                if (city.firstCity) {
                    System.out.println("First City: " + city.name);
                }
                if (city.lastCity) {
                    System.out.println("Last City: " + city.name);
                }
            }






        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }

    }
}

