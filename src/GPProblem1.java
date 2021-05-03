

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.GPProblem;
import org.jgap.gp.function.*;
import org.jgap.gp.impl.DeltaGPFitnessEvaluator;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.GPGenotype;
import org.jgap.gp.terminal.Terminal;
import org.jgap.gp.terminal.Variable;


public class GPProblem1 extends GPProblem {
    private Variable xVariable;
    private Double[] INPUT;
    private Double[] OUTPUT;

    public GPProblem1(Double[] INPUT, Double[] OUTPUT) throws InvalidConfigurationException {
        super(new GPConfiguration());

        GPConfiguration config = getGPConfiguration();

        this.INPUT = INPUT;
        this.OUTPUT = OUTPUT;
        this.xVariable = Variable.create(config, "X", CommandGene.DoubleClass);

        config.setGPFitnessEvaluator(new DeltaGPFitnessEvaluator());
        config.setMaxInitDepth(4);
        config.setPopulationSize(2000);
        config.setMaxCrossoverDepth(10);
        config.setFitnessFunction(new MyFitnessFunction(INPUT, OUTPUT, xVariable));
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
                        xVariable,
                        new Add(config, CommandGene.DoubleClass),
                        new Subtract(config, CommandGene.DoubleClass),
                        new Multiply(config, CommandGene.DoubleClass),
                        new Divide(config, CommandGene.DoubleClass),
                        new Pow(config, CommandGene.DoubleClass),
                        new Terminal(config, CommandGene.DoubleClass, 0.0, 10.0, true)
                }
        };

        GPGenotype result = GPGenotype.randomInitialGenotype(config, types, argTypes,
                nodeSets, 20, true);

        return result;
    }
}
