package com.dev.illiakalu.findyourmatch;

/**
 * Created by sonicmaster on 1/19/16.
 */
public class Person {

    public Person(int id, String name){
        this.id = id;
        this.name = name;
    }

    int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    String name;

    @Override
    public String toString() {
        return this.name;
    }
}
