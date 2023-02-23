package com.example.project;/*
CS-255 Getting started code for the assignment
I do not give you permission to post this code online
Do not post your solution online
Do not copy code
Do not use JavaFX functions or other libraries to do the main parts of the assignment (i.e. ray tracing steps 1-7)
All of those functions must be written by yourself
You may use libraries to achieve a better GUI
*/

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Toggle;
import javafx.scene.control.Slider;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.io.*;
import java.lang.Math.*;

import javafx.geometry.HPos;

import static java.lang.Math.sqrt;

public class Main extends Application {
    int Width = 640;
    int Height = 640;

    int col = 255; //just for the test example

    private boolean isSphere1Selected = false;
    private boolean isSphere2Selected = false;
    private boolean isSphere3Selected = false;


    @Override
    public void start(Stage stage) throws FileNotFoundException {
        stage.setTitle("Ray Tracing");

        //We need 3 things to see an image
        //1. We create an image we can write to
        WritableImage image = new WritableImage(Width, Height);
        //2. We create a view of that image
        ImageView view = new ImageView(image);
        //3. Add to the pane (below)

        //Create the simple GUI
        Slider g_slider = new Slider(0, 255, col); //add more sliders for every colour
        Slider r_slider1 = new Slider(0,255, col);
        Slider b_slider2 = new Slider(0,255, col);

        ToggleGroup tg = new ToggleGroup();

        RadioButton rb1 = new RadioButton("Sphere 1");
        RadioButton rb2 = new RadioButton("Sphere 2");
        RadioButton rb3 = new RadioButton("Sphere 3");

        rb1.setToggleGroup(tg);
        rb2.setToggleGroup(tg);
        rb3.setToggleGroup(tg);

        tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                RadioButton rb1 = (RadioButton)tg.getSelectedToggle();
                //RadioButton rb2 = (RadioButton)tg.getSelectedToggle();
                if (rb1.isSelected()){
                    isSphere1Selected = true;
                } else if (rb2.isSelected()) {
                    isSphere2Selected = true;
                } else if (rb3.isSelected()){
                    isSphere3Selected = true;
                } else  {
                    throw new IllegalArgumentException("Sphere is not selected");
                }
            }

        });


        //Add all the event handlers
        g_slider.valueProperty().addListener(
                new ChangeListener<Number>() {
                    public void changed(ObservableValue<? extends Number>
                                                observable, Number oldValue, Number newValue) {
                        col = newValue.intValue();
                        col = newValue.intValue();
                        col = newValue.intValue();

                        Render(image);
                    }
                });

        //The following is in case you want to interact with the image in any way
        //e.g., for user interaction, or you can find out the pixel position for debugging
        view.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_PRESSED, event -> {
            System.out.println(event.getX() + " " + event.getY());
            event.consume();
        });

        Render(image);

        GridPane root = new GridPane();
        root.setVgap(12);
        root.setHgap(12);

        //3. (referring to the 3 things we need to display an image)
        //we need to add it to the pane
        root.add(view, 0, 0);
        root.add(rb1,1,1);
        root.add(g_slider, 0, 1);
        root.add(rb2,1,2);
        root.add(r_slider1,0,2);
        root.add(rb3,1,3);
        root.add(b_slider2,0,3);


        //Display to user
        Scene scene = new Scene(root, 1024, 768);
        stage.setScene(scene);
        stage.show();
    }

    public void Render(WritableImage image) {
        //Get image dimensions, and declare loop variables
        int width = (int) image.getWidth(), height = (int) image.getHeight(), i, j;
        PixelWriter image_writer = image.getPixelWriter();

        double colour = col / 255.0;

        Sphere mySphere1 = new Sphere(new Vector(-100, 0, 0.), colour, 60);
        Sphere mySphere2 = new Sphere(new Vector( 0,100,0), colour, 60);
        Sphere mySphere3 = new Sphere(new Vector(100,200,0), colour, 60);

        Sphere[] myArray = new Sphere[3];
        myArray[0] = mySphere1;
        myArray[1] = mySphere2;
        myArray[2] = mySphere3;

        Vector o = new Vector(0, 0, 0);
        Vector d = new Vector(0, 0, 1);
        Vector p = new Vector(0, 0, 0);
        Vector Light = new Vector(250, 250, -400);
        double disc = -1;
        double t;
        Vector v;
        Vector col;
        Vector sphereCol = new Vector(1., colour, 0.);
        Vector bkgCol = new Vector(0.5, 0.5, 0.5);

        for (j = 0; j < height; j++) {
            for (i = 0; i < width; i++) {

                image_writer.setColor(i, j, Color.color(bkgCol.x, bkgCol.y,
                        bkgCol.z, 1.0)); //bkg col
                for (Sphere s : myArray) {
                    //if (s == mySphere1){
                   // }
                    o = new Vector(i - width / 2, j - height / 2, -400);
                    o.x = i - 250;
                    o.y = j - 250;
                    o.z = -200;
                    v = o.sub(s.getCs());
                    double a = d.dot(d); //intersection a
                    double b = 2 * v.dot(d); //intersection b
                    double c = v.dot(v) - s.getR() * s.getR(); //intersection c
                    disc = b * b - 4 * a * c; //part of the quadratic formula
                    if (disc> 0) { // ray hit the sphere
                        double current_t = (-b - sqrt(disc) / 2 * a); //quadratic formula
                        double new_t = current_t;
                        if (current_t < new_t){
                            new_t = current_t;
                        }
                        if (current_t < 0) {
                            current_t = (b - sqrt(disc) / 2 * a);
                        }
                        if (current_t < 0) {
                            image_writer.setColor(i, j, Color.color(sphereCol.x,
                                    sphereCol.y, sphereCol.z, 1.0)); //sphere col
                        } else {
                            p = o.add(d.mul(current_t));
                            Vector n = p.sub(s.getCs());
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
                    } //end of hit if
                }
            }
        }
    }
            /*
                    o = new Vector(i - width / 2, j - height / 2, -400);
                    o.x = i - 250;
                    o.y = j - 250;
                    o.z = -200;
                    multiSphere(height,width);
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

*/

    public static void main(String[] args) {
        launch();
    }
}

