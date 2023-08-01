package cs5004.imageprocessing.controller;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import cs5004.imageprocessing.model.ColorTransformation;
import cs5004.imageprocessing.model.Filter;
import cs5004.imageprocessing.model.ImageModel;
import cs5004.imageprocessing.model.Pixel;
import cs5004.imageprocessing.view.ImageView;

/**
 * `ImageControllerImpl` coordinates between `ImageModel` and `ImageView`.
 * It handles image processing tasks, such as load, transform, and save images.
 */
public class ImageControllerImpl implements ImageController {

  private final ImageModel model;
  private final ImageView view;

  /**
   * Constructs `ImageControllerImpl` with specified model and view.
   *
   * @param model the model for image processing tasks
   * @param view the view for displaying images and errors
   */
  public ImageControllerImpl(ImageModel model, ImageView view) {
    this.model = model;
    this.view = view;
  }


  @Override
  public void loadImage(String filename) throws IOException {
    File file = new File(filename);
    System.out.println("Loading image: " + filename);
    if (!file.exists()) {
      throw new FileNotFoundException("File " + filename + " does not exist!");
    }
    if (!file.canRead()) {
      throw new IOException("Cannot read file " + filename + "!");
    }

    // Check if the file is a PPM file
    if (filename.endsWith(".ppm")) {
      model.readPPM(filename);  // Load the PPM image into the model
    } else {
      model.loadImage(filename);  // Load the non-PPM image into the model
    }

    // Update the image in the ImageDisplayPanel
    view.updateImage(model.getPixels());
  }

  @Override
  public void changeBrightness(int amount) {
    model.modifyBrightness(amount);
    view.updateImage(model.getPixels());
  }

  @Override
  public void flipImage() {
    model.flipImage();
    view.updateImage(model.getPixels());
  }

  @Override
  public void applyFilter(String filterName) {
    try {
      model.applyFilter(filterName);
      view.updateImage(model.getPixels());
    } catch (NullPointerException e) {
      view.displayError("No image loaded. Please load an image first before applying a filter.");
    } catch (IllegalArgumentException e) {
      view.displayError(e.getMessage());
    }
  }


  @Override
  public void applyTransformation(String transformationName) {
    try {
      model.applyTransformation(transformationName);
      view.updateImage(model.getPixels());
    } catch (NullPointerException e) {
      view.displayError("No image loaded. Please load an image first before applying a transformation.");
    } catch (IllegalArgumentException e) {
      view.displayError(e.getMessage());
    }
  }

  @Override
  public void savePixelsAsText(String filename) {
    Pixel[][] pixels = model.getPixels();
    try {
      FileWriter writer = new FileWriter("res/" + filename + "-pixels.txt");
      BufferedWriter buffer = new BufferedWriter(writer);

      for (int i = 0; i < pixels.length; i++) {
        for (int j = 0; j < pixels[i].length; j++) {
          Pixel pixel = pixels[i][j];
          buffer.write(pixel.getRed() + ","
                  + pixel.getGreen() + ","
                  + pixel.getBlue() + " ");
        }
        buffer.newLine();
      }
      buffer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void saveImage(String filename) {
    try {
      if (filename.endsWith(".ppm")) {
        model.writePPM("res/" + filename);
      } else {
        model.saveImage("res/" + filename);
      }
    } catch (IOException e) {
      throw new RuntimeException("Error saving image: " + filename, e);
    }
  }

  @Override
  public void overwrite(String filename) {
    try {
      model.loadImage(filename);
      view.updateImage(model.getPixels());
    } catch (IOException e) {
      throw new RuntimeException("Error loading image: " + filename, e);
    }
  }

  @Override
  public void extractRedChannel() {
    model.extractRedChannel();
    view.updateImage(model.getPixels());
  }

  @Override
  public void extractGreenChannel() {
    model.extractGreenChannel();
    view.updateImage(model.getPixels());
  }

  @Override
  public void extractBlueChannel() {
    model.extractBlueChannel();
    view.updateImage(model.getPixels());
  }

  @Override
  public void extractValue() {
    model.extractValue();
    view.updateImage(model.getPixels());
  }

  @Override
  public void extractIntensity() {
    model.extractIntensity();
    view.updateImage(model.getPixels());
  }

  @Override
  public void extractLuma() {
    model.extractLuma();
    view.updateImage(model.getPixels());
  }

  @Override
  public void convertToGreyscale(String method) {
    model.convertToGreyscale(method);
    view.updateImage(model.getPixels());
  }

  @Override
  public void runProgram() {
    Scanner scanner = new Scanner(System.in);

    String imageName;
    int selection;

    do {
      view.displayMessage("Enter the name of the image file:");
      imageName = scanner.nextLine();

      // Check if the file is a PPM file
      boolean isPPM = imageName.endsWith(".ppm");

      do {
        if (isPPM) {
          // PPM menu
          view.displayMessage("Select an operation:");
          view.displayMessage("0. Save pixels as text");
          view.displayMessage("1. Greyscale");
          view.displayMessage("2. Brighter(10)");
          view.displayMessage("3. Darker(-10)");
          view.displayMessage("4. Flip");
          view.displayMessage("5. Other(Custom)");
          view.displayMessage("6. Modify another file");
          view.displayMessage("7. Exit");
        } else {
          // Non-PPM menu
          view.displayMessage("Select an operation:");
          view.displayMessage("0. Apply filter");
          view.displayMessage("1. Apply color transformation");
          view.displayMessage("2. Save image");
          view.displayMessage("3. Exit");
        }

        selection = scanner.nextInt();

        if (selection != 6 || !isPPM) {
          try {
            this.loadImage(imageName);
            if (isPPM) {
              switch (selection) {
                case 0 -> {
                  view.displayMessage("Enter the name of the text file to save:");
                  String textFilename = scanner.next();
                  this.savePixelsAsText(imageName);
                }
                case 1 -> {
                  this.convertToGreyscale("luma");
                  this.saveImage(imageName + "-greyscale.ppm");
                  view.displayMessage("Save the operation completed successfully.");
                  view.displayMessage("1. Continue with this file");
                  view.displayMessage("2. Exit");
                  int nextAction = scanner.nextInt();
                  switch (nextAction) {
                    case 1:
                      continue;
                    case 2:
                      view.displayMessage("Exiting the program.");
                      return;
                    default:
                      view.displayError("Please enter a number between 1 and 2.");
                      continue;
                  }
                }
                case 2 -> {
                  this.changeBrightness(10);
                  this.saveImage(imageName + "-brighter.ppm");
                  view.displayMessage("Save the operation completed successfully.");
                  view.displayMessage("1. Continue with this file");
                  view.displayMessage("2. Exit");
                  int nextAction = scanner.nextInt();
                  switch (nextAction) {
                    case 1:
                      continue;
                    case 2:
                      view.displayMessage("Exiting the program.");
                      return;
                    default:
                      view.displayError("Invalid selection. Please enter a number between 1 and 2.");
                      continue;
                  }
                }
                case 3 -> {
                  this.changeBrightness(-10);
                  this.saveImage(imageName + "-darker.ppm");
                  view.displayMessage("Save the operation completed successfully.");
                  view.displayMessage("1. Continue with this file");
                  view.displayMessage("2. Exit");
                  int nextAction = scanner.nextInt();
                  switch (nextAction) {
                    case 1:
                      continue;
                    case 2:
                      view.displayMessage("Exiting the program.");
                      return;
                    default:
                      view.displayError("Invalid selection. Please enter a number between 1 and 2.");
                      continue;
                  }
                }
                case 4 -> {
                  this.flipImage();
                  this.saveImage(imageName + "-flipped.ppm");
                  view.displayMessage("Save the operation completed successfully.");
                  view.displayMessage("1. Continue with this file");
                  view.displayMessage("2. Exit");
                  int nextAction = scanner.nextInt();
                  switch (nextAction) {
                    case 1:
                      continue;
                    case 2:
                      view.displayMessage("Exiting the program.");
                      return;
                    default:
                      view.displayError("Invalid selection. Please enter a number between 1 and 2.");
                      continue;
                  }
                }
                case 5 -> {
                  view.displayMessage("Select a custom option:");
                  view.displayMessage("1. Change brightness");
                  view.displayMessage("2. Convert to greyscale");
                  view.displayMessage("3. Flip image");
                  view.displayMessage("4. Save image");
                  int customOption = scanner.nextInt();
                  switch (customOption) {
                    case 1:
                      view.displayMessage("Enter the brightness change amount (negative to darken):");
                      int brightnessChange = scanner.nextInt();
                      this.changeBrightness(brightnessChange);
                      break;
                    case 2:
                      view.displayMessage("Enter the greyscale(value, intensity, luma):");
                      String method = scanner.next();
                      this.convertToGreyscale(method);
                      break;
                    case 3:
                      this.flipImage();
                      break;
                    case 4:
                      this.saveImage(imageName + "-custom.ppm");
                      break;
                    default:
                      view.displayError("Invalid option. Please enter a number between 1 and 4.");
                      break;
                  }
                }
                case 6 -> {
                  continue;
                }
                case 7 -> {
                  view.displayMessage("Exiting the program.");
                  return;
                }
                default -> view.displayError("Invalid selection.");
              }
            } else {
              // Handle non-PPM operations
              switch (selection) {
                case 0 -> {  // Apply filter
                  while (true) {  // Loop until user chooses to exit
                    view.displayMessage("Select an operation:");
                    view.displayMessage("1. Apply blur filter");
                    view.displayMessage("2. Apply sharpen filter");
                    view.displayMessage("3. Save image");
                    view.displayMessage("4. Exit");
                    int filterSelection = scanner.nextInt();
                    try {
                      switch (filterSelection) {
                        case 1 -> {
                          this.model.applyFilter("blur");
                          view.displayMessage("Blur filter applied."
                                  + " Would you like to apply another filter?");
                        }
                        case 2 -> {
                          this.model.applyFilter("sharpen");
                          view.displayMessage("Sharpen filter applied. "
                                  + "Would you like to apply another filter?");
                        }
                        case 3 -> {
                          view.displayMessage("Enter the name of the image file to save:");
                          String outputFilename = scanner.next();
                          this.saveImage(outputFilename + ".png");  // Add .png extension
                          view.displayMessage("Image saved successfully!");
                          view.displayMessage("Would you like to perform another image?");
                          view.displayMessage("1. Yes");
                          view.displayMessage("2. No, exit");
                          int continueSelection = scanner.nextInt();
                          if (continueSelection == 1) {
                            this.runProgram();
                          } else {
                            view.displayMessage("Exiting the program.");
                            System.exit(0);
                          }
                        }

                        case 4 -> {
                          view.displayMessage("Exiting the filter application.");
                          return;  // Exit the loop
                        }
                        default -> view.displayError("Invalid selection.");
                      }
                    } catch (Exception e) {
                      view.displayError(e.getMessage());
                    }
                  }
                }

                case 1 -> {  // Apply color transformation
                  view.displayMessage("Select a color transformation:");
                  view.displayMessage("1. Greyscale");
                  view.displayMessage("2. Sepia tone");
                  int transformationSelection = scanner.nextInt();
                  try {
                    switch (transformationSelection) {
                      case 1:
                        this.model.applyTransformation("greyscale");
                        this.saveImage(imageName + "-greyscale.png");
                        view.displayMessage("Greyscale transformation applied and image saved as "
                                + imageName + "-greyscale.png");
                        break;
                      case 2:
                        this.model.applyTransformation("sepia");
                        this.saveImage(imageName + "-sepia.png");
                        view.displayMessage("Sepia transformation applied and image saved as "
                                + imageName + "-sepia.png");
                        break;
                      default:
                        view.displayError("Invalid transformation selection.");
                        break;
                    }
                  } catch (Exception e) {
                    view.displayError(e.getMessage());
                  }
                  view.displayMessage("Do you want to perform another operation?");
                  view.displayMessage("1. Yes");
                  view.displayMessage("2. No, exit");
                  int continueSelection = scanner.nextInt();
                  if (continueSelection == 1) {
                    this.runProgram();
                  } else {
                    view.displayMessage("Exiting the program.");
                    System.exit(0);
                  }
                }
                case 2 -> {  // Save image
                  view.displayMessage("Enter the name of the image file to save:");
                  String outputFilename = scanner.next();
                  this.saveImage(outputFilename);
                }
                case 3 -> {  // Exit
                  view.displayMessage("Exiting the program.");
                  return;
                }
                default -> view.displayError("Invalid selection.");
              }
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
