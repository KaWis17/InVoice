package org.example;

import java.sql.*;

public class ProductMariaDB implements DbProduct {
  @Override
  public void addProduct(Product product) throws SQLException {
    Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/invoice", "root", "2104");
    Statement statement = connection.createStatement();
    String query = "INSERT INTO product(Name, UnitPrice) VALUES ('" + product.getName() + "','" + product.getUnitPrice() + "')";
    statement.executeQuery(query);
  }

  public int getId() throws SQLException {
    Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/invoice", "root", "2104");
    Statement statement = connection.createStatement();
    String query = "SELECT MIN(productID) FROM product";
    ResultSet set = statement.executeQuery(query);
    if (set.next())
      return set.getInt(1);

    return 0;
  }
}
