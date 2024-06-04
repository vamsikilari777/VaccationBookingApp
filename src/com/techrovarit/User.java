package com.techrovarit;
public class User {
    private  String name;
    private int age;
    private String gender;
    private String location;
    public User(String name, int age, String gender, String location, double phone_Number) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.location = location;
        this.phone_Number = phone_Number;
    }
    public User() {
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public double getPhone_Number() {
        return phone_Number;
    }

    public void setPhone_Number(double phone_Number) {
        this.phone_Number = phone_Number;
    }
    private double phone_Number;
}
 