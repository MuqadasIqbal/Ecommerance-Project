package com.example.ecommeranceapp.ModelClass;

public class Product {
    private  String name,image,status;
    private double price,discount;
    private int stock,id;

    public Product(String name, String image, String status, double price, double discount, int stock, int id) {
        this.name = name;
        this.image = image;
        this.status = status;
        this.price = price;
        this.discount = discount;
        this.stock = stock;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getStatus() {
        return status;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscount() {
        return discount;
    }

    public int getStock() {
        return stock;
    }

    public int getId() {
        return id;
    }
}
