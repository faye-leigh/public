/* Faye Leigh */

void debugBallPos(double ball[]) ;
void debugBallPrev(double ballPrev[]) ;
void debugBallVel(double ball[]) ;
void debugBlockPos(double block[]) ;
void debugCollisionBlock(double ball[], double block[]) ;
void debugCollisionBlockEdge(double yCross, double ballPrev[], double ball[], double block[]) ;
void debugCollisionBlockPrev(double ballPrev[], double ball[], double block[]) ;
void debugCollisionBlockVX(double ball[]) ;
void debugCollisionBlockVertical(double ball[]) ;
void debugCollisionPaddle(double ball[], double paddle[]) ;
void debugCollisionWall(char *wall, double ball[], double walls[]) ;
void debugPaddlePos(double paddle[]) ;
char *debugRejectSide(double ball[]) ;
void debugWallPos(double walls[]) ;
void print_state(double elapsed, double ball[], double block[], double paddle[]) ;
