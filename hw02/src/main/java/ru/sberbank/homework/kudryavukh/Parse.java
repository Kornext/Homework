package ru.sberbank.homework.kudryavukh;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parse {

    private static long decodeOnDecimal(String text) {
        if (text.charAt(text.length() - 1) == 'l') {
            text = text.substring(0, text.length() - 1);
        }
        if (text.startsWith("0b") || text.startsWith("+0b") || text.startsWith("-0b")) {
            text = text.replaceFirst("0b", "");
            return Long.parseLong(text, 2);
        } else {
            return Long.decode(text);
        }
    }

    private static boolean checkWithRegExp(String userNameString, String regExp) {
        Pattern pat = Pattern.compile(regExp);
        Matcher mat = pat.matcher(userNameString);
        return mat.matches();
    }

    public String output(String value) {
        String pattern = "###.##";
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
        otherSymbols.setDecimalSeparator('.');
        DecimalFormat decimformat = new DecimalFormat(pattern, otherSymbols);
        return decimformat.format(Double.parseDouble(value));
    }

    public static String validationAndParser(String word) {

        word = word.toLowerCase();
        double number;
        if (checkWithRegExp(word, "_{1}.*|.*_{1}|.*_{1}\\..*|.*\\._{1}.*|0b_.*|0_b.*|0x_.*|0_x.*|.*_l|.*_f")) {
            return null; //Проверка валидности нижнего подчеркивания
        } else {
            word = underScoreRemove(word);
            if (checkWithRegExp(word, "(-?|\\+?)[0-9]+\\.[0-9]+d?")) { //проверка double
                number = Double.parseDouble(word);
                return String.valueOf(number);
            } else if (checkWithRegExp(word, "(-?|\\+?)[0-9]*\\.[0-9]+f|(-?|\\+?)[0-9]*f")) {
                number = Float.parseFloat(word.substring(0, word.length() - 1)); //Проверка float литерала
                return String.valueOf(number);
            } else if (checkWithRegExp(word,
                    "(-?|\\+?)0b[0-1]+l?|(-?|\\+?)0[0-7]+l?|(-?|\\+?)0x[0-9abcdef]+l?|(-?|\\+?)[1-9][0-9]*l?|0")) {
                number = decodeOnDecimal(word); //проверка int и long во всех системах счисления
                return String.valueOf(number);
            } else if (checkWithRegExp(word, "[+*/-]")) { //Проверка на знак
                return word;
            } else if (word.equals("quit")) {
                System.exit(0);
            }
            return null;
        }
    }

    private static String underScoreRemove(String word) {
        word = word.replaceAll("_", "");
        return word;
    }
}
