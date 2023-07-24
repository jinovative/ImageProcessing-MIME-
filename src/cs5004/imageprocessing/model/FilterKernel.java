package cs5004.imageprocessing.model;

public enum FilterKernel {
  BLUR(new double[][]{
          {1.0/16, 1.0/8, 1.0/16},
          {1.0/8,  1.0/4, 1.0/8},
          {1.0/16, 1.0/8, 1.0/16}
  }),

  SHARPEN(new double[][]{
          {-1.0/8, -1.0/8, -1.0/8, -1.0/8, -1.0/8},
          {-1.0/8,  1.0/4,  1.0/4,  1.0/4, -1.0/8},
          {-1.0/8,  1.0/4,    1.0,  1.0/4, -1.0/8},
          {-1.0/8,  1.0/4,  1.0/4,  1.0/4, -1.0/8},
          {-1.0/8, -1.0/8, -1.0/8, -1.0/8, -1.0/8}
  });

  private final double[][] kernel;

  FilterKernel(double[][] kernel) {
    this.kernel = kernel;
  }

  public double[][] getKernel() {
    return this.kernel;
  }
}
