/* Copyright 2024 Faye Leigh */
/*    OSU CSE 2421 - AU24    */

// System libraries
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
// Custom libraries

// Constants
#include "constants.h"
#include "debug.h"
#include "subscripts.h"
// C code headers
#include "collision.h"
#include "input.h"
#include "output.h"
// Own header file
#include "sim.h"

#define BLOCK_L 3.0
#define BLOCK_H 2.0
#define TOP 20.0
#define LEFT 0.0
#define RIGHT 30.0
#define BOTTOM 0.0

// Run lab2 simulation
bool run() {
    // Initialize simulation variables
    double ball[SS_BALL_DATA_COUNT], block[SS_BLOCK_DATA_COUNT], paddle[SS_PADDLE_DATA_COUNT], walls[SS_WALL_COUNT];
    int ticks = 0;

    // Get input data, exit returning false if fail
    if (!getData(ball, block, paddle, walls)) return false;

    // Print initial state
    master_output(ticks * DELTA_T, ball, block, paddle);

    // Start main simulation loop
    ticks = simLoop(ticks, ball, block, paddle, walls);

    // Print final game state
    final_output(ticks * DELTA_T, ball, block, paddle);

    return true;
}

// Get input data and reconfigure
bool getData(double ball[], double block[], double paddle[], double walls[]) {
    if (!inputBall(ball)) return false;
    if (!inputBlock(block)) return false;
    if (!inputPaddle(paddle)) return false;

    // Reconfigure how game data is stored in variables
    saveBlock(block);
    savePaddle(paddle);
    saveWalls(walls);

    return true;
}

// Main simulation loop
int simLoop(int ticks, double ball[], double block[], double paddle[], double walls[]) {
    bool finished = false;
    while (!finished) {
        ticks++;
        incrementPos(ball);
        resolveCollisions(ball, block, paddle, walls);

        if (GRAPHICS)
            master_output(ticks * DELTA_T, ball, block, paddle);
        else if (ticks % SEC_INTERVAL == 0)
            master_output(ticks * DELTA_T, ball, block, paddle);

        // End simulation if ball exits playing field
        if (ball[SS_BALL_Y] < BOTTOM) finished = true;
    }
    return ticks;
}

// Update ball pos after one tick of movement
void incrementPos(double ball[]) {
    ball[SS_BALL_X] += DELTA_T * ball[SS_BALL_VX];
    ball[SS_BALL_Y] += DELTA_T * ball[SS_BALL_VY];
}

// Reconfigure block data
void saveBlock(double block[]) {
    block[SS_BLOCK_LEFT] = block[SS_BLOCK_X];
    block[SS_BLOCK_RIGHT] = block[SS_BLOCK_X] + BLOCK_L;
    block[SS_BLOCK_TOP] = block[SS_BLOCK_Y] + BLOCK_H;
    block[SS_BLOCK_BOTTOM] = block[SS_BLOCK_Y];
}

// Reconfigure paddle data
void savePaddle(double paddle[]) {
    paddle[SS_PADDLE_LEFT] = paddle[SS_PADDLE_X] - paddle[SS_PADDLE_SIZE];
    paddle[SS_PADDLE_RIGHT] = paddle[SS_PADDLE_X] + paddle[SS_PADDLE_SIZE];
    paddle[SS_PADDLE_Y] = BOTTOM;
}

// Reconfigure wall data
void saveWalls(double walls[]) {
    walls[SS_WALL_TOP] = TOP;
    walls[SS_WALL_LEFT] = LEFT;
    walls[SS_WALL_RIGHT] = RIGHT;
}
