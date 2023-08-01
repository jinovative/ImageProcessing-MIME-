package cs5004.imageprocessing.view;

import javax.swing.*;

public class HelpDisplay {

  public static void showHelp() {
    String helpMessage = "This is a help message. Include instructions for using the application here.";
    JOptionPane.showMessageDialog(null, helpMessage, "Help", JOptionPane.INFORMATION_MESSAGE);
  }
}

