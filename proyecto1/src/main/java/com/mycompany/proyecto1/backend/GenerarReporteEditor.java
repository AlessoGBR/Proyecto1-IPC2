/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1.backend;

import java.io.OutputStream;
import java.util.List;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

/**
 *
 * @author alesso
 */
public class GenerarReporteEditor {

    public GenerarReporteEditor() {

    }

    public void crearPDF(OutputStream outputStream, List<ComentarioRevista> comentariosRevistas) throws DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);
        document.open();

        // Título principal
        Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, BaseColor.BLACK);
        Font fontCabecera = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.BLACK);
        Font fontCuerpo = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);

        // Encabezado con fondo azul
        PdfPTable headerTable = new PdfPTable(1);
        headerTable.setWidthPercentage(100);
        PdfPCell cellHeader = new PdfPCell(new Phrase("Reporte de Comentarios", fontTitulo));
        cellHeader.setBackgroundColor(BaseColor.WHITE);
        cellHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellHeader.setBorder(Rectangle.NO_BORDER);
        headerTable.addCell(cellHeader);
        document.add(headerTable);

        // Espacio entre título y tabla
        document.add(Chunk.NEWLINE);

        // Tabla de detalles de la revista
        PdfPTable tableRevista = new PdfPTable(4);
        tableRevista.setWidthPercentage(100);
        tableRevista.setWidths(new int[]{1, 5, 3, 2});

        // Cabecera
        addTableHeader(tableRevista, fontCabecera, "ID", "TÍTULO", "FECHA", "VERSIÓN");

        // Ejemplo de datos de la revista (modifica estos valores según tu lógica)
        for (ComentarioRevista comentario : comentariosRevistas) {
            tableRevista.addCell(new PdfPCell(new Phrase(comentario.getIdRevista().toString(), fontCuerpo)));
            tableRevista.addCell(new PdfPCell(new Phrase(comentario.getTituloRevista(), fontCuerpo)));
            tableRevista.addCell(new PdfPCell(new Phrase(comentario.getFechaPublicacion().toString(), fontCuerpo)));
            tableRevista.addCell(new PdfPCell(new Phrase(comentario.getVersion(), fontCuerpo)));
        }
        document.add(tableRevista);

        // Espacio entre tablas
        document.add(Chunk.NEWLINE);

        // Tabla de comentarios
        PdfPTable tableComentarios = new PdfPTable(4);
        tableComentarios.setWidthPercentage(100);
        tableComentarios.setWidths(new int[]{1, 3, 7, 3});

        // Cabecera de comentarios
        addTableHeader(tableComentarios, fontCabecera, "ID", "FECHA", "COMENTARIO", "USUARIO");

        // Datos de los comentarios
        for (ComentarioRevista comentario : comentariosRevistas) {
            tableComentarios.addCell(new PdfPCell(new Phrase(String.valueOf(comentario.getIdComentario()), fontCuerpo)));
            tableComentarios.addCell(new PdfPCell(new Phrase(comentario.getFechaComentario().toString(), fontCuerpo)));
            tableComentarios.addCell(new PdfPCell(new Phrase(comentario.getTextoComentario(), fontCuerpo)));
            tableComentarios.addCell(new PdfPCell(new Phrase(comentario.getNombreUsuario(), fontCuerpo)));
        }

        document.add(tableComentarios);
        document.close();
    }

    private void addTableHeader(PdfPTable table, Font font, String... headers) {
        for (String header : headers) {
            PdfPCell headerCell = new PdfPCell(new Phrase(header, font));
            headerCell.setBackgroundColor(BaseColor.WHITE);
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(headerCell);
        }
    }
}
