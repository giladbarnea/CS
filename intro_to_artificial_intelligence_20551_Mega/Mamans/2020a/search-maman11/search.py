# search.py
# ---------
# Licensing Information: Please do not distribute or publish solutions to this
# project. You are free to use and extend these projects for educational
# purposes. The Pacman AI projects were developed at UC Berkeley, primarily by
# John DeNero (denero@cs.berkeley.edu) and Dan Klein (klein@cs.berkeley.edu).
# For more info, see http://inst.eecs.berkeley.edu/~cs188/sp09/pacman.html

"""
In search.py, you will implement generic search algorithms which are called
by Pacman agents (in searchAgents.py).
"""

import util
class Wraper:
  def __init__(self, frontier):
    self.frontier = frontier

  def push(self, item, priority):
    self.frontier.push(item)

  def pop(self):
    return self.frontier.pop()

  def isEmpty(self):
    return self.frontier.isEmpty()



class SearchProblem:
  """
  This class outlines the structure of a search problem, but doesn't implement
  any of the methods (in object-oriented terminology: an abstract class).

  You do not need to change anything in this class, ever.
  """

  def getStartState(self):
     """
     Returns the start state for the search problem
     """
     util.raiseNotDefined()

  def isGoalState(self, state):
     """
       state: Search state

     Returns True if and only if the state is a valid goal state
     """
     util.raiseNotDefined()

  def getSuccessors(self, state):
     """
       state: Search state

     For a given state, this should return a list of triples,
     (successor, action, stepCost), where 'successor' is a
     successor to the current state, 'action' is the action
     required to get there, and 'stepCost' is the incremental
     cost of expanding to that successor
     """
     util.raiseNotDefined()

  def getCostOfActions(self, actions):
     """
      actions: A list of actions to take

     This method returns the total cost of a particular sequence of actions.  The sequence must
     be composed of legal moves
     """
     util.raiseNotDefined()


def tinyMazeSearch(problem):
  """
  Returns a sequence of moves that solves tinyMaze.  For any other
  maze, the sequence of moves will be incorrect, so only use this for tinyMaze
  """
  from game import Directions
  s = Directions.SOUTH
  w = Directions.WEST
  return  [s,s,w,s,w,w,s,w]

def depthFirstSearch(problem):
  """
  Search the deepest nodes in the search tree first [p 74].

  Your search algorithm needs to return a list of actions that reaches
  the goal.  Make sure to implement a graph search algorithm [Fig. 3.18].

  To get started, you might want to try some of these simple commands to
  understand the search problem that is being passed in:

  print "Start:", problem.getStartState()
  print "Is the start a goal?", problem.isGoalState(problem.getStartState())
  print "Start's successors:", problem.getSuccessors(problem.getStartState())
  """
  from util import Stack
  frontier = Wraper(Stack())
  return dataStructureSearch(problem, frontier)

def breadthFirstSearch(problem):
  "Search the shallowest nodes in the search tree first. [p 74]"
  from util import Queue
  frontier = Wraper(Queue())
  return dataStructureSearch(problem, frontier)

def uniformCostSearch(problem):
  "Search the node of least total cost first. "
  "*** YOUR CODE HERE ***"
  from util import PriorityQueue
  frontier = PriorityQueue()
  return dataStructureSearch(problem, frontier)

def nullHeuristic(state, problem=None):
  """
  A heuristic function estimates the cost from the current state to the nearest
  goal in the provided SearchProblem.  This heuristic is trivial.
  """
  return 0

def aStarSearch(problem, heuristic=nullHeuristic):
  "Search the node that has the lowest combined cost and heuristic first."
  from util import PriorityQueue
  frontier = PriorityQueue()
  return dataStructureSearch(problem, frontier, heuristic)

# Abbreviations
bfs = breadthFirstSearch
dfs = depthFirstSearch
astar = aStarSearch
ucs = uniformCostSearch

def dataStructureSearch(problem, frontier, heuristic=nullHeuristic):
  explored = []
  node = problem.getStartState()
  path = []
  cost = 0 + heuristic(node, problem)
  frontier.push((node, path), cost)
  if problem.isGoalState(node):
    return []
  while not frontier.isEmpty():
    node, path = frontier.pop()
    successors = problem.getSuccessors(node)
    for successor in successors:
      child = successor[0]
      childPath = []
      childPath.extend(path)
      childPath.append(successor[1])
      childCost = problem.getCostOfActions(path) + successor[2] + heuristic(child, problem)
      if child not in explored:
        if problem.isGoalState(child):
          return childPath
        frontier.push((child, childPath), childCost)
        explored.append(child)
