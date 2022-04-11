import java.io.File;

public class DriverMST {

    public static void main(String[] args) {

        // Initialize verbose flag as false, only turned on if args[1] is "-e"
        boolean verbose = false;
        if (args.length > 1) verbose = args[1].equals("-e");

        // Pass file into each algorithm with verbose flag
        File infile = new File(args[0]);
        new KruskalAlgorithm(infile, verbose);
        new PrinnAlgorithm(infile, verbose);

    }
}
