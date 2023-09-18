package ru.inno.streams;

import java.util.List;
import java.util.function.Consumer;

public class PlaygroundConsumer {

    public static void main(String[] args) {
        List<String> strings = List.of("a", "b", "c", "d", "e");

//        strings.forEach(new PrintConsumer());

        // анонимный класс


//        Consumer<String> printConsumer = new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                System.out.println(s);
//            }
//        };

//        strings.forEach(printConsumer);

        Consumer<String> printConsumer = s ->  System.out.println(s); ;

        printConsumer = myString -> {
            System.out.println("делаем дело");
            System.out.println(myString);
        };

        strings.forEach(printConsumer);


        List<Item> items = List.of(new Item(), new Item(), new Item(), new Item());

        items.forEach(Item::setLike);

        items.forEach(i -> System.out.println(i));
    }


}
