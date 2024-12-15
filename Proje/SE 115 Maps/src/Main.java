import java.util.Scanner;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        String filePath = sc.nextLine();

        String input = """
                3
                X Y Z
                3
                X Y 6
                X Z 10   //Temporary example!!
                Y Z 2
                X Z""";
        CountryMap map = CountryMap.fromInput(input);
        RouteResult result = WayFinder.findShortestRoute(map);

        System.out.println("Shortest Way:");
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

