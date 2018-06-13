package com.inafalcao.tudochallenge.pdf;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * This class is responsible for converting the file byte array to
 * a pdf file, putting a hash to its footer and saving it
 * in the appropriated location.
 * I used the fluent pattern to make the method calls prettier.
 */
@Service
public class PdfHashService {

    @Value("#{systemProperties['user.dir']}")
    private String homeDir;

    @Value("${tudo.pdf.default.location}")
    private String pdfDir;

    @Value("${tudo.pdf.name.convention}")
    private String pdfName;

    private String hash;

    public PdfHashService pdf(InputStream in) {

        final String PDF_NAME = String.format(pdfName, hash);

        // Taking advantage of the Closeable interface :)
        try(OutputStream out = new FileOutputStream(PDF_NAME)) {

            IOUtils.copy(in, out);

        } catch (IOException e) {
            e.printStackTrace(); // todo:
        }

        return this;

    }

    public String using(String key) {
        final String FILE_PATH_FORMATTER = "%s/%s/%s";

        // todo: get a hash
        this.hash = "get a hash";

        final String path = String.format(FILE_PATH_FORMATTER, homeDir, pdfDir, this.hash);

        // save
        return this.hash;
    }

}
