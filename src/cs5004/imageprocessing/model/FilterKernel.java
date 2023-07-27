package cs5004.imageprocessing.model;

/**
 * Enum `FilterKernel` defines the kernels for image filters.
 * This enum includes `BLUR` and `SHARPEN` as its elements, each of which has a specific kernel.
 */
public enum FilterKernel {

  /**
   * BLUR kernel for blurring an image.
   * This is a 3x3 kernel with values following the Gaussian blur pattern.
   */
  BLUR(new double[][]{
          {1.0/16, 1.0/8, 1.0/16},
          {1.0/8,  1.0/4, 1.0/8},
          {1.0/16, 1.0/8, 1.0/16}
  }),

  /**
   * SHARPEN kernel for sharpening an image.
   * This is a 5x5 kernel with values suitable for image sharpening.
   */
  SHARPEN(new double[][]{
          {-1.0/8, -1.0/8, -1.0/8, -1.0/8, -1.0/8},
          {-1.0/8,  1.0/4,  1.0/4,  1.0/4, -1.0/8},
          {-1.0/8,  1.0/4,    1.0,  1.0/4, -1.0/8},
          {-1.0/8,  1.0/4,  1.0/4,  1.0/4, -1.0/8},
          {-1.0/8, -1.0/8, -1.0/8, -1.0/8, -1.0/8}
  });

  private final double[][] kernel;

  /**
   * Constructs a `FilterKernel` with the specified kernel.
   *
   * @param kernel the kernel for the filter
   */
  FilterKernel(double[][] kernel) {
    this.kernel = kernel;
  }

  /**
   * Returns the kernel of the filter.
   *
   * @return the kernel of the filter
   */
  public double[][] getKernel() {
    return this.kernel;
  }
}
