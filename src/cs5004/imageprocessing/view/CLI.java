package cs5004.imageprocessing.view;

import java.io.IOException;

import cs5004.imageprocessing.controller.ImageControllerImpl;

public class CLI {

  private final ImageControllerImpl controller;

  public CLI(ImageControllerImpl controller) {
    this.controller = controller;
  }

  public void processCommand(String command) {
    // Parse the command and execute the corresponding operation
    String[] parts = command.split(" ");
    try {
      switch (parts[0]) {
        case "load":
          // Check if the filename is specified
          if (parts.length < 2) {
            System.out.println("Error: No file specified for load command");
            break;
          }
          // Load an image
          controller.loadImage(parts[1]);
          break;
        case "save":
          // Check if the filename is specified
          if (parts.length < 2) {
            System.out.println("Error: No file specified for save command");
            break;
          }
          // Save the image
          controller.saveImage(parts[1]);
          break;
        // Add more cases for other operations
        default:
          System.out.println("Invalid command");
          break;
      }
    } catch (IOException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }
}
