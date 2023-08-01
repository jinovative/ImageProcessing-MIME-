package cs5004.imageprocessing.view;

import java.awt.*;
import javax.swing.*;

public class HistogramPanel extends JPanel {
  private int[] histogramData;

  public HistogramPanel() {
    super();
    // Initialize histogram data array
    histogramData = new int[256];
  }

  // Method to update histogram data
  public void updateHistogramData(int[] newHistogramData) {
    if (newHistogramData.length != 256) {
      throw new IllegalArgumentException("Histogram data must have 256 bins.");
    }
    this.histogramData = newHistogramData;
    this.repaint();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (histogramData != null) {
      // Draw the histogram
      for (int i = 0; i < 256; i++) {
        g.drawLine(i, this.getHeight(), i, this.getHeight() - histogramData[i]);
      }
    }
  }
}
