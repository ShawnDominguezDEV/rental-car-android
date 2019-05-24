package edu.txstate.sdd65.rentalcarapp;

import org.json.JSONObject;

public class RentalCar {

    //Private Variables
    private int id;
    private String name;
    private String brand;
    private String color;
    private double cost;

    //Constructor
    public RentalCar(){
    }

    //For JSON object
    public RentalCar (JSONObject object){
        try {
            this.id = object.getInt("Id");
            this.name = object.getString("Name");
            this.brand = object.getString("Brand");
            this.color = object.getString("Color");
            this.cost = object.getDouble("Cost");
        }catch (Exception ex){ex.printStackTrace();}


    }
    //Variables
    public RentalCar(int id, String name, String brand, String color, double cost){
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.color = color;
        this.cost = cost;
    }

    //IdMethods
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    //NameMethods
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    //BrandMethods
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }

    //ColorMethods
    public String getColor(){return color;}
    public void setColor(String color){this.color = color;}

    //CostMethods
    public double getCost() {
        return cost;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {return this.name;}
}
