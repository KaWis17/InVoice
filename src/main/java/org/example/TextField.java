package org.example;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JPanel;
import javax.swing.JTextField;

//GRASP - Responsible for style of TextFields inside interface
class TextField extends JTextField {
  /* default */ String defaultText;

  //GRASP - Information expert
  // obvious that this class knows the most about its own
  /* default */ TextField(final String defaultText, final JPanel panel) {
    super(1);
    this.defaultText = defaultText;
    setText(defaultText);
    setHorizontalAlignment(CENTER);
    setPreferredSize(new Dimension(200, 50));
    panel.add(this);
    addFocusListener(new FocusListener() {
      @Override
      public void focusLost(final FocusEvent event) {
        if ("".equals(getText())) {
          setText(defaultText);
        }
      }

      @Override
      public void focusGained(final FocusEvent event) {
        if (getText().trim().equals(defaultText)) {
          setText("");
        }
      }
    });

    setFont(new Font(Font.DIALOG, Font.PLAIN, 15));
  }
}