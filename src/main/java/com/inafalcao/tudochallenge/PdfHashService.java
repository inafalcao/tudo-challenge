package com.inafalcao.tudochallenge;

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

    @Value("#{tudo.pdf.default.location}")
    private String pdfDir;

    @Value("#{tudo.pdf.name.convention}")
    private String pdfName;

    private String hash;

    /**
     *
     * @param byteArray
     * @return
     */
    public String hash(byte[] byteArray) {

        final String PDF_NAME = String.format(pdfName, hash);

        try(OutputStream out = new FileOutputStream(PDF_NAME)) {
            out.write(byteArray);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return hash;

    }


    public PdfHashService using(String cpf, String ip) {
        final String FILE_PATH_FORMATER = "%s/%s/%s";

        // todo: get a hash
        this.hash = "get a hash";

        final String path = String.format(FILE_PATH_FORMATER, homeDir, pdfDir, this.hash);

        // save

        return this;
    }

}
