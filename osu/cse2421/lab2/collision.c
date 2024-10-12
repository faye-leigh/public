/* /* Copyright 2024 Faye Leigh */
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
#include "output.h"
#include "text.h"
// Own header file
#include "collision.h"

// Resolve collisions
void resolveCollisions(double ball[], double block[], double paddle[], double walls[]) {
    if (DEBUG) printf("\nCOLLISION CASES\n");
    collisionBlock(ball, block);
    collisionWall(ball, walls);
    collisionPaddle(ball, paddle);
}

// Determine if block collision occurs
void collisionBlock(double ball[], double block[]) {
    if (DEBUG) printf("Block:\n");
    bool collision = (ball[SS_BALL_X] >= block[SS_BLOCK_LEFT]) && (ball[SS_BALL_X] < block[SS_BLOCK_RIGHT]) && (ball[SS_BALL_Y] < block[SS_BLOCK_TOP]) && (ball[SS_BALL_Y] >= block[SS_BLOCK_BOTTOM]);

    // Block collision, determine side
    if (collision)
        collisionBlockVX(ball, block);
    else if (DEBUG)
        debugCollisionBlock(ball, block);
}

// Determine if collision with side is possible using ball VX
void collisionBlockVX(double ball[], double block[]) {
    if (DEBUG) debugCollisionBlockVX(ball);
    if (ball[SS_BALL_VX] == 0) {
        // No horizontal movement, reject left and right edge collision
        collisionBlockVertical(ball, block);
    } else {
        collisionBlockPrev(ball, block);
    }
}

// Determine if side is possible using ball previous position
void collisionBlockPrev(double ball[], double block[]) {
    // Previous position of ball
    double dx = ball[SS_BALL_VX] * DELTA_T;
    double dy = ball[SS_BALL_VY] * DELTA_T;
    double ballPrev[SS_PREV_DATA_COUNT] = {ball[SS_BALL_X] - dx, ball[SS_BALL_Y] - dy, dx, dy};

    bool movingLeft = ball[SS_BALL_VX] < 0;
    bool startedOutsideLeftEdge = ballPrev[SS_PREV_X] < block[SS_BLOCK_LEFT];
    bool startedOutsideRightEdge = ballPrev[SS_PREV_X] > block[SS_BLOCK_RIGHT];
    bool canHitSide = (movingLeft && startedOutsideRightEdge) || startedOutsideLeftEdge;

    if (canHitSide) {
        // Check ball y when crossed edge
        collisionBlockEdge(ballPrev, ball, block);
    } else {
        // Reject side collision
        if (DEBUG) debugCollisionBlockPrev(ballPrev, ball, block);
        collisionBlockVertical(ball, block);
    }
}

// Determine if side collision is possible using ball Y at intersection with block edge
void collisionBlockEdge(double ballPrev[], double ball[], double block[]) {
    // Compute x distance ball moved past edge
    bool collision;
    if (ball[SS_BALL_VX] < 0.0) {
        // Ball moving left, check against block right edge
        collision = collisionBlockEdgeRight(ballPrev, ball, block);
    } else {
        // Ball moving right, check against block left edge
        collision = collisionBlockEdgeLeft(ballPrev, ball, block);
    }
    // Crossed edge above or below block, reject side collision
    if (!collision) collisionBlockVertical(ball, block);
}

// Determine if left edge collision is possible
bool collisionBlockEdgeLeft(double ballPrev[], double ball[], double block[]) {
    // Ball moving right, check against block left edge
    double dxEdge = ball[SS_BALL_X] - block[SS_BLOCK_LEFT];
    // Compute ball y when crossing edge
    double yCross = ball[SS_BALL_Y] - (ballPrev[SS_PREV_DY] * dxEdge / ballPrev[SS_PREV_DX]);
    // Collision possible if ball crossed left edge within block bounds
    bool collision = yCross <= block[SS_BLOCK_TOP] && yCross >= block[SS_BLOCK_BOTTOM];

    if (collision) {
        collisionCorrectionX(ball, block[SS_BLOCK_LEFT]);
        collisionBounceHorizontal(ball);
    } else if (DEBUG) {
        debugCollisionBlockEdge(yCross, ballPrev, ball, block);
    }
    return collision;
}

// Determine if right edge collision is possible
bool collisionBlockEdgeRight(double ballPrev[], double ball[], double block[]) {
    // Ball moving left, check against block right edge
    double dxEdge = block[SS_BLOCK_RIGHT] - ball[SS_BALL_X];
    // Compute ball y when crossing edge
    double yCross = ball[SS_BALL_Y] - (ballPrev[SS_PREV_DY] * dxEdge / ballPrev[SS_PREV_DX]);
    // Collision possible if ball crossed right edge within block bounds
    bool collision = yCross <= block[SS_BLOCK_TOP] && yCross >= block[SS_BLOCK_BOTTOM];

    if (collision) {
        collisionCorrectionX(ball, block[SS_BLOCK_RIGHT]);
        collisionBounceHorizontal(ball);
    } else if (DEBUG) {
        debugCollisionBlockEdge(yCross, ballPrev, ball, block);
    }
    return collision;
}

// Collision with top or bottom of block
void collisionBlockVertical(double ball[], double block[]) {
    if (DEBUG) debugCollisionBlockVertical(ball);
    if (ball[SS_BALL_VY] > 0) {
        // Moving up, hit bottom
        collisionCorrectionY(ball, block[SS_BLOCK_BOTTOM]);
    } else {
        // Moving down, hit top
        collisionCorrectionY(ball, block[SS_BLOCK_TOP]);
    }
    collisionBounceVertical(ball);
}

// Check and correct for any wall collisions
void collisionWall(double ball[], double walls[]) {
    if (DEBUG) printf("Wall:\n");
    if (ball[SS_BALL_X] < walls[SS_WALL_LEFT]) {
        collisionCorrectionX(ball, walls[SS_WALL_LEFT]);
        bounce_message(ball[SS_COLOR], "left wall", ball[SS_BALL_X], ball[SS_BALL_Y]);
    } else if (DEBUG) {
        debugCollisionWall("left", ball, walls);
    }
    if (ball[SS_BALL_X] > walls[SS_WALL_RIGHT]) {
        collisionCorrectionX(ball, walls[SS_WALL_RIGHT]);
        bounce_message(ball[SS_COLOR], "right wall", ball[SS_BALL_X], ball[SS_BALL_Y]);
    } else if (DEBUG) {
        debugCollisionWall("right", ball, walls);
    }
    if (ball[SS_BALL_Y] > walls[SS_WALL_TOP]) {
        collisionCorrectionY(ball, walls[SS_WALL_TOP]);
        bounce_message(ball[SS_COLOR], "top wall", ball[SS_BALL_X], ball[SS_BALL_Y]);
    } else if (DEBUG) {
        debugCollisionWall("top", ball, walls);
    }
}

// Check and correct for paddle collision
void collisionPaddle(double ball[], double paddle[]) {
    if (DEBUG) printf("Paddle:\n");

    // Collision occured if ball is within paddle bounds
    bool collision = ball[SS_BALL_Y] <= paddle[SS_PADDLE_Y] && ball[SS_BALL_X] >= paddle[SS_PADDLE_LEFT] && ball[SS_BALL_X] <= paddle[SS_PADDLE_RIGHT];

    if (collision) {
        collisionCorrectionY(ball, paddle[SS_PADDLE_Y]);
        ball[SS_COLOR] = paddle[SS_COLOR];
        // paddle[SS_PADDLE_SCORE]++;
        bounce_message(ball[SS_COLOR], "paddle", ball[SS_BALL_X], ball[SS_BALL_Y]);
    } else if (DEBUG)
        debugCollisionPaddle(ball, paddle);
}

// Correct for horizontal collision
void collisionCorrectionX(double ball[], double boundary) {
    ball[SS_BALL_X] = 2.0 * boundary - ball[SS_BALL_X];
    ball[SS_BALL_VX] *= -1.0;
}

// Correct for vertical collision
void collisionCorrectionY(double ball[], double boundary) {
    ball[SS_BALL_Y] = 2.0 * boundary - ball[SS_BALL_Y];
    ball[SS_BALL_VY] *= -1.0;
}

// Print block bounce message for horizontal collision
void collisionBounceHorizontal(double ball[]) {
    if (ball[SS_BALL_VX] < 0)
        bounce_message(ball[SS_COLOR], "block left side", ball[SS_BALL_X], ball[SS_BALL_Y]);
    else
        bounce_message(ball[SS_COLOR], "block right side", ball[SS_BALL_X], ball[SS_BALL_Y]);
}

// Print block bounce message for vertical collision
void collisionBounceVertical(double ball[]) {
    if (ball[SS_BALL_VY] < 0)
        bounce_message(ball[SS_COLOR], "block bottom", ball[SS_BALL_X], ball[SS_BALL_Y]);
    else
        bounce_message(ball[SS_COLOR], "block top", ball[SS_BALL_X], ball[SS_BALL_Y]);
}
