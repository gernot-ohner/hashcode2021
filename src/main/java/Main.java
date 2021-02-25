import solution.ISolution;
import solution.Solution;
import solution.SolutionV1;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {

        String[] filenames = new String[]{
                "a.txt",
                "b.txt",
                "c.txt",
                "d.txt",
                "e.txt",
                "f.txt"
        };

        for (String filename : filenames) {
            compute(filename);
        }
//        compute(filenames[3]);
    }

    private static void compute(String filename) throws IOException, URISyntaxException {
        final var inputObject = new InputReader().parse(filename);

        final ISolution solution;
        solution = new SolutionV1();
        //solution = new Solution();

        final var outputObject = solution.compute(inputObject);

        new OutputWriter().writeOutput(filename, outputObject);
    }
}
