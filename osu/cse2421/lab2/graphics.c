// Copyringht  2024 Neil Kirby not for disclosure wiothout permission
// Comments added by Faye Leigh

// system libraries rarely have bugs, do them first
#include <stdbool.h>
#include <stdio.h>
// custom libraries are usxually clean as well
#include "bko.h"
// constants are next, along with structs
#include "constants.h"
#include "debug.h"
#include "subscripts.h"

// do C code headers last, this file.s header dead last.
#include "n2.h"

// my own header file is included dead last.  IT is MANDATORY!
#include "graphics.h"

// Draw balls
static void draw_balls(double ball[]) {
    bko_ball((int)ball[SS_COLOR], ball[SS_BALL_X], ball[SS_BALL_Y]);
}

// Draw blocks
static void draw_blocks(double block[]) {
    bko_block((int)block[SS_COLOR], block[SS_BLOCK_X], block[SS_BLOCK_Y]);
}

// Draw paddles
static void draw_paddles(double paddle[]) {
    bko_paddle((int)paddle[SS_COLOR], (int)paddle[SS_PADDLE_SCORE], paddle[SS_PADDLE_X], paddle[SS_PADDLE_SIZE]);
}

// Draw everything
void master_draw(double elapsed, double ball[], double block[], double paddle[], bool show_balls) {
    bko_clear();
    bko_sim_time((int)(elapsed * 1000));
    if (show_balls)
        draw_balls(ball);
    draw_blocks(block);
    draw_paddles(paddle);
    bko_refresh();
    microsleep((int)(100000));
}

// Keeping drawing the same frame to "freeze" display
void freeze(double elapsed, double ball[], double block[], double paddle[]) {
    for (double d = 0.0; d < 4.0; d += DELTA_T) {
        master_draw(elapsed, ball, block, paddle, false);
    }
}
