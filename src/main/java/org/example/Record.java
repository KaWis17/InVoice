package org.example;

import static java.lang.Math.round;

/**
 * GRASP - Responsible for managing specific record.
 * GRASP - High Cohesion - between Invoice and Product to lower dependency
 */
public class Record {
  /* default */ Product product;
  /* default */ int quantity;

  //GRASP - Information expert
  // obvious that this class knows the most about its own
  /* default */ Record(final String name, final double unitPrice, final int quantity) {
    this.quantity = quantity;
    //GRASP - Creator
    // instances of Record have the initializing information for instances of Product
    // (to be changed - choose product from database)
    product = new Product(name, unitPrice);

  }

  //GRASP - Information expert
  // to get total price we need unitPrice and quantity,
  // this class knows both of them
  // (e.g. product do not know quantity)
  /* default */ double getTotalPrice() {
    return round(product.unitPrice * quantity * 100) / 100.0;
  }
}
