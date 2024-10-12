/* Copyright 2024 Faye Leigh */
/*    OSU CSE 2421 - AU24    */

// System libraries
#include <stdbool.h>
#include <stdio.h>
// Custom libraries

// Constants
#include "subscripts.h"
// C code headers
#include "output.h"
// Own header file meowmeowmeowmeowmeowmeowmeowmeowmeowmeowmeowmeowmeowmeowmeowmeowmeowmeowmeowmeow
#include "input.h"

// Read ball data
bool inputBall(double ball[]) {
    // Read ball count and store scanf result
    int count = 0;
    int rval = scanf("%d", &count);
    // Stop and return scanf value if error reading ball count
    if (rval != SS_BALL_COUNT) {
        scanf_message("ball count", rval, SS_BALL_COUNT);
        return false;
    }
    return inputBallLoop(ball, count);
}

// Loop through all ball data
bool inputBallLoop(double ball[], int count) {
    int i = 0, rval;
    while (i < count && rval != EOF) {
        // Read ball data values and store scanf result
        rval = scanf("%lf %lf %lf %lf %lf", &ball[SS_COLOR], &ball[SS_BALL_X], &ball[SS_BALL_Y], &ball[SS_BALL_VX], &ball[SS_BALL_VY]);

        // Stop and return scanf value if failed to read all ball input data
        if (rval != SS_BALL_INPUT_COUNT) {
            scanf_message("ball", rval, SS_BALL_INPUT_COUNT);
            return false;
        }
        // Stop and return VY if VY = 0.0
        if (ball[SS_BALL_VY] == 0.0) {
            bad_ball_message(ball);
            return false;
        }
        ++i;
    }
    return true;
}

// Read block data
bool inputBlock(double block[]) {
    // Read block count and store scanf result
    int count = 0;
    int rval = scanf("%d", &count);
    // Stop and return scanf value if failed to read block count
    if (rval != SS_BLOCK_COUNT) {
        scanf_message("block count", rval, SS_BLOCK_COUNT);
        return false;
    }
    return inputBlockLoop(block, count);
}

// Loop through all block data
bool inputBlockLoop(double block[], int count) {
    int i = 0, rval;
    while (i < count && rval != EOF) {
        // Read ball data and store scanf result
        rval = scanf("%lf %lf %lf", &block[SS_COLOR], &block[SS_BLOCK_X], &block[SS_BLOCK_Y]);

        // Stop and return scanf value if failed to read all block input data
        if (rval != SS_BLOCK_INPUT_COUNT) {
            scanf_message("block", rval, SS_BLOCK_INPUT_COUNT);
            return false;
        }
        ++i;
    }
    return true;
}

// Read paddle data
bool inputPaddle(double paddle[]) {
    // Read paddle count and store scanf result
    int count = 0;
    int rval = scanf("%d", &count);
    // Stop and return scanf value if failed to read paddle count
    if (rval != SS_PADDLE_COUNT) {
        scanf_message("paddle count", rval, SS_PADDLE_COUNT);
        return false;
    }
    return inputPaddleLoop(paddle, count);
}

// Loop through all paddle data
bool inputPaddleLoop(double paddle[], int count) {
    int i = 0, rval;
    while (i < count && rval != EOF) {
        // Read paddle data values and store scanf result
        rval = scanf("%lf %lf %lf", &paddle[SS_COLOR], &paddle[SS_PADDLE_X], &paddle[SS_PADDLE_SIZE]);

        // Stop and return scanf value if failed to read all paddle input data
        if (rval != SS_PADDLE_INPUT_COUNT) {
            scanf_message("paddle", rval, SS_PADDLE_INPUT_COUNT);
            return false;
        }
        ++i;
    }
    return true;
}