package cs5004.imageprocessing;

import cs5004.imageprocessing.controller.ImageControllerImpl;
import cs5004.imageprocessing.model.ImageModel;
import cs5004.imageprocessing.model.ImageModelImpl;
import cs5004.imageprocessing.view.ImageView;
import cs5004.imageprocessing.view.ImageViewImpl;
import java.util.Scanner;

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

    Scanner scanner = new Scanner(System.in);

    String imageName;
    int selection;

    do {
      System.out.println("Enter the name of the image file:");
      imageName = scanner.nextLine();

      do {
        System.out.println("Select an operation:");
        System.out.println("0. Save pixels as text");
        System.out.println("1. Greyscale");
        System.out.println("2. Brighter(10)");
        System.out.println("3. Darker(-10)");
        System.out.println("4. Flip");
        System.out.println("5. Other(Custom)");
        System.out.println("6. Modify another file");
        System.out.println("7. Exit");

        selection = scanner.nextInt();

        if (selection != 6) {
          try {
            controller.loadImage(imageName + ".ppm");

            switch (selection) {
              case 0:
                System.out.println("Enter the name of the text file to save:");
                String textFilename = scanner.next();
                controller.savePixelsAsText(imageName);
                break;
              case 1:
                controller.convertToGreyscale("luma");
                controller.saveImage(imageName + "-greyscale.ppm");
                break;
              case 2:
                controller.changeBrightness(10);
                controller.saveImage(imageName + "-brighter.ppm");
                break;
              case 3:
                controller.changeBrightness(-10);
                controller.saveImage(imageName + "-darker.ppm");
                break;
              case 4:
                controller.flipImage();
                controller.saveImage(imageName + "-flipped.ppm");
                break;
              case 5:
                System.out.println("Select a custom option:");
                System.out.println("1. Change brightness");
                System.out.println("2. Convert to greyscale");
                System.out.println("3. Flip image");
                System.out.println("4. Save image");
                int customOption = scanner.nextInt();
                switch (customOption) {
                  case 1:
                    System.out.println("Enter the brightness change amount (negative to darken):");
                    int brightnessChange = scanner.nextInt();
                    controller.changeBrightness(brightnessChange);
                    break;
                  case 2:
                    System.out.println("Enter the greyscale(value, intensity, luma):");
                    String method = scanner.next();
                    controller.convertToGreyscale(method);
                    break;
                  case 3:
                    controller.flipImage();
                    break;
                  case 4:
                    controller.saveImage(imageName + "-custom.ppm");
                    break;
                  default:
                    System.out.println("Invalid option. Please enter a number between 1 and 4.");
                    break;
                }
                break;

              case 6:
                continue;
              case 7:
                System.out.println("Exiting the program.");
                return;
              default:
                System.out.println("Invalid selection.");
                break;
            }
          } catch (Exception e) {
            view.displayError(e.getMessage());
          }
        }
        scanner.nextLine();
      }
      while (selection != 6 && selection != 7);
    }
    while (selection != 7);

    scanner.close();
  }
}



