package ie.dit;

import controlP5.ControlP5;
import oscP5.OscMessage;
import oscP5.OscP5;
import processing.core.PApplet;
//Connecting to muse headband
//go to cmd
//type: muse-io --device Muse --60hz --osc osc.udp://localhost:5000

//to do here:
//pass headset data into the classes
//create the menu here

/**
 * Created by azkei on 22/03/2016.
 */
public class Main extends PApplet {

    PApplet papplet;
    Menu intf;
    OscP5 oscP5;
    ControlP5 cp5;
    Cube cube;
    Gesture gesture;
    graphWindow gw;

    //public variables where data flows
    public float delta_raw;
    public float theta_raw;
    public float alpha_raw;
    public float beta_raw;
    public float gamma_raw;
    public float concentration_raw;
    public float mellow_raw;
    public float acc_raw;

    static int recvPort = 5000;

    public void setup(){
        oscP5 = new OscP5(this, recvPort);
        gesture = new Gesture(this);
        cube = new Cube(this, alpha_raw,beta_raw,delta_raw,concentration_raw);
        papplet = new PApplet();
        gw = new graphWindow(gw);
        gw.rawAlpha = alpha_raw;
        gw.rawBeta = beta_raw;
        gw.rawDelta = delta_raw;
        gw.rawTheta = theta_raw;
        gw.rawGamma = gamma_raw;

        //this code helps pass by reference menu var
        intf = new Menu(this);
        intf.menu = 0;

    }

    public void settings(){
        size(600,600,P3D);
    }

    public void draw(){


            switch (intf.menu) {
                case 0:
                    //menu screen pass by reference
                    intf.render(intf);
                    break;
                case 1:
                    //gesture here, pass the accelerometer
//                gesture.drawFace(acc_raw);

                    break;
                case 2:
                    //mind cube here
                    cube.drawCube(alpha_raw, beta_raw, delta_raw, concentration_raw);

                    break;
                case 3:
                    //arduino robot here
                    break;
                case 4:
                    //exit
                    exit();
                    break;
            }

    }

    public void keyPressed(){
        if( key == ' ')
        {
            intf.menu = 0;
        }
    }

    //this method brings in real time data from the Muse headband.
    void oscEvent(OscMessage msg){

        if(msg.checkAddrPattern("/muse/acc")==true) {
            acc_raw = msg.get(0).floatValue();
            //System.out.println("acc_raw: "+acc_raw);
        }
        if(msg.checkAddrPattern("/muse/elements/experimental/concentration")==true) {
            concentration_raw = msg.get(0).floatValue();
           // System.out.println("concentration raw: "+concentration_raw);
        }
        if(msg.checkAddrPattern("/muse/eeg")==true) {
            alpha_raw = msg.get(0).floatValue();
           // System.out.println("alpha_Raw: "+alpha_raw);

        }
        if(msg.checkAddrPattern("/muse/elements/beta_relative")==true) {
            beta_raw = msg.get(0).floatValue();
              System.out.println("beta_Raw: "+beta_raw);
        }

//        if(msg.checkAddrPattern("/muse/elements/theta_relative")==true) {
//            theta_raw = msg.get(0).floatValue();
//            theta = theta_raw*1000;
//            thetaRGB = map(theta,0,1000,0,255);
//        }
        if(msg.checkAddrPattern("/muse/elements/delta_relative")==true) {
            delta_raw = msg.get(0).floatValue();
           // System.out.println("delta_raw: "+delta_raw);
        }

        if(msg.checkAddrPattern("muse/elements/experimental/mellow")==true){
            mellow_raw = msg.get(0).floatValue();
        }
    }

    public static void main(String[] args)
    {
        String[] a = {"MAIN"};
        PApplet.runSketch( a, new Main());
    }

}
