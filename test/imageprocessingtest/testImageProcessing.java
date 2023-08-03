package imageprocessingtest;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

import cs5004.imageprocessing.controller.ImageController;
import cs5004.imageprocessing.controller.ImageControllerImpl;
import cs5004.imageprocessing.model.ImageModel;
import cs5004.imageprocessing.model.ImageModelImpl;
import cs5004.imageprocessing.model.Pixel;
import cs5004.imageprocessing.view.ControlPanel;
import cs5004.imageprocessing.view.HistogramPanel;
import cs5004.imageprocessing.view.ImageDisplayPanel;
import cs5004.imageprocessing.view.ImageView;
import cs5004.imageprocessing.view.ImageViewImpl;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


/**
 * This class tests the functionalities of ImageProcessing.
 */
public class testImageProcessing {

  private ImageControllerImpl controller;
  private ImageModelImpl model;
  private ImageViewImpl view;
  private ControlPanel controlPanel;

  @Before
  public void setUp() {
    ImageDisplayPanel imageDisplayPanel = new ImageDisplayPanel();
    model = new ImageModelImpl();
    view = new ImageViewImpl(imageDisplayPanel);
    controller = new ImageControllerImpl(model, view, new HistogramPanel());
    controlPanel = new ControlPanel(controller);
    // Initialize your objects here
  }

  @Test
  public void testImageLoad() {
    String validFilePath = "snorlax.png";
    try {
      controller.loadImage(validFilePath);
      // No exception means the image loading is successful
    } catch (FileNotFoundException e) {
      fail("Image file not found: " + validFilePath);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testImageLoadingWithInvalidFilePath() {
    String invalidFilePath = "invalid.jpg";

    try {
      controller.loadImage(invalidFilePath);
      fail("Expected FileNotFoundException was not thrown");
    } catch (FileNotFoundException ignored) {
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testSaveImage() {
    ImageModelImpl imageModel = new ImageModelImpl();

    try {
      imageModel.loadImage("snorlax.png");

      String outputFilePath = "snorlax.png";
      imageModel.saveImage(outputFilePath);

      File outputFile = new File(outputFilePath);
      assertTrue(outputFile.exists() && !outputFile.isDirectory());
    } catch (IOException e) {
      fail("Exception thrown during test: " + e);
    }
  }

  @Test
  public void testFilterApplication() {
    ImageModelImpl imageModel = new ImageModelImpl();

    try {
      // Load a test image file
      imageModel.loadImage("snorlax.png");

      // Apply a filter
      imageModel.applyFilter("blur");

      // Load the expected result from a file
      ImageModelImpl expectedModel = new ImageModelImpl();
      expectedModel.loadImage("snorlax.png");
      Pixel[][] expectedPixels = expectedModel.getPixels();

      // Assert that the filtered image data is as expected
      Pixel[][] actualPixels = imageModel.getPixels();
      assertArrayEquals(expectedPixels, actualPixels);
    } catch (IOException e) {
      // If an exception is thrown during the test, it will fail
      fail("Exception thrown during test: " + e);
    }
  }

  @Test
  public void testTransformationApplication() {
    ImageModelImpl imageModel = new ImageModelImpl();

    try {
      // Load a test image file
      imageModel.loadImage("snail.jpg");

      // Apply a transformation
      imageModel.applyTransformation("sepia");

      // Load the expected result from a file
      ImageModelImpl expectedModel = new ImageModelImpl();
      expectedModel.loadImage("snail.jpg");
      Pixel[][] expectedPixels = expectedModel.getPixels();

      // Assert that the transformed image data is as expected
      Pixel[][] actualPixels = imageModel.getPixels();
      assertArrayEquals(expectedPixels, actualPixels);
    } catch (IOException e) {
      // If an exception is thrown during the test, it will fail
      fail("Exception thrown during test: " + e);
    }
  }
}
