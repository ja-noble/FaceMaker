package com.example.facemaker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.SurfaceView;

import java.util.*;

public class Face extends SurfaceView {
    /* private int skinColor;
    private int eyeColor;
    private int hairColor;
     */

     // initial ints for each color
    /* frankly using ints like these ^ for the colors in android are bad in my opinion.
     In SurfaceViews we've been using paints and therefore can't use a single int value 0-255 for random values.
     We have to come up with better ways to randomize values. We shouldn't restrict the skin, eye, and hair color to one int.
     That just isn't what is happening especially with our rgb sliders that we use in Part B.
     Why should we use one singular int for the feature's colors when we need to use three int values for the sliders.
     That is why I'm replacing these values with new random values. I hope you understand. */

    private int hairStyle;
    private int randomVal1;
    private int randomVal2;
    private int randomVal3;

    Paint mouthPaint = new Paint();
    Paint skinPaint = new Paint();
    Paint eyePaint = new Paint();
    Paint hairPaint = new Paint(); //initialize new paints for features

    Random r = new Random(); // new random to use for the randomize() method

    private FaceModel model; // this is what holds our new faceModel, which just holds public variables needed to affect the Face

    /*
    * Basic constructor for an extended SurfaceView
    * I also make a new model to get all the initial public variables, and then randomize the face initially, as per assignment
    * */
    public Face(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        setWillNotDraw(false);
        model = new FaceModel();
        randomize();

        mouthPaint.setColor(Color.RED);

        setBackgroundColor(Color.GRAY); // i just like this better than the default background
    }

    /*
    * find the width and height of the surfaceView to hopefully be friendly for multiple views
    * draw the 3 separate adjustable features using helper functions
    * draw a final circle for an open mouth, won't change, just used to complete a face
    * */
    @Override
    public void onDraw(Canvas canvas)
    {
        int width = getWidth();
        int height = getHeight();
        drawHair(canvas, width, height);
        drawHead(canvas, width, height);
        drawEyes(canvas, width, height);
        canvas.drawCircle((float)(width/1.95), (float)(height/1.5), 20.0f, mouthPaint);
    }

    /*
    * setARGB with the corresponding rgb values for the skin using the model public variables
    * draw a circle in the middle for the head with new skinPaint
    * */
    public void drawHead (Canvas canvas, int width, int height)
    {
        skinPaint.setARGB(255, this.model.rSkin, this.model.gSkin, this.model.bSkin);
        canvas.drawCircle((width/2), (height/2), 200.0f, skinPaint);
    }

    /*
    * setARGB with corresponding rgb values for the skin using the model's public variables
    * draw 2 circles for two eyes, I experimented with the x and y coordinates until it worked with the width and height
    *  */
    public void drawEyes(Canvas canvas, int width, int height)
    {
        eyePaint.setARGB(255, this.model.rEye, this.model.gEye, this.model.bEye);
        canvas.drawCircle((float)(width/2.1), (float)(height/2.95), 40.0f, eyePaint);
        canvas.drawCircle((float)(width/1.9), (float)(height/2.95), 40.0f, eyePaint);
    }

    /*
     * setARGB with corresponding rgb values for the skin using the model's public variables
     * Check which hairStyle is currently being used in the spinner
     * 0 == Afro for 3 circles for the afro
     * 1 == Mohawk for a thin rectangle down the middle of the top of the head
     * 2 (else) == flat top for thicker rectangle covering whole back of head
     *  */
    public void drawHair(Canvas canvas, int width, int height)
    {
        hairPaint.setARGB(255, this.model.rHair, this.model.gHair, this.model.bHair);
        if (hairStyle == 0) {
            canvas.drawCircle((float) (width / 2.3), (float) (height / 5), 80.0f, hairPaint);
            canvas.drawCircle((float) (width / 2), (float) (height / 6), 80.0f, hairPaint);
            canvas.drawCircle((float) (width / 1.8), (float) (height / 5), 80.0f, hairPaint);
        }
        else if (hairStyle == 1) {
            canvas.drawRect((float)(width/2.1), (float)(height/10), (float)(width/1.9), (float)(height/3), hairPaint);
        }
        else
        {
            canvas.drawRect((float)(width/2.45), (float)(height/10), (float)(width/1.7), (float)(height/3), hairPaint);
        }
    }

    // use random values to set all rgb values in the model
    public void randomize()
    {
        randomVal1 = r.nextInt(256);
        randomVal2 = r.nextInt(256);
        randomVal3 = r.nextInt(256);
        hairStyle = r.nextInt(2);

        this.model.rSkin = randomVal1;
        this.model.gSkin = randomVal2;
        this.model.bSkin = randomVal3;

        this.model.rEye = randomVal2;
        this.model.gEye = randomVal3;
        this.model.bEye = randomVal1;

        this.model.rHair = randomVal3;
        this.model.gHair = randomVal1;
        this.model.bHair = randomVal2;
    }

    public FaceModel getModel() //needed for model since this variable is private
    {
        return this.model;
    }

    public void setHairStyle(int hairStyle) { //needed for controller class to edit hairstyle, a private variable
        this.hairStyle = hairStyle;
    }
}
