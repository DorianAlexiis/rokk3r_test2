package com.example.uppersky_movil.test_rokk3r.data.realm.controller;

import com.example.uppersky_movil.test_rokk3r.data.realm.models.Product;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by Dorian on 26/08/2016.
 */

public class ProductManager {

    private static final String TAG = ProductManager.class.getSimpleName();

    private Realm mRealm;

    public ProductManager() {
        mRealm = Realm.getDefaultInstance();
    }

    public  boolean isEmpty() {
        return mRealm.isEmpty();
    }

    public RealmResults<Product> getAll() {
        return mRealm.where(Product.class)
                .findAll()
                .sort("name", Sort.ASCENDING);
    }

    public  RealmResults<Product> getInStock() {
        return mRealm.where(Product.class)
                .notEqualTo("stockamount",0)
                .equalTo("in_car",false)
                .findAll()
                .sort("name", Sort.ASCENDING);
    }

    public RealmResults<Product> getAllInCar() {
        return mRealm.where(Product.class)
                .notEqualTo("stockamount",0)
                .equalTo("in_car",true)
                .findAll()
                .sort("name", Sort.ASCENDING);
    }


    public boolean removeAll() {
        mRealm.beginTransaction();
        boolean status = mRealm.where(Product.class).findAll().deleteAllFromRealm();
        mRealm.commitTransaction();
        return status;
    }

    public Product add(int id, String name, int price, int stock) {
        add(id, name, price, 0, true);
        return add(id, name, price, stock, false);
    }

    public Product add(int id, String name, int price, int stock, boolean in_car) {
        Product product = null;
        try {
            mRealm.beginTransaction();
            product  = mRealm.createObject(Product.class);
            product.setId(id);
            product.setName(name);
            product.setPrice(price);
            product.setStockamount(stock);
            product.setIn_car(in_car);

            mRealm.commitTransaction();
            return product;
        } catch (Exception e) {
            mRealm.cancelTransaction();
            e.printStackTrace();
            return product;
        }
    }

    public void remoteItemProduct(Product product) {
        mRealm.beginTransaction();
        if(product.getStockamount() <  1){
            return;
        }
        product.setStockamount(
                product.getStockamount() - 1
        );
        mRealm.commitTransaction();
    }


    public void addItemStock(int id, boolean in_car) {
        Product product = mRealm.where(Product.class)
                .equalTo("id",id)
                .equalTo("in_car", in_car)
                .findFirst();

        mRealm.beginTransaction();
        product.setStockamount(product.getStockamount() + 1);
        mRealm.commitTransaction();
    }


}
