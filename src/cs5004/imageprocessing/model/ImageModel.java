package cs5004.imageprocessing.model;


import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The ImageModel interface represents the model component in the Model-View-Controller.
 * This interface contains methods for manipulating images.
 */
public interface ImageModel {

  /**
   * Load an image from a file.
   *
   * @param filename the path and name of the file to read.
   * @throws FileNotFoundException if the file is not found.
   */

  void loadImage(String filename) throws IOException;

  /**
   * Save the current image to a file.
   *
   * @param filename the path and name of the file to write.
   */
  void saveImage(String filename) throws IOException;

  /**
   * Apply a color transformation to the current image.
   *
   * @param transformationName the transformation to apply.
   */
  void applyTransformation(String transformationName);

  /**
   * Apply a filter to the current image.
   *
   * @param filterName the filter to apply.
   */
  void applyFilter(String filterName);

  /**
   * Load an image from an ASCII PPM file.
   *
   * @param filename the path and name of the file to read.
   */
  void readPPM(String filename) throws FileNotFoundException;

  /**
   * Extract the red color channel from the image.
   */
  void extractRedChannel();

  /**
   * Extract the green color channel from the image.
   */
  void extractGreenChannel();

  /**
   * Extract the blue color channel from the image.
   */
  void extractBlueChannel();

  /**
   * Extract the value component from the image.
   */
  void extractValue();

  /**
   * Extract the intensity component from the image.
   */
  void extractIntensity();

  /**
   * Extract the luma component from the image.
   */
  void extractLuma();

  /**
   * Convert the image to greyscale using the specified method (value, intensity, or luma).
   *
   * @param method the method to use for the greyscale conversion.
   */
  void convertToGreyscale(String method);

  /**
   * Modify the brightness of the image.
   *
   * @param amount the amount to adjust the brightness by.
   */
  void modifyBrightness(int amount);

  /**
   * Flips the current image horizontally.
   */
  void flipImage();

  /**
   * Get the pixels of the current image.
   *
   * @return a 2D array of Pixel objects representing the image.
   */
  Pixel[][] getPixels();

  /**
   * Save the current image to an ASCII PPM file.
   *
   * @param filename the path and name of the file to write.
   */
  void writePPM(String filename);
}

