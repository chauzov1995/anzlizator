package com.example.myapplication;


public class Product {
    public String id;
    public String nazv;
    public String start;
    public String fakt;




    Product(String id, String nazv, String start, String fakt){
        this.id = id;
        this.nazv = nazv;
        this.start = start;
        this.fakt = fakt;
    }

    public String getId(){
        return this.id;
    }
    public String getNazv(){
        return this.nazv;
    }
    public String getStart(){
        return this.start;
    }
    public String getFakt(){
        return this.fakt;
    }
}



