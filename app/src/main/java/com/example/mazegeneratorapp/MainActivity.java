package com.example.mazegeneratorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MazeView mazeView;
    private Button generateButton;
    private MazeGenerator mazeGenerator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mazeView = findViewById(R.id.mazeView);
        generateButton = findViewById(R.id.generateButton);

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateMaze();
            }
        });

        generateMaze();
    }

    private void generateMaze() {
        int mazeSize = 15; // maze size
        mazeGenerator = new MazeGenerator(mazeSize);
        mazeView.drawMaze(mazeGenerator.getMaze());
    }
}
