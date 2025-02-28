
import java.awt.*;
import javax.swing.*;

public class DigitalGraph extends JPanel {

    private int[] data; // Array to store the signal values

    public DigitalGraph(int[] data) {
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
        int midY = height / 2;          // Middle Y-axis (for 0 value)
        int unitHeight = height / 6;    // Vertical scaling factor

        // Draw X and Y axes
        g.setColor(Color.BLACK);
        g.drawLine(0, midY, width, midY); // X-axis for value 0
        g.drawLine(0, 0, 0, height);      // Y-axis

        // Labels for levels -1, 0, 1
        g.drawString("1", 5, midY - unitHeight);
        g.drawString("0", 5, midY);
        g.drawString("-1", 5, midY + unitHeight);

        // Draw the digital signal based on array values
        for (int i = 0; i < data.length - 1; i++) {
            int x1 = i * step;
            int x2 = (i + 1) * step;
            int y1 = midY - data[i] * unitHeight;   // Scale -1, 0, 1 values for better visibility
            int y2 = midY - data[i + 1] * unitHeight;

            g.setColor(Color.BLUE);
            ((Graphics2D) g).setStroke(new BasicStroke(2)); // Increase line thickness

            // Draw horizontal line segment
            g.drawLine(x1, y1, x2, y1);

            // Draw vertical line at the transition point
            if (data[i] != data[i + 1]) {
                g.drawLine(x2, y1, x2, y2);
            }

            // Mark points with small circles for clarity
            g.fillOval(x1 - 3, y1 - 3, 6, 6);
        }

        // Draw last point for clarity
        int lastX = (data.length - 1) * step;
        int lastY = midY - data[data.length - 1] * unitHeight;
        g.fillOval(lastX - 3, lastY - 3, 6, 6);
    }

    public static void main(String[] args, int[] signalData) {
        
        JFrame frame = new JFrame("Digital Signal Graph");
        DigitalGraph graph = new DigitalGraph(signalData);
        frame.add(graph);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
