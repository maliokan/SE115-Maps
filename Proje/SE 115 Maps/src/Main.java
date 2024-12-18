import java.io.*;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        System.out.print("Write file's name if it's in the project's file, if not write absolute path: ");
        String input = sc.nextLine();

        CountryMap map = CountryMap.inputReader(input);
        if (map != null) {
            System.out.println("Reading process completed successfully..!\n--------------------");
        } else {
            System.err.println("Reading process failed unfortunately..!");
        }

        Routes result = WayFinder.findShortestRoute(map);


        System.out.println("Fastest Way:");
        String final_result ="";
        for (City country : result.route) {
            if (country != null) {
                final_result += country.name + " -> ";
            }
        }
        System.out.println(final_result.substring(0,final_result.length()-4));
        System.out.println("Total Time: " + result.totalTime + " minutes");
    }
}

