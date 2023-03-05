package com.example.project;

import java.lang.Math.*;

public class Vector {

    //XYZ for each vector.
    double x, y, z;

    public Vector() {
    }

    /**
     * Create a new Vector point
     *
     * @param i is x
     * @param j is y
     * @param k is z
     */
    public Vector(double i, double j, double k) {
        x = i;
        y = j;
        z = k;
    }

    /**
     * Find the magnitude of a vector.
     *
     * @return the magnitude of a vector.
     */
    public double magnitude() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    /**
     * Normalise the magnitude of XYZ.
     */
    public void normalise() {
        double mag = magnitude();
        if (mag != 0) {
            x /= mag;
            y /= mag;
            z /= mag;
        }
    }

    /**
     * Find the Cross Product between a vector.
     *
     * @param v2 Vector to find cross.
     * @return The cross Product.
     */
    public Vector cross(Vector v2) {
        return new Vector(y * v2.z - z * v2.y, z * v2.x - x * v2.z, x * v2.y - y * v2.x);
    }

    /**
     * Return the dot product of a vector.
     *
     * @param a Vector to find the dot product of.
     * @return the dot product.
     */
    public double dot(Vector a) {
        return x * a.x + y * a.y + z * a.z;
    }

    /**
     * Subtract from a vector.
     *
     * @param a Vector to subtract from.
     * @return the subtracted vector.
     */
    public Vector sub(Vector a) {
        return new Vector(x - a.x, y - a.y, z - a.z);
    }

    /**
     * Add from a vector.
     *
     * @param a Vector to add from.
     * @return the added vector.
     */
    public Vector add(Vector a) {
        return new Vector(x + a.x, y + a.y, z + a.z);
    }

    /**
     * Multiply the vector specified
     *
     * @param d the vector wanting to be multiplied
     * @return the multiplied vector
     */
    public Vector mul(double d) {
        return new Vector(d * x, d * y, d * z);
    }

    /**
     * Print the vectors XYZ values.
     */
    public void print() {
        System.out.println("x=" + x + ", y=" + y + ", z=" + z);
    }
}