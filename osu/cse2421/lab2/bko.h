/* Copyright 2024 Neil Kirby, all rights reserved. */
/* Do not publish this code without written permission */
#include <stdbool.h>
/* Neil Kirby */

bool bko_ball(int color, double X, double Y);
void bko_beep();
bool bko_block(int color, double X, double Y);
void bko_clear();
void bko_flash();
int bko_getch();
int bko_initialize();
bool bko_paddle(int color, int score, double X, double hw);
void bko_refresh();
void bko_sim_time(int milliseconds);
void bko_status(const char *statstr);
void bko_teardown();
