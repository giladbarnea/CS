Course			Introduction to Artificial Intelligence 20551
Mamman #		13
Name			Itay Brandes
ID				
Date			17/11/2018

Question 1:
	I've implemented a better evaluation function, that prefers states that are closer (by their Manhattan distance) to
	the closest food.

	The evaluation function also:
	* Disfavour taking a stop action.
	* Disfavour taking an action that makes you bump into a ghost.
	* Prefers taking an action that makes you eat a scared ghost.

Question 2:
	I've implemented the minimax algorithm, but changes it a little to handle multiple minimum players (and not only one).
	The algorithm is implemented in the recursive function calcMinimaxValue, that gets a game state, the agent currently
	playing (0 for pacman, >1 for the ghosts) and the current depth. The function itself with each game state's possible
	successor states. It then calculates the max/min minimax value (depending on whether we're a maximum of minimum player)
	of all its successors and returns it to the caller, along with the direction that it took to reach that new state.

	Regarding the question about why Pacman prefers to die as soon as possible when the death is inevitable - it's because
	there's a time penalty. It the death is inevitable, Pacman should die as soon as possible to maximise its score.

Quetsion 3:
	Identical to question 2, but with alpha and beta factors implemented.
	Because we're using the weak inequality variant of the Alpha Beta algorithm (testing if alpha >= beta),
	we can count on minimax values passed from branches that been pruned, but we cannot let the algorithm choose
	them as a possible "direction" because the actual value may be actually lower/better than the other option available.
	Because of that, I've increased/decreased a small epsilon (0.001) from the minimax value of pruned branches, so
	the algorithm won't choose them.

	Also, the autograder tool that been used by the Berkeley university is testing against the string inequality variant
	of the Alpha Beta algorithm (testing if alpha > beta) and therefore is failing my implementation, even though my variant
	is more efficient.

Quetsion 4:
	Similar to question 2, but the minimum player takes the average of its successors instead the minimum.