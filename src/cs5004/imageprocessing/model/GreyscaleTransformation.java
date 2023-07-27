package cs5004.imageprocessing.model;

import java.awt.image.BufferedImage;

/**
 * Class for converting an image to greyscale.
 */
public class GreyscaleTransformation implements ColorTransformation {

  @Override
  public BufferedImage applyTransformation(BufferedImage image) {
    int width = image.getWidth();
    int height = image.getHeight();
    BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        int rgb = image.getRGB(x, y);
        Pixel pixel = new Pixel((rgb >> 16) & 0xFF, (rgb >> 8) & 0xFF, rgb & 0xFF);

        double newColor = 0.2126 * pixel.getRed() + 0.7152 * pixel.getGreen() + 0.0722 * pixel.getBlue();

        // Clamp the color values and set the new pixel
        int newRgb = ((clamp(newColor) << 16) | (clamp(newColor) << 8) | clamp(newColor));
        result.setRGB(x, y, newRgb);
      }
    }

    return result;
  }

  /**
   * Clamps a color value to be within the valid range [0, 255].
   *
   * @param value the color value to be clamped
   * @return the clamped color value
   */
  private int clamp(double value) {
    return (int) Math.max(0, Math.min(255, value));
  }
}