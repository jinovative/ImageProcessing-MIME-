package cs5004.imageprocessing.model;

import cs5004.imageprocessing.model.Pixel;
import java.awt.image.BufferedImage;

/**
 * Class for blurring an image.
 */
public class BlurFilter implements Filter {

  @Override
  public BufferedImage applyFilter(BufferedImage image) {
    int width = image.getWidth();
    int height = image.getHeight();
    BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        double newRed = 0.0;
        double newGreen = 0.0;
        double newBlue = 0.0;

        // Apply the blur filter kernel to the surrounding pixels
        for (int i = -1; i <= 1; i++) {
          for (int j = -1; j <= 1; j++) {
            int xi = Math.min(Math.max(x + i, 0), width - 1);
            int yj = Math.min(Math.max(y + j, 0), height - 1);

            int rgb = image.getRGB(xi, yj);
            Pixel pixel = new Pixel((rgb >> 16) & 0xFF, (rgb >> 8) & 0xFF, rgb & 0xFF);

            double kernelValue = FilterKernel.BLUR.getKernel()[i + 1][j + 1];
            newRed += pixel.getRed() * kernelValue;
            newGreen += pixel.getGreen() * kernelValue;
            newBlue += pixel.getBlue() * kernelValue;
          }
        }

        // Clamp the color values and set the new pixel
        int newRgb = ((clamp(newRed) << 16) | (clamp(newGreen) << 8) | clamp(newBlue));
        result.setRGB(x, y, newRgb);
      }
    }

    return result;
  }

  @Override
  public String getFilterName() {
    return "Blur";
  }

  private int clamp(double value) {
    return (int) Math.max(0, Math.min(255, value));
  }
}