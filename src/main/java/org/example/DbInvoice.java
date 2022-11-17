package org.example;

import java.sql.SQLException;

public interface DbInvoice {
  void addInvoice(Invoice invoice) throws SQLException;
}
