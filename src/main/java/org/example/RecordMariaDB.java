package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class RecordMariaDB implements DbRecord {
  @Override
  public void addRecord(Record record) throws SQLException {
    Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/invoice", "root", "2104");
    Statement statement = connection.createStatement();
    String query = "INSERT INTO record(Quantity, ProductID) VALUES(" + record.getQuantity() + ", '" + record.getProductId() + "')";
    statement.executeQuery(query);
  }
}
