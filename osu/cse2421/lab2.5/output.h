/* Copyright 2024 Neil Kirby, not for disclosure without permission */

void bad_ball_message(struct Ball *ball);
void bounce_message(int color, char *wall, double X, double Y);
void final_output(double elapsed, struct Ball *ball, struct Paddle *block, struct Paddle *paddle);
void master_output(double elapsed, struct Ball *ball, struct Paddle *block, struct Paddle *paddle);
void scanf_message(char *who, int got, int wanted);
