package com.inafalcao.tudochallenge.pdf;

import com.inafalcao.tudochallenge.commons.Files;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Paths;

/**
 * This class is responsible for converting the file byte array to
 * a pdf file, putting a hash to its footer and saving it
 * in the appropriated location.
 * I used the fluent pattern to make the method calls prettier.
 */
@Service
public class PdfHashService {

    @Value("#{systemProperties['user.home']}")
    private String homeDir;

    @Value("${pdf.default.location}")
    private String pdfDir;

    @Value("${pdf.name.convention}")
    private String pdfName;

    private String hash;

    public String pdf(InputStream in) {

        final String PATH_FORMATTER = "%s/%s";
        final String PDF_PATH = String.format(PATH_FORMATTER, homeDir, pdfDir);

        Files.createIfNotExists(Paths.get(PDF_PATH));

        final String PDF_NAME = PDF_PATH + this.hash + ".pdf";

        PdfHelper.markHashInPdf(PDF_NAME, this.hash, in);

        return this.hash;

    }

    public PdfHashService using(String hash) {
        this.hash = hash;
        return this;
    }

}
