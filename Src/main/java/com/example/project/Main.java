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

/**
 * Main Class to run Ray Tracing and Rendering.
 *
 * @author Sam and Emre.
 * @version 1.0.
 */
public class Main extends Application {
    private int Width = 500;
    private int Height = 500;

    private boolean isSphere1Selected = false;
    private boolean isSphere2Selected = false;
    private boolean isSphere3Selected = false;
    private final Sphere[] myArray = new Sphere[3];

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        stage.setTitle("Ray Tracing");

        WritableImage image = new WritableImage(Width, Height);

        ImageView view = new ImageView(image);

        //Create three spheres.
        Sphere mySphere1 = new Sphere(new Vector(0, 0, -100),
                new Vector(0, 1, 1), 30);
        Sphere mySphere2 = new Sphere(new Vector(100, 100, -100),
                new Vector(1, 1, 1), 50);
        Sphere mySphere3 = new Sphere(new Vector(200, 200, -100),
                new Vector(1, 1, 0), 70);

        //Add instances of Spheres to the Array
        myArray[0] = mySphere1;
        myArray[1] = mySphere2;
        myArray[2] = mySphere3;

        //Set the intitial colour of the spheres.
        Vector green_v = new Vector(0, 1, 0);
        myArray[0].setColour(green_v);
        Vector red_v = new Vector(1, 0, 0);
        myArray[1].setColour(red_v);
        Vector blue_v = new Vector(0, 0, 1);
        myArray[2].setColour(blue_v);
        Render(image);

        Label green = new Label("Green");
        Label red = new Label("Red");
        Label blue = new Label("Blue");
        Label l_x = new Label("X axis");
        Label l_y = new Label("Y axis");
        Label l_z = new Label("Z axis");
        Label l_r = new Label("Radius");

        //Create Sliders and set values.
        Slider g_slider = new Slider(0, 255, 1);
        g_slider.setMin(0);
        g_slider.setMax(255);
        g_slider.setShowTickLabels(true);
        g_slider.setShowTickMarks(true);
        Slider r_slider = new Slider(0, 255, 1);
        r_slider.setMin(0);
        r_slider.setMax(255);
        r_slider.setShowTickLabels(true);
        r_slider.setShowTickMarks(true);
        Slider b_slider = new Slider(0, 255, 1);
        b_slider.setMin(0);
        b_slider.setMax(255);
        b_slider.setShowTickLabels(true);
        b_slider.setShowTickMarks(true);

        Slider x_slider = new Slider(-250, 250, 1);
        x_slider.setMin(-100);
        x_slider.setMax(255);
        x_slider.setShowTickLabels(true);
        x_slider.setShowTickMarks(true);
        Slider y_slider = new Slider(-250, 250, 1);
        y_slider.setMin(-100);
        y_slider.setMax(255);
        y_slider.setShowTickLabels(true);
        y_slider.setShowTickMarks(true);
        Slider z_slider = new Slider(-250, 250, 1);
        z_slider.setMin(-100);
        z_slider.setMax(255);
        z_slider.setShowTickLabels(true);
        z_slider.setShowTickMarks(true);

        Slider radius_slider = new Slider(10, 200, 1);
        radius_slider.setMin(30);
        radius_slider.setMax(200);
        radius_slider.setShowTickLabels(true);
        radius_slider.setShowTickMarks(true);

        ToggleGroup tg = new ToggleGroup();

        RadioButton rb1 = new RadioButton("Sphere 1");
        RadioButton rb2 = new RadioButton("Sphere 2");
        RadioButton rb3 = new RadioButton("Sphere 3");

        rb1.setToggleGroup(tg);
        rb2.setToggleGroup(tg);
        rb3.setToggleGroup(tg);

        //Select a sphere via Radio Buttons.
        tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue,
                                Toggle toggle, Toggle t1) {
                RadioButton check = (RadioButton) tg.getSelectedToggle();
                if (check.equals(rb1)) {
                    isSphere1Selected = true;
                    isSphere2Selected = false;
                    isSphere3Selected = false;
                } else if (check.equals(rb2)) {
                    isSphere2Selected = true;
                    isSphere1Selected = false;
                    isSphere3Selected = false;
                } else if (check.equals(rb3)) {
                    isSphere3Selected = true;
                    isSphere1Selected = false;
                    isSphere2Selected = false;
                } else {
                    isSphere1Selected = false;
                    isSphere2Selected = false;
                    isSphere3Selected = false;
                }
            }

        });

        //Sliders Changed Listeners to add a new value to each.
        g_slider.valueProperty().addListener(
                new ChangeListener<Number>() {
                    public void changed(ObservableValue<? extends Number>
                                                observable, Number oldValue,
                                        Number newValue) {
                        if (isSphere1Selected) {
                            Vector newCol = new Vector(myArray[0].getColour().x,
                                    newValue.doubleValue() / 255,
                                    myArray[0].getColour().z);
                            myArray[0].setColour(newCol);
                            Render(image);
                        } else if (isSphere2Selected) {
                            Vector newCol2 = new Vector(myArray[1].getColour().x,
                                    newValue.doubleValue() / 255,
                                    myArray[1].getColour().z);
                            myArray[1].setColour(newCol2);
                            Render(image);
                        } else if (isSphere3Selected) {
                            Vector newCol3 = new Vector(myArray[2].getColour().x,
                                    newValue.doubleValue() / 255,
                                    myArray[2].getColour().z);
                            myArray[2].setColour(newCol3);
                            Render(image);
                        }
                    }
                });

        r_slider.valueProperty().addListener(
                new ChangeListener<Number>() {
                    public void changed(ObservableValue<? extends Number>
                                                observable, Number oldValue,
                                        Number newValue) {
                        if (isSphere1Selected == true) {
                            Vector newCol = new Vector(newValue.doubleValue() / 255,
                                    myArray[0].getColour().y, myArray[0].getColour().z);
                            myArray[0].setColour(newCol);
                            System.out.println(newCol.x);
                            Render(image);
                        } else if (isSphere2Selected == true) {
                            Vector newCol2 = new Vector(newValue.doubleValue() / 255,
                                    myArray[1].getColour().y, myArray[1].getColour().z);
                            myArray[1].setColour(newCol2);
                            Render(image);
                        } else if (isSphere3Selected) {
                            Vector newCol3 = new Vector(newValue.doubleValue() / 255,
                                    myArray[2].getColour().y, myArray[2].getColour().z);
                            myArray[2].setColour(newCol3);
                            Render(image);
                        }
                    }
                });

        b_slider.valueProperty().addListener(
                new ChangeListener<Number>() {
                    public void changed(ObservableValue<? extends Number>
                                                observable, Number oldValue,
                                        Number newValue) {
                        if (isSphere1Selected) {
                            Vector newCol = new Vector(myArray[0].getColour().x,
                                    myArray[0].getColour().y,
                                    newValue.doubleValue() / 255);
                            myArray[0].setColour(newCol);
                            Render(image);
                        } else if (isSphere2Selected) {
                            Vector newCol2 = new Vector(myArray[1].getColour().x,
                                    myArray[1].getColour().y,
                                    newValue.doubleValue() / 255);
                            myArray[1].setColour(newCol2);
                            Render(image);
                        } else if (isSphere3Selected) {
                            Vector newCol3 = new Vector(myArray[2].getColour().x,
                                    myArray[2].getColour().y,
                                    newValue.doubleValue() / 255);
                            myArray[2].setColour(newCol3);
                            Render(image);
                        }
                    }
                });

        x_slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number>
                                        observable, Number oldValue,
                                Number newValue) {

                if (isSphere1Selected) {
                    Vector newCol = new Vector(newValue.doubleValue(),
                            myArray[0].getCs().y, myArray[0].getCs().z);
                    myArray[0].setCs(newCol);
                    Render(image);
                } else if (isSphere2Selected) {
                    Vector newCol2 = new Vector(newValue.doubleValue(),
                            myArray[1].getCs().y, myArray[1].getCs().z);
                    myArray[1].setCs(newCol2);
                    Render(image);
                } else if (isSphere3Selected) {
                    Vector newCol3 = new Vector(newValue.doubleValue(),
                            myArray[2].getCs().y, myArray[2].getCs().z);
                    myArray[2].setCs(newCol3);
                    Render(image);
                }
            }
        });

        y_slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number>
                                        observable, Number oldValue,
                                Number newValue) {

                if (isSphere1Selected) {
                    Vector newCol = new Vector(myArray[0].getCs().x,
                            newValue.doubleValue(), myArray[0].getCs().z);
                    myArray[0].setCs(newCol);
                    Render(image);
                } else if (isSphere2Selected) {
                    Vector newCol2 = new Vector(myArray[1].getCs().x,
                            newValue.doubleValue(), myArray[1].getCs().z);
                    myArray[1].setCs(newCol2);
                    Render(image);
                } else if (isSphere3Selected) {
                    Vector newCol3 = new Vector(myArray[2].getCs().x,
                            newValue.doubleValue(), myArray[2].getCs().z);
                    myArray[2].setCs(newCol3);
                    Render(image);
                }
            }
        });

        z_slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number>
                                        observable, Number oldValue,
                                Number newValue) {

                if (isSphere1Selected) {
                    Vector newCol = new Vector(myArray[0].getCs().x,
                            myArray[0].getCs().y, newValue.doubleValue());
                    myArray[0].setCs(newCol);
                    Render(image);
                } else if (isSphere2Selected) {
                    Vector newCol2 = new Vector(myArray[1].getCs().x,
                            myArray[1].getCs().y, newValue.doubleValue());
                    myArray[1].setCs(newCol2);
                    Render(image);
                } else if (isSphere3Selected) {
                    Vector newCol3 = new Vector(myArray[2].getCs().x,
                            myArray[2].getCs().y, newValue.doubleValue());
                    myArray[2].setCs(newCol3);
                    Render(image);
                }
            }
        });

        radius_slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number>
                                        observable, Number oldValue,
                                Number newValue) {

                if (isSphere1Selected) {
                    double newRadius = newValue.doubleValue();
                    myArray[0].setR(newRadius);
                    Render(image);
                } else if (isSphere2Selected) {
                    double newRadius = newValue.doubleValue();
                    myArray[1].setR(newRadius);
                    Render(image);
                } else if (isSphere3Selected) {
                    double newRadius = newValue.doubleValue();
                    myArray[2].setR(newRadius);
                    Render(image);
                }
            }
        });

       //Get the grid coordinates for each mouse clicked on the gridpane.
        view.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_PRESSED, event -> {
            System.out.println(event.getX() + " " + event.getY());
            event.consume();
        });

        Render(image);

        GridPane root = new GridPane();
        root.setVgap(12);
        root.setHgap(12);

        //Set the view and sliders into the gridpane.
        root.add(view, 0, 0);
        root.add(rb1, 1, 2);
        root.add(r_slider, 0, 2);
        root.add(rb2, 1, 3);
        root.add(g_slider, 0, 3);
        root.add(rb3, 1, 4);
        root.add(b_slider, 0, 4);

        root.add(green, 0, 2);
        root.add(red, 0, 3);
        root.add(blue, 0, 4);
        root.add(l_x, 0, 5);
        root.add(l_y, 0, 6);
        root.add(l_z, 0, 7);
        root.add(l_r, 0, 8);

        root.add(x_slider, 0, 5);
        root.add(y_slider, 0, 6);
        root.add(z_slider, 0, 7);

        root.add(radius_slider, 0, 8);


        //Display to user
        Scene scene = new Scene(root, 630, 1000);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Render the spheres.
     *
     * @param image to render.
     */
    public void Render(WritableImage image) {
        //Get image dimensions, and declare loop variables
        int width = (int) image.getWidth(), height = (int) image.getHeight(), i, j;
        PixelWriter image_writer = image.getPixelWriter();

        //Initiation variables for rendering.
        Vector Light = new Vector(0, 300, -1000);
        Vector bkgCol = new Vector(0, 0, 0);
        Vector col;

        Vector vrp = new Vector(0, 0, 400);
        Vector vuv = new Vector(0, 1, 0);
        Vector lookAt = new Vector(0, 0, 0);
        Vector vpn = lookAt.sub(vrp);
        vpn.print();
        vpn.normalise();
        Vector vrv = vpn.cross(vuv);
        vrv.normalise();
        vuv = vrv.cross(vpn);
        vuv.normalise();

        double scale = 0.5;

        //Render loop for shading,intersection, and reflections.
        for (j = 0; j < height; j++) {
            for (i = 0; i < width; i++) {
                double u = (i - width / 2) * scale;
                double v = ((height - j) - height / 2) * scale;
                Vector d = vrp.add(vrv.mul(u)).add(vuv.mul(v));
                Vector o = new Vector(0, 0, -1000);
                double small_t = 10000;
                boolean hasHit = false;
                Sphere testSphere = new Sphere(new Vector(0, 0, 0),
                        new Vector(0, 0, 0), 30);
                for (Sphere s : myArray) {
                    if (s.intersectionHappened(o, d)) { // ray hit the sphere
                        double current_t = s.intersection(o, d); //quadratic formula
                        if (current_t < small_t) {
                            hasHit = true;
                            testSphere = s;
                            small_t = current_t;
                        }
                    }
                } //end of hit if
                if (hasHit) { //Add shading to spheres
                    Vector p = o.add(d.mul(small_t));
                    Vector n = p.sub(testSphere.getCs());
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
                    col = testSphere.getColour().mul(dp * .7).
                            add(testSphere.getColour().mul(.3));
                    image_writer.setColor(i, j, Color.color(col.x,
                            col.y, col.z, 1.0));
                } else {
                    image_writer.setColor(i, j, Color.color(bkgCol.x, bkgCol.y,
                            bkgCol.z, 1.0)); //bkg col
                }
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}

