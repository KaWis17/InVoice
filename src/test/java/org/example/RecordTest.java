package org.example;

import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RecordTest {
  @Test
  void getTotalPrice1() throws SQLException {
    Record rec = new Record("Item", 5.50, 7);
    assertEquals(38.50, rec.getTotalPrice());
  }

  @Test
  void getTotalPrice2() throws SQLException {
    Record rec = new Record("Item", 5.52, 0);
    assertEquals(0, rec.getTotalPrice());
  }
}