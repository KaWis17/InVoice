package org.example;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PdfGeneration {
    static Document document;
    Invoice invoice;
    PdfGeneration(Invoice invoice) throws FileNotFoundException, DocumentException {
        this.invoice = invoice;
        document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("C:/Users/krzym/Desktop/invoice.pdf"));

        document.open();

        Paragraph title = new Paragraph("InVoice", FontFactory.getFont(FontFactory.COURIER, 25, BaseColor.BLACK));
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(new Phrase("\n"));

        Paragraph number = new Paragraph("No: " + invoice.invoiceNumber, FontFactory.getFont(FontFactory.COURIER, 15, BaseColor.BLACK));

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        Paragraph datePlace = new Paragraph(dtf.format(now) + ", " + invoice.place, FontFactory.getFont(FontFactory.COURIER, 15, BaseColor.BLACK));

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
        table.getDefaultCell().setMinimumHeight(25);
        table.addCell(number);
        table.addCell(datePlace);

        document.add(table);

    }

    void writingToPDF(JTable table) throws DocumentException, IOException {

        PdfPTable personTable = new PdfPTable(2);
        personTable.setWidthPercentage(100);
        personTable.addCell(new personParagraph("Issuer", invoice.issuer));
        personTable.addCell(new personParagraph("Customer", invoice.client));
        document.add(personTable);

        document.add(new Phrase("\n"));

        //https://stackoverflow.com/questions/28448377/fitting-a-jtable-in-an-itext-pdf-document
        PdfPTable pdfTable = new PdfPTable(table.getColumnCount());
        for (int i = 0; i < table.getColumnCount(); i++) {

            Paragraph paragraph = new Paragraph(table.getColumnName(i), FontFactory.getFont(FontFactory.COURIER, 15, BaseColor.BLACK));
            pdfTable.addCell(paragraph);
        }
        for (int rows = 0; rows < table.getRowCount(); rows++) {
            for (int cols = 0; cols < table.getColumnCount(); cols++) {
                Paragraph paragraph = new Paragraph(table.getModel().getValueAt(rows, cols).toString(), FontFactory.getFont(FontFactory.COURIER, 15, BaseColor.BLACK));
                pdfTable.addCell(paragraph);

            }
        }
        //https://stackoverflow.com/questions/28448377/fitting-a-jtable-in-an-itext-pdf-document
        pdfTable.setWidthPercentage(100);
        document.add(pdfTable);

        document.close();
    }

}

class personParagraph extends Paragraph{
    personParagraph(String text, Person person){
        super(text + "'s Info: \n"+
                person.getName() +
                person.getSurname() + "\n" +
                person.getCountry() + ", " +
                person.getCity() + "\n" +
                person.getAddress() + "\n" +
                person.getMail() + "\n" +
                person.getPhoneNumber(), FontFactory.getFont(FontFactory.COURIER, 15, BaseColor.BLACK));
    }
}
