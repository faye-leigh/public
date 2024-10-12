// Copyright 2024 Neil Kirby, not for dislosure without permission

// system libraries rarely have bugs, do them first
#include <stdbool.h>
#include <stdio.h>

// constants are next, along with structs
#include "constants.h"
#include "debug.h"
#include "structs.h"

// do C code headers last, this file.s header dead last.
#include "input.h"
#include "output.h"

// my own header file is included dead last.  IT is MANDATORY!
#include "sim.h"

// 3defines that are local to me only go here
#define FIELD_BOTTOM 0.0
#define FIELD_TOP 20.0
#define FIELD_LEFT 0.0
#define FIELD_RIGHT 30.0

#define BLOCK_LENGTH 3.0
#define BLOCK_HEIGHT 2.0

/* No one job comment */
static void basic_motion(struct Ball *ball) {
    // update the ball's position
    ball->x_position += ball->x_velocity * DELTA_T;
    ball->y_position += ball->y_velocity * DELTA_T;
}

/* No one job comment.
I prefer the structs over double arrays and will be using them for future labs.
Multiple return statements */
static bool reject_on_iy(struct Ball *ball, struct Paddle *block,
                         double offset) {
    // where was the ball before it moved?
    double px = ball->x_position - ball->x_velocity * DELTA_T;
    double py = ball->y_position - ball->y_velocity * DELTA_T;
    double dx = ball->x_position - px;  // travelled this far in x
    double dy = ball->y_position - py;  // travelled this far in y
    // offset is used to do the right side of the block
    double percent = (block->x_position + offset - px) / dx;
    // where (in y) did the ball cross the side of the block?
    double iy = py + percent * (dy);

    /* I prefer making a separate .c file that handles debug statements */
    if (DEBUG) {
        printf("side case: px %f py %f    dx %f dy %f   percent %f offset %f\n",
               px, py, dx, dy, percent, offset);
        printf("    block: x %f y %f\n", block->x_position, block->y_position);
        printf("    ball: x %f y %f\n", ball->x_position, ball->y_position);
    }
    if (iy < block->y_position) {
        if (DEBUG)
            printf("    rejected: iy %f < block->y_position %f\n", iy,
                   block->y_position);
        return true;
    }

    if (iy > block->y_position + BLOCK_HEIGHT) {
        if (DEBUG)
            printf(
                "    rejected: iy %f > block->y_position + BLOCK_HEIGHT %f\n",
                iy, block->y_position + BLOCK_HEIGHT);
        return true;
    }
    return false;
}

/* I made mode of my collision functions able to handle either left or right
 * cases, which might be too much complexity. Would have been easier to write if
 * split left and right cases, but I do like the simplicity of calling when a
 * function can handle both left and right */

/* No one job comment. No debug statements. Multiple return statements */
static bool simple_left_rejections(struct Ball *ball, struct Paddle *block) {
    if (ball->x_velocity <= 0.0) return true;  // not when it is going right
    double px = ball->x_position - ball->x_velocity * DELTA_T;
    // was it already left of the left side?
    if (px >= block->x_position) return true;  // didn't cross the left wall
    return false;
}

/* No one job comment. No debug statements. Multiple return statements */
static bool left_case(struct Ball *ball, struct Paddle *block) {
    // if we get a rejection, return false (didn't hit the block)
    // otherwise we have a hit, return true after dealing with changes to the
    // ball
    if (simple_left_rejections(ball, block)) {
        // DEBUG goes here?
        return false;
    }
    if (reject_on_iy(ball, block, 0.0)) return false;

    // reflect x position, negate x velocty, print, and return true
    //  note that some of the lines below are different in the right case
    ball->x_position = 2 * block->x_position - ball->x_position;
    ball->x_velocity = -ball->x_velocity;
    bounce_message((int)ball->color, "block left side", ball->x_position,
                   ball->y_position);
    return true;
}

/* No one job comment. No debug statements. Multiple return statements */
static bool simple_right_rejections(struct Ball *ball, struct Paddle *block) {
    if (ball->x_velocity >= 0.0) return true;  // not when it is going left
    double px = ball->x_position - ball->x_velocity * DELTA_T;
    // was it already right of the right side?
    if (px <= block->x_position + BLOCK_LENGTH)
        return true;  // didn't cross the right wall
    return false;
}

/* No one job comment. No debug statements. Multiple return statements */
static bool right_case(struct Ball *ball, struct Paddle *block) {
    // if we get a rejection, return false (didn't hit the block)
    if (simple_right_rejections(ball, block)) {
        // DEBUG goes here?
        return false;
    }
    // we offset to the right side of the block
    if (reject_on_iy(ball, block, BLOCK_LENGTH)) return false;

    // reflect x position, negate x velocty, print, and return true
    ball->x_position =
        2 * (block->x_position + BLOCK_LENGTH) - ball->x_position;
    ball->x_velocity = -ball->x_velocity;
    bounce_message((int)ball->color, "block right side", ball->x_position,
                   ball->y_position);
    return true;
}

/* No one job comment. No debug statements */
static void top_bottom_case(struct Ball *ball, struct Paddle *block) {
    if (ball->y_velocity <= 0.0) {
        ball->y_position =
            2 * (block->y_position + BLOCK_HEIGHT) - ball->y_position;
        bounce_message((int)ball->color, "block top", ball->x_position,
                       ball->y_position);
    } else {
        ball->y_position = 2 * block->y_position - ball->y_position;
        bounce_message((int)ball->color, "block bottom", ball->x_position,
                       ball->y_position);
    }
    ball->y_velocity = -ball->y_velocity;
}

/* No one job comment. No debug statements. Multiple return statements */
static void collision_cases(struct Ball *ball, struct Paddle *block) {
    // if we find the collision case, stop looking
    // we already know that we have some kind of collision
    if (left_case(ball, block)) return;
    if (right_case(ball, block)) return;
    // what is left is either a top/bottom
    top_bottom_case(ball, block);
}

/* No one job comment. No debug statements. Multiple return statements */
static bool ball_inside_block(struct Ball *ball, struct Paddle *block) {
    if (ball->x_position < block->x_position) return false;
    if (ball->x_position >= block->x_position + BLOCK_LENGTH) return false;
    if (ball->y_position < block->y_position) return false;
    if (ball->y_position >= block->y_position + BLOCK_HEIGHT) return false;
    return true;
}

/* No one job comment. No debug statements. Multiple return statements */
static bool hit_block(struct Ball *ball, struct Paddle *block) {
    if (ball_inside_block(ball, block)) {
        // we are inside the block.  Get the case (which changes ball)
        collision_cases(ball, block);
        // this is where wee'd keep score
        return true;
    }
    return false;
}

/* No one job comment. No debug statements */
static void hit_top(struct Ball *ball) {
    if (ball->y_position > FIELD_TOP) {
        ball->y_position = 2 * FIELD_TOP - ball->y_position;
        ball->y_velocity = -ball->y_velocity;
        bounce_message((int)ball->color, "top wall", ball->x_position,
                       ball->y_position);
    }
}

/* No one job comment. No debug statements */
static void hit_left(struct Ball *ball) {
    if (ball->x_position < FIELD_LEFT) {
        ball->x_position = 2 * FIELD_LEFT - ball->x_position;
        ball->x_velocity = -ball->x_velocity;
        bounce_message((int)ball->color, "left wall", ball->x_position,
                       ball->y_position);
    }
}

/* No one job comment/ No debug statements */
static void hit_right(struct Ball *ball) {
    if (ball->x_position > FIELD_RIGHT) {
        ball->x_position = 2 * FIELD_RIGHT - ball->x_position;
        ball->x_velocity = -ball->x_velocity;
        bounce_message((int)ball->color, "right wall", ball->x_position,
                       ball->y_position);
    }
}

/* No one job comment */
static void hit_walls(struct Ball *ball) {
    hit_top(ball);
    hit_left(ball);
    hit_right(ball);
}

/* No one job comment. No debug statements */
static void hit_paddle(struct Ball *ball, struct Paddle *paddle) {
    if (ball->x_position >= paddle->x_position - paddle->size &&
        ball->x_position <= paddle->x_position + paddle->size &&
        ball->y_position <= FIELD_BOTTOM) {
        ball->y_position = 2 * FIELD_BOTTOM - ball->y_position;
        ball->y_velocity = -ball->y_velocity;
        ball->color = paddle->color;
        bounce_message((int)ball->color, "paddle", ball->x_position,
                       ball->y_position);
    }
}

/* No one job comment */
static void update_ball(struct Ball *ball, struct Paddle *block,
                        struct Paddle *paddle) {
    basic_motion(ball);
    hit_block(ball, block);
    hit_walls(ball);
    hit_paddle(ball, paddle);
}

/* No one job comment. No debug statements */
bool ball_is_on_field(struct Ball *ball) {
    return (
        (ball->y_position >= FIELD_BOTTOM) && (ball->y_position <= FIELD_TOP) &&
        (ball->x_position >= FIELD_LEFT) && (ball->x_position <= FIELD_RIGHT));
}

/* No on job comment */
static bool game_on(struct Ball *ball) { return (ball_is_on_field(ball)); }

/* No one job comment */
static void run_simulation(struct Ball *ball, struct Paddle *block,
                           struct Paddle *paddle) {
    double elapsed = 0.0;
    // run the simulation until the ball is done
    while (game_on(ball)) {
        master_output(elapsed, ball, block, paddle);
        // upoate the simulation, starting with the clock
        elapsed += DELTA_T;
        update_ball(ball, block, paddle);
    }

    final_output(elapsed, ball, block, paddle);
}

/* No one job comment. Multiple return statements */
bool do_everything() {
    // we own the input data, so we have to declare it here
    // at the monent, we are not inplementing the paddle(s)
    struct Ball basket, *ball = &basket;
    struct Paddle pingpong, *paddle = &pingpong;
    struct Paddle concrete, *block = &concrete;

    if (good_input(ball, block, paddle)) {
        run_simulation(ball, block, paddle);
    } else {
        return false;
    }
    return true;
}
