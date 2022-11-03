package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RecordTest {
  @Test
  void getTotalPrice1() {
    Record rec = new Record("Item", 5.50, 7);
    assertEquals(38.50, rec.getTotalPrice());
  }

  @Test
  void getTotalPrice2() {
    Record rec = new Record("Item", 5.52, 0);
    assertEquals(0, rec.getTotalPrice());
  }
}