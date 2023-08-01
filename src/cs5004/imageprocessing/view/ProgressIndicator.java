package cs5004.imageprocessing.view;

import javax.swing.*;

public class ProgressIndicator extends JPanel {
  private JProgressBar progressBar;

  public ProgressIndicator() {
    super();
    progressBar = new JProgressBar(0, 100);
    progressBar.setValue(0);
    progressBar.setStringPainted(true);
    this.add(progressBar);
  }

  public void updateProgress(int progress) {
    progressBar.setValue(progress);
  }
}
