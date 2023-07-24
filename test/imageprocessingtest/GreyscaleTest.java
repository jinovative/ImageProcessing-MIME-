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
 * Test cases for grayscale conversion in the ImageProcessing application.
 */
public class GreyscaleTest {

  private ImageModelImpl model;
  private ImageControllerImpl controller;

  @Before
  public void setup() {
    model = new ImageModelImpl();
    ImageViewImpl view = new ImageViewImpl();
    controller = new ImageControllerImpl(model, view);
  }

  @Test
  public void testImageLoading() {
    // Test image file path
    String imagePath = "snail.ppm";

    // Load the image
    try {
      controller.loadImage(imagePath);
    } catch (Exception e) {
      fail("Failed to load the image: " + e.getMessage());
    }

    // Verify the image dimensions
    int expectedWidth = 256;
    int expectedHeight = 256;
    int actualWidth = model.getPixels()[0].length;
    int actualHeight = model.getPixels().length;
    assertEquals(expectedWidth, actualWidth);
    assertEquals(expectedHeight, actualHeight);
  }

  @Test
  public void testGrayscaleConversion() {
    // Test image file path
    String imagePath = "snail.ppm";

    // Load the image
    try {
      controller.loadImage(imagePath);
    } catch (Exception e) {
      fail("Failed to load the image: " + e.getMessage());
    }

    // Convert the image to grayscale
    controller.convertToGreyscale("luma");

    // Verify the image dimensions remain the same
    int expectedWidth = 256;
    int expectedHeight = 256;
    int actualWidth = model.getPixels()[0].length;
    int actualHeight = model.getPixels().length;
    assertEquals(expectedWidth, actualWidth);
    assertEquals(expectedHeight, actualHeight);


    // Verify the pixel values are correctly converted to grayscale
    Pixel[][] pixels = model.getPixels();
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[i].length; j++) {
        Pixel pixel = pixels[i][j];
        int r = pixel.getRed();
        int g = pixel.getGreen();
        int b = pixel.getBlue();
        int expectedGrayscale = (int) (0.2126 * r + 0.7152 * g + 0.0722 * b);
        assertEquals(expectedGrayscale, r);
        assertEquals(expectedGrayscale, g);
        assertEquals(expectedGrayscale, b);
      }
    }
  }

}
