package ru.inno.streams;

import java.util.Optional;

public class PlaygroundSupplier {

    public static void main(String[] args) {
//        Supplier<Long> supplier = () -> System.currentTimeMillis();
//
//        Long id = getId().orElseGet(supplier);
//        System.out.println(id);

        Long id = getId().orElseGet(() -> System.currentTimeMillis());
        System.out.println(id);
    }

    private static Optional<Long> getId(){
        return Optional.ofNullable(null);
    }
}
