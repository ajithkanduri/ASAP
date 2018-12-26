package com.example.android.asap.Model;

public class Order {

    private String Restaurant_Name;
    private String Item;
    private String Parent;
    private String Cost;
    private String Discount;
    private String quantity;
    private String net;
    public String getParent() {
        return Parent;
    }

    public void setParent(String parent) {
        Parent = parent;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }
    public Order()
    {

    }
    public Order(String item, String quantity)
    {
        this.Item = item;
        this.quantity = quantity;
    }

    public Order(String restaurant_Name, String item, String cost, String discount, String parent, String quantity, String net) {

        Restaurant_Name = restaurant_Name;
        Item = item;
        Parent = parent;
        Cost = cost;
        Discount = discount;
        this.quantity = quantity;
        this.net = net;
    }



    public String getRestaurant_Name() {
        return Restaurant_Name;
    }

    public void setRestaurant_Name(String restaurant_Name) {
        Restaurant_Name = restaurant_Name;
    }

    public String getItem() {
        return Item;
    }

    public void setItem(String item) {
        Item = item;
    }

    public String getCost() {
        return Cost;
    }

    public void setCost(String cost) {
        Cost = cost;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }
}
