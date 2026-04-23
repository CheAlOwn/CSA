package com.chealown.csa.Entities;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.microsoft.ooxml.OOXMLParser;
import org.apache.tika.parser.odf.OpenDocumentParser;
import org.apache.tika.sax.ToTextContentHandler;
import org.xml.sax.SAXException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateParser {
    private static final Pattern PLACEHOLDER_PATTERN =
            Pattern.compile("\\{\\{(\\w+)\\}\\}|\\$\\{(\\w+)\\}");

    private static final Tika tika = new Tika();

    /**
     * Ультра-простое извлечение через Tika
     */
    public static List<String> extractPlaceholders(String filePath) throws IOException {
        Set<String> placeholders = new LinkedHashSet<>();

        try (InputStream input = new FileInputStream(filePath)) {
            // Tika сам определит тип файла и извлечет текст!
            String text = tika.parseToString(input);
            findPlaceholders(text, placeholders);
        } catch (Exception e) {
            throw new IOException("Failed to parse file: " + filePath, e);
        }

        return new ArrayList<>(placeholders);
    }

    /**
     * Более контролируемый вариант с выбором парсера
     */
    public static List<String> extractPlaceholdersAdvanced(String filePath) throws IOException {
        Set<String> placeholders = new LinkedHashSet<>();
        String text = extractTextFromFile(filePath);
        findPlaceholders(text, placeholders);
        return new ArrayList<>(placeholders);
    }

    private static String extractTextFromFile(String filePath) throws IOException {
        String lowerPath = filePath.toLowerCase();

        try (InputStream input = new FileInputStream(filePath)) {
            if (lowerPath.endsWith(".docx")) {
                return extractWithParser(input, new OOXMLParser());
            } else if (lowerPath.endsWith(".odt")) {
                return extractWithParser(input, new OpenDocumentParser());
            } else {
                throw new IOException("Unsupported format");
            }
        } catch (SAXException | TikaException e) {
            throw new IOException("Failed to extract text", e);
        }
    }

    private static String extractWithParser(InputStream input, org.apache.tika.parser.Parser parser)
            throws IOException, SAXException, TikaException {

        ToTextContentHandler handler = new ToTextContentHandler();
        Metadata metadata = new Metadata();
        ParseContext context = new ParseContext();

        parser.parse(input, handler, metadata, context);
        return handler.toString();
    }

    private static void findPlaceholders(String text, Set<String> placeholders) {
        Matcher matcher = PLACEHOLDER_PATTERN.matcher(text);
        while (matcher.find()) {
            String placeholder = matcher.group(1) != null ?
                    "{{" + matcher.group(1) + "}}" :
                    "${" + matcher.group(2) + "}";
            placeholders.add(placeholder);
        }
    }
}