package com.example.uppersky_movil.test_rokk3r.data.realm.models;


import io.realm.RealmObject;

/**
 * Created by Dorian on 26/08/2016.
 */
public class Product extends RealmObject {

    int id;
    int price;
    String name;
    int stockamount;
    boolean in_car;

    public Product(){

    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStockamount() {
        return stockamount;
    }

    public void setStockamount(int stockamount) {
        this.stockamount = stockamount;
    }

    public boolean isIn_car() {
        return in_car;
    }

    public void setIn_car(boolean in_car) {
        this.in_car = in_car;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
