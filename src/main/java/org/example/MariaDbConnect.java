
// WORK IN PROGRESS

package org.example;

import java.sql.*;

public class MariaDbConnect {
  static Connection connection;

  public MariaDbConnect() throws SQLException {
    connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/invoice", "root", "2104");
  }

  static void addInvoice(Invoice invoice) throws SQLException {
    Statement statement = connection.createStatement();
    String query = "INSERT INTO invoice VALUES()";
    statement.executeQuery(query);
  }

  static void addProduct(String names, double unitPrice) throws SQLException {
    Statement statement = connection.createStatement();

    String query = "INSERT INTO product(Name, UnitPrice) VALUES('', UnitPrice)";

    statement.executeQuery(query);
  }

}
