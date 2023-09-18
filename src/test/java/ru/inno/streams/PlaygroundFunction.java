package ru.inno.streams;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class PlaygroundFunction {


    public static void main(String[] args) {

        Function<Integer, Item> generate = counter -> {
            Item item = new Item();
            item.setCountOnStorage(counter);
            return item;
        };

        Function<Integer, Item> generate1 = counter -> new Item(counter);

        List<Integer> counters = List.of(3,4,5,6);

        List<Item> items = new LinkedList<>();
        for (Integer c : counters) {
            Item item = generate.apply(c);
            items.add(item);
        }

        items.forEach(System.out::println);


    }



}
