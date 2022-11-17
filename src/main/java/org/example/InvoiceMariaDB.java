package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InvoiceMariaDB implements DbInvoice {

  public void addInvoice(Invoice invoice) throws SQLException {
    Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/invoice", "root", "2104");
    Statement statement = connection.createStatement();
    String query = "INSERT INTO invoice(InvoiceNumber, Place) VALUES('" + invoice.getInvoiceNumber() + "','" + invoice.getPlace() + "')";
    statement.executeQuery(query);
  }
}
