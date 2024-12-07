import java.util.Scanner;
import java.io.*;
public class Main{
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        String path = sc.nextLine();
        FileInputStream fis;
        try {
            fis = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            throw new RuntimeException(e);
        }

        int control;
        String inp_text = "";  //This will contain everything in the file as a string.
        while ((control = fis.read()) != -1) {
            inp_text += (char)control;
        }
        System.out.println(inp_text);



    }
}