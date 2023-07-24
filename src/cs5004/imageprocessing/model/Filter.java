package cs5004.imageprocessing.model;
import java.awt.image.BufferedImage;

/**
 * Interface for image filters.
 */
public interface Filter {

  /**
   * Applies the filter to an image.
   *
   * @param image the image to be filtered
   * @return the filtered image
   */
  BufferedImage applyFilter(BufferedImage image);

  /**
   * Returns the name of the filter.
   *
   * @return the filter name
   */
  String getFilterName();
}
