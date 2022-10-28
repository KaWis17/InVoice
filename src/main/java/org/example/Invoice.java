package org.example;

import java.util.ArrayList;

public class Invoice {
    Person issuer, client;
    String invoiceNumber, place;
    ArrayList<Product> products = new ArrayList<>();

    Invoice(){
        issuer = new Person();
        client = new Person();
    }

    public void setPlace(String place) {
        this.place = place;
    }
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public void addProduct(String name, double unitPrice, double totalPrice){
        products.add(new Product(name, unitPrice, totalPrice));
    }

}

class Person{
    String name, surname, mail, country, city, address, phoneNumber;

    Person(){
        name = "Name";
        surname = "Surname";
        mail = "eMail";
        country = "Country";
        city = "City";
        address = "Address";
        phoneNumber = "Phone Number";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}