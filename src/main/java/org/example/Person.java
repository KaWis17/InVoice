package org.example;

//GRASP - Responsible for managing person
class Person {
  /* default */ String name;
  /* default */ String surname;
  /* default */ String mail;
  /* default */ String country;
  /* default */ String city;
  /* default */ String address;
  /* default */ String phoneNumber;

  //GRASP - Information expert
  // obvious that this class knows the most about its own
  /* default */ Person() {
    name = "Name";
    surname = "Surname";
    mail = "eMail";
    country = "Country";
    city = "City";
    address = "Address";
    phoneNumber = "Phone Number";
  }

  //GRASP - Information expert
  // obvious that this class knows the most about info inside getters and setters
  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(final String surname) {
    this.surname = surname;
  }

  public String getMail() {
    return mail;
  }

  public void setMail(final String mail) {
    this.mail = mail;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(final String country) {
    this.country = country;
  }

  public String getCity() {
    return city;
  }

  public void setCity(final String city) {
    this.city = city;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(final String address) {
    this.address = address;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(final String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
}
