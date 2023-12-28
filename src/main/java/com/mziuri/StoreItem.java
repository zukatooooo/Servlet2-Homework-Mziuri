package com.mziuri;

public class StoreItem {
    private String name;
    private float price;

    public StoreItem(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }
}
