package lesson31;/*
Created by Pavel Gryshchenko on 23.11.2022
*/

class YearsData {
    private Integer id;
    private String name;
    private String year;
    private String color;
    private String pantone_value;

    public YearsData() {

    }

    public YearsData(Integer id, String name, String year, String color, String pantone_value) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.color = color;
        this.pantone_value = pantone_value;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    public String getPantone_value() {
        return pantone_value;
    }
}
