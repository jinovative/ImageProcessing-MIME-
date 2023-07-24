package imageprocessingtest;

import org.junit.Before;
import org.junit.Test;

import cs5004.imageprocessing.controller.ImageControllerImpl;
import cs5004.imageprocessing.model.ImageModelImpl;
import cs5004.imageprocessing.model.Pixel;
import cs5004.imageprocessing.view.ImageViewImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test cases for custom operations in the ImageProcessing application.
 */
public class CustomPPMTest {
  private ImageModelImpl model;
  private ImageControllerImpl controller;

  @Before
  public void setup() {
    model = new ImageModelImpl();
    ImageViewImpl view = new ImageViewImpl();
    controller = new ImageControllerImpl(model, view);
  }

  @Test
  public void testCustomOperation() {
    // Test image file path
    String imagePath = "snail.ppm";

    // Load the image
    try {
      controller.loadImage(imagePath);
    } catch (Exception e) {
      fail("Failed to load the image: " + e.getMessage());
    }

    // Perform custom operation (e.g., change brightness and flip)
    controller.changeBrightness(30);
    controller.flipImage();

    // Verify the operation result (e.g., compare the adjusted pixel values with expected values)
    Pixel[][] pixels = model.getPixels();
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[i].length; j++) {
        Pixel pixel = pixels[i][j];
        int r = pixel.getRed();
        int g = pixel.getGreen();
        int b = pixel.getBlue();
        // Verify brightness change
        assertEquals(r + 30, r);
        assertEquals(g + 30, g);
        assertEquals(b + 30, b);
        // Verify flip
        Pixel flippedPixel = pixels[i][pixels[i].length - j - 1];
        assertEquals(r, flippedPixel.getRed());
        assertEquals(g, flippedPixel.getGreen());
        assertEquals(b, flippedPixel.getBlue());
      }
    }
  }

}
