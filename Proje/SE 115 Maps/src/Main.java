import java.io.*;
import java.io.FileWriter;

public class Main{
    public static void main(String[] args) throws IOException{
        String input = args[0];

        CountryMap map = CountryMap.inputReader(input);
        if (map != null) {
            System.out.println("--------------------\nReading process completed successfully..!\n--------------------");
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
        String final_result_substring = final_result.substring(0, final_result.length() - 4);
        System.out.println(final_result_substring);
        System.out.println("Total Time: " + result.totalTime + " minutes");



        FileWriter fw = null;
        try{
            fw = new FileWriter("output.txt",false);
            fw.write("Fastest Way: \n"+final_result_substring+"\n"+"Total Time: "+result.totalTime+" minutes");
            System.out.println("--------------------\noutput.txt file has been created.");
            System.out.println("File writing process completed successfully..!\n--------------------");
        }catch(Exception e){
            System.err.println("Something went wrong when writing the result to a file.");
        }finally {
            if (fw != null){
                fw.close();
            }
        }
    }

}

