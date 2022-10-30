package org.example;

public class Record {
    Product product;
    int quantity;
    Record(String name, double unitPrice, int quantity){
        this.quantity = quantity;
        product = new Product(name, unitPrice);

    }

    double getTotalPrice(){
        return product.unitPrice * quantity;
    }
}
