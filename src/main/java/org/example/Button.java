package org.example;

import javax.swing.JButton;

//GRASP - Responsible for style of buttons inside interface
class Button extends JButton {
  //GRASP - Information expert
  // obvious that this class knows the most about its own
  /* default */ Button(final String text) {
    super();
    setText(text);
    setFocusable(false);
  }
}