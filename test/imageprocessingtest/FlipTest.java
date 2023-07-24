package imageprocessingtest;

import org.junit.Before;
import org.junit.Test;

import cs5004.imageprocessing.model.Pixel;
import cs5004.imageprocessing.controller.ImageControllerImpl;
import cs5004.imageprocessing.model.ImageModelImpl;
import cs5004.imageprocessing.view.ImageViewImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test cases for image flipping in the ImageProcessing application.
 */
public class FlipTest {

  private ImageModelImpl model;
  private ImageControllerImpl controller;

  @Before
  public void setup() {
    model = new ImageModelImpl();
    ImageViewImpl view = new ImageViewImpl();
    controller = new ImageControllerImpl(model, view);
  }

  @Test
  public void testImageFlip() {
    // Test image file path
    String imagePath = "snail.ppm";

    // Get the original pixels
    Pixel[][] originalPixels = {
            {new Pixel(255, 0, 0), new Pixel(0, 255, 0), new Pixel(0, 0, 255)},
            {new Pixel(255, 255, 0), new Pixel(255, 0, 255), new Pixel(0, 255, 255)}
    };

    // Load the image
    try {
      controller.loadImage(imagePath);
    } catch (Exception e) {
      fail("Failed to load the image: " + e.getMessage());
    }

    // Flip the image
    controller.flipImage();

    // Save the flipped image
    String outputImagePath = "snail.ppm";
    try {
      controller.saveImage(outputImagePath);
    } catch (Exception e) {
      fail("Failed to save the flipped image: " + e.getMessage());
    }

    // Load the saved flipped image
    try {
      controller.loadImage(outputImagePath);
    } catch (Exception e) {
      fail("Failed to load the saved flipped image: " + e.getMessage());
    }

    // Verify the image flip
    Pixel[][] flippedPixels = model.getPixels();
    int height = originalPixels.length;
    int width = originalPixels[0].length;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Pixel originalPixel = originalPixels[i][j];
        Pixel flippedPixel = flippedPixels[height - 1 - i][width - 1 - j];
        assertEquals(originalPixel.getRed(), flippedPixel.getRed());
        assertEquals(originalPixel.getGreen(), flippedPixel.getGreen());
        assertEquals(originalPixel.getBlue(), flippedPixel.getBlue());
      }
    }
  }

}
