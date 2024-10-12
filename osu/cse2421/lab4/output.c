// Copyringht  2024 Neil Kirby not for disclosure wiothout written permission

// system libraries rarely have bugs, do them first
#include <stdbool.h>
#include <stdio.h>  
// custom libraries are usxually clean as well
#include "bko.h"
// constants are next, along with structs
#include "libll.h"
#include "constants.h"
#include "debug.h"
#include "structs.h"

// do C code headers last, this file.s header dead last.
#include "n2.h"

// my own header file is included dead last.  IT is MANDATORY!  
#include  "output.h"

static bool print_now(double elapsed)
{
        if(DEBUG) return true;
        return(elapsed == 0.0 || (int) elapsed > (int) (elapsed - DELTA_T));
}

static void print_a_ball(void *data)
{
        struct Ball *ball = data;
        printf("Ball %d is at (%8.5lf, %8.5lf) with velocity (%8.5lf, %8.5lf).\n", (int) ball->color, ball->x_position, ball->y_position, ball->x_velocity, ball->y_velocity);
}

static void print_a_block(void *data)
{
        struct Block *block = data;
        printf("Block %d is at (%8.5lf, %8.5lf).\n", (int) block->color, block->x_position, block->y_position);
}

static void print_a_paddle( const struct Paddle *paddle)
{
        printf("Paddle %d is at %8.5lf,  size %4.2lf with score %d.\n", (int) paddle->color, paddle->x_position, paddle->size, (int) paddle->score);
}

static void print_balls(const struct Sim *sim)
{
        if(sim->ball_list)
        {
                printf("Balls:\n");
                iterate(sim->ball_list, print_a_ball); 
        }
        else printf("No Balls in play\n");
}

static void print_blocks(const struct Sim *sim)
{
        if(sim->block_list)
        {
                printf("Blocks:\n");
                iterate(sim->block_list, print_a_block);
        }
        else printf("No Blocks in play\n");
}   

static void print_paddles(const struct Sim *sim)
{
        printf("Paddles:\n");
        for(int i = 0; i < PADDLE_COUNT; i++)
        {
            if(sim->paddle_array[i].color !=0)print_a_paddle (&sim->paddle_array[i]);
        }
}


static void master_print(const struct Sim *sim, bool ok)
{
    if(ok)
    {
            printf("\nElapsed time: %8.5lf\n", sim->elapsed);
            print_balls(sim);
            print_blocks(sim);
            print_paddles(sim);
            printf("\n");
    }
}

static void draw_a_ball(void *data)
{
        struct Ball *ball = data;
        bko_ball((int) ball->color, ball->x_position, ball->y_position);
}

static void draw_a_block(void *data)
{
        struct Block *block = data;
        bko_block((int) block->color, block->x_position, block->y_position);
}   

static void draw_a_paddle(const struct Paddle *paddle)
{
        bko_paddle((int) paddle->color, (int) paddle->score, paddle->x_position, paddle->size);
} 

static void draw_balls(const struct Sim *sim)
{
        iterate(sim->ball_list, draw_a_ball);
}

static void draw_blocks(const struct Sim *sim)
{
        iterate(sim->block_list, draw_a_block);
}  

static void draw_paddles(const struct Sim *sim)
{
        for(int i = 0; i < PADDLE_COUNT; i++)
        {
            if(sim->paddle_array[i].color != 0)draw_a_paddle(&sim->paddle_array[i]);
        }
}
  

static void master_draw(const struct Sim *sim, bool show_balls)
{
        bko_clear();
        bko_sim_time((int) (sim->elapsed * 1000));
        if(show_balls)draw_balls(sim);
        draw_blocks(sim);
        draw_paddles(sim);
        bko_refresh();
        microsleep((int) (DELTA_T * 1000000));  
}


void master_output(const struct Sim *sim)
{
    if(TEXT)master_print(sim, print_now(sim->elapsed));
    if(GRAPHICS)master_draw(sim, true);

}

static void freeze(const struct Sim *sim)
{
    for(double d = 0.0; d < 4.0; d += DELTA_T)
    {
        master_draw(sim, false);
    }
}
void final_output(const struct Sim *sim)
{
    // may differ from master_output
    if(TEXT)master_print(sim, true);
    if(GRAPHICS)freeze(sim);
}
// various mesages
void bounce_message(int color, char *wall, double X, double Y)
{
    if(TEXT)printf("Ball %d bounces off %s, winding up at (%.2lf, %.2lf).\n", color, wall, X, Y);
    if(GRAPHICS)
    {
        char buf[80];
        sprintf(buf, "Ball %d bounces off %s, winding up at (%.2lf, %.2lf).", color, wall, X, Y);
        bko_status(buf);
    }
}

void scanf_message(char *who, int got, int wanted)
{
    if(TEXT)printf("ERROR: %s read %d of %d tokens.\n", who, got, wanted);
}

void bad_ball_message(const struct Ball *ball)
{
    if(TEXT)printf("ERROR: Ball %d has a zero Y velocity.\n", (int) ball->color);
}

bool bad_paddle_message(const struct Paddle *paddle)
{
    if(TEXT)printf("ERROR: Bad paddle: color= %d, size=%lf.\n", (int) paddle->color, paddle->size);
    return false;
}
