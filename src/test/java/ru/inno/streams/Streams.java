package ru.inno.streams;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Streams {

    public static void main(String[] args) {

        // StreamAPI Java (8+)

        Map<String, Item> items = getStream()
                .filter(i -> i.getCountOnStorage() > 0)
                .sorted((i1, i2) -> i2.getLikes() - i1.getLikes())
                .distinct()
                .map(i -> i.getLikes())
                .sorted( (l1, l2) -> l1 - l2 )
                .map(l  -> new Item(l ,0))
                .collect(Collectors.toMap(
                        item -> "Item " + item.hashCode(),
                        item -> item)
                );


        System.out.println(items.size());



        // source
        // stream api
        // terminate
    }

    private static Stream<Item> getStream() {
        List<Item> items = List.of(
                new Item(4, 10),
                new Item(5, 0),
                new Item(0, 99),
                new Item(10, 2)
        );

        return items.stream();
    }
}
