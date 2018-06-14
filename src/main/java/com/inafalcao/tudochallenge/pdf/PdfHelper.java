package com.inafalcao.tudochallenge.pdf;

import com.inafalcao.tudochallenge.exception.TudoChallengeException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class PdfHelper {

    public static void markHashInPdf(String pdfName, String hash, InputStream in) throws TudoChallengeException {
        // Taking advantage of the Closeable interface :)
        try(OutputStream out = new FileOutputStream(pdfName)) {

            IOUtils.copy(in, out);
            // todo: need to fix something here. gotta sleep.
            mark(pdfName, hash);

        } catch (IOException | DocumentException e) {
            // todo: set reason.
            // error saving hash inside pdf file.
            throw new TudoChallengeException();
        }
    }

    private static void mark(String pdf, String hash) throws IOException, DocumentException {

        PdfReader reader = new PdfReader(pdf);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(pdf));
        Phrase footer = new Phrase(hash, new Font(Font.FontFamily.HELVETICA, 12));

        for (int i = 1; i <= reader.getNumberOfPages(); i++) {

            float x = reader.getPageSize(i).getWidth() / 2;
            float y = reader.getPageSize(i).getBottom(20);

            ColumnText.showTextAligned(
                    stamper.getOverContent(i), Element.ALIGN_CENTER,
                    footer, x, y, 0);
        }

        stamper.close(); // there is no closeable interface for them :(
        reader.close();
    }






}
