package org.example;

public class Passenger {
    //to autogenrate passenger_id
    public static int id = 1;
    public int passenger_id;
    public String name;
    public int age;
    public String berth_preference;
    public String berth_alloted;
    public int seat_number;
    public Passenger(String name, int age, String berth_preference){
        this.name = name;
        this.age = age;
        this.berth_preference = berth_preference;
        passenger_id++;
        berth_alloted = "";
        seat_number = -1;
    }
}
