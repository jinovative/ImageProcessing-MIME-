package cs5004.imageprocessing.view;

import java.awt.*;
import javax.swing.*;

/**
 * `HistogramPanel` class represents a JPanel that is used to draw histograms of
 * red, green, blue, and intensity values of an image.
 */
public class HistogramPanel extends JPanel {
  private int[] redHistogramData;
  private int[] greenHistogramData;
  private int[] blueHistogramData;
  private int[] intensityHistogramData;

  /**
   * Constructor for `HistogramPanel` initializes the histogram data arrays.
   */
  public HistogramPanel() {
    super();
    // Initialize histogram data arrays
    redHistogramData = new int[256];
    greenHistogramData = new int[256];
    blueHistogramData = new int[256];
    intensityHistogramData = new int[256];
  }

  /**
   * Updates the histogram data with the provided data arrays and repaints the histograms.
   *
   * @param redData        Array of red color values
   * @param greenData      Array of green color values
   * @param blueData       Array of blue color values
   * @param intensityData  Array of intensity values
   */
  public void updateHistogramData(int[] redData, int[] greenData, int[] blueData, int[] intensityData) {
    this.redHistogramData = redData;
    this.greenHistogramData = greenData;
    this.blueHistogramData = blueData;
    this.intensityHistogramData = intensityData;
    this.repaint();
  }

  /**
   * Overrides the `paintComponent` method from the `JPanel` class to draw the histograms.
   *
   * @param g  The Graphics object to protect
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (redHistogramData != null
            && greenHistogramData != null
            && blueHistogramData != null
            && intensityHistogramData != null) {
      // Draw the histograms
      drawHistogram(g, redHistogramData, Color.RED);
      drawHistogram(g, greenHistogramData, Color.GREEN);
      drawHistogram(g, blueHistogramData, Color.BLUE);
      drawHistogram(g, intensityHistogramData, Color.PINK);
    }
  }

  /**
   * Helper method to draw a histogram with a given color.
   *
   * @param g              The Graphics object to protect
   * @param histogramData  The data array for the histogram
   * @param color          The color of the histogram
   */
  private void drawHistogram(Graphics g, int[] histogramData, Color color) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setStroke(new BasicStroke(2));  // Set line thickness to 2
    g2.setColor(color);
    for (int i = 0; i < 256; i++) {
      g2.drawLine(i, this.getHeight(), i, this.getHeight() - histogramData[i]);
    }
  }

}
