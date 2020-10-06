package com.example.facemaker;

public class FaceModel { //this class is made to keep track of the values for the face, as the controller makes changes to it

    public int rEye = 0;
    public int gEye = 0;
    public int bEye = 0; //rgb values for eyes

    public int rSkin = 0;
    public int gSkin = 0;
    public int bSkin = 0; //rgb values for the skin

    public int rHair = 0;
    public int gHair = 0;
    public int bHair = 0; //rgb values for the hair

    public int featurePos; // checks which feature is chosen at the moment for the radio group

    public boolean randomCheck = true; // check to see if currently using random values, true default so that the initial face is always random


}
