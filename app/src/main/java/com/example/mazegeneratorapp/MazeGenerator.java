package com.example.mazegeneratorapp;

import java.util.Random;

public class MazeGenerator {

    private final int size;
    private final int[][] maze;
    private final boolean[][] visited;
    private final Random random;

    public MazeGenerator(int size) {
        this.size = size;
        this.maze = new int[size][size];
        this.visited = new boolean[size][size];
        this.random = new Random();

        generateMaze();
    }

    private void generateMaze() {
        initializeMaze();

        int startX = 1;
        int startY = 1;
        visited[startX][startY] = true;
        maze[startX][startY] = 0; // this is teh start position as a path
        recursiveBacktracking(startX, startY);

        // ensuring that entrance and exit are open
        maze[0][1] = 0; // Entrance
        maze[size - 1][size - 2] = 0; // Exit
    }

    private void initializeMaze() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                maze[i][j] = 1; // Initializing all cells as walls (1 equates to wall)
                visited[i][j] = false;
            }
        }
    }

    private void recursiveBacktracking(int x, int y) {
        int[] directions = {1, 2, 3, 4};
        shuffleArray(directions);

        for (int direction : directions) {
            int nx = x, ny = y;

            switch (direction) {
                case 1: // Up
                    nx -= 2;
                    break;
                case 2: // Right
                    ny += 2;
                    break;
                case 3: // Down
                    nx += 2;
                    break;
                case 4: // Left
                    ny -= 2;
                    break;
            }

            if (isValid(nx, ny) && !visited[nx][ny]) {
                visited[nx][ny] = true;
                maze[nx][ny] = 0; // Path
                maze[(x + nx) / 2][(y + ny) / 2] = 0; // removing the wall
                recursiveBacktracking(nx, ny);
            }
        }
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size;
    }

    private void shuffleArray(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    public int[][] getMaze() {
        return maze;
    }
}
