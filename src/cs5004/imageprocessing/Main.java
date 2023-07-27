package cs5004.imageprocessing;

import cs5004.imageprocessing.controller.ImageControllerImpl;
import cs5004.imageprocessing.model.ImageModel;
import cs5004.imageprocessing.model.ImageModelImpl;
import cs5004.imageprocessing.view.ImageView;
import cs5004.imageprocessing.view.ImageViewImpl;

/**
 * Main class that contains the entry point of the ImageProcessing program.
 * It allows users to perform various image processing operations on PPM images.
 */
public class Main {
  /**
   * The main method of the program.
   *
   * @param args The command-line arguments.
   */
  public static void main(String[] args) {
    ImageModel model = new ImageModelImpl();
    ImageView view = new ImageViewImpl();
    ImageControllerImpl controller = new ImageControllerImpl(model, view);

    controller.runProgram();
  }
}



