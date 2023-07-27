package imageprocessingtest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import cs5004.imageprocessing.controller.ImageController;
import cs5004.imageprocessing.controller.ImageControllerImpl;
import cs5004.imageprocessing.model.ImageModel;
import cs5004.imageprocessing.model.ImageModelImpl;
import cs5004.imageprocessing.model.Pixel;
import cs5004.imageprocessing.view.ImageView;
import cs5004.imageprocessing.view.ImageViewImpl;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * This class tests the functionalities of ImageProcessing.
 */
public class testImageProcessing {
  private ImageController controller;
  private ImageModel model;
  private ImageView view;
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

  @Before
  public void setUp() {
    model = new ImageModelImpl();
    view = new ImageViewImpl();
    controller = new ImageControllerImpl(model, view);
    System.setOut(new PrintStream(outContent));
  }

  @After
  public void restoreStreams() {
    System.setOut(System.out);
  }

  @Test
  public void testRunProgramScript() {
    String simulatedUserInput = "snail.jpg\n0\n1\n4\n";
    System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

    controller.runProgram();

    String expectedOutput = "Enter the name of the image file:\n"
            + "Select an operation:\n"
            + "0. Apply filter\n"
            + "1. Apply color transformation\n"
            + "2. Save image\n"
            + "3. Exit\n"
            + "Select an operation:\n"
            + "1. Apply blur filter\n"
            + "2. Apply sharpen filter\n"
            + "3. Save image\n"
            + "4. Exit\n"
            + "Blur filter applied. Would you like to apply another filter?\n"
            + "Select an operation:\n"
            + "1. Apply blur filter\n"
            + "2. Apply sharpen filter\n"
            + "3. Save image\n"
            + "4. Exit\n"
            + "Exiting the filter application.\n";

    assertEquals(expectedOutput, outContent.toString());
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
  public void testFilterApplication() {
    ImageModelImpl imageModel = new ImageModelImpl();

    try {
      // Load a test image file
      imageModel.loadImage("snail.jpg");

      // Apply a filter
      imageModel.applyFilter("blur");

      // Load the expected result from a file
      ImageModelImpl expectedModel = new ImageModelImpl();
      expectedModel.loadImage("snail.jpg");
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


  @Test
  public void testImageSaving() {
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

}
