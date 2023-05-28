package com.game.truck.bean;

public class Truck {
    public Truck(String name, int price, int face) {
        this.name = name;
        this.price = price;
        this.face = face;
    }

   public String name;
   public int price;
   public int face;

    @Override
    public String toString() {
        return "Truck{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", face=" + face +
                '}';
    }
}
