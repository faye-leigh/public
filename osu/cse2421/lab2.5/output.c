// Copyringht  2024 Neil Kirby not for disclosure wiothout permission

// system libraries rarely have bugs, do them first
#include <stdbool.h>
#include <stdio.h>
// custom libraries are usxually clean as well
#include "bko.h"
// constants are next, along with structs
#include "constants.h"
#include "debug.h"
#include "structs.h"

// do C code headers last, this file.s header dead last.
#include "n2.h"

// my own header file is included dead last.  IT is MANDATORY!
#include "output.h"

/* No on job comment */
static bool print_now(double elapsed) {
    if (DEBUG) return true;
    return (elapsed == 0.0 || (int)elapsed > (int)(elapsed - DELTA_T));
}

/* No on job comment */
static void print_balls(struct Ball *ball) {
    printf("Ball %d is at (%8.5lf, %8.5lf) with velocity (%8.5lf, %8.5lf).\n",
           (int)ball->color, ball->x_position, ball->y_position,
           ball->x_velocity, ball->y_velocity);
}

/* No on job comment */
static void print_blocks(struct Paddle *block) {
    printf("Block %d is at (%8.5lf, %8.5lf).\n", (int)block->color,
           block->x_position, block->y_position);
}

/* No on job comment */
static void print_paddles(struct Paddle *paddle) {
    printf("Paddle %d is at %8.5lf,  size %4.2lf with score %d.\n",
           (int)paddle->color, paddle->x_position, paddle->size,
           (int)paddle->score);
}

/* No on job comment */
static void master_print(double elapsed, struct Ball *ball, struct Paddle *block,
                         struct Paddle *paddle, bool ok) {
    if (ok) {
        printf("\nElapsed time: %8.5lf\n", elapsed);
        print_balls(ball);
        print_blocks(block);
        print_paddles(paddle);
        printf("\n");
    }
}

/* No on job comment */
static void draw_balls(struct Ball *ball) {
    bko_ball((int)ball->color, ball->x_position, ball->y_position);
}

/* No on job comment */
static void draw_blocks(struct Paddle *block) {
    bko_block((int)block->color, block->x_position, block->y_position);
}

/* No on job comment */
static void draw_paddles(struct Paddle *paddle) {
    bko_paddle((int)paddle->color, (int)paddle->score, paddle->x_position,
               paddle->size);
}

/* No on job comment. Magic numbers */
static void master_draw(double elapsed, struct Ball *ball, struct Paddle *block,
                        struct Paddle *paddle, bool show_balls) {
    bko_clear();
    bko_sim_time((int)(elapsed * 1000));
    if (show_balls) draw_balls(ball);
    draw_blocks(block);
    draw_paddles(paddle);
    bko_refresh();
    microsleep((int)(DELTA_T * 1000000));
}

/* No on job comment */
void master_output(double elapsed, struct Ball *ball, struct Paddle *block,
                   struct Paddle *paddle) {
    if (TEXT) master_print(elapsed, ball, block, paddle, print_now(elapsed));
    if (GRAPHICS) master_draw(elapsed, ball, block, paddle, true);
}

/* No on job comment. Magic numbers */
static void freeze(double elapsed, struct Ball *ball, struct Paddle *block,
                   struct Paddle *paddle) {
    for (double d = 0.0; d < 4.0; d += DELTA_T) {
        master_draw(elapsed, ball, block, paddle, false);
    }
}

/* No on job comment */
void final_output(double elapsed, struct Ball *ball, struct Paddle *block,
                  struct Paddle *paddle) {
    // may differ from master_output
    if (TEXT) master_print(elapsed, ball, block, paddle, true);
    if (GRAPHICS) freeze(elapsed, ball, block, paddle);
}

/* No further comment */
// various mesages
void bounce_message(int color, char *wall, double X, double Y) {
    if (TEXT)
        printf("Ball %d bounces off %s, winding up at (%.2lf, %.2lf).\n", color,
               wall, X, Y);
    if (GRAPHICS) {
        char buf[80];
        sprintf(buf, "Ball %d bounces off %s, winding up at (%.2lf, %.2lf).",
                color, wall, X, Y);
        bko_status(buf);
    }
}

/* No on job comment */
void scanf_message(char *who, int got, int wanted) {
    if (TEXT) printf("ERROR: %s read %d of %d tokens.\n", who, got, wanted);
}

/* No on job comment */
void bad_ball_message(struct Ball *ball) {
    if (TEXT)
        printf("ERROR: Ball %d has a zero Y velocity.\n", (int)ball->color);
}
