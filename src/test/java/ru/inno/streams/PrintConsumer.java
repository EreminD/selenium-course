package ru.inno.streams;

import java.util.function.Consumer;

public class PrintConsumer implements Consumer<String> {
    @Override
    public void accept(String s) {
        System.out.println(s);
    }
}
