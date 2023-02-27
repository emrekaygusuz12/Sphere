package com.example.project;

import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

import java.lang.reflect.Array;

import static java.lang.Math.sqrt;

;
public class Sphere {
    double r; //radius
    Vector cs; //centre
    Vector colour; // colour
/*
    private double x;
    private double y;
    private double z;
*/

    /**
     * Constructor for Sphere Class
     *
     * @param cs
     * @param colour
     * @param r
     */
    public Sphere(Vector cs, Vector colour, double r) {
        this.cs = cs;
        this.colour = colour;
        this.r = r;
    }
/*
    public void setXYZ(Vector o, double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
*/
    public void setR(double r) {
        this.r = r;
    }

    public void setCs(Vector cs) {
        this.cs = cs;
    }
    public Vector getCs(){
        return cs;
    }
    public void setColour(Vector colour) {
        this.colour = colour;
    }
    public Vector getColour() {
        return colour;
    }

    public double getR() {
        return r;
    }






}


