package ru.inno.labirint.block;

public enum Chips {
    AVAILABLE("в наличии"),
    PREORDER("предзаказ"),
    AWAITING("ожидаются"),
    NOT_AVAILABLE("нет в продаже");

    private final String text;

    Chips(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
