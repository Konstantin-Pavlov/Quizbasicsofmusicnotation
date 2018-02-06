package com.example.android.quizbasicsofmusicnotation;

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

    //hard coded string - works fine
    String whatIsChecked =  "Summary:\n", treble_clef_quiz_result = "Question 1: not answered\n", bass_clef_quiz_result = "Question 2: not answered\n",

                            treble_clef_a_quiz_result = "Question 3: not answered\n", treble_clef_f_quiz_result = "Question 4: not answered\n",
                            bass_clef_g_quiz_result = "Question 5: not answered\n", bass_clef_d_quiz_result = "Question 6: not answered\n",
                            BassCheckBox = "Question 7: not answered\n",  TrebleCheckBox = "Question 8: not answered\n",
                            major_quiz_result = "Question 9: not answered\n",  minor_quiz_result = "Question 10: not answered\n",  major_and_minor_quiz_result = "Question 11: not answered\n";

    // using strings.xml - doesn't work
    /*    String whatIsChecked =  getString(R.string.Summary), treble_clef_quiz_result = getString(R.string.Question_1_default), bass_clef_quiz_result = getString(R.string.Question_2_default),

                            treble_clef_a_quiz_result = getString(R.string.Question_3_default), treble_clef_f_quiz_result = getString(R.string.Question_4_default),
                            bass_clef_g_quiz_result = getString(R.string.Question_5_default), bass_clef_d_quiz_result = getString(R.string.Question_6_default),
                            BassCheckBox = getString(R.string.Question_7_default),  TrebleCheckBox = getString(R.string.Question_8_default),
                            major_quiz_result = getString(R.string.Question_9_default),  minor_quiz_result = getString(R.string.Question_10_default),  major_and_minor_quiz_result = getString(R.string.Question_11_default);*/


    byte points = 0, maxPoints = 12;

    boolean alreadyCounted = false;

    MediaPlayer mPlayer;
    MediaPlayer mPlayerMinor;
    MediaPlayer mPlayerMajorAndMinor;

/*    CheckBox bass_aceg_aceg2_CheckBox  = (CheckBox) findViewById(R.id.bass_aceg_aceg2);
    CheckBox bass_aceg_face_CheckBox = (CheckBox) findViewById(R.id.bass_aceg_face);
    CheckBox bass_aceg_adbg_CheckBox = (CheckBox) findViewById(R.id.bass_aceg_adbg);

    CheckBox treble_egbdf_egbdf_CheckBox  = (CheckBox) findViewById(R.id.treble_egbdf_egbdf);
    CheckBox treble_egbdf_ecfg_CheckBox  = (CheckBox) findViewById(R.id.treble_egbdf_ecfg);
    CheckBox treble_egbdf_ed_CheckBox = (CheckBox) findViewById(R.id.treble_egbdf_ed);
    CheckBox treble_egbdf_gbdfa_CheckBox = (CheckBox) findViewById(R.id.treble_egbdf_gbdfa);*/

    CheckBox bass_aceg_aceg_CheckBox;
    CheckBox bass_aceg_aceg2_CheckBox;
    CheckBox bass_aceg_face_CheckBox;
    CheckBox bass_aceg_adbg_CheckBox;

    CheckBox treble_egbdf_egbdf_CheckBox;
    CheckBox treble_egbdf_ecfg_CheckBox;
    CheckBox treble_egbdf_ed_CheckBox;
    CheckBox treble_egbdf_gbdfa_CheckBox;

    EditText nameField;

    TextView result;

    //RadioGroup radioGroup;
    //RadioGroup radioGroup_Treble_Clef_btns = (RadioGroup) findViewById(R.id.Treble_Clef_btns);

    Button startButton, pauseButton, stopButton, startButtonMinor, pauseButtonMinor, stopButtonMinor, startButtonMajorAndMinor, pauseButtonMajorAndMinor, stopButtonMajorAndMinor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        treble_egbdf_gbdfa_CheckBox = (CheckBox) findViewById(R.id.treble_egbdf_gbdfa);

        nameField = (EditText)findViewById(R.id.name_field);

        result = (TextView) findViewById(R.id.result);


    }

    /* ////////////////////////////// */

/*    public void onClick(View view) {
        // очистить все переключатели
        radioGroup.clearCheck();
    }
*/

 /* ////////////////////////////// */


    public void treble_clef_quiz(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.Treble_Clef_treble_clef:
                if (checked){
                    treble_clef_quiz_result = "Question 1: 1/1\n";
                    points++;
                } break;

            case R.id.Treble_Clef_bass_clef:
                if (checked){
                    treble_clef_quiz_result = "Question 1: 0/1\n";
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
                    bass_clef_quiz_result = "Question 2: 0/1\n";
                } break;

            case R.id.trebleClefs_bass_btn:
                if (checked){
                    bass_clef_quiz_result = "Question 2: 1/1\n";
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
                    treble_clef_a_quiz_result = "Question 3: 1/1\n";
                    points++;
                } break;

            case R.id.treble_a__c:
                if (checked){
                    treble_clef_a_quiz_result = "Question 3: 0/1\n";
                } break;

            case R.id.treble_a__f:
                if (checked){
                    treble_clef_a_quiz_result = "Question 3: 0/1\n";

                } break;

            case R.id.treble_a__h:
                if (checked){
                    treble_clef_a_quiz_result = "Question 3: 0/1\n";
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
                    treble_clef_f_quiz_result = "Question 4: 1/1\n";
                    points++;
                } break;

            case R.id.treble_f__e:
                if (checked){
                    treble_clef_f_quiz_result = "Question 4: 0/1\n";
                } break;

            case R.id.treble_f__b:
                if (checked){
                    treble_clef_f_quiz_result = "Question 4: 0/1\n";
                } break;

            case R.id.treble_f__d:
                if (checked){
                    treble_clef_f_quiz_result = "Question 4: 0/1\n";
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
                    bass_clef_g_quiz_result = "Question 5: 1/1\n";
                    points++;
                } break;

            case R.id.bass_g__a:
                if (checked){
                    bass_clef_g_quiz_result = "Question 5: 0/1\n";
                } break;

            case R.id.bass_g__e:
                if (checked){
                    bass_clef_g_quiz_result = "Question 5: 0/1\n";
                } break;

            case R.id.bass_g__f:
                if (checked){
                    bass_clef_g_quiz_result = "Question 5: 0/1\n";
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
                    bass_clef_d_quiz_result = "Question 6: 1/1\n";
                    points++;
                } break;

            case R.id.bass_d__a:
                if (checked){
                    bass_clef_d_quiz_result = "Question 6: 0/1\n";
                } break;

            case R.id.bass_d__c:
                if (checked){
                    bass_clef_d_quiz_result = "Question 6: 0/1\n";
                } break;

            case R.id.bass_d__g:
                if (checked){
                    bass_clef_d_quiz_result = "Question 6: 0/1\n";
                } break;
        }
    }


    public void collectDataFromCheckBoxes(){
/*        CheckBox bass_aceg_aceg_CheckBox  = (CheckBox) findViewById(R.id.bass_aceg_aceg);
        CheckBox bass_aceg_aceg2_CheckBox  = (CheckBox) findViewById(R.id.bass_aceg_aceg2);
        CheckBox bass_aceg_face_CheckBox = (CheckBox) findViewById(R.id.bass_aceg_face);
        CheckBox bass_aceg_adbg_CheckBox = (CheckBox) findViewById(R.id.bass_aceg_adbg);

        CheckBox treble_egbdf_egbdf_CheckBox  = (CheckBox) findViewById(R.id.treble_egbdf_egbdf);
        CheckBox treble_egbdf_ecfg_CheckBox  = (CheckBox) findViewById(R.id.treble_egbdf_ecfg);
        CheckBox treble_egbdf_ed_CheckBox = (CheckBox) findViewById(R.id.treble_egbdf_ed);
        CheckBox treble_egbdf_gbdfa_CheckBox = (CheckBox) findViewById(R.id.treble_egbdf_gbdfa);*/

        boolean has_Bass_aceg_aceg_CheckBox = bass_aceg_aceg_CheckBox.isChecked(); //ok
        boolean has_Bass_aceg_aceg2_CheckBox = bass_aceg_aceg2_CheckBox.isChecked(); //ok
        boolean has_bass_aceg_face_CheckBox = bass_aceg_face_CheckBox.isChecked(); //nope
        boolean has_bass_aceg_adbg_CheckBox = bass_aceg_adbg_CheckBox.isChecked(); //nope

        boolean has_Treble_egbdf_egbdf_CheckBox = treble_egbdf_egbdf_CheckBox.isChecked(); //ok
        boolean has_Treble_egbdf_ecfg_CheckBox = treble_egbdf_ecfg_CheckBox.isChecked(); //nope
        boolean has_Treble_egbdf__ed_CheckBox = treble_egbdf_ed_CheckBox.isChecked(); //nope
        boolean has_Treble_egbdf_gbdfa_CheckBox = treble_egbdf_gbdfa_CheckBox.isChecked(); //nope

        if ( has_Bass_aceg_aceg_CheckBox || has_Bass_aceg_aceg2_CheckBox || has_bass_aceg_face_CheckBox || has_bass_aceg_adbg_CheckBox ||
                has_Treble_egbdf_egbdf_CheckBox || has_Treble_egbdf_ecfg_CheckBox || has_Treble_egbdf__ed_CheckBox || has_Treble_egbdf_gbdfa_CheckBox ) {


            if ( has_bass_aceg_face_CheckBox || has_bass_aceg_adbg_CheckBox )
                BassCheckBox = "Question 7: 0/2\n";
            else {
                BassCheckBox = has_Bass_aceg_aceg_CheckBox || has_Bass_aceg_aceg2_CheckBox ? "Question 7: 1/2\n": "Question 7: 0/2\n";
                points += ( (has_Bass_aceg_aceg_CheckBox ? 1 : 0) + (has_Bass_aceg_aceg2_CheckBox ? 1 : 0) );
                if (has_Bass_aceg_aceg_CheckBox && has_Bass_aceg_aceg2_CheckBox)
                    BassCheckBox = "Question 7: 2/2\n";
            }

            if ( has_Treble_egbdf_ecfg_CheckBox || has_Treble_egbdf__ed_CheckBox || has_Treble_egbdf_gbdfa_CheckBox )
                TrebleCheckBox =  "Question 8: 0/1\n";
            else {
                TrebleCheckBox = has_Treble_egbdf_egbdf_CheckBox ? "Question 8: 1/1\n": "Question 8: 0/1\n";
                points +=  ( has_Treble_egbdf_egbdf_CheckBox ? 1 : 0 );
            }

        }



        //if (has_Bass_aceg_aceg_CheckBox || has_Bass_aceg_aceg2_CheckBox || has_Treble_egbdf_egbdf_CheckBox ) {
        //if ( !(has_bass_aceg_face_CheckBox || has_bass_aceg_adbg_CheckBox || has_Treble_egbdf_ecfg_CheckBox || has_Treble_egbdf__ed_CheckBox || has_Treble_egbdf_gbdfa_CheckBox)  ) {

    }


    public void major_quiz(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.major_major:
                if (checked){
                    major_quiz_result = "Question 9: 1/1\n";
                    points++;
                } break;

            case R.id.major_minor:
                if (checked){
                    major_quiz_result = "Question 9: 0/1\n";
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
                    minor_quiz_result = "Question 10: 1/1\n";
                    points++;
                } break;

            case R.id.minor_major:
                if (checked){
                    minor_quiz_result = "Question 10: 0/1\n";
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
                    major_and_minor_quiz_result = "Question 11: 1/1\n";
                    points++;
                } break;

            case R.id.major_and_minor_minor:
                if (checked){
                    major_and_minor_quiz_result = "Question 11: 0/1\n";
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
        //EditText nameField = (EditText)findViewById(R.id.name_field);
        String name = nameField.getText().toString(); //return editable object, then method toString() makes it String type (chaining method calls)
        return name;
    }




    public String summary(){

        if (!alreadyCounted) {
            collectDataFromCheckBoxes();

            double percentage = ((double) points / maxPoints)*100;
            String formattedPercentage = String.format("%.2f", percentage);
            //String formattedDouble = new DecimalFormat("#0.00").format(0.1321231);

            whatIsChecked += ("Name: " + getName() + "\n\n" + treble_clef_quiz_result + bass_clef_quiz_result +
                    treble_clef_a_quiz_result + treble_clef_f_quiz_result + bass_clef_g_quiz_result + bass_clef_d_quiz_result +
                    BassCheckBox + TrebleCheckBox +
                    major_quiz_result + minor_quiz_result + major_and_minor_quiz_result +
                    "Points: " + points + "\nMax points = " + maxPoints + "\n" + formattedPercentage + "%");
            alreadyCounted = true;
        }

        return whatIsChecked;
    }


    public void displayWhatIsChecked(View v) {
        display(summary());
        //resetAll();
    }

    public void resetWhatIsChecked(View v) {

        resetAll();
        display(whatIsChecked);

    }

    public void send_via_email (View v){

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        //intent.putExtra(Intent.EXTRA_EMAIL, addresses); //addresses - a String[] holding e-mail addresses that should be delivered to.
        intent.putExtra(Intent.EXTRA_SUBJECT, "Music Notation Quiz results for " + getName()); //email SUBJECT
        intent.putExtra(Intent.EXTRA_TEXT, summary()); //email body
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

        //resetAll();
    }


    public void display(String s) {
        //TextView result = (TextView) findViewById(R.id.result);
        result.setText(String.valueOf(s));
    }

    public void resetAll(){
        //whatIsChecked = "";
        whatIsChecked =  "";

/*        treble_clef_quiz_result = bass_clef_quiz_result =
        treble_clef_a_quiz_result = treble_clef_f_quiz_result = bass_clef_g_quiz_result = bass_clef_d_quiz_result =
        BassCheckBox = TrebleCheckBox = "";*/

        treble_clef_quiz_result = "Question 1: not answered\n";
        bass_clef_quiz_result = "Question 2: not answered\n";
        treble_clef_a_quiz_result = "Question 3: not answered\n";
        treble_clef_f_quiz_result = "Question 4: not answered\n";
        bass_clef_g_quiz_result = "Question 5: not answered\n";
        bass_clef_d_quiz_result = "Question 6: not answered\n";
        BassCheckBox = "Question 7: not answered\n";
        TrebleCheckBox = "Question 8: not answered\n";
        major_quiz_result = "Question 9: not answered\n";
        minor_quiz_result = "Question 10: not answered\n";
        major_and_minor_quiz_result = "Question 11: not answered\n";

        points = 0;

        alreadyCounted = false;

        //clear all radio buttons
        // radioGroup_Treble_Clef_btns.clearCheck();
    }


}
