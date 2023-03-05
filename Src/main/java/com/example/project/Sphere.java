package com.example.project;

import static java.lang.Math.sqrt;


/**
 * Class to create a Sphere.
 *
 * @author Sam and Emre.
 * @version 1.0.
 */
public class Sphere {

    //Global Variables to implement Constructor.
    private double r;
    private Vector cs;
    private Vector colour;

    /**
     * Constructor to create a Sphere
     *
     * @param cs     Center of the sphere
     * @param colour Colour of the sphere
     * @param r      Radius of the sphere
     */
    public Sphere(Vector cs, Vector colour, double r) {
        this.cs = cs;
        this.colour = colour;
        this.r = r;
    }

    /**
     * Find if an intersection has happened or not.
     *
     * @param o Origin of the ray
     * @param d Direction of the ray.
     * @return if an intersection has happened or not dependent on discriminant greater than 0.
     */
    public boolean intersectionHappened(Vector o, Vector d) {
        Vector v = o.sub(getCs());
        double a = d.dot(d); //intersection a
        double b = 2 * v.dot(d); //intersection b
        double c = v.dot(v) - getR() * getR(); //intersection c
        double disc = b * b - 4 * a * c; //part of the quadratic formula
        return disc >= 0;
    }

    /**
     * Find the intersection and calculate it.
     *
     * @param o
     * @param d Direction of ray
     * @return the intersected solution
     */
    public double intersection(Vector o, Vector d) {
        Vector v = o.sub(getCs());
        double a = d.dot(d); //intersection a
        double b = 2 * v.dot(d); //intersection b
        double c = v.dot(v) - getR() * getR(); //intersection c
        double disc = b * b - 4 * a * c; //part of the quadratic formula
        double current_t = ((-b - sqrt(disc)) / (2 * a)); //quadratic formula
        if (current_t < 0) {
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

    public Vector getCs() {
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


