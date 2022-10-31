package com.api.cadastro.metodoUtil;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

public class Slug {
    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    public static String slug (String title) {
        String nowhitespace = WHITESPACE.matcher(title).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }
}
