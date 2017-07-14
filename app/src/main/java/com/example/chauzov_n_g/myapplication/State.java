package com.example.chauzov_n_g.myapplication;

/**
 * Created by chauzov_n_g on 30.04.2017.
 */

public class State {
    private String name;
    private int count;
    private String unit;

    State(String name, String unit){
        this.name = name;
        this.count=0;
        this.unit = unit;
    }
    public String getUnit() {
        return this.unit;
    }
    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
}