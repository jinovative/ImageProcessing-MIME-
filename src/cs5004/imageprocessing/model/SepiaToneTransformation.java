package cs5004.imageprocessing.model;

import java.awt.image.BufferedImage;

/**
 * Class for applying a sepia tone to an image.
 */
public class SepiaToneTransformation implements ColorTransformation {
  @Override
  public BufferedImage applyTransformation(BufferedImage image) {
    int width = image.getWidth();
    int height = image.getHeight();
    BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        int rgb = image.getRGB(x, y);
        Pixel pixel = new Pixel((rgb >> 16) & 0xFF, (rgb >> 8) & 0xFF, rgb & 0xFF);

        int newRed = clamp(0.393 * pixel.getRed() + 0.769 * pixel.getGreen() + 0.189 * pixel.getBlue());
        int newGreen = clamp(0.349 * pixel.getRed() + 0.686 * pixel.getGreen() + 0.168 * pixel.getBlue());
        int newBlue = clamp(0.272 * pixel.getRed() + 0.534 * pixel.getGreen() + 0.131 * pixel.getBlue());

        // Set the new pixel
        Pixel newPixel = new Pixel(newRed, newGreen, newBlue);
        result.setRGB(x, y, ((newPixel.getRed() << 16) | (newPixel.getGreen() << 8) | newPixel.getBlue()));
      }
    }

    return result;
  }

  @Override
  public String getTransformationName() {
    return "SepiaTone";
  }

  private int clamp(double value) {
    return (int) Math.max(0, Math.min(255, value));
  }
}
