package Model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.LocalDate;

public class Event {
    private String name;
    private String description;
    private BufferedImage image;
    private double price;
    private LocalDate date;

    public Event() {}

    public Event(String Name, String description, BufferedImage image, double price, LocalDate date) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.date = date;
    }

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public BufferedImage getImage() {return image;}
    public void setImage(BufferedImage image) {this.image = image;}

    public double getPrice() {return price;}
    public void setPrice(double price) {this.price = price; }

    public LocalDate getDate() {return date;}
    public void setDate(LocalDate date) {this.date = date;}
}
