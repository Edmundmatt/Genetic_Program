import org.jgap.gp.GPFitnessFunction;
import org.jgap.gp.IGPProgram;
import org.jgap.gp.terminal.Variable;

public class MyFitnessFunction extends GPFitnessFunction {

    private Double[] input;
    private Double[] output;
    private Variable xVariable;

    private static Object[] NO_ARGS = new Object[0];

    public MyFitnessFunction(Double[] input, Double[] output, Variable x) {
        this.input = input;
        this.output = output;
        this.xVariable = x;
    }

    @Override
    protected double evaluate(final IGPProgram program) {
        double result = 0.0f;

        long longResult = 0;
        for (int i = 0; i < input.length; i++) {
            // Set the input values
            xVariable.set(input[i]);
            // Execute the genetically engineered algorithm
            long value = (long) program.execute_double(0, NO_ARGS);

            // The closer longResult gets to 0 the better the algorithm.
            longResult += Math.abs(value - output[i]);
        }

        result = longResult;
        return result;

    }

}