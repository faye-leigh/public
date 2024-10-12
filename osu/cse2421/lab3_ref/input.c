// Copyright 2024 Neil Kirby, not for disclosure without written permission
#include <stdbool.h>
#include <stdio.h>

// custom libraries and things like custom libraries go here  
#include "libll.h" 
#include "debug.h"
#include "structs.h" 

//  do C code headers last, this file.s header dead last.
#include "output.h"
#include "memory.h"


// my own header file is included dead last.  IT is MANDATORY!
#include "input.h"

// my callbacks for ordering
bool ball_order(void *data1, void *data2)
{
        struct Ball *ball1 = data1, *ball2 = data2;
        return(ball1->y_position > ball2->y_position);
}

bool block_order(void *data1, void *data2)
{
        struct Block *block1 = data1, *block2 = data2;
        if(block1->y_position < block2->y_position)return true;
        if(block1->y_position > block2->y_position)return false;
        return(block1->x_position < block2->x_position);
}

// now the rest of the input stuff for balls, blocks, and paddles

static void add_block(struct Block *input_block, struct Sim *sim)
{
        // use two different names to avoid confusion
        struct Block *list_block = allocate_thing(sizeof(struct Block));
        if(list_block)
        {
            *list_block = *input_block;
            if( insert(&sim->block_list, list_block, block_order, TEXT) )
            {
                    if(DEBUG)printf( "add_block: added block %p\n", (void *)list_block);
            }
            else
            {
                    // give it back
            }
        }
}

static void add_ball(struct Ball *input_ball, struct Sim *sim)
{
        // use two different names to avoid confusion
        struct Ball *list_ball = allocate_thing(sizeof(struct Ball));
        if(list_ball)
        {
            *list_ball = *input_ball;
            if( insert(&sim->ball_list, list_ball, ball_order, TEXT) )
            {
                    if(DEBUG)printf( "add_ball: added ball %p\n", (void *) list_ball);
            }
            else
            {
                    // give it back
            }
        }
}

static bool read_a_block(struct Sim *sim)
{
    int count, tokens;
    struct Block concrete, *block = &concrete;
    
	if(3==(tokens =scanf("%d %lf %lf", &block->color, &block->x_position, &block->y_position)))
	{
	    add_block(block, sim);
	}
	else
	{
	    scanf_message("block",  tokens, 3);
	    return false;
	}
	return true;
}

static bool read_blocks(struct Sim *sim)
{
    int count, tokens;
    
    if(1==(tokens = scanf("%d", &count)))
    {
        for(int i = 0; i < count; i++)
        {
	    if(!read_a_block(sim))return false;
        }
    }
    else
    {
        scanf_message("block count", tokens, 1);
        return false;
    }
    return true;
}

static bool read_a_ball(struct Sim *sim)
{
        int tokens;
        struct Ball basket, *ball = &basket;
        ball->sim = sim;
        if(5==(tokens =scanf("%d %lf %lf %lf %lf", &ball->color, &ball->x_position, &ball->y_position, &ball->x_velocity, &ball->y_velocity)))
        {
            if(ball->y_velocity == 0.0)
            {
                bad_ball_message(ball);
                return false;
            }
            add_ball(ball, sim);
        }
        else
        {
            scanf_message("ball",  tokens, 5);
            return false;
        }
        return true;
}

static bool read_balls(struct Sim *sim)
{
    int count, tokens;

    if(1==(tokens = scanf("%d", &count)))
    {
        for(int i = 0; i < count; i++)
        {
            if(!read_a_ball(sim))return false;
        }   
    }
    else
    {
        scanf_message("ball count", tokens, 1);
        return false;
    }

    return true;
}

static bool read_a_paddle(struct Sim *sim)
{
    int tokens;
    struct Paddle pingpong, *paddle = &pingpong;
    paddle->score = 0;
    if(3==(tokens =scanf("%d %lf %lf", &paddle->color,  &paddle->x_position, &paddle->size)))
    {
        if(paddle->size < 0.0)return bad_paddle_message(paddle);
        if(paddle->color <= 0 || paddle->color > 7)return bad_paddle_message(paddle);
        sim->paddle_array[paddle->color] = *paddle;
    }
    else
    {
        scanf_message("paddle",  tokens, 3);
        return false;
    }
    return true;
}

static bool read_paddles(struct Sim *sim)
{
    int count, tokens;
    struct Paddle pingpong, *paddle = &pingpong;
    if(1==(tokens = scanf("%d", &count)))
    {
        for(int i = 0; i < count; i++)
        {
            if(!read_a_paddle(sim))return false;
        }
    }
    else
    {
        scanf_message("paddle count", tokens, 1);
        return false;
    }
    return true;
}

bool good_input( struct Sim *sim)
{
    if(!read_balls(sim))return false;
    if(!read_blocks(sim))return false;
    if(!read_paddles(sim))return false;
    return true;
}

