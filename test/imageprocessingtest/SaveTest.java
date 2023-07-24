package imageprocessingtest;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import cs5004.imageprocessing.controller.ImageControllerImpl;
import cs5004.imageprocessing.model.ImageModelImpl;

import cs5004.imageprocessing.model.Pixel;
import cs5004.imageprocessing.view.ImageViewImpl;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Test cases for saving functionality of the Image Processing application.
 */
public class SaveTest {

  private ImageModelImpl model;
  private ImageControllerImpl controller;

  @Before
  public void setup() {
    model = new ImageModelImpl();
    ImageViewImpl view = new ImageViewImpl();
    controller = new ImageControllerImpl(model, view);
  }

  @Test
  public void testImageSaving() {
    // Test image file path
    String imagePath = "path/to/test_image.jpg";
    int expectedWidth = 640;
    int expectedHeight = 480;
    int actualWidth = model.getPixels()[0].length;
    int actualHeight = model.getPixels().length;
    Pixel[][] expectedPixels = {
            {new Pixel(255, 0, 0), new Pixel(0, 255, 0), new Pixel(0, 0, 255)},
            {new Pixel(255, 255, 0), new Pixel(255, 0, 255), new Pixel(0, 255, 255)}
    };

    // Load the image
    try {
      controller.loadImage(imagePath);
    } catch (Exception e) {
      fail("Failed to load the image: " + e.getMessage());
    }

    // Save the image
    String outputImagePath = "path/to/saved_image.jpg";
    try {
      controller.saveImage(outputImagePath);
    } catch (Exception e) {
      fail("Failed to save the image: " + e.getMessage());
    }

    // Check if the saved image file exists
    File outputFile = new File(outputImagePath);
    assertTrue("Saved image file does not exist", outputFile.exists());

    // Load the saved image
    try {
      controller.loadImage(outputImagePath);
    } catch (Exception e) {
      fail("Failed to load the saved image: " + e.getMessage());
    }

    // Verify the image size and content
    assertEquals("Image width mismatch", expectedWidth, actualWidth);
    assertEquals("Image height mismatch", expectedHeight, actualHeight);
    assertArrayEquals("Image pixels mismatch", expectedPixels, model.getPixels());
  }
}
