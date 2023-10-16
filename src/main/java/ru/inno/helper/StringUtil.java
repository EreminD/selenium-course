package ru.inno.helper;

public class StringUtil {

    // "диМа" -> "Дима"
    public String normalizeName(String text){
        String tail = text.substring(1);
        String head = text.substring(0,1);
        return head.toUpperCase() + tail.toLowerCase();
    }

}
