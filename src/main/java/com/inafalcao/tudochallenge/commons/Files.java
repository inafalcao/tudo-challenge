package com.inafalcao.tudochallenge.commons;

import java.io.File;
import java.nio.file.Path;

public class Files {

    public static void createIfNotExists(Path path) {
        if(!java.nio.file.Files.exists(path)) {
            new File(path.toString()).mkdirs();
        }
    }

}
