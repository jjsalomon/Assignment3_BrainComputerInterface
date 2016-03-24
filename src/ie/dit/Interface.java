package ie.dit;

import controlP5.ControlP5;
import processing.core.PApplet;

/**
 * Created by azkei on 22/03/2016.
 */
public class Interface {
    PApplet papplet;
    ControlP5 cp5;


    //passing processing applet so we can use proccesing methods
    Interface(PApplet papplet){
        this.papplet = papplet;

    }


    void menu(){
        //video background perhaps?

//        papplet.textSize(45);
//        papplet.text("Mind Ball",200,200);
//        papplet.text("Mind Cube",200,300);
//        papplet.text("Exit",200,400);

        cp5.addButton("Face Gesture")
                .setValue(0)
                .setPosition(200,200)
                .setSize(200,19)
        ;
        cp5.addButton("Mind Cube")
                .setValue(0)
                .setPosition(200,300)
                .setSize(200,19)
        ;
        cp5.addButton("Exit")
                .setValue(0)
                .setPosition(100,400)
                .setSize(200,19)
        ;
//

    }


}
