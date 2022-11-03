package org.example;

/**
 * GRASP - Responsible for managing specific product.
 */
public class Product {
  /* default */ String name;
  /* default */ double unitPrice;

  //GRASP - Information expert
  // obvious that this class knows the most about its own
  /* default */ Product(final String name, final double unitPrice) {
    this.name = name;
    this.unitPrice = unitPrice;
  }
}
