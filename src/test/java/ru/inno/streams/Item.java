package ru.inno.streams;

import java.util.Objects;

public class Item {
    private long id;
    private int likes;

    private int countOnStorage;

    public Item() {
        id = System.currentTimeMillis();
        likes = 0;
    }

    public Item(int counter) {
        this();
        countOnStorage = counter;
    }

    public Item(int likes, int counter) {
        this(counter);
        this.likes = likes;
    }

    public void  setLike(){
        this.likes += 1;
    }

    public long getId() {
        return id;
    }

    public int getLikes() {
        return likes;
    }

    public int getCountOnStorage() {
        return countOnStorage;
    }

    public void setCountOnStorage(int countOnStorage) {
        this.countOnStorage = countOnStorage;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return getId() == item.getId() && getLikes() == item.getLikes() && getCountOnStorage() == item.getCountOnStorage();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLikes(), getCountOnStorage());
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", likes=" + likes +
                ", counter=" + countOnStorage +
                '}';
    }
}
