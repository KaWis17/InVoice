package org.example;

import com.itextpdf.text.DocumentException;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;

public class UserInterface extends JFrame {
  JTable table;
  Invoice invoice;
  TextField invoiceNumberField;
  TextField placeField;

  UserInterface() {
    invoice = new Invoice();
    setTitle("FaktUra");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(650, 650);
    setResizable(false);
    setLayout(new BorderLayout());
    addingInfoPanel();
    addingProductsPanel();
    generateButton();
    setVisible(true);
  }

  void addingInfoPanel() {
    JPanel panel = new JPanel();
    panel.setBorder(new EmptyBorder(10, 10, 10, 10));
    panel.setLayout(new GridLayout(2, 2, 15, 15));
    invoiceNumberField = new TextField("Invoice number", panel);
    placeField = new TextField("Place", panel);

    PersonButton issuerButton = new PersonButton("Issuer's details", invoice.issuer);
    PersonButton clientButton = new PersonButton("Client's details", invoice.client);

    panel.add(issuerButton);
    panel.add(clientButton);
    add(panel, BorderLayout.NORTH);
  }

  void addingProductsPanel() {
    JPanel panel = new JPanel();
    JPanel header = new JPanel();
    header.setPreferredSize(new Dimension(600, 125));
    header.setLayout(new GridLayout(2, 1, 0, 5));
    TextField descriptionField = new TextField("Description", header);
    JPanel box = new JPanel();
    header.add(box);
    box.setLayout(new GridLayout(1, 3, 5, 0));
    TextField unitPriceField = new TextField("Unit price", box);
    TextField totalPriceField = new TextField("Total price", box);


    DefaultTableModel model = new DefaultTableModel();
    table = new JTable(model);
    table.setDefaultEditor(Object.class, null);
    model.addColumn("QTY");
    model.addColumn("Description");
    model.addColumn("Unit price");
    model.addColumn("Total");
    JScrollPane pane = new JScrollPane(table);
    pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    pane.setPreferredSize(new Dimension(600, 275));

    Button addProduct = new Button("ADD");
    addProduct.addActionListener(e -> {
      invoice.addProduct(descriptionField.getText(),
              Double.parseDouble(unitPriceField.getText()),
              Double.parseDouble(totalPriceField.getText()));
      model.addRow(new Object[]
              {invoice.products.size(),
                      descriptionField.getText(),
                      unitPriceField.getText(),
                      totalPriceField.getText()});

      descriptionField.setText("Description");
      unitPriceField.setText("Unit price");
      totalPriceField.setText("Total price");

    });
    box.add(addProduct);

    panel.add(header);
    panel.add(pane);
    this.add(panel, BorderLayout.CENTER);
  }

  void generateButton() {
    JPanel panel = new JPanel();
    Button button = new Button("Generate");
    panel.add(button);
    button.addActionListener(e -> {
      //BufferedImage tableImage = new BufferedImage(table.getWidth(), table.getHeight(), BufferedImage.TYPE_INT_RGB);

      invoice.setInvoiceNumber(invoiceNumberField.getText());
      invoice.setPlace(placeField.getText());

      try {
        PdfGeneration generatePDF = new PdfGeneration(invoice);
        generatePDF.writingToPdf(table);
      } catch (DocumentException | IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    this.add(panel, BorderLayout.SOUTH);
  }
}

class TextField extends JTextField {
  String defaultText;

  TextField(String defaultText, JPanel panel) {
    super(1);
    this.defaultText = defaultText;
    setText(defaultText);
    setHorizontalAlignment(CENTER);
    setPreferredSize(new Dimension(200, 50));
    panel.add(this);
    addFocusListener(new FocusListener() {
      @Override
      public void focusLost(FocusEvent e) {
        if ("".equals(getText())) {
          setText(defaultText);
        }
      }

      @Override
      public void focusGained(FocusEvent e) {
        if (getText().trim().equals(defaultText)) {
          setText("");
        }
      }
    });

    setFont(new Font(Font.DIALOG, Font.PLAIN, 15));
  }
}

class Button extends JButton {
  Button(String text) {
    setText(text);
    setFocusable(false);
  }
}

class PersonButton extends JButton {
  PersonButton(String text, Person person) {
    setText(text);
    setFocusable(false);
    addActionListener(e -> {
      JPanel panel = new JPanel();
      panel.setLayout(new GridLayout(7, 1));
      TextField name = new TextField(person.getName(), panel);
      TextField surname = new TextField(person.getSurname(), panel);
      TextField country = new TextField(person.getCountry(), panel);
      TextField city = new TextField(person.getCity(), panel);
      TextField address = new TextField(person.getAddress(), panel);
      TextField phoneNumber = new TextField(person.getPhoneNumber(), panel);
      TextField eMail = new TextField(person.getMail(), panel);
      int i = JOptionPane.showConfirmDialog(null, panel);
      if (i == JOptionPane.OK_OPTION) {
        person.setName(name.getText());
        person.setSurname(surname.getText());
        person.setCountry(country.getText());
        person.setCity(city.getText());
        person.setAddress(address.getText());
        person.setPhoneNumber(phoneNumber.getText());
        person.setMail(eMail.getText());
      }
    });
  }
}



