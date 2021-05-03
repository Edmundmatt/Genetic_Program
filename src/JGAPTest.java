import org.jgap.gp.GPProblem;
import org.jgap.gp.impl.GPGenotype;

import java.io.File;

public class JGAPTest {
    private static final int VALUES_LENGTH = 20;

    private static Double[] INPUT;
    private static Double[] OUTPUT;

    public static void main(String args[]) throws Exception {
        //Data read in
        Double[][] values =
                FileManager.read(new File("C:\\Users\\Matthew\\IdeaProjects\\comp307assignment2part2v3\\regression.txt"));
        INPUT = values[0];
        OUTPUT = values[1];
        System.out.println("Input Values: ");
        for(int i = 0; i < VALUES_LENGTH; i++){
            System.out.print(INPUT[i] + "  ");
        }
        System.out.println("\nOutput Values: ");
        for(int i = 0; i < VALUES_LENGTH; i++){
            System.out.print(OUTPUT[i] + "  ");
        }

        GPProblem problem = new GPProblem1(INPUT, OUTPUT);
        GPGenotype gp = problem.create();
        gp.setVerboseOutput(true);
        gp.evolve(30);

        System.out.println("Yay, I can run!");
        gp.outputSolution(gp.getAllTimeBest());
    }
}
