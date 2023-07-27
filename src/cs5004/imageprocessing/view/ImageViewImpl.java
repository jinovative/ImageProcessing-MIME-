package cs5004.imageprocessing.view;

import cs5004.imageprocessing.model.Pixel;

/**
 * `ImageViewImpl` class implements `ImageView` interface.
 * It's used for displaying image processing results or errors to the user.
 */
public class ImageViewImpl implements ImageView {

  @Override
  public void displayUI() {
    System.out.println("UI is displayed.");
  }

  @Override
  public void updateImage(Pixel[][] pixels) {
    System.out.println("Image updated with size: " + pixels.length + "x" + pixels[0].length);
    System.out.println("Whole Pixels info : Option 0");
  }

  @Override
  public void displayMessage(String message) {
    System.out.println(message);
  }

  @Override
  public void displayError(String error) {
    System.err.println("Error: " + error);
  }
}
