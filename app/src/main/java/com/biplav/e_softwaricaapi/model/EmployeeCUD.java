package com.biplav.e_softwaricaapi.model;

public class EmployeeCUD {

    private int id;
    private String name;
    private String salary;
    private int age;
    private String profile_image;

    public EmployeeCUD( String name, String salary, int age) {

        this.name = name;
        this.salary = salary;
        this.age = age;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }
}

