package cs5004.imageprocessing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import cs5004.imageprocessing.model.ImageModelImpl;
import cs5004.imageprocessing.view.ApplicationWindow;
import cs5004.imageprocessing.controller.ImageControllerImpl;
import cs5004.imageprocessing.view.HistogramPanel;
import cs5004.imageprocessing.view.ImageDisplayPanel;
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
    ImageModelImpl model = new ImageModelImpl();
    ImageViewImpl view = new ImageViewImpl(new ImageDisplayPanel());
    ImageControllerImpl controller = new ImageControllerImpl(model, view, new HistogramPanel());
    if (args.length == 0) {
      // No command-line arguments were provided.
      // Open the program in GUI mode.
      ApplicationWindow window = new ApplicationWindow();
    } else if (args.length == 1 && args[0].equals("-text")) {
      // The "-text" command-line argument was provided.
      // Run the program in interactive text mode
      controller.runProgram();
    } else if (args.length == 2 && args[0].equals("-file")) {
      // The "-file" command-line argument was provided.
      try (BufferedReader scriptReader = new BufferedReader(new FileReader(args[1]))) {
        String command;
        while ((command = scriptReader.readLine()) != null) {
          controller.executeCommand(command);  // Execute the command from the script file
        }
      } catch (IOException e) {
        System.err.println("Error reading script file: " + args[1]);
        System.exit(1);
      }
    } else {
      // Invalid command-line arguments were provided.
      System.out.println("Error: Invalid command-line arguments.");
      System.exit(1);
    }
  }
}



