package org.example;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JTable;

/**
 * GRASP - Responsible for managing PDF generation process.
 */
public class PdfGeneration {
  /* default */ Document document;
  /* default */ Invoice invoice;

  //GRASP - Information expert
  // obvious that this class knows the most about its own
  /* default */ PdfGeneration(final Invoice invoice) throws IOException, DocumentException {
    this.invoice = invoice;
    document = new Document();
    PdfWriter.getInstance(document, Files.newOutputStream(Paths.get("invoice.pdf")));

    document.open();

    final Paragraph title = new Paragraph("InVoice",
            FontFactory.getFont(FontFactory.COURIER, 25, BaseColor.BLACK));
    title.setAlignment(Element.ALIGN_CENTER);
    document.add(title);
    document.add(new Phrase("\n"));

    final PdfPTable table = new PdfPTable(2);
    table.setWidthPercentage(100);
    table.getDefaultCell().setBorder(Rectangle.BOTTOM);
    table.getDefaultCell().setMinimumHeight(25);

    final Paragraph number = new Paragraph("No: "
            + invoice.getInvoiceNumber(),
            FontFactory.getFont(FontFactory.COURIER, 15, BaseColor.BLACK));

    final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    final LocalDateTime now = LocalDateTime.now();
    final Paragraph datePlace = new Paragraph(dtf.format(now)
            + ", "
            + invoice.getPlace(), FontFactory.getFont(FontFactory.COURIER, 15, BaseColor.BLACK));
    table.addCell(number);
    table.addCell(datePlace);

    document.add(table);

  }

  //GRASP - Information expert - class poses the most information about document
  /* default */ void writingToPdf(final JTable table) throws DocumentException, IOException {

    final PdfPTable personTable = new PdfPTable(2);
    personTable.setWidthPercentage(100);
    //GRASP - Creator
    // instances of PdfGeneration closely use instances of PersonParagraph
    personTable.addCell(new PersonParagraph("Issuer", invoice.issuer));
    //GRASP - Creator
    // instances of PdfGeneration closely use instances of PersonParagraph
    personTable.addCell(new PersonParagraph("Customer", invoice.client));
    document.add(personTable);

    document.add(new Phrase("\n"));

    final PdfPTable pdfTable = new PdfPTable(table.getColumnCount());
    for (int i = 0; i < table.getColumnCount(); i++) {
      pdfTable.addCell(new Paragraph(table.getColumnName(i),
              FontFactory.getFont(FontFactory.COURIER, 15, BaseColor.BLACK)));
    }
    for (int rows = 0; rows < table.getRowCount(); rows++) {
      for (int cols = 0; cols < table.getColumnCount(); cols++) {
        pdfTable.addCell(new Paragraph(table.getModel().getValueAt(rows, cols).toString(),
                FontFactory.getFont(FontFactory.COURIER, 15, BaseColor.BLACK)));

      }
    }

    pdfTable.setWidthPercentage(100);
    document.add(pdfTable);

    final Paragraph total = new Paragraph("TOTAL: "
            + invoice.getTotalInvoicePrice()
            + "PLN", FontFactory.getFont(FontFactory.COURIER, 20, BaseColor.BLACK));
    total.setAlignment(Element.ALIGN_RIGHT);
    document.add(total);

    document.close();
  }
}
