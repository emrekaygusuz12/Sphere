package com.example.project;

import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

import java.lang.reflect.Array;

import static java.lang.Math.sqrt;

;
public class Sphere {
    double r = 100; //radius
    Vector cs = new Vector(0, 0, 0);
    double colour;


    /**
     * Constructor for Sphere Class
     *
     * @param cs
     * @param colour
     * @param r
     */
    public Sphere(Vector cs, double colour, double r) {
        this.cs = cs;
        this.colour = colour;
        this.r = r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public void setCs(Vector cs) {
        this.cs = cs;
    }

    public void setColour(double colour) {
        this.colour = colour;
    }


    public double getR() {
        return r;
    }

    public double getColour() {
        return colour;
    }

    public Vector getCs(){
        return cs;
    }
}


