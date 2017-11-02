package com.example.android.volleyballscorekeeper;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int score_Team_A = 0;
    int score_Team_B = 0;
    int set_Team_A = 0;
    int set_Team_B = 0;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private Chronometer chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayForTeamA(0);
        displayForTeamB(0);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        chronometer = (Chronometer) findViewById(R.id.chronometer);
        findViewById(R.id.btnStart).setOnClickListener(this);
        findViewById(R.id.btnStop).setOnClickListener(this);
    }

    public void addOneForTeamA(View v) {
        score_Team_A++;
        checkSets();
        displayForTeamA(score_Team_A);
    }

    public void addOneForTeamB(View v) {
        score_Team_B++;
        checkSets();
        displayForTeamB(score_Team_B);
    }

    /* Calculate the number of won sets */
    public void checkSets() {
        if ((score_Team_A >= 25) && ((score_Team_A - score_Team_B) > 2)) {
            score_Team_A = 0;
            score_Team_B = 0;
            set_Team_A = set_Team_A + 1;
            displaySetTeamA(set_Team_A);
            displayForTeamA(score_Team_A);
            displayForTeamB(score_Team_B);

        } else if ((score_Team_B >= 25) && ((score_Team_B - score_Team_A) > 2)) {
            score_Team_A = 0;
            score_Team_B = 0;
            set_Team_B = set_Team_B + 1;
            displaySetTeamB(set_Team_B);
            displayForTeamA(score_Team_A);
            displayForTeamB(score_Team_B);
        }
    }

    /**
     * Resets the score for both teams back to zero. * /
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStart:
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                break;
            case R.id.btnStop:
                chronometer.stop();
                break;
        }
    }

    public void resetNow(View v) {
        chronometer.stop();
        chronometer.setBase(SystemClock.elapsedRealtime());
    }

    public void resetScore(View v) {
        score_Team_A = 0;
        score_Team_B = 0;
        set_Team_A = 0;
        set_Team_B = 0;

        displayForTeamA(score_Team_A);
        displayForTeamB(score_Team_B);
        displaySetTeamA(set_Team_A);
        displaySetTeamB(set_Team_B);
    }

    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.scoreTeamA);
        scoreView.setText(String.valueOf(score));
    }

    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.scoreTeamB);
        scoreView.setText(String.valueOf(score));
    }

    public void displaySetTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.setTeamA);
        scoreView.setText(String.valueOf(score));
    }

    public void displaySetTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.setTeamB);
        scoreView.setText(String.valueOf(score));
    }
}