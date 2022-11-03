package org.example;

import java.awt.GridLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//GRASP - Responsible for style of specific buttons, extending button
class PersonButton extends Button {
  //GRASP - Information expert
  // obvious that this class knows the most about its own
  /* default */ PersonButton(final String text, final Person person) {
    super(text);
    addActionListener(e -> {
      final JPanel panel = new JPanel();
      panel.setLayout(new GridLayout(7, 1));
      final TextField name = new TextField(person.getName(), panel);
      final TextField surname = new TextField(person.getSurname(), panel);
      final TextField country = new TextField(person.getCountry(), panel);
      final TextField city = new TextField(person.getCity(), panel);
      final TextField address = new TextField(person.getAddress(), panel);
      final TextField phoneNumber = new TextField(person.getPhoneNumber(), panel);
      final TextField mail = new TextField(person.getMail(), panel);
      final int index = JOptionPane.showConfirmDialog(null, panel);
      if (index == JOptionPane.OK_OPTION) {
        person.setName(name.getText());
        person.setSurname(surname.getText());
        person.setCountry(country.getText());
        person.setCity(city.getText());
        person.setAddress(address.getText());
        person.setPhoneNumber(phoneNumber.getText());
        person.setMail(mail.getText());
      }
    });
  }
}