package org.example;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;

//GRASP - Responsible for style of person paragraph inside table
class PersonParagraph extends Paragraph {
  //GRASP - Information expert
  // obvious that this class knows the most about its own
  /* default */ PersonParagraph(final String text, final Person person) {
    super(text + "'s Info: \n"
                    + person.getName() + " "
                    + person.getSurname() + "\n"
                    + person.getCountry() + ", "
                    + person.getCity() + "\n"
                    + person.getAddress() + "\n"
                    + person.getMail() + "\n"
                    + person.getPhoneNumber(),
            FontFactory.getFont(FontFactory.COURIER, 15, BaseColor.BLACK));
  }
}
