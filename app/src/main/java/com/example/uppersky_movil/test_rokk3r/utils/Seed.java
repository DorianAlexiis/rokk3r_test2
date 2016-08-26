package com.example.uppersky_movil.test_rokk3r.utils;

import com.example.uppersky_movil.test_rokk3r.data.realm.controller.ProductManager;
import com.example.uppersky_movil.test_rokk3r.data.realm.models.Product;

import java.util.Random;

/**
 * Created by Dorian on 26/08/2016.
 */
public class Seed {

    public static void generate(){
        Random random = new Random();
        ProductManager productManager = new ProductManager();
        // ********************** 1 ***************************************
        productManager.add(
                0,
                "iPhone 4s",
                random.nextInt(300) + 400,
                random.nextInt(40));

        // ********************** 2 ***************************************
        productManager.add(
                1,
                "Monitor",
                random.nextInt(50) + 100,
                random.nextInt(10) + 1);

        // ********************** 3 ***************************************
        productManager.add(
                2,
                "Latop HP 15",
                random.nextInt(200) + 200,
                random.nextInt(4) + 1);
        // ********************** 4 ***************************************
        productManager.add(
                3,
                "LG70",
                random.nextInt(100) + 50,
                random.nextInt(55) + 1);

        // ********************** 5 ***************************************
        productManager.add(
                4,
                "Nexus 4",
                random.nextInt(100) + 100,
                random.nextInt(100) + 1);

        // ********************** 6 ***************************************
        productManager.add(
                5,
                "Cargador Blackberry",
                random.nextInt(25) + 50,
                random.nextInt(60) + 1);

        // ********************** 7 ***************************************
        productManager.add(
                6,
                "Apple i5",
                random.nextInt(1000) + 2000,
                random.nextInt(1000) + 1);
        // ********************** 8 ***************************************
        productManager.add(
                7,
                "Cartas",
                random.nextInt(1) + 5,
                random.nextInt(3) + 1);
        // ********************** 9 ***************************************

        productManager.add(
                8,
                "Bobim de Cargo 7040",
                random.nextInt(500) + 700,
                random.nextInt(10) + 1);

        // ********************** 10 ***************************************
        productManager.add(
                9,
                "TV LG 4k",
                random.nextInt(6000) + 8000,
                random.nextInt(100) + 1);
        // ********************** 11 ***************************************
        productManager.add(
                10,
                "Harina Pan",
                random.nextInt(5) + 10,
                random.nextInt(50000) + 1);

        // ********************** 12 ***************************************
        productManager.add(
                11,
                "Boberman",
                random.nextInt(50) + 100,
                random.nextInt(50) + 1);

        // ********************** 13 ***************************************
        productManager.add(
                12,
                "Juguete Pokemon",
                random.nextInt(100) + 100,
                random.nextInt(44) + 1);

        // ********************** 14 ***************************************
        productManager.add(
                13,
                "Cargador Portatil",
                random.nextInt(5) + 20,
                random.nextInt(5) + 1);

        // ********************** 15 ***************************************
        productManager.add(
                14,
                "Flips",
                random.nextInt(1) + 4,
                random.nextInt(5000000) + 1);
    }
}
