package imageprocessingtest;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import cs5004.imageprocessing.controller.ImageControllerImpl;
import cs5004.imageprocessing.model.ImageModelImpl;
import cs5004.imageprocessing.model.Pixel;
import cs5004.imageprocessing.view.ImageViewImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


/**
 * Test cases for the ImageProcessing application.
 */
public class ImageProcessingTest {

  private ImageModelImpl model;
  private ImageControllerImpl controller;

  @Before
  public void setup() {
    model = new ImageModelImpl();
    ImageViewImpl view = new ImageViewImpl();
    controller = new ImageControllerImpl(model, view);
  }

  @Test
  public void testValidPixelValues() {
    int r = 100;
    int g = 150;
    int b = 200;

    // Construct a pixel with valid RGB color values
    Pixel pixel = new Pixel(r, g, b);

    // Verify the pixel values
    assertEquals("Incorrect red color value", r, pixel.getRed());
    assertEquals("Incorrect green color value", g, pixel.getGreen());
    assertEquals("Incorrect blue color value", b, pixel.getBlue());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPixelValues() {
    int r = -10;
    int g = 300;
    int b = 256;

    // Construct a pixel with invalid RGB color values
    Pixel pixel = new Pixel(r, g, b);

    // The above line should throw an IllegalArgumentException
  }

  @Test
  public void testReadPPMWithValidFile() {
    String filename = "snail.ppm";

    try {
      model.readPPM(filename);

      // Verify the image dimensions
      assertEquals(256, model.getPixels().length);
      assertEquals(256, model.getPixels()[0].length);
    } catch (FileNotFoundException e) {
      fail("Failed to read PPM file: " + e.getMessage());
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testReadPPMWithInvalidFile() throws FileNotFoundException {
    String filename = "invalidimage.ppm";
    model.readPPM(filename);
  }

  @Test
  public void testConvertToGreyscaleWithValueMethod() {
    // Load the image
    try {
      controller.loadImage("snail.ppm");
    } catch (Exception e) {
      fail("Failed to load the image: " + e.getMessage());
    }

    // Convert to greyscale using the "value" method
    controller.convertToGreyscale("value");

    // Verify the converted pixel values
    Pixel[][] convertedPixels = model.getPixels();
    // Add assertions to verify the pixel values
    assertEquals(255, convertedPixels[0][0].getRed());
    assertEquals(255, convertedPixels[0][0].getGreen());
    assertEquals(255, convertedPixels[0][0].getBlue());
  }

  @Test
  public void testModifyBrightness() {
    // Load the image
    try {
      controller.loadImage("snail.ppm");
    } catch (Exception e) {
      fail("Failed to load the image: " + e.getMessage());
    }

    // Modify brightness by adding 50
    controller.changeBrightness(50);

    // Verify the modified pixel values
    Pixel[][] modifiedPixels = model.getPixels();
    assertEquals(255, modifiedPixels[0][0].getRed());
    assertEquals(255, modifiedPixels[0][0].getGreen());
    assertEquals(255, modifiedPixels[0][0].getBlue());
    assertEquals(255, modifiedPixels[0][1].getRed());
    assertEquals(255, modifiedPixels[0][1].getGreen());
    assertEquals(255, modifiedPixels[0][1].getBlue());
    assertEquals(255, modifiedPixels[1][0].getRed());
    assertEquals(255, modifiedPixels[1][0].getGreen());
    assertEquals(255, modifiedPixels[1][0].getBlue());
    assertEquals(255, modifiedPixels[1][1].getRed());
    assertEquals(255, modifiedPixels[1][1].getGreen());
    assertEquals(255, modifiedPixels[1][1].getBlue());
  }

  @Test
  public void testFlipImage() {
    try {
      controller.loadImage("snail.ppm");
    } catch (Exception e) {
      fail("Failed to load the image: " + e.getMessage());
    }

    // Set up an image with known pixel values
    Pixel[][] pixels = {
            { new Pixel(100, 100, 100), new Pixel(200, 200, 200) },
            { new Pixel(50, 50, 50), new Pixel(150, 150, 150) }
    };
    model.getPixels();

    // Flip the image
    model.flipImage();

    // Verify the flipped image
    Pixel[][] flippedPixels = model.getPixels();
    assertEquals(255, flippedPixels[0][0].getRed());
    assertEquals(255, flippedPixels[0][0].getGreen());
    assertEquals(255, flippedPixels[0][0].getBlue());
    assertEquals(255, flippedPixels[0][1].getRed());
    assertEquals(255, flippedPixels[0][1].getGreen());
    assertEquals(255, flippedPixels[0][1].getBlue());
    assertEquals(255, flippedPixels[1][0].getRed());
    assertEquals(255, flippedPixels[1][0].getGreen());
    assertEquals(255, flippedPixels[1][0].getBlue());
    assertEquals(255, flippedPixels[1][1].getRed());
    assertEquals(255, flippedPixels[1][1].getGreen());
    assertEquals(255, flippedPixels[1][1].getBlue());
  }

  @Test
  public void testWritePPM() {
    try {
      controller.loadImage("snail.ppm");
    } catch (Exception e) {
      fail("Failed to load the image: " + e.getMessage());
    }

    // Set up an image with known pixel values
    Pixel[][] pixels = {
            { new Pixel(100, 100, 100), new Pixel(200, 200, 200) },
            { new Pixel(50, 50, 50), new Pixel(150, 150, 150) }
    };
    model.getPixels();

    // Write the image to a PPM file
    String filename = "testoutput.ppm";
    model.writePPM(filename);

    // Read the written file and verify its content
    try {
      model.readPPM(filename);
      Pixel[][] writtenPixels = model.getPixels();

      // Verify the dimensions
      assertEquals(256, writtenPixels.length);
      assertEquals(256, writtenPixels[0].length);

      // Verify the pixel values
      assertEquals(255, writtenPixels[0][0].getRed());
      assertEquals(255, writtenPixels[0][0].getGreen());
      assertEquals(255, writtenPixels[0][0].getBlue());
      assertEquals(255, writtenPixels[0][1].getRed());
      assertEquals(255, writtenPixels[0][1].getGreen());
      assertEquals(255, writtenPixels[0][1].getBlue());
      assertEquals(255, writtenPixels[1][0].getRed());
      assertEquals(255, writtenPixels[1][0].getGreen());
      assertEquals(255, writtenPixels[1][0].getBlue());
      assertEquals(255, writtenPixels[1][1].getRed());
      assertEquals(255, writtenPixels[1][1].getGreen());
      assertEquals(255, writtenPixels[1][1].getBlue());
    } catch (FileNotFoundException e) {
      fail("Failed to read PPM file: " + e.getMessage());
    }
  }
}