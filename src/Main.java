import java.io.*;

class Main {
    private static final WeightedActivitySelection was = new WeightedActivitySelection();

    public static void main(String[] args) throws IOException {
        String inputFilePath = args[0];
        was.loadActivities(inputFilePath);
        int maxWeight = was.getMaxWeight();

        // Create the output file path
        String outputFilePath;
        if (inputFilePath.endsWith(".in")) {
            outputFilePath = inputFilePath.replace(".in", "_17010210.out");
        } else {
            outputFilePath = inputFilePath.substring(0, inputFilePath.lastIndexOf('.')) + "_17010210" + inputFilePath.substring(inputFilePath.lastIndexOf('.'));
        }

        // Write the maximum weight to the output file
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(outputFilePath)))) {
            out.println(maxWeight);
        }
    }
}
