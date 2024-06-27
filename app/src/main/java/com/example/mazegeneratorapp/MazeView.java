package com.example.mazegeneratorapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class MazeView extends View {

    private int[][] maze;
    private Paint wallPaint;
    private Path mazePath;
    private float cellSize;

    public MazeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        wallPaint = new Paint();
        wallPaint.setColor(Color.BLACK);
        wallPaint.setStyle(Paint.Style.STROKE);
        wallPaint.setStrokeWidth(10f); // wall thickness
        mazePath = new Path();
    }

    public void drawMaze(int[][] maze) {
        this.maze = maze;
        calculateMazePath();
        invalidate();
    }

    private void calculateMazePath() {
        mazePath.reset();
        if (maze == null) return;

        int width = getWidth();
        int height = getHeight();

        cellSize = Math.min(width, height) / (float) maze.length;

        float offsetX = (width - (maze.length * cellSize)) / 2;
        float offsetY = (height - (maze.length * cellSize)) / 2;

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == 1) { // If it's a wall
                    float left = j * cellSize + offsetX;
                    float top = i * cellSize + offsetY;
                    float right = (j + 1) * cellSize + offsetX;
                    float bottom = (i + 1) * cellSize + offsetY;

                    if (i > 0 && maze[i - 1][j] == 0) {
                        // Draw top wall
                        mazePath.moveTo(left, top);
                        mazePath.lineTo(right, top);
                    }

                    if (j < maze[0].length - 1 && maze[i][j + 1] == 0) {
                        // Draw right wall
                        mazePath.moveTo(right, top);
                        mazePath.lineTo(right, bottom);
                    }

                    if (i < maze.length - 1 && maze[i + 1][j] == 0) {
                        // Draw bottom wall
                        mazePath.moveTo(left, bottom);
                        mazePath.lineTo(right, bottom);
                    }

                    if (j > 0 && maze[i][j - 1] == 0) {
                        // Draw left wall
                        mazePath.moveTo(left, top);
                        mazePath.lineTo(left, bottom);
                    }
                }
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (maze == null) return;

        // Draw the entire maze path at once
        canvas.drawPath(mazePath, wallPaint);
    }
}
