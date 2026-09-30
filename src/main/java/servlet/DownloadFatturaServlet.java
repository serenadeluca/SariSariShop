package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import model.Ordine;
import model.DettaglioOrdine;

public class DownloadFatturaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unchecked")
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        // Recupera i dati dalla sessione
        Ordine ordine = (Ordine) request.getSession().getAttribute("ordineFattura");
        List<DettaglioOrdine> righe = (List<DettaglioOrdine>) request.getSession().getAttribute("righeFattura");

        if (ordine == null || righe == null) {
            response.sendError(400, "Nessuna fattura da scaricare");
            return;
        }

        // Impostazioni risposta
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition",
                "attachment; filename=fattura_" + ordine.getIdOrdine() + ".pdf");

        // Creazione PDF
        Document pdf = new Document(PageSize.A4, 40, 40, 40, 40);
        PdfWriter.getInstance(pdf, response.getOutputStream());

        pdf.open();

        // ======= TITOLO =======
        Font titoloFont = new Font(Font.HELVETICA, 22, Font.BOLD);
        Paragraph titolo = new Paragraph("FATTURA ORDINE #" + ordine.getIdOrdine(), titoloFont);
        titolo.setAlignment(Element.ALIGN_CENTER);
        titolo.setSpacingAfter(20);
        pdf.add(titolo);

        // ======= INFO ORDINE =======
        Font infoFont = new Font(Font.HELVETICA, 12);
        Paragraph info = new Paragraph(
                "Data ordine: " + ordine.getDataOrdine() + "\n" +
                "Totale ordine: € " + ordine.getTotaleOrdine(),
                infoFont
        );
        info.setSpacingAfter(20);
        pdf.add(info);

        // ======= TABELLA DETTAGLI =======
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10);
        table.setSpacingAfter(10);
        table.setWidths(new float[]{50, 25, 25});

        // Header
        Font headerFont = new Font(Font.HELVETICA, 12, Font.BOLD);
        PdfPCell h1 = new PdfPCell(new Phrase("Prodotto", headerFont));
        PdfPCell h2 = new PdfPCell(new Phrase("Quantità", headerFont));
        PdfPCell h3 = new PdfPCell(new Phrase("Prezzo Unitario", headerFont));

        h1.setHorizontalAlignment(Element.ALIGN_CENTER);
        h2.setHorizontalAlignment(Element.ALIGN_CENTER);
        h3.setHorizontalAlignment(Element.ALIGN_CENTER);

        table.addCell(h1);
        table.addCell(h2);
        table.addCell(h3);

        // Righe
        Font rowFont = new Font(Font.HELVETICA, 12);

        for (DettaglioOrdine r : righe) {
            table.addCell(new Phrase(r.getNomeProdotto(), rowFont));
            table.addCell(new Phrase(String.valueOf(r.getQuantita()), rowFont));
            table.addCell(new Phrase("€ " + r.getPrezzoUnitario(), rowFont));
        }

        pdf.add(table);

        // ======= TOTALE =======
        Font totaleFont = new Font(Font.HELVETICA, 14, Font.BOLD);
        Paragraph totale = new Paragraph("Totale: € " + ordine.getTotaleOrdine(), totaleFont);
        totale.setAlignment(Element.ALIGN_RIGHT);
        totale.setSpacingBefore(20);
        pdf.add(totale);

        pdf.close();
    }
}
