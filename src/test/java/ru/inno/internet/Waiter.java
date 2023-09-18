package ru.inno.internet;

import java.util.function.Predicate;

public class Waiter<T> {
    private final T val;
    public Waiter(T val){
        this.val = val;
    }
    public void await(long time, Predicate<T> waiting) throws InterruptedException {

        long start = System.currentTimeMillis();

        while (System.currentTimeMillis() - start <= time){
            boolean result = waiting.test(val);
            if (result) {
                return;
            }
            Thread.sleep(200L);
        }

        throw new RuntimeException("не дождались условия");
    }
}
