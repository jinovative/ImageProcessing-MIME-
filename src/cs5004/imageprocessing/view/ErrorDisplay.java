package cs5004.imageprocessing.view;

import javax.swing.*;

public class ErrorDisplay {

  public static void showError(String message) {
    JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
  }
}
