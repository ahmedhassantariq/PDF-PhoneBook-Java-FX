package Functionality;

import Entities.Person;
import com.dlsc.pdfviewfx.PDFView;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class PDFDocument {
    PdfWriter pdfWriter;
    PdfDocument pdfDocument;
    static PDFView pdfDisplayer;
    LocalTime localTime;
    LocalDate localDate;
    PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
    public PDFDocument() throws IOException, SQLException {
        localTime = LocalTime.now();
        localDate = LocalDate.now();
        UUID uuid = UUID.randomUUID();
        String fileName = uuid.toString().substring(0,8)+".pdf";
        String destination = ""+fileName;
        pdfWriter = new PdfWriter(destination);
        pdfDocument = new PdfDocument(pdfWriter);
        com.itextpdf.layout.Document document = new com.itextpdf.layout.Document(pdfDocument);
        pdfDocument.setDefaultPageSize(PageSize.A4);



        float itemInfoColWidth[] = {280,50,100,100};
        Table itemInfoTable = new Table(itemInfoColWidth);
        itemInfoTable.setHorizontalAlignment(HorizontalAlignment.CENTER);
        itemInfoTable.addCell(new Cell().add("Name").setBackgroundColor(Color.LIGHT_GRAY).setTextAlignment(TextAlignment.LEFT));
        itemInfoTable.addCell(new Cell().add("Phone").setBackgroundColor(Color.LIGHT_GRAY).setTextAlignment(TextAlignment.CENTER));
        itemInfoTable.addCell(new Cell().add("Area-Code").setBackgroundColor(Color.LIGHT_GRAY).setTextAlignment(TextAlignment.RIGHT));
        itemInfoTable.addCell(new Cell().add("Address").setBackgroundColor(Color.LIGHT_GRAY).setTextAlignment(TextAlignment.RIGHT));

        for(int i = 0 ;i<Controller.personArrayList.size();i++) {
            Person person = Controller.personArrayList.get(i);
            itemInfoTable.addCell(new Cell().add(person.getFirstName()+" "+person.getMiddleName()+" "+person.getLastName()).setBackgroundColor(Color.WHITE).setTextAlignment(TextAlignment.LEFT));
            itemInfoTable.addCell(new Cell().add(person.getPhoneNo()).setBackgroundColor(Color.WHITE).setTextAlignment(TextAlignment.LEFT));
            itemInfoTable.addCell(new Cell().add(person.getAreaCode()).setBackgroundColor(Color.WHITE).setTextAlignment(TextAlignment.LEFT));
            itemInfoTable.addCell(new Cell().add(person.getAddress()).setBackgroundColor(Color.WHITE).setTextAlignment(TextAlignment.LEFT));
        }




        document.add(itemInfoTable);
        document.close();
        show(destination,fileName);
    }

    public static void show(String destination,String fileName) throws IOException, SQLException {
        //New Invoice
        Stage stage = new Stage();
        File file = new File(destination);
        pdfDisplayer = new PDFView();
        pdfDisplayer.setShowAll(false);
        pdfDisplayer.setShowToolBar(false);
        pdfDisplayer.setShowThumbnails(false);
        pdfDisplayer.setShowSearchResults(false);
        pdfDisplayer.load(file);
        stage.setScene(new Scene(pdfDisplayer,500,700));
        stage.show();

    }

    public static void printPdfFile(String destination) throws PrinterException {
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        PrintRequestAttributeSet attrs = new HashPrintRequestAttributeSet();
        attrs.add(new Copies(1));
        printerJob.print();
    }
}
