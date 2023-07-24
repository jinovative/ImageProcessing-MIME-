package imageprocessingtest;

import org.junit.Before;
import org.junit.Test;

import cs5004.imageprocessing.controller.ImageControllerImpl;
import cs5004.imageprocessing.model.ImageModelImpl;
import cs5004.imageprocessing.view.ImageViewImpl;
import cs5004.imageprocessing.model.Pixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test cases for brightness adjustment in the ImageProcessing application.
 */
public class BrightnessControlTest {

  private ImageModelImpl model;
  private ImageControllerImpl controller;

  @Before
  public void setup() {
    model = new ImageModelImpl();
    ImageViewImpl view = new ImageViewImpl();
    controller = new ImageControllerImpl(model, view);
  }

  @Test
  public void testBrightnessAdjustment() {
    // Test image file path
    String imagePath = "snail.ppm";

    // Load the image
    try {
      controller.loadImage(imagePath);
    } catch (Exception e) {
      fail("Failed to load the image: " + e.getMessage());
    }

    // Adjust the brightness of the image
    int brightnessChange = 20;
    controller.changeBrightness(brightnessChange);

    // Save the modified image
    String outputImagePath = "snail.ppm";
    try {
      controller.saveImage(outputImagePath);
    } catch (Exception e) {
      fail("Failed to save the image: " + e.getMessage());
    }

    // Load the saved image
    try {
      controller.loadImage(outputImagePath);
    } catch (Exception e) {
      fail("Failed to load the saved image: " + e.getMessage());
    }

    // Verify the brightness adjustment
    Pixel[][] originalPixels = {
            {new Pixel(255, 0, 0), new Pixel(0, 255, 0), new Pixel(0, 0, 255)},
            {new Pixel(255, 255, 0), new Pixel(255, 0, 255), new Pixel(0, 255, 255)}
    };
    Pixel[][] modifiedPixels = model.getPixels();
    for (int i = 0; i < originalPixels.length; i++) {
      for (int j = 0; j < originalPixels[i].length; j++) {
        Pixel originalPixel = originalPixels[i][j];
        Pixel modifiedPixel = modifiedPixels[i][j];
        int originalR = originalPixel.getRed();
        int modifiedR = modifiedPixel.getRed();
        int expectedR = originalR + brightnessChange;
        assertEquals(expectedR, modifiedR);
      }
    }
  }
}
