package org.example;

import java.sql.SQLException;

public interface DbRecord {
  void addRecord(Record record) throws SQLException;
}
