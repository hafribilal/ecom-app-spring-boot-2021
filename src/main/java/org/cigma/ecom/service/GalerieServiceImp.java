package org.cigma.ecom.service;

import org.cigma.ecom.repository.GalerieRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class GalerieServiceImp implements IGalerieService {
    @Autowired
    GalerieRepository repository;

//    public Image generateImage(Article article) {
//        new ByteArrayInputStream(article.getThumbnail());
//        StreamResource sr = new StreamResource();
//        sr.setContentType("image/png");
//        Image image = new Image();
//        return image;
//    }

    /**
     * Read file into byte array
     *
     * @param imagePath path to a file
     * @return byte array out of file
     * @throws IOException File not found or could not be read
     */
    public static byte[] getBytesFromFile(String imagePath) throws IOException {
        File file = new File(imagePath);
        return Files.readAllBytes(file.toPath());
    }
}
