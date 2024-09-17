/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1.backend;

import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author alesso
 */
public class GuardarImagen {

    private static final String CARPETA_BASE = "imagenes"; // Carpeta base para almacenar imágenes

    public String guardarImagen(Part filePart) throws IOException {
        if (filePart == null || filePart.getSize() == 0) {
            throw new IllegalArgumentException("No se ha seleccionado ningún archivo.");
        }

        String fileName = extractFileName(filePart);
        if (fileName == null || fileName.isEmpty()) {
            throw new IllegalArgumentException("El nombre del archivo no puede estar vacío.");
        }

        Path carpetaImagenes = Path.of(System.getProperty("user.dir"), CARPETA_BASE);

        Files.createDirectories(carpetaImagenes);

        Path filePath = carpetaImagenes.resolve(fileName);

        try (InputStream input = filePart.getInputStream()) {
            Files.copy(input, filePath, StandardCopyOption.REPLACE_EXISTING);
        }

        return filePath.toString();
    }

    private String extractFileName(Part filePart) {
        String contentDisposition = filePart.getHeader("Content-Disposition");
        for (String part : contentDisposition.split(";")) {
            if (part.trim().startsWith("filename")) {
                String fileName = part.substring(part.indexOf('=') + 1).trim().replace("\"", "");
                return fileName;
            }
        }
        return null;
    }
}
