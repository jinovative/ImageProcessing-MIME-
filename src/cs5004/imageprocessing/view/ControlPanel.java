package cs5004.imageprocessing.view;

import javax.swing.*;
import cs5004.imageprocessing.controller.ImageControllerImpl;
import cs5004.imageprocessing.model.ImageModelImpl;

/**
 * `ControlPanel` class represents a control panel section in the Image Processor application GUI.
 * It includes various buttons for image processing operations.
 *
 * Depending on the type of image (PPM or Non-PPM), certain buttons are enabled or disabled.
 */
public class ControlPanel extends JPanel {
  private JButton flipButton;
  private JButton grayscaleButton;
  private JButton blurButton;
  private JButton sharpenButton;
  private JButton sepiaButton;
  private JButton brightnessButton;
  private ImageControllerImpl controller;
  private ImageModelImpl model;
  private ImageViewImpl view;

  /**
   * Constructs a ControlPanel object with a given ImageController.
   *
   * @param controller the ImageController used to manipulate images
   */
  public ControlPanel(ImageControllerImpl controller) {
    super();
    this.controller = controller;

    flipButton = new JButton("Flip");
    flipButton.addActionListener(e -> controller.flipImage());

    brightnessButton = new JButton("Set Brightness");
    brightnessButton.addActionListener(e -> {
      String input = JOptionPane.showInputDialog("Enter brightness value (-255 to 255)");
      int brightnessValue = Integer.parseInt(input);
      controller.changeBrightness(brightnessValue);
    });

    blurButton = new JButton("Blur");
    blurButton.addActionListener(e -> controller.applyFilter("blur"));

    sharpenButton = new JButton("Sharpen");
    sharpenButton.addActionListener(e -> controller.applyFilter("sharpen"));

    grayscaleButton = new JButton("Greyscale");
    grayscaleButton.addActionListener(e -> controller.applyTransformation("greyscale"));

    sepiaButton = new JButton("SepiaTone");
    sepiaButton.addActionListener(e -> controller.applyTransformation("sepia"));



    disableAllFeatures();

    this.add(flipButton);
    this.add(brightnessButton);
    this.add(blurButton);
    this.add(sharpenButton);
    this.add(grayscaleButton);
    this.add(sepiaButton);
  }

  /**
   * Enables the image processing features that are specific to PPM images.
   */
  public void enablePPMFeatures() {
    disableAllFeatures();
    flipButton.setEnabled(true);
    brightnessButton.setEnabled(true);
  }

  /**
   * Enables the image processing features that are specific to non-PPM images.
   */
  public void enableNonPPMFeatures() {
    disableAllFeatures();
    blurButton.setEnabled(true);
    sharpenButton.setEnabled(true);
    sepiaButton.setEnabled(true);
    grayscaleButton.setEnabled(true);
  }

  /**
   * Disables all image processing features.
   */
  private void disableAllFeatures() {
    flipButton.setEnabled(false);
    grayscaleButton.setEnabled(false);
    blurButton.setEnabled(false);
    sharpenButton.setEnabled(false);
    sepiaButton.setEnabled(false);
    brightnessButton.setEnabled(false);
  }
}
