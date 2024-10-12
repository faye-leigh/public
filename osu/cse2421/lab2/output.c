// Copyringht  2024 Neil Kirby not for disclosure wiothout permission
// Modified by Faye Leigh

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
#include "graphics.h"
#include "n2.h"
#include "text.h"

// my own header file is included dead last.  IT is MANDATORY!
#include "output.h"

// True if in DEBUG mode, elapsed = 0, or (int) elapsed > (int)(elapsed - DELTA_T)
static bool print_now(double elapsed) {
    if (DEBUG)
        return true;
    return (elapsed == 0.0 || (int)elapsed > (int)(elapsed - DELTA_T));
}

// Output game state information
void master_output(double elapsed, double ball[], double block[], double paddle[]) {
    if (TEXT) {
        if (print_now(elapsed))
            print_state(elapsed, ball, block, paddle);  // Changed function name - Faye Leigh
    }
    if (GRAPHICS)
        master_draw(elapsed, ball, block, paddle, true);
}

// Output final game state information
void final_output(double elapsed, double ball[], double block[], double paddle[]) {
    // may differ from master_output
    if (TEXT)
        print_state(elapsed, ball, block, paddle);  // Changed function name - Faye Leigh
    if (GRAPHICS)
        freeze(elapsed, ball, block, paddle);
}

// Output message when ball bounces off a surface
void bounce_message(int color, char *wall, double X, double Y) {
    if (TEXT)
        printf("Ball %d bounces off %s, winding up at (%.2lf, %.2lf).\n", color, wall, X, Y);
    if (GRAPHICS) {
        char buf[80];
        sprintf(buf, "Ball %d bounces off %s, winding up at (%.2lf, %.2lf).", color, wall, X, Y);
        bko_status(buf);
    }
}

// Error message: scanf read error
void scanf_message(char *who, int got, int wanted) {
    if (TEXT)
        printf("ERROR: %s read %d of %d tokens.\n", who, got, wanted);
}

// Error message: ball zero Y velocity
void bad_ball_message(double ball[]) {
    if (TEXT)
        printf("ERROR: Ball %d has a zero Y velocity.\n", (int)ball[SS_COLOR]);
}
