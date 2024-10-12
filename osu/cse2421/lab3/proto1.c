
#include <stdio.h>

#define PADDLE_COUNT 8

struct Ball{
    int color;
    double x;
    struct Sim *sim;
};

struct Block {
    int color;
};

struct Paddle{
    int color;
};

struct Sim {
    double time;
    struct Paddle *paddles[PADDLE_COUNT];
};

int main(){
    struct Ball test_ball_init, *test_ball = &test_ball_init;
    struct Sim sim_init, *sim = &sim_init;
    struct Paddle test_paddle_init, *test_block = &test_paddle_init;
    test_block->color = 8;
    sim->paddles[0] = test_block;
    sim->time = 1.234;
    test_ball->color = 4;
    test_ball->x = 3.14;
    test_ball->sim = sim;
    test_ball->sim->time = 9.876;
    

    printf("%d %lf %lf\n", test_ball->color, test_ball->x, test_ball->sim->time);
    printf("%lf %d\n", sim->time, sim->paddles[0]->color);
    return 0;
}
