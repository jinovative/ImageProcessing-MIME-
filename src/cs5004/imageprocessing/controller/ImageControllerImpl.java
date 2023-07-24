package cs5004.imageprocessing.controller;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import cs5004.imageprocessing.model.ImageModel;
import cs5004.imageprocessing.model.Pixel;
import cs5004.imageprocessing.view.ImageView;

/**
 * `ImageControllerImpl` coordinates between `ImageModel` and `ImageView`.
 * It handles image processing tasks, such as load, transform, and save images.
 */
public class ImageControllerImpl implements ImageController {

  private ImageModel model;
  private ImageView view;

  /**
   * Constructs `ImageControllerImpl` with specified model and view.
   *
   * @param model the model for image processing tasks
   * @param view the view for displaying images and errors
   */
  public ImageControllerImpl(ImageModel model, ImageView view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void loadImage(String filename) throws FileNotFoundException {
    model.readPPM(filename);
    view.updateImage(model.getPixels());
  }

  @Override
  public void extractRedChannel() {
    model.extractRedChannel();
    view.updateImage(model.getPixels());
  }

  @Override
  public void extractGreenChannel() {
    model.extractGreenChannel();
    view.updateImage(model.getPixels());
  }

  @Override
  public void extractBlueChannel() {
    model.extractBlueChannel();
    view.updateImage(model.getPixels());
  }

  @Override
  public void extractValue() {
    model.extractValue();
    view.updateImage(model.getPixels());
  }

  @Override
  public void extractIntensity() {
    model.extractIntensity();
    view.updateImage(model.getPixels());
  }

  @Override
  public void extractLuma() {
    model.extractLuma();
    view.updateImage(model.getPixels());
  }

  @Override
  public void convertToGreyscale(String method) {
    model.convertToGreyscale(method);
    view.updateImage(model.getPixels());
  }

  @Override
  public void changeBrightness(int amount) {
    model.modifyBrightness(amount);
    view.updateImage(model.getPixels());
  }

  @Override
  public void flipImage() {
    model.flipImage();
    view.updateImage(model.getPixels());
  }

  @Override
  public void savePixelsAsText(String filename) {
    Pixel[][] pixels = model.getPixels();
    try {
      FileWriter writer = new FileWriter("res/" + filename + "-pixels.txt");
      BufferedWriter buffer = new BufferedWriter(writer);

      for (int i = 0; i < pixels.length; i++) {
        for (int j = 0; j < pixels[i].length; j++) {
          Pixel pixel = pixels[i][j];
          buffer.write(pixel.getRed() + "," + pixel.getGreen() + "," + pixel.getBlue() + " ");
        }
        buffer.newLine();
      }
      buffer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void saveImage(String filename) {
    model.writePPM("res/" + filename);
  }

  @Override
  public void overwrite(String filename) throws FileNotFoundException {
    model.readPPM(filename);
    view.updateImage(model.getPixels());
  }
}
