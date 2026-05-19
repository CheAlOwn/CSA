package com.chealown.csa.Entities;

import org.apache.tika.Tika;

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

    public static List<String> extractPlaceholders(String filePath) throws IOException {
        Set<String> placeholders = new LinkedHashSet<>();

        try (InputStream input = new FileInputStream(filePath)) {
            String text = tika.parseToString(input);
            findPlaceholders(text, placeholders);
        } catch (Exception e) {
            throw new IOException("Failed to parse file: " + filePath, e);
        }

        return new ArrayList<>(placeholders);
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