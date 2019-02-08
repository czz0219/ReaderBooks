package com.beans;

/**
 * Hello world!
 *
 */
public class App 
{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "App{" +
                "name='" + name + '\'' +
                '}';
    }
}
