package imageprocessingtest;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import cs5004.imageprocessing.controller.ImageControllerImpl;
import cs5004.imageprocessing.model.ImageModelImpl;
import cs5004.imageprocessing.view.ImageViewImpl;

import static org.junit.Assert.fail;

/**
 * Test class for image loading functionality.
 */
public class LoadingTest {

  private ImageControllerImpl controller;

  @Before
  public void setup() {
    ImageModelImpl model = new ImageModelImpl();
    ImageViewImpl view = new ImageViewImpl();
    controller = new ImageControllerImpl(model, view);
  }

  @Test
  public void testImageLoadingWithValidFilePath() {
    String validFilePath = "snail.ppm";

    try {
      controller.loadImage(validFilePath);
      // No exception means the image loading is successful
    } catch (FileNotFoundException e) {
      fail("Image file not found: " + validFilePath);
    }
  }

  @Test
  public void testImageLoadingWithInvalidFilePath() {
    String invalidFilePath = "invalid.ppm";

    try {
      controller.loadImage(invalidFilePath);
      fail("Expected FileNotFoundException was not thrown");
    } catch (FileNotFoundException ignored) {
    }
  }



}
