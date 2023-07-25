package cs5004.imageprocessing.controller;


import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The ImageController interface represents the controller component in the Model-View-Controller.
 * It defines the operations that can be performed on the ImageModel.
 */
public interface ImageController {
  void runProgram();

  /**
   * Processes a script command.
   *
   * @param script the script command to be processed.
   */
  void processCommand(String script);

  /**
   * Loads a script from a file.
   *
   * @param filename the path and name of the file to read.
   */
  void loadScript(String filename);

  /**
   * Load an image from an ASCII PPM file.
   *
   * @param filename the path to the file to load.
   */
  void loadImage(String filename) throws IOException;

  /**
   * Create an image that visualizes the red channel of the current image.
   */
  void extractRedChannel();

  /**
   * Create an image that visualizes the green channel of the current image.
   */
  void extractGreenChannel();

  /**
   * Create an image that visualizes the blue channel of the current image.
   */
  void extractBlueChannel();

  /**
   * Create an image that visualizes the value of the current image.
   */
  void extractValue();

  /**
   * Create an image that visualizes the intensity of the current image.
   */
  void extractIntensity();

  /**
   * Create an image that visualizes the luma of the current image.
   */
  void extractLuma();

  /**
   * Converts the current image to greyscale using the specified method.
   *
   * @param method the method to use for greyscale conversion.
   *               Must be one of "value", "intensity", or "luma".
   * @throws IllegalArgumentException if an unknown method is provided.
   */
  void convertToGreyscale(String method);

  /**
   * Brighten or darken the current image by a specified amount.
   *
   * @param amount the amount to change the brightness by, can be negative to darken the image.
   */
  void changeBrightness(int amount);

  /**
   * Flips the current image horizontally.
   */
  void flipImage();

  /**
   * Saves the pixel data of the current image as a text file.
   * Each pixel is represented by its red, green, and blue components separated by commas,
   * and each row of pixels is written on a new line.
   *
   * @param filename the name of the text file to save the pixel data to
   */
  void savePixelsAsText(String filename);

  /**
   * Save the current image to an ASCII PPM file.
   *
   * @param filename the path to the file to save to.
   */
  void saveImage(String filename);

  /**
   * Overwrite an existing image with another image.
   *
   * @param filename the path to the image to overwrite with.
   */
  void overwrite(String filename) throws FileNotFoundException;

}
