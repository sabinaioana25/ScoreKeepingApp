package com.example.android.volleyballscorekeeper;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    private int score_team_home = 0;
    private int score_team_guest = 0;
    private int set_team_home = 0;
    private int set_team_guest = 0;
    private Button timer_start;
    private Button timer_stop;
    private Button timer_reset;
    private Chronometer chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer = findViewById(R.id.chronometer);
        timer_start = findViewById(R.id.start_timer);
        timer_start.setOnClickListener(this);
        timer_stop = findViewById(R.id.stop_timer);
        timer_stop.setOnClickListener(this);
        timer_reset = findViewById(R.id.reset_timer);
        timer_reset.setOnClickListener(this);

        displayForTeamHome(score_team_home);
        displayForTeamGuest(score_team_guest);
    }

    public void addOneForTeamHome(View v) {
        score_team_home++;
        checkSets();
        displayForTeamHome(score_team_home);
    }

    public void addOneForTeamGuest(View v) {
        score_team_guest++;
        checkSets();
        displayForTeamGuest(score_team_guest);
    }

    /* Calculate the number of won sets */
    public void checkSets() {
        if ((score_team_home >= 25) && ((score_team_home - score_team_guest) > 2)) {
            score_team_home = 0;
            score_team_guest = 0;
            set_team_home++;
            displaySetTeamHome(set_team_home);
            displayForTeamHome(score_team_home);
            displayForTeamGuest(score_team_guest);

        } else if ((score_team_guest >= 25) && ((score_team_guest - score_team_home) >= 2)) {
            score_team_home = 0;
            score_team_guest = 0;
            set_team_guest++;
            displaySetTeamGuest(set_team_guest);
            displayForTeamHome(score_team_home);
            displayForTeamGuest(score_team_guest);
        }

        if (set_team_home == 3 || set_team_guest == 3) {
            resetScore();
            Toast.makeText(this, "Game finished!", Toast.LENGTH_SHORT).show();
            chronometer.stop();
            chronometer.setBase(SystemClock.elapsedRealtime());
        }
    }

    /**
     * Resets the score for both teams back to zero. * /
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_timer:
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                break;
            case R.id.stop_timer:
                chronometer.stop();
                break;
            case R.id.reset_timer:
                chronometer.stop();
                resetNow(view);
                resetScore();
                break;
        }
    }

    public void resetNow(View v) {
        chronometer.stop();
        chronometer.setBase(SystemClock.elapsedRealtime());
    }

    public void resetScore() {
        score_team_home = 0;
        score_team_guest = 0;
        set_team_home = 0;
        set_team_guest = 0;

        displayForTeamHome(score_team_home);
        displayForTeamGuest(score_team_guest);
        displaySetTeamHome(set_team_home);
        displaySetTeamGuest(set_team_guest);
    }

    public void displayForTeamHome(int score) {
        TextView scoreView = findViewById(R.id.points_team_one);
        scoreView.setText(String.valueOf(score));
    }

    public void displayForTeamGuest(int score) {
        TextView scoreView = findViewById(R.id.points_team_two);
        scoreView.setText(String.valueOf(score));
    }

    public void displaySetTeamHome(int score) {
        TextView scoreView = findViewById(R.id.set_team_one);
        scoreView.setText(String.valueOf(score));
    }

    public void displaySetTeamGuest(int score) {
        TextView scoreView = findViewById(R.id.set_team_two);
        scoreView.setText(String.valueOf(score));
    }
}