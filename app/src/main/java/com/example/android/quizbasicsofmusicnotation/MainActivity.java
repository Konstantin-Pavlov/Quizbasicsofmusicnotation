package com.example.android.quizbasicsofmusicnotation;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //declaration of string variables
    String
            question,
            whatIsChecked,
            extraPoint,
            treble_clef_quiz_result, bass_clef_quiz_result,
            treble_clef_a_quiz_result, treble_clef_f_quiz_result ,
            bass_clef_g_quiz_result , bass_clef_d_quiz_result,
            BassCheckBox , TrebleCheckBox ,
            major_quiz_result , minor_quiz_result, major_and_minor_quiz_result,
            name, userPoints, MaxQuizPoints,
            mailTo, emailSubject;
    String correctAnswer;

    byte points = 0;
    byte maxPoints = 14;

    boolean alreadyCounted = false;

    MediaPlayer mPlayer;
    MediaPlayer mPlayerMinor;
    MediaPlayer mPlayerMajorAndMinor;

    CheckBox bass_aceg_aceg_CheckBox;
    CheckBox bass_aceg_aceg2_CheckBox;
    CheckBox bass_aceg_face_CheckBox;
    CheckBox bass_aceg_adbg_CheckBox;

    CheckBox treble_egbdf_egbdf_CheckBox;
    CheckBox treble_egbdf_ecfg_CheckBox;
    CheckBox treble_egbdf_ed_CheckBox;
    CheckBox treble_egbdf_egbdf1_CheckBox;

    EditText nameField;
    EditText extraTask;

    TextView result;

    Button startButton, pauseButton, stopButton, startButtonMinor, pauseButtonMinor, stopButtonMinor, startButtonMajorAndMinor, pauseButtonMajorAndMinor, stopButtonMajorAndMinor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //variables declaration
        question = getString(R.string.Question);
        whatIsChecked =  getString(R.string.Summary);
        extraPoint = getString(R.string.extra_point);
        treble_clef_quiz_result = getString(R.string.Question_1_default) ;
        bass_clef_quiz_result = getString(R.string.Question_2_default) ;
        treble_clef_a_quiz_result = getString(R.string.Question_3_default);
        treble_clef_f_quiz_result = getString(R.string.Question_4_default);
        bass_clef_d_quiz_result = getString(R.string.Question_6_default);
        BassCheckBox = getString(R.string.Question_7_default);
        TrebleCheckBox = getString(R.string.Question_8_default);
        major_quiz_result = getString(R.string.Question_9_default) ;
        minor_quiz_result = getString(R.string.Question_10_default);
        major_and_minor_quiz_result = getString(R.string.Question_11_default);

        name = getString(R.string.Name);
        userPoints = getString(R.string.userPoints);
        MaxQuizPoints = getString(R.string.MaxPoints);

        mPlayer=MediaPlayer.create(this, R.raw.major_sound);
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopPlay();
            }
        });

        mPlayerMinor=MediaPlayer.create(this, R.raw.minor_sound);
        mPlayerMinor.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {stopPlayMinor();}
        });

        mPlayerMajorAndMinor=MediaPlayer.create(this, R.raw.major_and_minor_sound);
        mPlayerMajorAndMinor.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {stopPlayMajorAndMinor();}
        });

        startButton = (Button) findViewById(R.id.start_major);
        pauseButton = (Button) findViewById(R.id.pause_major);
        stopButton = (Button) findViewById(R.id.stop_major);

        startButtonMinor = (Button) findViewById(R.id.start_minor);
        pauseButtonMinor = (Button) findViewById(R.id.pause_minor);
        stopButtonMinor = (Button) findViewById(R.id.stop_minor);

        startButtonMajorAndMinor = (Button) findViewById(R.id.start_major_and_minor);
        pauseButtonMajorAndMinor = (Button) findViewById(R.id.pause_major_and_minor);
        stopButtonMajorAndMinor = (Button) findViewById(R.id.stop_major_and_minor);

        pauseButton.setEnabled(false);
        stopButton.setEnabled(false);

        pauseButtonMinor.setEnabled(false);
        stopButtonMinor.setEnabled(false);

        pauseButtonMajorAndMinor.setEnabled(false);
        stopButtonMajorAndMinor.setEnabled(false);

        bass_aceg_aceg_CheckBox = (CheckBox) findViewById(R.id.bass_aceg_aceg);
        bass_aceg_aceg2_CheckBox  = (CheckBox) findViewById(R.id.bass_aceg_aceg2);
        bass_aceg_face_CheckBox = (CheckBox) findViewById(R.id.bass_aceg_face);
        bass_aceg_adbg_CheckBox = (CheckBox) findViewById(R.id.bass_aceg_adbg);

        treble_egbdf_egbdf_CheckBox  = (CheckBox) findViewById(R.id.treble_egbdf_egbdf);
        treble_egbdf_ecfg_CheckBox  = (CheckBox) findViewById(R.id.treble_egbdf_ecfg);
        treble_egbdf_ed_CheckBox = (CheckBox) findViewById(R.id.treble_egbdf_ed);
        treble_egbdf_egbdf1_CheckBox = (CheckBox) findViewById(R.id.treble_egbdf_egbdf1);

        nameField = (EditText)findViewById(R.id.name_field);
        extraTask = (EditText)findViewById(R.id.extra_task);

        result = (TextView) findViewById(R.id.result);
    }

 /* /////////////   Begin of the methods   ///////////////// */

    public void treble_clef_quiz(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.Treble_Clef_treble_clef:
                if (checked){
                    treble_clef_quiz_result = question + "1: 1/1\n";
                    points++;
                } break;

            case R.id.Treble_Clef_bass_clef:
                if (checked){
                    treble_clef_quiz_result = question + "1: 0/1\n";
                } break;
        }
    }

    public void bass_clef_quiz(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.trebleClefs_treble_btn:
                if (checked){
                    bass_clef_quiz_result = question + "2: 0/1\n";
                } break;

            case R.id.trebleClefs_bass_btn:
                if (checked){
                    bass_clef_quiz_result = question + "2: 1/1\n";
                    points++;
                } break;
        }
    }

    public void treble_clef_a_quiz(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.treble_a__a:
                if (checked){
                    treble_clef_a_quiz_result = question + "3: 1/1\n";
                    points++;
                } break;

            case R.id.treble_a__c:
                if (checked){
                    treble_clef_a_quiz_result = question + "3: 0/1\n";
                } break;

            case R.id.treble_a__f:
                if (checked){
                    treble_clef_a_quiz_result = question + "3: 0/1\n";

                } break;

            case R.id.treble_a__h:
                if (checked){
                    treble_clef_a_quiz_result = question + "3: 0/1\n";
                } break;
        }
    }

    public void treble_clef_f_quiz(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.treble_f__f:
                if (checked){
                    treble_clef_f_quiz_result = question + "4: 1/1\n";
                    points++;
                } break;

            case R.id.treble_f__e:
                if (checked){
                    treble_clef_f_quiz_result = question + "4: 0/1\n";
                } break;

            case R.id.treble_f__b:
                if (checked){
                    treble_clef_f_quiz_result = question + "4: 0/1\n";
                } break;

            case R.id.treble_f__d:
                if (checked){
                    treble_clef_f_quiz_result = question + "4: 0/1\n";
                } break;
        }
    }

    public void bass_clef_g_quiz(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.bass_g__g:
                if (checked){
                    bass_clef_g_quiz_result = question + "5: 1/1\n";
                    points++;
                } break;

            case R.id.bass_g__a:
                if (checked){
                    bass_clef_g_quiz_result = question + "5: 0/1\n";
                } break;

            case R.id.bass_g__e:
                if (checked){
                    bass_clef_g_quiz_result = question + "5: 0/1\n";
                } break;

            case R.id.bass_g__f:
                if (checked){
                    bass_clef_g_quiz_result = question + "5: 0/1\n";
                } break;
        }
    }

    public void bass_clef_d_quiz(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.bass_d__d:
                if (checked){
                    bass_clef_d_quiz_result = question + "6: 1/1\n";
                    points++;
                } break;

            case R.id.bass_d__a:
                if (checked){
                    bass_clef_d_quiz_result = question + "6: 0/1\n";
                } break;

            case R.id.bass_d__c:
                if (checked){
                    bass_clef_d_quiz_result = question + "6: 0/1\n";
                } break;

            case R.id.bass_d__g:
                if (checked){
                    bass_clef_d_quiz_result = question + "6: 0/1\n";
                } break;
        }
    }

    public void collectDataFromCheckBoxes(){

        boolean has_Bass_aceg_aceg_CheckBox = bass_aceg_aceg_CheckBox.isChecked(); //ok
        boolean has_Bass_aceg_aceg2_CheckBox = bass_aceg_aceg2_CheckBox.isChecked(); //ok
        boolean has_bass_aceg_face_CheckBox = bass_aceg_face_CheckBox.isChecked(); //nope
        boolean has_bass_aceg_adbg_CheckBox = bass_aceg_adbg_CheckBox.isChecked(); //nope

        boolean has_Treble_egbdf_egbdf_CheckBox = treble_egbdf_egbdf_CheckBox.isChecked(); //ok
        boolean has_Treble_egbdf_egbdf1_CheckBox = treble_egbdf_egbdf1_CheckBox.isChecked(); //ok
        boolean has_Treble_egbdf_ecfg_CheckBox = treble_egbdf_ecfg_CheckBox.isChecked(); //nope
        boolean has_Treble_egbdf__ed_CheckBox = treble_egbdf_ed_CheckBox.isChecked(); //nope

        if ( has_Bass_aceg_aceg_CheckBox || has_Bass_aceg_aceg2_CheckBox || has_bass_aceg_face_CheckBox || has_bass_aceg_adbg_CheckBox ||
                has_Treble_egbdf_egbdf_CheckBox || has_Treble_egbdf_ecfg_CheckBox || has_Treble_egbdf__ed_CheckBox || has_Treble_egbdf_egbdf1_CheckBox ) {


            if ( has_bass_aceg_face_CheckBox || has_bass_aceg_adbg_CheckBox )
                BassCheckBox = question + "7: 0/2\n";
            else {
                BassCheckBox = has_Bass_aceg_aceg_CheckBox || has_Bass_aceg_aceg2_CheckBox ? question + "7: 1/2\n": question + "7: 0/2\n";
                points += ( (has_Bass_aceg_aceg_CheckBox ? 1 : 0) + (has_Bass_aceg_aceg2_CheckBox ? 1 : 0) );
                if (has_Bass_aceg_aceg_CheckBox && has_Bass_aceg_aceg2_CheckBox)
                    BassCheckBox = question + "7: 2/2\n";
            }

            if ( has_Treble_egbdf_ecfg_CheckBox || has_Treble_egbdf__ed_CheckBox )
                TrebleCheckBox =  question + "8: 0/2\n";
            else {
                TrebleCheckBox = has_Treble_egbdf_egbdf_CheckBox || has_Treble_egbdf_egbdf1_CheckBox ? question + "8: 1/2\n": question + "8: 0/2\n";
                points +=  ( has_Treble_egbdf_egbdf_CheckBox ? 1 : 0 ) + ( has_Treble_egbdf_egbdf1_CheckBox ? 1 : 0 );
                if (has_Treble_egbdf_egbdf_CheckBox && has_Treble_egbdf_egbdf1_CheckBox)
                    TrebleCheckBox = question + "8: 2/2\n";
            }
        }
    }

    public void major_quiz(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.major_major:
                if (checked){
                    major_quiz_result = question + "9: 1/1\n";
                    points++;
                } break;

            case R.id.major_minor:
                if (checked){
                    major_quiz_result = question + "9: 0/1\n";
                } break;
        }
    }

    public void minor_quiz(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.minor_minor:
                if (checked){
                    minor_quiz_result = question + "10: 1/1\n";
                    points++;
                } break;

            case R.id.minor_major:
                if (checked){
                    minor_quiz_result = question + "10: 0/1\n";
                } break;
        }
    }

    public void major_and_minor_quiz(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.major_and_minor_major:
                if (checked){
                    major_and_minor_quiz_result = question + "11: 1/1\n";
                    points++;
                } break;

            case R.id.major_and_minor_minor:
                if (checked){
                    major_and_minor_quiz_result = question + "11: 0/1\n";
                } break;
        }
    }

    /* ///////////sound code//////////////// */

    private void stopPlay(){
        mPlayer.stop();
        startButton.setEnabled(true);
        pauseButton.setEnabled(false);
        stopButton.setEnabled(false);
        try {
            mPlayer.prepare();
            mPlayer.seekTo(0);
            startButton.setEnabled(true);
        }
        catch (Throwable t) {
            Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void stopPlayMinor(){
        mPlayerMinor.stop();
        startButtonMinor.setEnabled(true);
        pauseButtonMinor.setEnabled(false);
        stopButtonMinor.setEnabled(false);
        try {
            mPlayerMinor.prepare();
            mPlayerMinor.seekTo(0);
            startButtonMinor.setEnabled(true);
        }
        catch (Throwable t) {
            Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void stopPlayMajorAndMinor(){
        mPlayerMajorAndMinor.stop();
        startButtonMajorAndMinor.setEnabled(true);
        pauseButtonMajorAndMinor.setEnabled(false);
        stopButtonMajorAndMinor.setEnabled(false);
        try {
            mPlayerMajorAndMinor.prepare();
            mPlayerMajorAndMinor.seekTo(0);
            startButtonMajorAndMinor.setEnabled(true);
        }
        catch (Throwable t) {
            Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    /* ////////////////////////////// */

    public void play_major(View view){
        mPlayer.start();
        startButton.setEnabled(false);
        pauseButton.setEnabled(true);
        stopButton.setEnabled(true);
    }
    public void pause_major(View view){
        mPlayer.pause();
        startButton.setEnabled(true);
        pauseButton.setEnabled(false);
        stopButton.setEnabled(true);
    }
    public void stop_major(View view){
        stopPlay();
    }

/* ////////////////////////////// */

    public void play_minor(View view){

        mPlayerMinor.start();
        startButtonMinor.setEnabled(false);
        pauseButtonMinor.setEnabled(true);
        stopButtonMinor.setEnabled(true);
    }
    public void pause_minor(View view){

        mPlayerMinor.pause();
        startButtonMinor.setEnabled(true);
        pauseButtonMinor.setEnabled(false);
        stopButtonMinor.setEnabled(true);
    }
    public void stop_minor(View view){
        stopPlayMinor();
    }

/* ////////////////////////////// */

    public void play_major_and_minor(View view){

        mPlayerMajorAndMinor.start();
        startButtonMajorAndMinor.setEnabled(false);
        pauseButtonMajorAndMinor.setEnabled(true);
        stopButtonMajorAndMinor.setEnabled(true);
    }
    public void pause_major_and_minor(View view){

        mPlayerMajorAndMinor.pause();
        startButtonMajorAndMinor.setEnabled(true);
        pauseButtonMajorAndMinor.setEnabled(false);
        stopButtonMajorAndMinor.setEnabled(true);
    }
    public void stop_major_and_minor(View view){
        stopPlayMajorAndMinor();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPlayer.isPlaying()) {
            stopPlay();
        }
        if (mPlayerMinor.isPlaying()) {
            stopPlay();
        }
        if (mPlayerMajorAndMinor.isPlaying()) {
            stopPlay();
        }
    }

    /* ///////////end of sound code//////////////// */

    public String getName(){
        return nameField.getText().toString(); //return editable object, then method toString() makes it String type (chaining method calls);
    }

    public String summary(){
        correctAnswer = "treble clef";
        String userExtraPoint = extraTask.getText().toString();

        boolean isEditTextTrue = correctAnswer.equals(userExtraPoint);

        if (!alreadyCounted) {
            collectDataFromCheckBoxes();

            whatIsChecked += name + getName() + "\n\n";

            //treble clef extra point
            if (isEditTextTrue){
                points++;
                whatIsChecked += extraPoint;
            }
            else
                whatIsChecked += "there is no extra point\n";

            double percentage = ((double) points / maxPoints)*100;
            String formattedPercentage = String.format("%.2f", percentage);

            whatIsChecked +=  (treble_clef_quiz_result + bass_clef_quiz_result +
                    treble_clef_a_quiz_result + treble_clef_f_quiz_result + bass_clef_g_quiz_result + bass_clef_d_quiz_result +
                    BassCheckBox + TrebleCheckBox +
                    major_quiz_result + minor_quiz_result + major_and_minor_quiz_result +
                    userPoints + points + MaxQuizPoints + maxPoints + "\n" + formattedPercentage + "%");
            alreadyCounted = true;
        }

        return whatIsChecked;
    }

    public void displayWhatIsChecked(View v) {
        collectDataFromCheckBoxes();

        double percentage = ((double) points / maxPoints)*100;
        String formattedPercentage = String.format("%.2f", percentage);
        String score = "your score is " + formattedPercentage + "%!";

        whatIsChecked += (name + getName() + "\n\n" + treble_clef_quiz_result + bass_clef_quiz_result +
                treble_clef_a_quiz_result + treble_clef_f_quiz_result + bass_clef_g_quiz_result + bass_clef_d_quiz_result +
                BassCheckBox + TrebleCheckBox +
                major_quiz_result + minor_quiz_result + major_and_minor_quiz_result +
                userPoints + points + MaxQuizPoints + maxPoints + "\n" + formattedPercentage + "%");

        display(whatIsChecked);
        Context context = getApplicationContext();
        CharSequence text = score;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        resetAll();
    }

    public void resetWhatIsChecked(View v) {

        resetAll();
        display(whatIsChecked);

    }

    public void send_via_email (View v){
        //open mail
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Music Notation Quiz results for " + getName()); //email SUBJECT
        intent.putExtra(Intent.EXTRA_TEXT, summary()); //email body
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void display(String s) {
        //TextView result = (TextView) findViewById(R.id.result);
        result.setText(String.valueOf(s));
    }

    public void resetAll(){

        RadioGroup question1  =  findViewById(R.id.Treble_Clef_r_btns);
        RadioGroup question2  =  findViewById(R.id.Bass_Clef_r_btns);
        RadioGroup question3  =  findViewById(R.id.Treble_Clef_a_RadioGroup);
        RadioGroup question4  =  findViewById(R.id.Treble_Clef_f_RadioGroup);
        RadioGroup question5  =  findViewById(R.id.Bass_Clef_g_RadioGroup);
        RadioGroup question6  =  findViewById(R.id.Bass_Clef_d_RadioGroup);
        RadioGroup question9  =  findViewById(R.id.majorRadioGroup);
        RadioGroup question10 =  findViewById(R.id.minorRadioGroup);
        RadioGroup question11 =  findViewById(R.id.major_and_minor_RadioGroup);

        //clear all radio buttons
        question1.clearCheck();
        question2.clearCheck();
        question3.clearCheck();
        question4.clearCheck();
        question5.clearCheck();
        question6.clearCheck();
        question9.clearCheck();
        question10.clearCheck();
        question11.clearCheck();

        whatIsChecked =  getString(R.string.Summary);
        treble_clef_quiz_result = getString(R.string.Question_1_default) ;
        bass_clef_quiz_result = getString(R.string.Question_2_default) ;
        treble_clef_a_quiz_result = getString(R.string.Question_3_default);
        treble_clef_f_quiz_result = getString(R.string.Question_4_default);
        bass_clef_d_quiz_result = getString(R.string.Question_6_default);
        BassCheckBox = getString(R.string.Question_7_default);
        TrebleCheckBox = getString(R.string.Question_8_default);
        major_quiz_result = getString(R.string.Question_9_default) ;
        minor_quiz_result = getString(R.string.Question_10_default);
        major_and_minor_quiz_result = getString(R.string.Question_11_default);

        points = 0;

        alreadyCounted = false;
    }
}
