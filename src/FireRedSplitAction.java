package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

final class B implements ActionListener {

  private JFrame frame;

  B(JFrame paramJFrame) {
    this.frame = paramJFrame;
  }

  public final void actionPerformed(ActionEvent paramActionEvent) {
    ArrayList<String> arrayList = null;

    try {
      arrayList = Z.I((String[]) null);
    } catch (Exception iOException) {
      JOptionPane.showMessageDialog(frame,
          "Something happened, aborted sprite making.",
          "Error",
          JOptionPane.ERROR_MESSAGE);
      iOException.printStackTrace();
      return;
    }

    if (arrayList == null) {
      JOptionPane.showMessageDialog(frame,
          "Sprite splitting done, but something failed.",
          "Warning",
          JOptionPane.WARNING_MESSAGE);
    } else {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("<html>");

      for (String str1 : arrayList) {
        stringBuilder.append(str1).append("<br>");
      }

      stringBuilder.append("</html>");

      String str = stringBuilder.toString().replace("<br></html>", "</html>");

      JOptionPane.showMessageDialog(frame, str, "Info", JOptionPane.INFORMATION_MESSAGE);
    }
  }

}
