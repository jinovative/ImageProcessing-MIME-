package cs5004.imageprocessing.view;

import cs5004.imageprocessing.model.Pixel;

/**
 * `ImageViewImpl` class implements `ImageView` interface.
 * It's used for displaying image processing results or errors to the user.
 */
public class ImageViewImpl implements ImageView {
  private ImageDisplayPanel imageDisplayPanel;

  /**
   * Constructs an ImageViewImpl object.
   *
   * @param imageDisplayPanel the ImageDisplayPanel used for displaying images
   */
  public ImageViewImpl(ImageDisplayPanel imageDisplayPanel) {
    this.imageDisplayPanel = imageDisplayPanel;
  }
  @Override
  public void displayUI() {
    System.out.println("UI is displayed.");
  }

  @Override
  public void updateImage(Pixel[][] pixels) {
    System.out.println("Image updated with size: " + pixels.length + "x" + pixels[0].length);
    imageDisplayPanel.updateImage(pixels);
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
