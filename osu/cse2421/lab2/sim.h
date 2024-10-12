/* Faye Leigh */

bool getData(double ball[], double block[], double paddle[], double walls[]) ;
void incrementPos(double ball[]) ;
bool run() ;
void saveBlock(double block[]) ;
void savePaddle(double paddle[]) ;
void saveWalls(double walls[]) ;
int simLoop(int ticks, double ball[], double block[], double paddle[], double walls[]) ;
