package Model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Date;


public class Event {
    private static int IDReference;
    private String eventID;
    private String name;
    private String description;
    private String imagePath;
    private double price;
    private String date;

    public Event() {}

    public Event(String name, String description, String imagePath, double price, String date) {
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
        this.price = price;
        this.date = date;
        this.eventID = String.valueOf(IDReference + 1000);
        IDReference++;
    }

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public String getImagePath() {return imagePath;}
    public void setImage(String image) {this.imagePath = imagePath;}

    public double getPrice() {return price;}
    public void setPrice(double price) {this.price = price; }

    public String getDate() {return date;}
    public void setDate(String date) {this.date = date;}
}
