/* Faye Leigh */

void collisionBlock(double ball[], double block[]) ;
void collisionBlockEdge(double ballPrev[], double ball[], double block[]) ;
bool collisionBlockEdgeLeft(double ballPrev[], double ball[], double block[]) ;
bool collisionBlockEdgeRight(double ballPrev[], double ball[], double block[]) ;
void collisionBlockPrev(double ball[], double block[]) ;
void collisionBlockVX(double ball[], double block[]) ;
void collisionBlockVertical(double ball[], double block[]) ;
void collisionBounceHorizontal(double ball[]) ;
void collisionBounceVertical(double ball[]) ;
void collisionCorrectionX(double ball[], double boundary) ;
void collisionCorrectionY(double ball[], double boundary) ;
void collisionPaddle(double ball[], double paddle[]) ;
void collisionWall(double ball[], double walls[]) ;
void resolveCollisions(double ball[], double block[], double paddle[], double walls[]) ;
