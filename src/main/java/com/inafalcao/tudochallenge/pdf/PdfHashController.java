package com.inafalcao.tudochallenge.pdf;

import com.inafalcao.tudochallenge.hash.Hasher;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
public class PdfHashController {

    private PdfHashService hash;

    @PostMapping(
            path = "/pdf-hash/{cpf}"
    )
    public String hashPdf(
            @RequestParam("file") MultipartFile file,
            @PathVariable String cpf,
            HttpServletRequest request) {

        try {

            final String ip = request.getRemoteAddr();
            InputStream pdf = file.getInputStream();
            hash.pdf(pdf).using(Hasher.from(ip, today(), cpf));

        } catch(Exception ex) {
            // TODO
            return "";
        }

        return "";

    }

    private static String today() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMYYYHH");
        return today.format(formatter);
    }

}
