/* Copyright 2024 Neil Kirby, not for disclosure without permission */

void bad_ball_message(const struct Ball *ball);
bool bad_paddle_message(const struct Paddle *paddle);
void bounce_message(int color, char *wall, double X, double Y);
void final_output(const struct Sim *sim);
void master_output(const struct Sim *sim);
void scanf_message(char *who, int got, int wanted);
