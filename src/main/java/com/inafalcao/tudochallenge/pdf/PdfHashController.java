package com.inafalcao.tudochallenge.pdf;

import com.inafalcao.tudochallenge.exception.TudoChallengeException;
import com.inafalcao.tudochallenge.hash.Hasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

import static com.inafalcao.tudochallenge.commons.Dates.today;

@RestController
public class PdfHashController {

    private PdfHashService hash;

    public PdfHashController(PdfHashService hash) {
        this.hash = hash;
    }

    @PostMapping(
            path = "/pdf-hash/{cpf}"
    )
    public String hashPdf(
            @RequestParam("file") MultipartFile file,
            @PathVariable String cpf,
            HttpServletRequest request) throws TudoChallengeException { // todo: make error handler

        // todo: validations (cpf, file empty)

        InputStream pdf;
        try {

            pdf = file.getInputStream();

        } catch(IOException ex) {
            throw new TudoChallengeException(); // todo: set reason. error reading uploaded file input stream.
        }

        final String ip = request.getRemoteAddr();
        final String hashKey  = Hasher.from(ip, today(), cpf); // todo: compose with a concat function.
        return hash.using(hashKey).pdf(pdf);

    }

}
