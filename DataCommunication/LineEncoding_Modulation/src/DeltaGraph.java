
import java.awt.*;
import javax.swing.*;

public class DeltaGraph extends JPanel {

    private int[] data; // Array to store the signal values

    public DeltaGraph(int[] data) {
        this.data = data;
        setPreferredSize(new Dimension(600, 300)); // Set a larger preferred size for better visibility
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Graph settings
        int height = getHeight();
        int width = getWidth();
        int step = width / data.length; // Width of each data point
        int midY = height / 2;          // Middle Y-axis (starting point)
        int unitHeight = height / 16;   // Smaller vertical scaling factor for smaller steps

        // Draw X and Y axes
        g.setColor(Color.BLACK);
        g.drawLine(0, midY, width, midY); // X-axis for reference
        g.drawLine(0, 0, 0, height);      // Y-axis

        // Labels for levels
        g.drawString("1", 5, midY - unitHeight);
        g.drawString("0", 5, midY + unitHeight);

        // Initial position
        int currentY = midY; // Starting Y position

        // Draw the digital signal based on 0/1 values in array
        for (int i = 0; i < data.length; i++) {
            int x1 = i * step;
            int x2 = (i + 1) * step;

            // Store the previous Y position for the horizontal line
            int prevY = currentY;

            // Step up or down based on the value
            if (data[i] == 1) {
                currentY -= unitHeight; // Step up
            } else {
                currentY += unitHeight; // Step down
            }

            g.setColor(Color.BLUE);
            ((Graphics2D) g).setStroke(new BasicStroke(2)); // Increase line thickness

            // Draw horizontal line to the start of the next point's vertical line
            g.drawLine(x1, prevY, x2, prevY);

            // Draw vertical stroke at the beginning of the next step, if not the last point
            if (i < data.length - 1) {
                g.drawLine(x2, prevY, x2, currentY);
            }

            // Mark points with small circles for clarity
            g.fillOval(x1 - 3, prevY - 3, 6, 6);
        }

        // Draw last point for clarity
        int lastX = (data.length - 1) * step;
        g.fillOval(lastX - 3, currentY - 3, 6, 6);
    }

    public static void main(String[] args, int[] signalData) {
        // Example data array with only 0 and 1 values
        // int[] signalData = {1, 1, 1, 1, 1, 1, 1, 1, 1};

        // Set up the JFrame and add the DeltaGraph JPanel
        JFrame frame = new JFrame("DeltaGraph/Delta Modulation Graph");
        DeltaGraph graph = new DeltaGraph(signalData);
        frame.add(graph);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
