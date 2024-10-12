// COpyright 2024 Neil Kirby.  Not for disclosure without permission
//

struct Ball {
	int color;
	double x_position, y_position, x_velocity, y_velocity;
	struct Sim *sim;
};

struct Paddle {
	int color, score;
	double x_position, size;
};

struct Block {
	int color;
	double x_position, y_position;
};

#define PADDLE_COUNT 8

struct Sim {
	void *ball_list;
	void *block_list;
	struct Paddle paddle_array[PADDLE_COUNT];
	double elapsed;	
};