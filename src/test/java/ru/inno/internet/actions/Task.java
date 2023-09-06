package ru.inno.internet.actions;

import java.util.Objects;

public class Task {
    int id;
    String title;
    boolean completed;
    String url;
    Object order;

    public Task() {
    }

    public Task(int id, String title, boolean completed, String url, Object order) {
        this.id = id;
        this.title = title;
        this.completed = completed;
        this.url = url;
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getOrder() {
        return order;
    }

    public void setOrder(Object order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task task)) return false;
        return getId() == task.getId() && isCompleted() == task.isCompleted() && Objects.equals(getTitle(), task.getTitle()) && Objects.equals(getUrl(), task.getUrl()) && Objects.equals(getOrder(), task.getOrder());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), isCompleted(), getUrl(), getOrder());
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                ", url='" + url + '\'' +
                ", order=" + order +
                '}';
    }
}
