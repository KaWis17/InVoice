package org.example;

import java.sql.SQLException;

/**
 * GRASP - Responsible for managing specific product.
 */
public class Product {
  /* default */ String name;
  /* default */ double unitPrice;

  //GRASP - Information expert
  // obvious that this class knows the most about its own
  /* default */ Product(final String name, final double unitPrice) throws SQLException {
    this.name = name;
    this.unitPrice = unitPrice;
    new ProductMariaDB().addProduct(this);
  }

  public int getDBid() throws SQLException {
    return new ProductMariaDB().getId();
  }

  public String getName() {
    return name;
  }

  public double getUnitPrice() {
    return unitPrice;
  }
}
