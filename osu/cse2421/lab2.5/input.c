// Copyright 2024 Neil Kirby - not for distribution without permission

#include <stdbool.h>
#include <stdio.h>

// custom libraries and things like custom libraries go here
#include "structs.h"

//  do C code headers last, this file.s header dead last.
#include "output.h"

// my own header file is included dead last.  IT is MANDATORY!
#include "input.h"

/* No one job comment .
Magic numbers.
I had delegated the loop off to a separate function, which I might prefer.
Multiple return statements */
static bool read_blocks(struct Paddle *block) {
    // only the last one counts
    int count, tokens;
    if (1 == (tokens = scanf("%d", &count))) {
        for (int i = 0; i < count; i++) {
            if (3 == (tokens = scanf("%d %lf %lf", &block->color,
                                     &block->x_position, &block->y_position))) {
                // do nothing further in lab 2
            } else {
                scanf_message("block", tokens, 3);
                return false;
            }
        }
    } else {
        scanf_message("block count", tokens, 1);
        return false;
    }
    return true;
}

/* No one job comment .
Magic numbers.
I had delegated the loop off to a separate function, which I might prefer.
Multiple return statements */
static bool read_balls(struct Ball *ball) {
    // only the last one counts
    int count, tokens;
    if (1 == (tokens = scanf("%d", &count))) {
        for (int i = 0; i < count; i++) {
            if (5 == (tokens = scanf("%d %lf %lf %lf %lf", &ball->color,
                                     &ball->x_position, &ball->y_position,
                                     &ball->x_velocity, &ball->y_velocity))) {
                // do nothing further in lab 2
                if (ball->y_velocity == 0.0) {
                    bad_ball_message(ball);
                    return false;
                }
            } else {
                scanf_message("ball", tokens, 5);
                return false;
            }
        }
    } else {
        scanf_message("ball count", tokens, 1);
        return false;
    }

    return true;
}

/* No one job comment .
Magic numbers.
I had delegated the loop off to a separate function, which I might prefer.
Multiple return statements*/
static bool read_paddles(struct Paddle *paddle) {
    // only the last one counts
    int count, tokens;
    if (1 == (tokens = scanf("%d", &count))) {
        for (int i = 0; i < count; i++) {
            if (3 == (tokens = scanf("%d %lf %lf", &paddle->color,
                                     &paddle->x_position, &paddle->size))) {
                // do nothing further in lab 2
            } else {
                scanf_message("paddle", tokens, 3);
                return false;
            }
        }
    } else {
        scanf_message("paddle count", tokens, 1);
        return false;
    }
    return true;
}

/* No one job comment. Multiple return statements */
bool good_input(struct Ball *ball, struct Paddle *block, struct Paddle *paddle) {
    if (!read_balls(ball)) return false;
    if (!read_blocks(block)) return false;
    if (!read_paddles(paddle)) return false;
    return true;
}
