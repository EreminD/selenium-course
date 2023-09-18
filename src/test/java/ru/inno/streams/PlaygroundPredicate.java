package ru.inno.streams;

import java.util.List;
import java.util.function.Predicate;

public class PlaygroundPredicate {

    public static void main(String[] args) {
        List<Item> items = List.of(new Item(4, 10), new Item(5, 20), new Item(0, 99), new Item(10, 2));

        Predicate<Item> likesMoreThan4 = item -> item.getLikes() > 4;

        for (Item item : items) {
            if (likesMoreThan4.test(item)) {
                System.out.println(item);
            }
        }

    }
}
