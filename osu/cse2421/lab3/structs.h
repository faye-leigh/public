/* Copyright 2024 Faye Leigh */
/*    OSU CSE 2421 - AU24    */
/* Based on structs.h provided by Neil Kirby */

#define PADDLE_COUNT 8

struct Ball {
    int color;
    double x, y, vx, vy;
    struct Sim *sim;
};

struct Block {
    int color;
    double x_left, x_right, y_top, y_bottom; 
} Block;

struct Paddle {
    int color, score;
    double x_left, x_right;
};

struct Sim {
    double time;
    struct Paddle test[PADDLE_COUNT];
};
