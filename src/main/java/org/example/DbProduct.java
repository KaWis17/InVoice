package org.example;

import java.sql.SQLException;

public interface DbProduct {
  void addProduct(Product product) throws SQLException;
}
