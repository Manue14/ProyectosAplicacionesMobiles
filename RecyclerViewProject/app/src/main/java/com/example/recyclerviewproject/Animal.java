package com.example.recyclerviewproject;

public class Animal {
    private String name;
    private String text;
    private int img_resource_id;

    public Animal() {}

    public Animal(String name, String text, int img_resource_id) {
        this.name = name;
        this.text = text;
        this.img_resource_id = img_resource_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg_resource_id() {
        return img_resource_id;
    }

    public void setImg_resource_id(int img_resource_id) {
        this.img_resource_id = img_resource_id;
    }
}