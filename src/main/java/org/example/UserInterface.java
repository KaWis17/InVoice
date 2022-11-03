package org.example;

import com.itextpdf.text.DocumentException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 * GRASP - Responsible for managing user interface.
 */
public class UserInterface extends JFrame {
  /* default */ JTable table;
  /* default */ Invoice invoice;
  /* default */ TextField invoiceNrField;
  /* default */ TextField placeField;

  //GRASP - Information expert - obvious that this class
  // knows the most about its own
  /* default */ UserInterface() {
    super();
    //GRASP - Creator
    // instances of UserInterface have the initializing information
    // for instances of Invoice
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

  //GRASP - Information expert
  // this function is adding elements to JFrame,
  // so this class know the most about frame
  /* default */ void addingInfoPanel() {
    final JPanel panel = new JPanel();
    panel.setBorder(new EmptyBorder(10, 10, 10, 10));
    panel.setLayout(new GridLayout(2, 2, 15, 15));
    invoiceNrField = new TextField("Invoice number", panel);
    placeField = new TextField("Place", panel);

    //GRASP - Creator
    // instances of UserInterface closely use instances of PersonButton
    final PersonButton issuerButton = new PersonButton("Issuer's details", invoice.issuer);
    //GRASP - Creator
    // instances of UserInterface closely use instances of PersonButton
    final PersonButton clientButton = new PersonButton("Client's details", invoice.client);

    panel.add(issuerButton);
    panel.add(clientButton);
    add(panel, BorderLayout.NORTH);
  }

  //GRASP - Information expert
  // this function is adding elements to JFrame,
  // so this class know the most about frame
  /* default */ void addingProductsPanel() {
    final JPanel panel = new JPanel();
    final JPanel header = new JPanel();
    panel.add(header);
    header.setPreferredSize(new Dimension(600, 125));
    header.setLayout(new GridLayout(2, 1, 0, 5));
    //GRASP - Creator - instances of UserInterface closely use instances of TextField
    final TextField descriptionField = new TextField("Description", header);
    final JPanel box = new JPanel();
    header.add(box);
    box.setLayout(new GridLayout(1, 3, 5, 0));
    //GRASP - Creator - instances of UserInterface closely use instances of TextField
    final TextField unitPriceField = new TextField("Unit price", box);
    //GRASP - Creator - instances of UserInterface closely use instances of TextField
    final TextField quantityField = new TextField("Quantity", box);

    final DefaultTableModel model = new DefaultTableModel();
    //GRASP - Creator - instances of UserInterface closely use instances of Button
    final Button addProduct = new Button("ADD");
    addProduct.addActionListener(e -> {
      invoice.addRecord(descriptionField.getText(),
              Double.parseDouble(unitPriceField.getText()),
              Integer.parseInt(quantityField.getText()));
      model.addRow(new Object[]{invoice.records.size(),
              descriptionField.getText(),
              unitPriceField.getText(),
              quantityField.getText(),
              invoice.records.get(invoice.records.size() - 1).getTotalPrice()});
      descriptionField.setText("Description");
      unitPriceField.setText("Unit price");
      quantityField.setText("Quantity");

    });


    table = new JTable(model);
    table.setDefaultEditor(Object.class, null);
    model.addColumn("QTY");
    model.addColumn("Description");
    model.addColumn("Unit price");
    model.addColumn("Quantity");
    model.addColumn("Total price");
    final JScrollPane pane = new JScrollPane(table);
    pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    pane.setPreferredSize(new Dimension(600, 275));

    box.add(addProduct);

    panel.add(pane);
    this.add(panel, BorderLayout.CENTER);
  }

  //GRASP - Information expert
  // this function is adding elements to JFrame,
  // so this class know the most about frame
  /* default */ void generateButton() {
    final JPanel panel = new JPanel();
    //GRASP - Creator - instances of UserInterface closely use instances of Button
    final Button button = new Button("Generate");
    panel.add(button);
    button.addActionListener(e -> {
      invoice.setInvoiceNumber(invoiceNrField.getText());
      invoice.setPlace(placeField.getText());

      try {
        //GRASP - Creator
        // instances of UserInterface have the initializing information
        // for instances of PdfGeneration
        final PdfGeneration generatePdf = new PdfGeneration(invoice);
        generatePdf.writingToPdf(table);
      } catch (DocumentException | IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    this.add(panel, BorderLayout.SOUTH);
  }
}



