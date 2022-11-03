package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * GRASP - Responsible for managing invoice.
 */
public class Invoice {
  /* default */ Person issuer;
  /* default */ Person client;
  /* default */ String invoiceNumber;
  /* default */ String place;
  /* default */ List<Record> records = new ArrayList<>();

  //GRASP - Information expert
  // obvious that this class knows the most about its own
  /* default */ Invoice() {
    //GRASP - Creator
    // instances of Invoice have the initializing information for instances of Person
    issuer = new Person();
    //GRASP - Creator
    // instances of Invoice have the initializing information for instances of Person
    client = new Person();
  }

  /**
   * GRASP - Information Expert.
   * class invoice has the most info about all the records,
   * so it calculates total price of an invoice
   *
   * @return total price of all products
   */
  public double getTotalInvoicePrice() {
    double total = 0;
    for (final Record r : records) {
      total += r.getTotalPrice();
    }
    return total;
  }

  //GRASP - Information expert
  // obvious that this class knows the most about info inside getters and setters
  public String getInvoiceNumber() {
    return invoiceNumber;
  }

  public String getPlace() {
    return place;
  }

  public void setPlace(final String place) {
    this.place = place;
  }

  public void setInvoiceNumber(final String invoiceNumber) {
    this.invoiceNumber = invoiceNumber;
  }

  //GRASP - Low Coupling
  // delegate creation of product to record, reduces dependency between classes
  //GRASP - High Cohesion
  // Low Coupling = High Cohesion
  public void addRecord(final String name, final double unitPrice, final int quantity) {
    records.add(new Record(name, unitPrice, quantity));
  }
}