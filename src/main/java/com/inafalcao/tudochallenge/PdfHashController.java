package com.inafalcao.tudochallenge;

import org.springframework.web.bind.annotation.*;

@RestController
public class PdfHashController {

    private PdfHashService pdfHasher;

    @PostMapping(
            path = "/pdf-hash/{cpf}"
    )
    public String hash(@RequestBody byte[] pdf, @PathVariable String cpf) {
        try {

            // TODO: get ip.
            // TODO: byte array empty validation.
            // TODO: cpf validation.
            // TODO: model api response errors.

            return pdfHasher.using(cpf, "ip").hash(pdf);

        } catch(Exception ex) {
            // TODO
            return "";
        }

    }

}
