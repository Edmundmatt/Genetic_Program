import org.jgap.gp.GPProblem;
import org.jgap.gp.impl.GPGenotype;

import java.io.File;

public class JGAPTest {
    private static final int VALUES_LENGTH = 20;

    private static Double[] valuesX;
    private static Double[] valuesY;

    public static void main(String args[]) throws Exception {
        //Data read in
        Double[][] values =
                FileManager.read(new File("/home/edmundmatt/IdeaProjects/comp307assignment1part2v2/regression.txt"));
        valuesX = values[0];
        valuesY = values[1];
        System.out.println("X Values: ");
        for(int i = 0; i < VALUES_LENGTH; i++){
            System.out.print(valuesX[i] + "  ");
        }
        System.out.println("\nY Values: ");
        for(int i = 0; i < VALUES_LENGTH; i++){
            System.out.print(valuesY[i] + "  ");
        }

        GPProblem problem = new GPProblem1();
        GPGenotype gp = problem.create();
        gp.setVerboseOutput(true);
        gp.evolve(100);
        System.out.println("Yay, I can run!");
        gp.outputSolution(gp.getAllTimeBest());
    }
}
