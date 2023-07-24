package cs5004.imageprocessing.model;


import java.awt.image.BufferedImage;

/**
 * Interface for color transformations.
 */
public interface ColorTransformation {

  /**
   * Applies the color transformation to an image.
   *
   * @param image the image to be transformed
   * @return the transformed image
   */
  BufferedImage applyTransformation(BufferedImage image);

  String getTransformationName();
}