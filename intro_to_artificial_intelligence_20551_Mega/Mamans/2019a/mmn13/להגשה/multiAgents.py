# multiAgents.py
# --------------
# Licensing Information:  You are free to use or extend these projects for
# educational purposes provided that (1) you do not distribute or publish
# solutions, (2) you retain this notice, and (3) you provide clear
# attribution to UC Berkeley, including a link to http://ai.berkeley.edu.
# 
# Attribution Information: The Pacman AI projects were developed at UC Berkeley.
# The core projects and autograders were primarily created by John DeNero
# (denero@cs.berkeley.edu) and Dan Klein (klein@cs.berkeley.edu).
# Student side autograding was added by Brad Miller, Nick Hay, and
# Pieter Abbeel (pabbeel@cs.berkeley.edu).


from util import manhattanDistance
from game import Directions
import random, util

from game import Agent

class ReflexAgent(Agent):
    """
      A reflex agent chooses an action at each choice point by examining
      its alternatives via a state evaluation function.

      The code below is provided as a guide.  You are welcome to change
      it in any way you see fit, so long as you don't touch our method
      headers.
    """


    def getAction(self, gameState):
        """
        You do not need to change this method, but you're welcome to.

        getAction chooses among the best options according to the evaluation function.

        Just like in the previous project, getAction takes a GameState and returns
        some Directions.X for some X in the set {North, South, West, East, Stop}
        """
        # Collect legal moves and successor states
        legalMoves = gameState.getLegalActions()

        # Choose one of the best actions
        scores = [self.evaluationFunction(gameState, action) for action in legalMoves]
        bestScore = max(scores)
        bestIndices = [index for index in range(len(scores)) if scores[index] == bestScore]
        chosenIndex = random.choice(bestIndices) # Pick randomly among the best

        "Add more of your code here if you want to"

        return legalMoves[chosenIndex]

    def evaluationFunction(self, currentGameState, action):
        """
        Design a better evaluation function here.

        The evaluation function takes in the current and proposed successor
        GameStates (pacman.py) and returns a number, where higher numbers are better.

        The code below extracts some useful information from the state, like the
        remaining food (newFood) and Pacman position after moving (newPos).
        newScaredTimes holds the number of moves that each ghost will remain
        scared because of Pacman having eaten a power pellet.

        Print out these variables to see what you're getting, then combine them
        to create a masterful evaluation function.
        """
        # Useful information you can extract from a GameState (pacman.py)
        successorGameState = currentGameState.generatePacmanSuccessor(action)
        newPos = successorGameState.getPacmanPosition()
        newFood = successorGameState.getFood()
        newGhostStates = successorGameState.getGhostStates()
        newScaredTimes = [ghostState.scaredTimer for ghostState in newGhostStates]

        "*** YOUR CODE HERE ***"

        # never prefer to stop
        if action == 'Stop':
          return float('-inf')
        
        for state in newGhostStates:
          # if we're having a ghost on the next state
          if state.getPosition() == newPos:
            # if it's not scared - get away
            if state.scaredTimer == 0:
              return float('-inf')
            # if it's scared - eat it!
            else:
              return float('inf')
        
        if newFood.count() == 0:
          # if there's no food left, we're done
          return 0

        # if we're eating a food in this turn
        if newFood.count() != currentGameState.getFood().count():
          return float('inf')
        
        min_distance = min([manhattanDistance(newPos, x) for x in newFood.asList()])

        return -1 * min_distance

def scoreEvaluationFunction(currentGameState):
    """
      This default evaluation function just returns the score of the state.
      The score is the same one displayed in the Pacman GUI.

      This evaluation function is meant for use with adversarial search agents
      (not reflex agents).
    """
    return currentGameState.getScore()

class MultiAgentSearchAgent(Agent):
    """
      This class provides some common elements to all of your
      multi-agent searchers.  Any methods defined here will be available
      to the MinimaxPacmanAgent, AlphaBetaPacmanAgent & ExpectimaxPacmanAgent.

      You *do not* need to make any changes here, but you can if you want to
      add functionality to all your adversarial search agents.  Please do not
      remove anything, however.

      Note: this is an abstract class: one that should not be instantiated.  It's
      only partially specified, and designed to be extended.  Agent (game.py)
      is another abstract class.
    """

    def __init__(self, evalFn = 'scoreEvaluationFunction', depth = '2'):
        self.index = 0 # Pacman is always agent index 0
        self.evaluationFunction = util.lookup(evalFn, globals())
        self.depth = int(depth)

class MinimaxAgent(MultiAgentSearchAgent):
    """
      Your minimax agent (question 2)
    """

    def getAction(self, gameState):
        """
          Returns the minimax action from the current gameState using self.depth
          and self.evaluationFunction.

          Here are some method calls that might be useful when implementing minimax.

          gameState.getLegalActions(agentIndex):
            Returns a list of legal actions for an agent
            agentIndex=0 means Pacman, ghosts are >= 1

          gameState.generateSuccessor(agentIndex, action):
            Returns the successor game state after an agent takes an action

          gameState.getNumAgents():
            Returns the total number of agents in the game
        """
        "*** YOUR CODE HERE ***"
        # find the minimax value
        minimaxValue, action = self.calcMinimaxValue(gameState, 0, depth=0)
        return action

    def calcMinimaxValue(self, gameState, agent, depth):
        # We'll stop if we reached the max depth, we have no moves left, we're a winning state or a losing state.
        if depth >= self.depth or not gameState.getLegalActions(agent) or gameState.isWin() or gameState.isLose():
          return self.evaluationFunction(gameState), None

        newStates = [(gameState.generateSuccessor(agent, action), action) for action in gameState.getLegalActions(agent)]
        nextAgent = (agent+1) % gameState.getNumAgents()
        if nextAgent == 0:
          # if the next agent is pacman, it means that we've finished the current turn of the pacman and all the
          # ghosts, and the depth should be increased.
          depth += 1

        minimaxes = [(self.calcMinimaxValue(state, nextAgent, depth)[0], action) for state, action in newStates]
        if agent == 0: # it's pacman turn (maximum player)
          return max(minimaxes)
        else:  # it's a ghost turn (minimum player)
          return min(minimaxes)


class AlphaBetaAgent(MultiAgentSearchAgent):
    """
      Your minimax agent with alpha-beta pruning (question 3)
    """

    def getAction(self, gameState):
        """
          Returns the minimax action using self.depth and self.evaluationFunction
        """
        "*** YOUR CODE HERE ***"
        minimaxValue, action = self.calcMinimaxValue(gameState, 0, depth=0, alpha=float('-inf'), beta=float('inf'))
        #action = self.value(gameState, 0, depth=0, alpha=float('-inf'), beta=float('inf'))[-1]
        return action

    def calcMinimaxValue(self, gameState, agent, depth, alpha, beta):
        # We'll stop if we reached the max depth, we have no moves left, we're a winning state or a losing state.
        if depth >= self.depth or not gameState.getLegalActions(agent) or gameState.isWin() or gameState.isLose():
          return [self.evaluationFunction(gameState), None]

        actions = gameState.getLegalActions(agent)
        nextAgent = (agent+1) % gameState.getNumAgents()
        if nextAgent == 0:
          # if the next agent is pacman, it means that we've finished the current turn of the pacman and all the
          # ghosts, and the depth should be increased.
          depth += 1

        if agent == 0: # it's pacman turn (maximum player)
          bestMove = [float('-inf'), None]
          for action in actions:
            state = gameState.generateSuccessor(agent, action)
            currentMove = [self.calcMinimaxValue(state, nextAgent, depth, alpha, beta)[0], action]
            bestMove = max(bestMove, currentMove)
            alpha = max(alpha, bestMove[0])
            if alpha >= beta:  # pruning this branch
              bestMove[0] += 0.001  # increasing the minimax value by a small epsilon, so it won't get chosen.
              break
        else:
          bestMove = [float('inf'), None]
          for action in actions:
            state = gameState.generateSuccessor(agent, action)
            currentMove = [self.calcMinimaxValue(state, nextAgent, depth, alpha, beta)[0], action]
            bestMove = min(bestMove, currentMove)
            beta = min(beta, bestMove[0])
            if alpha >= beta:  # pruning this branch
              bestMove[0] -= 0.001  # decreasing the minimax value by a small epsilon, so it won't get chosen.
              break

        return bestMove

class ExpectimaxAgent(MultiAgentSearchAgent):
    """
      Your expectimax agent (question 4)
    """

    def getAction(self, gameState):
        """
          Returns the expectimax action using self.depth and self.evaluationFunction

          All ghosts should be modeled as choosing uniformly at random from their
          legal moves.
        """
        "*** YOUR CODE HERE ***"
        minimaxValue, action = self.calcMinimaxValue(gameState, 0, depth=0)
        return action

    def calcMinimaxValue(self, gameState, agent, depth):
        # We'll stop if we reached the max depth, we have no moves left, we're a winning state or a losing state.
        if depth >= self.depth or not gameState.getLegalActions(agent) or gameState.isWin() or gameState.isLose():
          return self.evaluationFunction(gameState), None

        newStates = [(gameState.generateSuccessor(agent, action), action) for action in gameState.getLegalActions(agent)]
        nextAgent = (agent+1) % gameState.getNumAgents()
        if nextAgent == 0:
          # if the next agent is pacman, it means that we've finished the current turn of the pacman and all the
          # ghosts, and the depth should be increased.
          depth += 1

        minimaxes = [(self.calcMinimaxValue(state, nextAgent, depth)[0], action) for state, action in newStates]
        if agent == 0: # it's pacman turn (maximum player)
          return max(minimaxes)
        else:  # it's a ghost turn (minimum player)
          minimaxes_vals = [x[0] for x in minimaxes]
          avg = sum(minimaxes_vals) / float(len(minimaxes_vals))

          # since it's an average, we're losing the data of which direction we came from. We pass None
          return (avg, None)

def betterEvaluationFunction(currentGameState):
    """
      Your extreme ghost-hunting, pellet-nabbing, food-gobbling, unstoppable
      evaluation function (question 5).

      DESCRIPTION: <write something here so we know what you did>
    """
    "*** YOUR CODE HERE ***"
    util.raiseNotDefined()

# Abbreviation
better = betterEvaluationFunction

