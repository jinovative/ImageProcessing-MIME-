package cs5004.imageprocessing.model;

/**
 * `Pixel` class represents a pixel in an image.
 * It stores the RGB color values for a pixel.
 */
public class Pixel {
  private int r;
  private int g;
  private int b;

  /**
   * Constructs a `Pixel` object with the specified RGB color values.
   *
   * @param r the red color value.
   * @param g the green color value.
   * @param b the blue color value.
   * @throws IllegalArgumentException RGB color values are less than 0 or greater than 255.
   */
  public Pixel(int r, int g, int b) {
    if (!isValidColorValue(r) || !isValidColorValue(g) || !isValidColorValue(b)) {
      throw new IllegalArgumentException("Invalid RGB color values: " + r + ", " + g + ", " + b);
    }
    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * Returns the red color value of this pixel.
   *
   * @return the red color value.
   */
  public int getRed() {
    return r;
  }

  /**
   * Returns the green color value of this pixel.
   *
   * @return the green color value.
   */
  public int getGreen() {
    return g;
  }

  /**
   * Returns the blue color value of this pixel.
   *
   * @return the blue color value.
   */
  public int getBlue() {
    return b;
  }

  public int getRGB() {
    // Assuming that the color values are in the range 0-255
    int red = this.getRed();
    int green = this.getGreen();
    int blue = this.getBlue();

    // Pack the color values into an integer
    int rgb = (red << 16) | (green << 8) | blue;

    return rgb;
  }


  /**
   * Checks if the given color value is valid (within the range 0-255).
   *
   * @param value the color value to check.
   * @return true if the color value is valid, false otherwise.
   */
  private boolean isValidColorValue(int value) {
    return value >= 0 && value <= 255;
  }
}
