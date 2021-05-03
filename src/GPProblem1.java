import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.GPProblem;
import org.jgap.gp.function.Add;
import org.jgap.gp.function.Multiply;
import org.jgap.gp.function.Pow;
import org.jgap.gp.impl.DeltaGPFitnessEvaluator;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.GPGenotype;
import org.jgap.gp.terminal.Terminal;
import org.jgap.gp.terminal.Variable;

/**
 * @author carlos
 *
 */
public class GPProblem1 extends GPProblem {
    private Variable _xVariable;

    public GPProblem1() throws InvalidConfigurationException {
        super(new GPConfiguration());

        GPConfiguration config = getGPConfiguration();

        _xVariable = Variable.create(config, "X", CommandGene.DoubleClass);

        config.setGPFitnessEvaluator(new DeltaGPFitnessEvaluator());
        config.setMaxInitDepth(4);
        config.setPopulationSize(1000);
        config.setMaxCrossoverDepth(8);
        config.setFitnessFunction(new MyFitnessFunction(INPUT_1, OUTPUT, _xVariable));
        config.setStrictProgramCreation(true);
    }

    @Override
    public GPGenotype create() throws InvalidConfigurationException {
        GPConfiguration config = getGPConfiguration();

        // The return type of the GP program.
        Class[] types = { CommandGene.DoubleClass };

        // Arguments of result-producing chromosome: none
        Class[][] argTypes = { {} };

        // Next, we define the set of available GP commands and terminals to
        // use.
        CommandGene[][] nodeSets = {
                {
                        _xVariable,
                        new Add(config, CommandGene.DoubleClass),
                        new Multiply(config, CommandGene.DoubleClass),
                        new Terminal(config, CommandGene.DoubleClass, 0.0, 10.0, true)
                }
        };

        GPGenotype result = GPGenotype.randomInitialGenotype(config, types, argTypes,
                nodeSets, 20, true);

        return result;
    }

    public static void main(String[] args) throws Exception {
        GPProblem problem = new GPProblem1();

        GPGenotype gp = problem.create();
        gp.setVerboseOutput(true);
        gp.evolve(30);

        System.out.println("Formula to discover: -");
        gp.outputSolution(gp.getAllTimeBest());
    }

}
