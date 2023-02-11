package com.example.project;

import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import static java.lang.Math.sqrt;

public class Sphere {
    PixelWriter image_writer;
    final double r = 100; //radius
    int height;
    int width;
    double c;

    /**
     * Constructor for Sphere Class
     *
     * @param Height
     * @param Width
     * @param c
     * @param image_writer
     */
    public Sphere(int Height, int Width, double c, PixelWriter image_writer) {
        this.height = Height;
        this.width = Width;
        this.c = c;
        this.image_writer = image_writer;
    }

    /**
     * Intersection method
     */
    public void intersection() {
        Vector p = new Vector(0, 0, 0);
        Vector cs = new Vector(0, 0, 0);
        Vector o = new Vector(0, 0, 0);
        Vector d = new Vector(0, 0, 1);
        Vector sphereCol = new Vector(1., c, 0.);
        Vector bkgCol = new Vector(0.5, 0.5, 0.5);
        Vector Light = new Vector(250, 250, -400);
        Vector col;
        double t; //solution

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                Vector v = o.sub(cs);
                o = new Vector(i - width / 2, j - height / 2, -400);
                o.x = i - 250;
                o.y = j - 250;
                o.z = -200;
                double a = d.dot(d); //intersection a
                double b = 2 * v.dot(d); //intersection b
                c = v.dot(v) - r * r; //intersection c
                double disc = b * b - 4 * a * c; //part of the quadratic formula
                if (disc < 0) {
                    image_writer.setColor(i, j, Color.color(bkgCol.x, bkgCol.y,
                            bkgCol.z, 1.0));
                } else {
                    t = (-b - sqrt(disc) / 2 * a); //quadratic formula
                    if (t < 0) {
                        t = (b - sqrt(disc) / 2 * a);
                    }
                    if (t < 0) {
                        image_writer.setColor(i, j, Color.color(sphereCol.x,
                                sphereCol.y, sphereCol.z, 1.0));
                    } else {
                        p = o.add(d.mul(t));
                        Vector n = p.sub(cs);
                        n.normalise();
                        Vector Lv = Light.sub(p);
                        Lv.normalise();
                        double dp = Lv.dot(n);
                        if (dp < 0) {
                            dp = 0;
                        }
                        if (dp > 1) {
                            dp = 1;
                        }
                        col = sphereCol.mul(dp * 7).add(sphereCol.mul(.3));
                        image_writer.setColor(i, j, Color.color(col.x,
                                col.y, col.z, 1.0));
                    }
                }
            } // column loop
        } // row loop
    }

    /**
     * Setter for height
     *
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Setter for width
     * @param Width
     */
    public void setWidth(int Width) {
        this.width = Width;
    }

    /**
     * Setter for c
     * @param c
     */
    public void setC(double c) {
        this.c = c;
    }

    /**
     * Setter for image_writer
     * @param image_writer
     */
    public void setImage_writer(PixelWriter image_writer) {
        this.image_writer = image_writer;
    }

    /**
     * Getter for height
     *
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Getter for width
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Getter for c
     * @return c
     */
    public double getC() {
        return c;
    }

    /**
     * Getter for image_writer
     * @return image_writer
     */
    public PixelWriter getImage_writer() {
        return image_writer;
    }


}


