package com.example.facemaker;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.SpinnerAdapter;

public class FaceController implements SeekBar.OnSeekBarChangeListener, View.OnClickListener, AdapterView.OnItemSelectedListener, RadioGroup.OnCheckedChangeListener {
    /*
    My Private instance variables.
    Need face to use invalidate and model to access the variables i need
    Need SeekBars because I need to adjust those values through radio buttons
    * */
    private Face face;
    private FaceModel model;
    private SeekBar redBar;
    private SeekBar greenBar;
    private SeekBar blueBar;

    public FaceController (Face face, SeekBar rBar, SeekBar gBar, SeekBar bBar) { // basic constructor, constructing all private variables needed
        this.face = face;
        this.model = this.face.getModel();
        this.redBar = rBar;
        this.greenBar = gBar;
        this.blueBar = bBar;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean user) { // method to be overridden for seekbars
        /*
        * checking if affected by user to see if i need to invalidate face or not
        * checking which feature's rgb values needed to edit based on the Radiobutton position, found through onCheckedChanged button
        * set seekbar progress to whatever the model's corresponding rgb values are
        *  */
        if(user) {
            this.face.invalidate();
        }
        if (this.model.featurePos == 2131230854)
        {
            if(seekBar.getId() == R.id.seekRed) this.model.rHair = progress;
            if(seekBar.getId() == R.id.seekGreen) this.model.gHair = progress;
            if(seekBar.getId() == R.id.seekBlue) this.model.bHair = progress;
        }
        else if(this.model.featurePos == 2131230841)
        {
            if(seekBar.getId() == R.id.seekRed) this.model.rEye = progress;
            if(seekBar.getId() == R.id.seekGreen) this.model.gEye = progress;
            if(seekBar.getId() == R.id.seekBlue) this.model.bEye = progress;
        }
        else
        {
            if(seekBar.getId() == R.id.seekRed) this.model.rSkin = progress;
            if(seekBar.getId() == R.id.seekGreen) this.model.gSkin = progress;
            if(seekBar.getId() == R.id.seekBlue) this.model.bSkin = progress;
        }
    }

    // not needed for this program
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    /*
    listening for onClick on the randomize button
    POST-NOTE:
        I realized i don't actually need the view.getID()... will keep it there anyway
     */
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.randomFace) {
            this.face.randomize();
            this.face.invalidate();
            Log.d("button", "works");
        }
    }

    // Set hair style based on position of the spinner value selected
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        this.face.invalidate();
        this.face.setHairStyle(pos);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    /*
    Set the model's featurePos based on which button is selected
    POST-NOTE: See the comment below to see where I got those huge numbers for the position... would like to know a better way of getting these numbers or using other numbers
     */
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int pos) {
        this.model.featurePos = pos;
        Log.d("buttons","" + this.model.featurePos);
        if(pos == 2131230854) // honestly, i grabbed these positions by using logcat. They correspond to each position of the buttons in the radio group in order.
        {
            redBar.setProgress(this.model.rHair);
            greenBar.setProgress(this.model.gHair);
            blueBar.setProgress(this.model.bHair);
        }
        else if(pos == 2131230841)
        {
            redBar.setProgress(this.model.rEye);
            greenBar.setProgress(this.model.gEye);
            blueBar.setProgress(this.model.bEye);
        }
        else
        {
            redBar.setProgress(this.model.rSkin);
            greenBar.setProgress(this.model.gSkin);
            blueBar.setProgress(this.model.bSkin);
        }
    }
}
