// Copyright 2024 Neil Kirby, not for dislosure without permission

//system libraries rarely have bugs, do them first
#include <stdbool.h>
#include <stdlib.h>
#include <stdio.h>

// constants are next, along with structs
#include "constants.h"  
#include "structs.h"
#include "libll.h"
#include "debug.h"

// do C code headers last, this file.s header dead last.
#include "input.h"
#include "output.h"
#include "memory.h"

// my own header file is included dead last.  IT is MANDATORY!
#include "sim.h"

// 3defines that are local to me only go here
#define FIELD_BOTTOM 0.0
#define FIELD_TOP 20.0
#define FIELD_LEFT 0.0  
#define FIELD_RIGHT 30.0

#define BLOCK_LENGTH 3.0
#define BLOCK_HEIGHT 2.0

static void basic_motion(struct Ball *ball)
{
        // update the ball's position
        ball->x_position += ball->x_velocity * DELTA_T;
        ball->y_position += ball->y_velocity * DELTA_T;

}

static bool reject_on_iy(struct Ball *ball, struct Block *block , double offset)
{
        // where was the ball before it moved?
        double px = ball->x_position - ball->x_velocity * DELTA_T;
        double py = ball->y_position - ball->y_velocity * DELTA_T;
        double dx = ball->x_position - px; // travelled this far in x
        double dy = ball->y_position - py; // travelled this far in y
        // offset is used to do the right side of the block
        double percent = (block->x_position + offset - px)/dx;
        // where (in y) did the ball cross the side of the block?
        double iy = py + percent * (dy);
        if(DEBUG) 
        {
                printf("side case: px %f py %f    dx %f dy %f   percent %f offset %f\n", px, py, dx, dy, percent, offset);
                printf("    block: x %f y %f\n", block->x_position, block->y_position);
                printf("    ball: x %f y %f\n", ball->x_position, ball->y_position);
        }
        if(iy < block->y_position)
        {
                if(DEBUG)printf("    rejected: iy %f < block->y_position %f\n", iy, block->y_position);
                return true;
        }
        
        if( iy > block->y_position + BLOCK_HEIGHT) 
        {
                if(DEBUG)printf("    rejected: iy %f > block->y_position + BLOCK_HEIGHT %f\n", iy, block->y_position + BLOCK_HEIGHT);
                return true;
        }
	if(DEBUG)printf("    we do have a side case iy=%f\n", iy);
        return false;
}

static bool simple_left_rejections(struct Ball *ball, struct Block *block)
{
        if(ball->x_velocity <= 0.0)
	{
if(DEBUG)printf("    simple_left rejecting on VX %.2lf\n", ball->x_velocity);
	    return true;  // not when it is going right
	}
        double px = ball->x_position - ball->x_velocity * DELTA_T;
        // was it already left of the left side?
        if(px >= block->x_position) 
	{
if(DEBUG)printf("    simple_left rejecting on prior X %.3lf >= left %.1lf \n", px, block->x_position);
	    return true;  // didn't cross the left wall
	}
        return false;
}

static bool left_case(struct Ball *ball, struct Block *block)
{
        // if we get a rejection, return false (didn't hit the block)
        // otherwise we have a hit, return true after dealing with changes to the ball
	if(DEBUG)printf("left_cases\n");
        if(simple_left_rejections(ball, block))
        {
                // DEBUG goes here?
                return false;
        }    
        if(reject_on_iy(ball, block, 0.0)) return false;        

 
        //reflect x position, negate x velocty, print, and return true
        // note that some of the lines below are different in the right case
        ball->x_position = 2 * block->x_position - ball->x_position;
        ball->x_velocity = -ball->x_velocity;
        bounce_message((int) ball->color, "block left side", ball->x_position, ball->y_position);
        return true;       
}

static bool simple_right_rejections(struct Ball *ball, struct Block *block)
{
        if(ball->x_velocity >= 0.0)
	{
if(DEBUG)printf("    simple_right rejecting on VX %.2lf\n", ball->x_velocity);
	    return true;  // not when it is going left
	}
        double px = ball->x_position - ball->x_velocity * DELTA_T;
        // was it already right of the right side?
        if(px <= block->x_position + BLOCK_LENGTH)
	{
if(DEBUG)printf("    simple_right rejecting on prior X %.3lf <= right %.1lf \n", px, block->x_position + BLOCK_LENGTH);
	    return true;  // didn't cross the right wall
	}
        return false;
}       

static bool right_case(struct Ball *ball, struct Block *block)
{
        // if we get a rejection, return false (didn't hit the block)
	if(DEBUG)printf("right_cases\n");
        if(simple_right_rejections(ball, block))
        {
                // DEBUG goes here?
                return false;
        }
        // we offset to the right side of the block
        if(reject_on_iy(ball, block, BLOCK_LENGTH)) return false;

        //reflect x position, negate x velocty, print, and return true
        ball->x_position = 2 * (block->x_position + BLOCK_LENGTH) - ball->x_position;
        ball->x_velocity = -ball->x_velocity;
        bounce_message((int) ball->color, "block right side", ball->x_position, ball->y_position);
        return true;       
}

static void top_bottom_case(struct Ball *ball, struct Block *block)
{
        if(ball->y_velocity <= 0.0)
        {
            ball->y_position = 2 * (block->y_position + BLOCK_HEIGHT) - ball->y_position;
            bounce_message((int) ball->color, "block top", ball->x_position, ball->y_position);
        }
        else
        {
            ball->y_position = 2 * block->y_position - ball->y_position;
            bounce_message((int) ball->color, "block bottom", ball->x_position, ball->y_position);
        }
        ball->y_velocity = -ball->y_velocity;
}

static void collision_cases(struct Ball *ball, struct Block *block)
{
        // if we find the collision case, stop looking
        // we already know that we have some kind of collision
        if(DEBUG)printf("collision cases:\n");
        if(left_case(ball, block)) return;
        if (right_case(ball, block)) return;
        // what is left is either a top/bottom 
        top_bottom_case(ball, block);
}

static bool ball_inside_block(struct Ball *ball, struct Block *block)
{
        if(ball->x_position < block->x_position ) return false;
        if(ball->x_position >= block->x_position + BLOCK_LENGTH) return false;
        if(ball->y_position < block->y_position) return false;
        if(ball->y_position >= block->y_position + BLOCK_HEIGHT) return false;
        return true;
}


static bool hit_block(void *data, void *helper)
{
        struct Ball *ball = helper;
        struct Block *block = data;

        if(ball_inside_block(ball, block) ) 
        {
                // we are inside the block.  Get the case (which changes ball)
                collision_cases(ball, block);
                //side effect warning
                ball->sim->paddle_array[ball->color].score += block->color *10;
		// TODO uncomment below when I want blocks to go away!
                return true;
        }
       return false;
}

static void hit_top(struct Ball *ball)
{
        if(ball->y_position > FIELD_TOP)
        {
            ball->y_position = 2 * FIELD_TOP - ball->y_position;
            ball->y_velocity = -ball->y_velocity;
            bounce_message((int) ball->color, "top wall", ball->x_position, ball->y_position);
        }
}  

static void hit_left(struct Ball *ball)
{
        if(ball->x_position < FIELD_LEFT)
        {
            ball->x_position = 2 * FIELD_LEFT - ball->x_position;
            ball->x_velocity = -ball->x_velocity;
            bounce_message((int) ball->color, "left wall", ball->x_position, ball->y_position);
        }
}

static void hit_right(struct Ball *ball)
{
        if(ball->x_position > FIELD_RIGHT)
        {
            ball->x_position = 2 * FIELD_RIGHT - ball->x_position;
            ball->x_velocity = -ball->x_velocity;
            bounce_message((int) ball->color, "right wall", ball->x_position, ball->y_position);
        }
}

static void hit_walls(struct Ball *ball)
{
        hit_top(ball);
        hit_left(ball);
        hit_right(ball);
}

static void hit_paddle(struct Ball *ball, struct Paddle *paddle)
{
        if(ball->x_position >= paddle->x_position - paddle->size && ball->x_position <= paddle->x_position + paddle->size && ball->y_position <= FIELD_BOTTOM)
        {
            ball->y_position = 2 * FIELD_BOTTOM - ball->y_position;
            ball->y_velocity = -ball->y_velocity;
            ball->color = paddle->color;
            bounce_message((int) ball->color, "paddle", ball->x_position, ball->y_position);
        }
}

static void hit_paddles(struct Ball *ball)
{
        for(int i = 0; i < PADDLE_COUNT; i++)
        {
            struct Paddle *paddle = &ball->sim->paddle_array[i];
            // color zero means ignore the paddle
            if(paddle->color != 0)
            {
                hit_paddle(ball, paddle);
            }
        }
}


static void update_ball(void *data)
{
        struct Ball *ball = data;

        basic_motion(ball);
        deleteSome(&ball->sim->block_list, hit_block, ball, free_thing, TEXT);
        hit_walls(ball);
        hit_paddles(ball);
}

static void update_balls(struct Sim *sim)
{
        iterate(sim->ball_list, update_ball);
}

bool ball_is_off_field(void *data, void *helper)
{
        struct Ball *ball = data;
        return( 
                (ball->y_position < FIELD_BOTTOM) ||
                (ball->y_position > FIELD_TOP) ||
                (ball->x_position < FIELD_LEFT) ||   
                (ball->x_position > FIELD_RIGHT) 
        );
}


static bool game_on(struct Sim *sim)
{
        return( 
            sim->ball_list != NULL 
        );
}

bool always_true(void *data, void *helper)
{
        return true;
}

static void clear_lists(struct Sim *sim)
{
        int removed;
        removed = deleteSome(&sim->ball_list, always_true, NULL, free_thing, TEXT);
        if(TEXT)printf("removed %d balls\n", removed);
        removed = deleteSome(&sim->block_list, always_true, NULL, free_thing, TEXT);
        if(TEXT)printf("removed %d blocks\n", removed);
}

static void run_simulation(struct Sim *sim)
{
        
        // run the simulation until no balls are left
        while(game_on(sim))
        {
	    sort(sim->ball_list, ball_order);
            master_output(sim);
            // upoate the simulation, starting with the clock
            sim->elapsed += DELTA_T;
            update_balls(sim);
            deleteSome(&sim->ball_list, ball_is_off_field, NULL, free_thing, TEXT);
        }
        final_output(sim);

}

bool do_everything()
{
            // we own the input data, so we have to declare it here
        struct Sim world = {NULL, NULL}, *sim = &world;
        bool rval = true;

        if(good_input(sim))
        { 
            run_simulation(sim);
        }
        else
        {
            rval = false;
        }      
        // input may have loaded items onto a list befoe croaking
        // so we have to clear the lists before we leave  
        clear_lists(sim);

        return rval;
}

