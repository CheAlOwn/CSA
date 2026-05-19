package com.chealown.csa.Entities;

import org.apache.poi.xwpf.usermodel.*;
import org.odftoolkit.odfdom.doc.OdfTextDocument;
import org.odftoolkit.odfdom.dom.element.text.TextPElement;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.*;
import java.util.*;

public class TemplateProcessor {

    public enum ExportFormat {
        DOCX, ODT
    }

    public static void processTemplate(String templatePath, String outputPath,
                                       Map<String, String> dataMap, ExportFormat exportFormat) throws Exception {
        TemplateFormat sourceFormat = detectFormat(templatePath);

        switch (sourceFormat) {
            case DOCX:
                processDocxWithExport(templatePath, outputPath, dataMap, exportFormat);
                break;
            case ODT:
                processOdtWithExport(templatePath, outputPath, dataMap, exportFormat);
                break;
            default:
                throw new IOException("Unsupported template format. Use .docx or .odt");
        }
    }

    public static void processTemplate(String templatePath, String outputPath,
                                       Map<String, String> dataMap) throws Exception {
        processTemplate(templatePath, outputPath, dataMap,
                detectFormat(templatePath) == TemplateFormat.DOCX ? ExportFormat.DOCX : ExportFormat.ODT);
    }

    private static TemplateFormat detectFormat(String filePath) {
        String lowerPath = filePath.toLowerCase();
        if (lowerPath.endsWith(".docx")) {
            return TemplateFormat.DOCX;
        } else if (lowerPath.endsWith(".odt")) {
            return TemplateFormat.ODT;
        }
        return TemplateFormat.UNKNOWN;
    }

    private enum TemplateFormat {
        DOCX, ODT, UNKNOWN
    }

    private static void processDocxWithExport(String templatePath, String outputPath,
                                              Map<String, String> dataMap,
                                              ExportFormat exportFormat) throws Exception {
        XWPFDocument doc;
        try (FileInputStream fis = new FileInputStream(templatePath)) {
            doc = new XWPFDocument(fis);
            processDocxDocument(doc, dataMap);
        }

        switch (exportFormat) {
            case DOCX:
                try (FileOutputStream fos = new FileOutputStream(outputPath)) {
                    doc.write(fos);
                }
                break;

            case ODT:
                File tempDocx = File.createTempFile("temp", ".docx");
                try (FileOutputStream fos = new FileOutputStream(tempDocx)) {
                    doc.write(fos);
                }
                convertDocxToOdt(tempDocx.getAbsolutePath(), outputPath, dataMap);
                tempDocx.delete();
                break;
        }
        doc.close();
    }

    private static void processOdtWithExport(String templatePath, String outputPath,
                                             Map<String, String> dataMap,
                                             ExportFormat exportFormat) throws Exception {
        OdfTextDocument doc = (OdfTextDocument) OdfTextDocument.loadDocument(templatePath);
        processOdtDocument(doc, dataMap);

        switch (exportFormat) {
            case ODT:
                doc.save(outputPath);
                break;

            case DOCX:
                File tempOdt = File.createTempFile("temp", ".odt");
                doc.save(tempOdt.getAbsolutePath());
                convertOdtToDocx(tempOdt.getAbsolutePath(), outputPath, dataMap);
                tempOdt.delete();
                break;
        }
        doc.close();
    }

    private static void processDocxDocument(XWPFDocument doc, Map<String, String> dataMap) {
        for (XWPFParagraph p : doc.getParagraphs()) {
            replaceInParagraph(p, dataMap);
        }

        for (XWPFTable table : doc.getTables()) {
            for (XWPFTableRow row : table.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    for (XWPFParagraph p : cell.getParagraphs()) {
                        replaceInParagraph(p, dataMap);
                    }
                }
            }
        }
    }

    private static void processOdtDocument(OdfTextDocument doc, Map<String, String> dataMap) throws Exception {
        NodeList paragraphs = doc.getContentRoot().getElementsByTagName("text:p");

        for (int i = 0; i < paragraphs.getLength(); i++) {
            Node paragraph = paragraphs.item(i);
            String originalText = paragraph.getTextContent();
            String replacedText = replacePlaceholders(originalText, dataMap);

            if (!originalText.equals(replacedText)) {
                while (paragraph.getFirstChild() != null) {
                    paragraph.removeChild(paragraph.getFirstChild());
                }
                paragraph.setTextContent(replacedText);
            }
        }
    }


    private static void convertDocxToOdt(String docxPath, String odtPath,
                                         Map<String, String> dataMap) throws Exception {
        OdfTextDocument odtDoc = OdfTextDocument.newTextDocument();

        try (FileInputStream fis = new FileInputStream(docxPath);
             XWPFDocument docxDoc = new XWPFDocument(fis)) {

            for (XWPFParagraph p : docxDoc.getParagraphs()) {
                String text = p.getText();
                if (text != null && !text.trim().isEmpty()) {
                    String replacedText = replacePlaceholders(text, dataMap);
                    TextPElement paragraph = odtDoc.getContentRoot().newTextPElement();
                    paragraph.setTextContent(replacedText);
                }
            }
        }

        odtDoc.save(odtPath);
        odtDoc.close();
    }

    private static void convertOdtToDocx(String odtPath, String docxPath,
                                         Map<String, String> dataMap) throws Exception {

        XWPFDocument docxDoc = new XWPFDocument();


        OdfTextDocument odtDoc = (OdfTextDocument) OdfTextDocument.loadDocument(odtPath);
        NodeList paragraphs = odtDoc.getContentRoot().getElementsByTagName("text:p");

        for (int i = 0; i < paragraphs.getLength(); i++) {
            String text = paragraphs.item(i).getTextContent();
            if (text != null && !text.trim().isEmpty()) {
                String replacedText = replacePlaceholders(text, dataMap);
                XWPFParagraph paragraph = docxDoc.createParagraph();
                XWPFRun run = paragraph.createRun();
                run.setText(replacedText);
            }
        }

        try (FileOutputStream fos = new FileOutputStream(docxPath)) {
            docxDoc.write(fos);
        }

        odtDoc.close();
        docxDoc.close();
    }

    private static void replaceInParagraph(XWPFParagraph p, Map<String, String> dataMap) {
        StringBuilder text = new StringBuilder();
        for (XWPFRun run : p.getRuns()) {
            String runText = run.getText(0);
            if (runText != null) text.append(runText);
        }

        String replaced = replacePlaceholders(text.toString(), dataMap);
        if (!text.toString().equals(replaced)) {
            while (p.getRuns().size() > 0) p.removeRun(0);
            p.createRun().setText(replaced);
        }
    }

    private static String replacePlaceholders(String text, Map<String, String> dataMap) {
        if (text == null || text.isEmpty()) return text;

        String result = text;
        for (Map.Entry<String, String> entry : dataMap.entrySet()) {
            result = result.replace(entry.getKey(), entry.getValue());
        }
        return result;
    }
}