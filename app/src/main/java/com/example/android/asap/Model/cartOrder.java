package com.example.android.asap.Model;

import java.util.ArrayList;

public class cartOrder {
    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public ArrayList<Order> getOrder() {
        return order;
    }

    public void setOrder(ArrayList<Order> order) {
        this.order = order;
    }

    public String getResTotal() {
        return resTotal;
    }

    public void setResTotal(String resTotal) {
        this.resTotal = resTotal;
    }

    public String getNetTotal() {
        return netTotal;
    }

    public void setNetTotal(String netTotal) {
        this.netTotal = netTotal;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhnNumber() {
        return phnNumber;
    }

    public void setPhnNumber(String phnNumber) {
        this.phnNumber = phnNumber;
    }

    private String phnNumber;
    private String id;
    private String resName;
    private ArrayList<Order> order;
    private String resTotal;
    private String netTotal;
    private String deliveryAddress;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;
    public cartOrder(){

    }
    public cartOrder(String phnNumber, String id, String resName, ArrayList<Order> order, String resTotal, String netTotal, String deliveryAddress, String status)
    {
        this.phnNumber = phnNumber;
        this.id = id;
        this.resName = resName;
        this.order = order;
        this.resTotal = resTotal;
        this.netTotal = netTotal;
        this.deliveryAddress = deliveryAddress;
        this.status = status;
    }
}
