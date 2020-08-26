Course			Introduction to Artificial Intelligence 20551
Mamman #		11
Name			Itay Brandes
ID				
Date			24/10/2018

Question 1:
	I've implemented a generic function named generalSearch. DFS is then achived by passing a stack
	as the data structure.
	I've decided that the data structure will include nodes, each node of the following format:
		(state, moves, cost)
	
	Regarding the question whether DFS found the optimal solution:
	No, the solution isn't optimal. DFS does not guarantee finding the optimal route. It just guarantees
	to found an applicable route.
	DFS on the mediumMaze finds a path with total cost of 130.
	
	On the other hand, BFS finds the optimal route, which is a path with total cost of 68.
	
Question 2:
	I've used the same generic function generalSearch, this time passing a queue.
	BFS do find the optimal solution. This is because when BFS scans routes of depth n, it had already
	scanned all routes smaller than n, and didn't found a route to the destination.
	Therefore, BFS always finds the optimal route.

Question 3:
	I've used the same generic function generalSearch, this time passing a Priority Queue, with each
	node getting a priority of the price. This essentially impelemtns the UCS algorithm.

Question 4:
	I've used the same generic function generalSearch, this time passing a Priority Queue, with each
	node getting a priority of the price, plus the value returned by the heuristic of the state.
	This essentially impelemtns the AStar algorithm.
	
	In the openMaze maze:
	DFS has the worst performance - though finding a route, it is far from optimal, with path of 298
	compared to the optimal route of 54.
	
	BFS, UCS and AStar are both finding the optimal route, however they differ by the search nodes
	expanded count:
	
	BFS - 682 nodes expanded
	AStar with no heuristic / UCS - 682 nodes expanded
	AStar with Manhattan Heuristic - 535 nodes expanded
	
	That's not suprising, since the Manhattan Heuristic helps the AStar algorithm to focus on more
	relevant routes, decreasing the total nodes expansion count.
	
Question 5:
	I've decided to create the state presentation as the following tuple:
		(position, corners)

Question 6:
	I've decided to implement the heuristic as the manhattan distance from the closest corner.
	As a result, AStar would prefer a route if it's getting us closer to a corner.
	
Question 7:
	I've decided to implement the heuristic as the distance to the farthest food. The distance
	is not a manhattan distance, but the actual maze distance (calculated by the provided function
	mazeDistance, which actually is implemented as a BFS between the points).
	
	As a result, AStar would prefer a route that's closer to the farthest food from the starting point.
	
Question 8:
	I've used BFS to implement findPathToClosestDot.
