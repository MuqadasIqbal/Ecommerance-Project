package com.example.ecommeranceapp.ModelClass;

public class Categories {
    private String name,icon,color,brief;

    public Categories(String name, String icon,String color,String brief,Integer id) {
        this.name = name;
        this.icon = icon;
        this.color = color;
        this.brief = brief;
        this.id = id;
    }

    private Integer id;

    public String getName() {
        return name;
    }



    public String getIcon() {
        return icon;

    }

    public String getColor() {
        return color;
    }


    public String getBrief() {
        return brief;
    }



    public Integer getId() {
        return id;
    }

}
