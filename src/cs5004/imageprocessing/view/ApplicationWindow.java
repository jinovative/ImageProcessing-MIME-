package cs5004.imageprocessing.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

import cs5004.imageprocessing.controller.ImageControllerImpl;
import cs5004.imageprocessing.model.ImageModelImpl;

public class ApplicationWindow {

  public ApplicationWindow() {
    JFrame frame = new JFrame("Image Processor");

    // Initialize imagePanel and add it to a JScrollPane
    ImageDisplayPanel imagePanel = new ImageDisplayPanel();
    JScrollPane imageScrollPane = new JScrollPane(imagePanel);

    // Initialize ImageControllerImpl
    ImageModelImpl model = new ImageModelImpl();
    ImageViewImpl view = new ImageViewImpl(imagePanel);
    ImageControllerImpl controller = new ImageControllerImpl(model, view);

    // Initialize controlPanel and add it to the frame
    ControlPanel controlPanel = new ControlPanel(controller);
    frame.add(controlPanel, BorderLayout.SOUTH);

    // Create a panel for the buttons
    JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
    frame.add(buttonPanel, BorderLayout.NORTH);

    // Create "Load Image" button and add it to the buttonPanel
    JButton loadButton = getLoadButton(frame, controlPanel, controller);
    buttonPanel.add(loadButton);

    // Create "Save Image" button and add it to the buttonPanel
    JButton saveButton = getSaveButton(frame, controller);
    buttonPanel.add(saveButton);

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(800, 600);

    // Add the JScrollPane to the frame
    frame.getContentPane().add(imageScrollPane, BorderLayout.CENTER);

    frame.setVisible(true);
  }

  private static JButton getLoadButton(JFrame frame, ControlPanel controlPanel, ImageControllerImpl controller) {
    JButton loadButton = new JButton("Load Image");
    loadButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Create a file chooser
        final JFileChooser fc = new JFileChooser();

        // In response to a button click:
        int returnVal = fc.showOpenDialog(frame);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
          File file = fc.getSelectedFile();

          // Check if the file is a PPM image
          if (file.getName().endsWith(".ppm")) {
            // Enable features specific to PPM images
            controlPanel.enablePPMFeatures();
          } else {
            // Enable features specific to non-PPM images
            controlPanel.enableNonPPMFeatures();
          }

          // Load the image
          try {
            controller.loadImage(file.getAbsolutePath());
          } catch (Exception ex) {
            ex.printStackTrace();
          }
        }
      }
    });
    return loadButton;
  }

  private static JButton getSaveButton(JFrame frame, ImageControllerImpl controller) {
    JButton saveButton = new JButton("Save Image");
    saveButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Create a file chooser
        final JFileChooser fc = new JFileChooser();

        // In response to a button click:
        int returnVal = fc.showSaveDialog(frame);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
          File file = fc.getSelectedFile();

          // Save the image
          try {
            controller.saveImage(file.getAbsolutePath());
          } catch (Exception ex) {
            ex.printStackTrace();
          }
        }
      }
    });
    return saveButton;
  }
}
