package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class InvoiceTest {

  @Test
  void getTotalInvoicePrice1() {
    Invoice inv1 = new Invoice();
    inv1.addRecord("Item1", 5.20, 5);
    inv1.addRecord("Item2", 2.14, 2);
    assertEquals(30.28, inv1.getTotalInvoicePrice());

    Invoice inv2 = new Invoice();
    assertEquals(0, inv2.getTotalInvoicePrice());
  }

  @Test
  void getTotalInvoicePrice2() {
    Invoice inv2 = new Invoice();
    assertEquals(0, inv2.getTotalInvoicePrice());
  }

}