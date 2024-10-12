/* Copyright 2024 Faye Leigh */
/*    OSU CSE 2421 - AU24    */

// System libraries
#include <stdio.h>
// Custom libraries

// Constants
#include "subscripts.h"
// C code headers

// Own header file
#include "text.h"

// Print current state of data to stdout
void print_state(double elapsed, double ball[], double block[], double paddle[]) {
    printf("\nElapsed time: %8.5lf\n", elapsed);
    printf("Ball %d is at (%8.5lf, %8.5lf) with velocity (%8.5lf, %8.5lf).\n", (int)ball[SS_COLOR], ball[SS_BALL_X], ball[SS_BALL_Y], ball[SS_BALL_VX], ball[SS_BALL_VY]);
    printf("Block %d is at (%8.5lf, %8.5lf).\n", (int)block[SS_COLOR], block[SS_BALL_X], block[SS_BALL_Y]);
    printf("Paddle %d is at %8.5lf,  size %4.2lf with score %d.\n\n", (int)paddle[SS_COLOR], paddle[SS_PADDLE_X], paddle[SS_PADDLE_SIZE], (int)paddle[SS_PADDLE_SCORE]);
}

// Ball cannot hit left side if moving left, so return char based on vx
char *debugRejectSide(double ball[]) {
    if (ball[SS_BALL_VX] < 0) return "left";
    if (ball[SS_BALL_VX] > 0) return "right";
    return "left and right";
}

// Print debug message: reject block collision
void debugCollisionBlock(double ball[], double block[]) {
    printf("\tReject block collision by ball position\n");
    debugBallPos(ball);
    debugBlockPos(block);
}

// Print debug message: reject horizontal block collision (1)
void debugCollisionBlockVX(double ball[]) {
    printf("\tReject %s edge collision by ball vx\n", debugRejectSide(ball));
    debugBallVel(ball);
}

// Print debug message: reject horizontal block collision (2)
void debugCollisionBlockPrev(double ballPrev[], double ball[], double block[]) {
    printf("\tReject %s edge collision by previous X position\n", debugRejectSide(ball));
    debugBallPrev(ballPrev);
    debugBallPos(ball);
    debugBlockPos(block);
}

// Print debug message: reject horizontal block collision (3)
void debugCollisionBlockEdge(double yCross, double ballPrev[], double ball[], double block[]) {
    printf("\tReject %s edge collision by Y position at block edge intersection\n", debugRejectSide(ball));
    debugBallPrev(ballPrev);
    debugBallPos(ball);
    debugBlockPos(block);
    printf("\t\ty @ cross:\t\t\t\t\t\t\t y\t%8.5lf\n", yCross);
}

// Print debug message: reject vertical block collision
void debugCollisionBlockVertical(double ball[]) {
    if (ball[SS_BALL_VY] > 0) {
        printf("\tReject top edge collision by ball vy\n");
    } else {
        printf("\tReject bottom edge collision by ball vy\n");
    }
    debugBallVel(ball);
}

// Print debug message: reject wall collision
void debugCollisionWall(char *wall, double ball[], double walls[]) {
    printf("\tReject %s wall collision by ball position\n", wall);
    debugBallPos(ball);
    debugWallPos(walls);
}

// Print debug message: reject paddle collision
void debugCollisionPaddle(double ball[], double paddle[]) {
    printf("\tReject paddle collision by ball position\n");
    debugBallPos(ball);
    debugPaddlePos(paddle);
}

// Print debug message: ball position data
void debugBallPos(double ball[]) {
    printf("\t\tBall %d:\t\t(x\t%8.5lf\t\t   , y\t%8.5lf)\n", (int)ball[SS_COLOR], ball[SS_BALL_X], ball[SS_BALL_Y]);
}

// Print debug message: previous ball position and dx/dy
void debugBallPrev(double ballPrev[]) {
    printf("\t\tBall prev:\t(x\t%8.5lf\t\t   , y\t%8.5lf)\n", ballPrev[SS_PREV_X], ballPrev[SS_PREV_Y]);
    printf("\t\t\t\t\t(dx\t%8.5lf\t\t   , dy\t%8.5lf)\n", ballPrev[SS_PREV_DX], ballPrev[SS_PREV_DY]);
}

// Print debug message: ball velocity data
void debugBallVel(double ball[]) {
    printf("\t\tBall %d:\t\t(vx\t%8.5lf\t\t   , vy\t%8.5lf)\n", (int)ball[SS_COLOR], ball[SS_BALL_VX], ball[SS_BALL_VY]);
}

// Print debug message: block position data
void debugBlockPos(double block[]) {
    printf("\t\tBlock %d:\t(x\t%8.5lf - %8.5lf, y\t%8.5lf - %8.5lf)\n", (int)block[SS_COLOR], block[SS_BLOCK_LEFT], block[SS_BLOCK_RIGHT], block[SS_BLOCK_BOTTOM], block[SS_BLOCK_TOP]);
}

// Print debug message: wall position data
void debugWallPos(double walls[]) {
    printf("\t\tWalls:\t\t(x\t%8.5lf - %8.5lf, y\t%8.5lf)\n", walls[SS_WALL_LEFT], walls[SS_WALL_RIGHT], walls[SS_WALL_TOP]);
}

// Print debug message: paddle position data
void debugPaddlePos(double paddle[]) {
    printf("\t\tPaddle %d:\t(x\t%8.5lf - %8.5lf, y\t%8.5lf)\n", (int)paddle[SS_COLOR], paddle[SS_PADDLE_LEFT], paddle[SS_PADDLE_RIGHT], paddle[SS_PADDLE_Y]);
}
