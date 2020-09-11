package com.example.facemaker;

import java.util.*;

public class Face {
    private int skinColor;
    private int eyeColor;
    private int hairColor;
    private int hairStyle;

    public Face (int sColor, int eColor, int hColor, int hStyle)
    {
        skinColor = sColor;
        eyeColor = eColor;
        hairColor = hColor;
        hairStyle = hStyle;
    }

    public void randomize()
    {
        Random r = new Random();
        skinColor = r.nextInt(256);
        eyeColor = r.nextInt(256);
        hairColor = r.nextInt(256);
        hairStyle = r.nextInt(4);
    }
}
