package com.cue.GestordeTareas.domain.entities;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name = "tasks", schema = "public")
public class Task {//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;

    private Task(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
    }

    public Task(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public Task() {
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

    public static class Builder {
        private int id;
        private String title;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Task build() {
            return new Task(this);
        }
    }

}
