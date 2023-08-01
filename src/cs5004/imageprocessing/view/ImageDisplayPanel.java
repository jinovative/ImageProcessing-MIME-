package cs5004.imageprocessing.view;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

import cs5004.imageprocessing.model.Pixel;

public class ImageDisplayPanel extends JPanel {
  private Image image;

  public ImageDisplayPanel() {
    super();
    this.setBackground(Color.WHITE);
  }

  public void setImage(Image image) {
    System.out.println("setImage called");
    this.image = image;
    this.repaint();  // This will trigger a call to paintComponent
    System.out.println("repaint called");
  }


  public void updateImage(Pixel[][] pixels) {
    // Check if the pixels array is empty
    if (pixels == null || pixels.length == 0 || pixels[0].length == 0) {
      System.err.println("No image data to display!");
      return;
    }

    // Create a new BufferedImage
    BufferedImage newImage = new BufferedImage(pixels[0].length, pixels.length, BufferedImage.TYPE_INT_RGB);
    System.out.println("Created BufferedImage with width: " + newImage.getWidth() + ", height: " + newImage.getHeight());

    // Fill the BufferedImage with Pixel data
    for (int y = 0; y < pixels.length; y++) {
      for (int x = 0; x < pixels[0].length; x++) {
        Pixel pixel = pixels[y][x];
        int rgb = pixel.getRGB();
        newImage.setRGB(x, y, rgb);
      }
    }

    // Set the new image
    setImage(newImage);

    // Print the image dimensions
    System.out.println("Image width: " + newImage.getWidth() + ", height: " + newImage.getHeight());
  }



  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (image != null) {
      System.out.println("Image is not null in paintComponent");

      // Scale the image to fit the panel
      Image scaledImage = image.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
      System.out.println("Scaled image width: " + scaledImage.getWidth(null) + ", height: " + scaledImage.getHeight(null));

      // Draw the image on the panel
      g.drawImage(scaledImage, 0, 0, this);
    } else {
      System.out.println("Image is null in paintComponent");
    }
  }

}