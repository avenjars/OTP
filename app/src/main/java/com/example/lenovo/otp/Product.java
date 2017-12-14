package com.example.lenovo.otp;

/**
 * Created by NgocTri on 11/15/2015.
 */
public class Product {
    private int id;
    private String name;
    private String price;
    private String description;
    private String Phone;

    //Constructor

    public Product(int id, String name, String price, String description, String Phone) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.Phone = Phone;
    }

    //Setter, getter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String description) {
        this.Phone = Phone;
    }
}
