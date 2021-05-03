import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class FileManager {
    public static Double[][] read(File file) throws Exception {
        try{
            Double[][] values = new Double[2][20];
            Double[] valuesX = new Double[20];
            Double[] valuesY = new Double[20];
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            br.readLine(); // Skip the first two lines
            br.readLine();
            int index = -1;
            while((line = br.readLine()) != null){
                index++;
                Scanner sc = new Scanner(line); //Feed each line into scanner
                valuesX[index] = Double.parseDouble(sc.next());
                valuesY[index] = Double.parseDouble(sc.next());
                sc.close();
            }
            br.close();
            values[0] = valuesX;
            values[1] = valuesY;
            return values;
        }catch(Exception e){
            System.err.println("Error: Problem reading dataset");
            e.printStackTrace();
            return null;
        }
    }
}
