package com.example.project;

import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

import java.lang.reflect.Array;

import static java.lang.Math.sqrt;

;
public class Sphere {
    private double r; //radius
    private Vector cs; //centre
    private Vector colour; // colour

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

    public boolean intersectionHappened(Vector o, Vector d){
        Vector v = o.sub(getCs());
        double a = d.dot(d); //intersection a
        double b = 2 * v.dot(d); //intersection b
        double c = v.dot(v) - getR() * getR(); //intersection c
        double disc = b * b - 4 * a * c; //part of the quadratic formula
        return disc>=0;
    }

    public double intersection(Vector o, Vector d){
        Vector v = o.sub(getCs());
        double a = d.dot(d); //intersection a
        double b = 2 * v.dot(d); //intersection b
        double c = v.dot(v) - getR() * getR(); //intersection c
        double disc = b * b - 4 * a * c; //part of the quadratic formula
        double current_t = ((-b - sqrt(disc)) / (2 * a)); //quadratic formula
        if(current_t < 0){
            current_t = ((-b + sqrt(disc)) / (2 * a));
        }
        return current_t;
    }

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


