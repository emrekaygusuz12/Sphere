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
    int Width = 540;
    int Height = 600;

    private boolean isSphere1Selected = false;
    private boolean isSphere2Selected = false;
    private boolean isSphere3Selected = false;
    private Sphere[] myArray = new Sphere[3];




    @Override
    public void start(Stage stage) throws FileNotFoundException {
        stage.setTitle("Ray Tracing");

        //We need 3 things to see an image
        //1. We create an image we can write to
        WritableImage image = new WritableImage(Width, Height);
        //2. We create a view of that image
        ImageView view = new ImageView(image);
        //3. Add to the pane (below)


        Sphere mySphere1 = new Sphere(new Vector(-100, 0, -100),
                new Vector(0,1,1), 50);
        Sphere mySphere2 = new Sphere(new Vector( 0,100,-100),
                new Vector(1,1,1), 50);
        Sphere mySphere3 = new Sphere(new Vector(100,200,-100),
                new Vector(1,1,0), 50);

        myArray[0] = mySphere1;
        myArray[1] = mySphere2;
        myArray[2] = mySphere3;

        Vector green_v = new Vector(0, 1 , 0);
        myArray[0].setColour(green_v);
        Vector red_v = new Vector(1, 0 , 0);
        myArray[1].setColour(red_v);
        Vector blue_v = new Vector(0, 0 , 1);
        myArray[2].setColour(blue_v);
        Render(image);

        Label green = new Label("Green");
        Label red = new Label("Red");
        Label blue = new Label("Blue");
        Label l_x = new Label("X");
        Label l_y = new Label("Y");
        Label l_z = new Label("Z");
        //Create the simple GUI
        Slider g_slider = new Slider(0, 255, 1); //add more sliders for every colour
        g_slider.setMin(0);
        g_slider.setMax(255);
        g_slider.setShowTickLabels(true);
        g_slider.setShowTickMarks(true);
        Slider r_slider = new Slider(0,255, 1);
        r_slider.setMin(0);
        r_slider.setMax(255);
        r_slider.setShowTickLabels(true);
        r_slider.setShowTickMarks(true);
        Slider b_slider = new Slider(0,255, 1);
        b_slider.setMin(0);
        b_slider.setMax(255);
        b_slider.setShowTickLabels(true);
        b_slider.setShowTickMarks(true);

        Slider x_slider = new Slider(-250,250,1);
        x_slider.setMin(0);
        x_slider.setMax(255);
        x_slider.setShowTickLabels(true);
        x_slider.setShowTickMarks(true);

        Slider y_slider = new Slider(-250,250,1);
        y_slider.setMin(0);
        y_slider.setMax(255);
        y_slider.setShowTickLabels(true);
        y_slider.setShowTickMarks(true);

        Slider z_slider = new Slider(-250,250,1);
        z_slider.setMin(0);
        z_slider.setMax(255);
        z_slider.setShowTickLabels(true);
        z_slider.setShowTickMarks(true);

        ToggleGroup tg = new ToggleGroup();

        RadioButton rb1 = new RadioButton("Sphere 1");
        RadioButton rb2 = new RadioButton("Sphere 2");
        RadioButton rb3 = new RadioButton("Sphere 3");

        rb1.setToggleGroup(tg);
        rb2.setToggleGroup(tg);
        rb3.setToggleGroup(tg);

        tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue,
                                Toggle toggle, Toggle t1) {
                RadioButton check = (RadioButton)tg.getSelectedToggle();
                if (check.equals(rb1)){
                    isSphere1Selected = true;
                    isSphere2Selected = false;
                    isSphere3Selected = false;
                } else if (check.equals(rb2)) {
                    isSphere2Selected = true;
                    isSphere1Selected = false;
                    isSphere3Selected = false;
                } else if (check.equals(rb3)){
                    isSphere3Selected = true;
                    isSphere1Selected = false;
                    isSphere2Selected = false;
                } else  {
                    isSphere1Selected = false;
                    isSphere2Selected = false;
                    isSphere3Selected = false;
                }
            }

        });

        //Add all the event handlers
        g_slider.valueProperty().addListener(
                new ChangeListener<Number>() {
                    public void changed(ObservableValue<? extends Number>
                                                observable, Number oldValue, Number newValue) {
                        if(isSphere1Selected == true) {
                            Vector newCol = new Vector(myArray[0].getColour().x, newValue.doubleValue()/255 , myArray[0].getColour().z);
                            myArray[0].setColour(newCol);
                            Render(image);
                        } else if (isSphere2Selected == true) {
                            Vector newCol2 = new Vector(myArray[1].getColour().x, newValue.doubleValue()/255, myArray[1].getColour().z);
                            myArray[1].setColour(newCol2);
                            Render(image);
                        } else if (isSphere3Selected) {
                            Vector newCol3 = new Vector(myArray[2].getColour().x, newValue.doubleValue()/255, myArray[2].getColour().z);
                            myArray[2].setColour(newCol3);
                            Render(image);
                        }
                    }
                });

        //Add all the event handlers
        r_slider.valueProperty().addListener(
                new ChangeListener<Number>() {
                    public void changed(ObservableValue<? extends Number>
                                                observable, Number oldValue, Number newValue) {
                        if(isSphere1Selected == true) {
                            Vector newCol = new Vector(newValue.doubleValue()/255, myArray[0].getColour().y, myArray[0].getColour().z);
                            myArray[0].setColour(newCol);
                            System.out.println(newCol.x);
                            Render(image);
                        } else if (isSphere2Selected == true) {
                            Vector newCol2 = new Vector(newValue.doubleValue()/255, myArray[1].getColour().y, myArray[1].getColour().z);
                            myArray[1].setColour(newCol2);
                            Render(image);
                        } else if (isSphere3Selected) {
                            Vector newCol3 = new Vector(newValue.doubleValue()/255, myArray[2].getColour().y, myArray[2].getColour().z);
                            myArray[2].setColour(newCol3);
                            Render(image);
                        }
                    }
                });

        //Add all the event handlers
        b_slider.valueProperty().addListener(
                new ChangeListener<Number>() {
                    public void changed(ObservableValue<? extends Number>
                                                observable, Number oldValue, Number newValue) {
                        if(isSphere1Selected == true) {
                            Vector newCol = new Vector(myArray[0].getColour().x, myArray[0].getColour().y, newValue.doubleValue()/255);
                            myArray[0].setColour(newCol);
                            Render(image);
                        } else if (isSphere2Selected == true) {
                            Vector newCol2 = new Vector(myArray[1].getColour().x, myArray[1].getColour().y, newValue.doubleValue()/255);
                            myArray[1].setColour(newCol2);
                            Render(image);
                        } else if (isSphere3Selected) {
                            Vector newCol3 = new Vector(myArray[2].getColour().x, myArray[2].getColour().y, newValue.doubleValue()/255);
                            myArray[2].setColour(newCol3);
                            Render(image);
                        }
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
        root.add(rb1,1,2);
        root.add(r_slider, 0, 2);
        root.add(rb2,1,3);
        root.add(g_slider,0,3);
        root.add(rb3,1,4);
        root.add(b_slider,0,4);

        root.add(green,0,2);
        root.add(red,0,3);
        root.add(blue, 0, 4);
        root.add(l_x,0,5);
        root.add(l_y, 0, 6);
        root.add(l_z,0,7);

        root.add(x_slider,0,5);
        root.add(y_slider,0,6);
        root.add(z_slider,0,7);


        //Display to user
        Scene scene = new Scene(root, 1024, 1000);
        stage.setScene(scene);
        stage.show();
    }

    public void Render(WritableImage image) {
        //Get image dimensions, and declare loop variables
        int width = (int) image.getWidth(), height = (int) image.getHeight(), i, j;
        PixelWriter image_writer = image.getPixelWriter();

        Vector o;
        Vector d = new Vector(1, 0, 1);
        Vector Light = new Vector(250, 250, 300);
        double disc;
        double t;
        Vector v;
        Vector bkgCol = new Vector(0, 0, 0);
        Vector col;

        for (j = 0; j < height; j++) {
            for (i = 0; i < width; i++) {
                o = new Vector(i - width / 2, j - height / 2, -400);
                image_writer.setColor(i, j, Color.color(bkgCol.x, bkgCol.y,
                        bkgCol.z, 1.0)); //bkg col
                double small_t = 0;
                for (Sphere s : myArray) {
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
                        if (current_t < 0) {
                            current_t = (-b + sqrt(disc) / 2 * a);
                            if (current_t < small_t){
                                small_t = current_t;
                            }
                            image_writer.setColor(i, j, Color.color(s.getColour().x,
                                    s.getColour().y, s.getColour().z, 1.0)); //sphere col
                        }
                         else {
                            Vector p = o.add(d.mul(current_t));
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
                            col = s.getColour().mul(dp * .7).add(s.getColour().mul(.3));


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

