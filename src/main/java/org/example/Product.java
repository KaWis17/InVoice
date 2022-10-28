package org.example;

public class Product {
    String name;
    double unitPrice, totalPrice;
    Product(String name, double unitPrice, double totalPrice){
        this.name = name;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
    }

    public String getName() {
        return name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
