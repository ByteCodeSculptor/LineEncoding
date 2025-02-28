
import java.awt.*;
import javax.swing.*;
import java.util.Arrays;

public class PCMgraph extends JPanel {

    private int[] data; // Array to store the signal values

    public PCMgraph(int[] data) {
        this.data = data;
        setPreferredSize(new Dimension(800, 400)); // Increased width for better spacing
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Graph settings
        int height = getHeight();
        int width = getWidth();
        int step = Math.max(1, width / data.length); // Width of each data point, with a minimum size of 1
        int maxLevels = 16;             // Number of levels (0 to 15)
        int levelHeight = height / maxLevels; // Adjusted height of each level to fit all 16 levels

        // Draw X and Y axes
        g.setColor(Color.BLACK);
        g.drawLine(0, height - levelHeight, width, height - levelHeight); // X-axis at bottom level 0
        g.drawLine(0, 0, 0, height);          // Y-axis at left

        // Labels for levels
        for (int i = 0; i < maxLevels; i++) {
            int y = height - (i * levelHeight);
            g.drawString(String.valueOf(i), 5, y - 2);
            g.drawLine(0, y, width, y); // Optional: Draw horizontal grid lines for levels
        }

        // Initial position
        int currentY = height - (data[0] * levelHeight); // Start at the level of the first data point

        // Draw the digital signal based on binary levels (0-15)
        for (int i = 0; i < data.length; i++) {
            int x1 = i * step;
            int x2 = (i + 1) * step;
            int newY = height - (data[i] * levelHeight);

            g.setColor(Color.BLUE);
            ((Graphics2D) g).setStroke(new BasicStroke(2)); // Increase line thickness

            // Draw horizontal line at current level
            g.drawLine(x1, currentY, x2, currentY);

            // Draw vertical stroke for level change if there's a transition
            if (i < data.length - 1 && data[i] != data[i + 1]) {
                g.drawLine(x2, currentY, x2, newY);
            }

            // Move to the next level
            currentY = newY;
        }
    }

    // Utility function to convert a single binary string with space-separated values to integer levels
    public static int[] parseBinaryString(String binaryString) {
        return Arrays.stream(binaryString.trim().split(" "))
                .mapToInt(bin -> Integer.parseInt(bin, 2))
                .toArray();
    }

    public void main(String[] args, String binaryString) {
        // Example input string in binary (0000 to 1111)
        // String binaryString = "0000 1111 0010 0011 0100 0101 0110 0111 1000 1001 1010 1011 1100 1101 1110 1111";

        // Convert binary string to integer levels
        int[] signalData = parseBinaryString(binaryString);

        // Set up the JFrame and add the PCMGraph JPanel
        JFrame frame = new JFrame("16-Level Digital Signal Graph");
        PCMgraph graph = new PCMgraph(signalData);
        frame.add(graph);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
