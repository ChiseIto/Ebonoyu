package Ebonoyu.JFrame;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class JSample9_1 extends JFrame implements ActionListener {
  JLabel label;
  JTextField text;

  public static void main(String args[]) {
    JSample9_1 frame = new JSample9_1("MyTitle");
    frame.setVisible(true);
  }

  JSample9_1(String title) {
    setTitle(title);
    setBounds(100, 100, 600, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    text = new JTextField(20);
    JButton button = new JButton("Get");
    button.addActionListener(this);

    JPanel p = new JPanel();
    p.add(text);
    p.add(button);

    label = new JLabel();

    Container contentPane = getContentPane();
    contentPane.add(p, BorderLayout.CENTER);
    contentPane.add(label, BorderLayout.SOUTH);
  }

  public void actionPerformed(ActionEvent e) {
    label.setText(text.getText());
  }
}