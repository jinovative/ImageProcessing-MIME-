package cs5004.imageprocessing.view;

import cs5004.imageprocessing.model.Pixel;


/**
 * This interface represents the operations offered by the image view layer.
 */
public interface ImageView {

  /**
   * Display the User Interface (UI) for the image application.
   */
  void displayUI();

  /**
   * Update the current image displayed on the UI with the given pixel array.
   *
   * @param pixels the pixel array to update the image with.
   */
  void updateImage(Pixel[][] pixels);

  /**
   * Display a message to the user.
   *
   * @param message the message to be displayed.
   */
  void displayMessage(String message);

  /**
   * Display an error message to the user.
   *
   * @param error the error message to be displayed.
   */
  void displayError(String error);
}


