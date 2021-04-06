package com.example.restservice;

public class warehouseItem {
    private final String name;
    private int quantity = 0;

    public warehouseItem(String IteName){
        name = IteName;
    }

    public int getItemQauntity() {
        return quantity;
    }

    public void setItemQauntity(int newVal) {
        this.quantity = newVal;
    }

    public void increaseItemQauntity(int increment) {
        this.quantity += increment;
    }

    public void decreaseItemQauntity(int increment) {
        this.quantity -= increment;
    }
}
