# multiAgents.py
# --------------
# Licensing Information: Please do not distribute or publish solutions to this
# project. You are free to use and extend these projects for educational
# purposes. The Pacman AI projects were developed at UC Berkeley, primarily by
# John DeNero (denero@cs.berkeley.edu) and Dan Klein (klein@cs.berkeley.edu).
# For more info, see http://inst.eecs.berkeley.edu/~cs188/sp09/pacman.html

from util import manhattanDistance
from game import Directions
import random
import util

from game import Agent
from game import Actions


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
        scores = [self.evaluationFunction(
            gameState, action) for action in legalMoves]
        bestScore = max(scores)
        bestIndices = [index for index in range(
            len(scores)) if scores[index] == bestScore]
        # Pick randomly among the best
        chosenIndex = random.choice(bestIndices)

        "Add more of your code here if you want to"

        return legalMoves[chosenIndex]

    def evaluationFunction(self, currentGameState, action):
        """
        Design a better evaluation function here.

        The evaluation function takes in the current and proposed successor
        GameStates (pacman.py) and returns a number, where higher numbers are better.

        The code below extracts some useful information from the state, like the
        remaining food (oldFood) and Pacman position after moving (newPos).
        newScaredTimes holds the number of moves that each ghost will remain
        scared because of Pacman having eaten a power pellet.

        Print out these variables to see what you're getting, then combine them
        to create a masterful evaluation function.
        """
        # Useful information you can extract from a GameState (pacman.py)
        successorGameState = currentGameState.generatePacmanSuccessor(action)
        newPos = successorGameState.getPacmanPosition()
        oldFood = currentGameState.getFood().asList()
        oldCapsules = currentGameState.getCapsules()
        walls = currentGameState.getWalls()
        gridSize = walls.height * walls.width

        newGhostStates = successorGameState.getGhostStates()

        evaluation = successorGameState.getScore()
        for ghostState in newGhostStates:
            gPos = ghostState.getPosition()
            if util.manhattanDistance(gPos, newPos) < 2:
                evaluation -= gridSize

        if oldCapsules:
            capsulesDistances = [util.manhattanDistance(newPos, capsule) for capsule in oldCapsules]
            evaluation -= 2 * min(capsulesDistances)

        if oldFood:
            foodDistances = [util.manhattanDistance(newPos, food) for food in oldFood]
            evaluation -= min(foodDistances)

        return evaluation


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

    def __init__(self, evalFn='scoreEvaluationFunction', depth='2'):
        self.index = 0  # Pacman is always agent index 0
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

          Directions.STOP:
            The stop direction, which is always legal

          gameState.generateSuccessor(agentIndex, action):
            Returns the successor game state after an agent takes an action

          gameState.getNumAgents():
            Returns the total number of agents in the game
        """
        options = {}
        actions = gameState.getLegalActions(0)
        for action in actions:
            successor = gameState.generateSuccessor(0, action)
            options[action] = self.minValue(successor, self.depth)
        chosenAction = max(options, key=options.get)
        return chosenAction

    def maxValue(self, gameState, depth):
        if depth <= 0 or gameState.isWin() or gameState.isLose():
            return self.evaluationFunction(gameState)

        actions = gameState.getLegalActions(0)
        values = []
        for action in actions:
            successor = gameState.generateSuccessor(0, action)
            values.append(self.minValue(successor, depth))
        if not values:
            return self.evaluationFunction(gameState)
        return max(values)

    def minValue(self, gameState, depth, agent=1):
        if depth <= 0 or gameState.isWin() or gameState.isLose():
            return self.evaluationFunction(gameState)

        values = []
        actions = gameState.getLegalActions(agent)
        for action in actions:
            successor = gameState.generateSuccessor(agent, action)
            if agent == gameState.getNumAgents() - 1:
                values.append(self.maxValue(successor, depth - 1))
            else:
                values.append(self.minValue(successor, depth, agent + 1))

        if not values:
            return self.evaluationFunction(gameState)
        return min(values)


class AlphaBetaAgent(MultiAgentSearchAgent):
    """
      Your minimax agent with alpha-beta pruning (question 3)
    """

    def getAction(self, gameState):
        """
          Returns the minimax action using self.depth and self.evaluationFunction
        """
        options = {}
        actions = gameState.getLegalActions(0)
        for action in actions:
            successor = gameState.generateSuccessor(0, action)
            a = float("-inf")
            b = float("inf")
            options[action] = self.minValue(successor, self.depth, a, b)
        stopAction = options["Stop"]
        options["Stop"] = float("-inf")
        chosenAction = max(options, key=options.get)
        if stopAction > options[chosenAction]:
            return "Stop"
        return chosenAction

    def maxValue(self, gameState, depth, a, b):
        if depth <= 0 or gameState.isWin() or gameState.isLose():
            return self.evaluationFunction(gameState)

        actions = gameState.getLegalActions(0)
        values = []
        for action in actions:
            successor = gameState.generateSuccessor(0, action)
            v = self.minValue(successor, depth, a, b)
            if v >= b:
                return b
            a = max(v, a)
            values.append(v)
        if not values:
            return self.evaluationFunction(gameState)
        return max(values)

    def minValue(self, gameState, depth, a, b, agent=1):
        if depth <= 0 or gameState.isWin() or gameState.isLose():
            return self.evaluationFunction(gameState)

        values = []
        actions = gameState.getLegalActions(agent)
        for action in actions:
            successor = gameState.generateSuccessor(agent, action)
            if agent == gameState.getNumAgents() - 1:
                v = self.maxValue(successor, depth - 1, a, b)
            else:
                v = self.minValue(successor, depth, a, b, agent + 1)
                if v <= a:
                    return v
                b = min(v, b)
            values.append(v)

        if not values:
            return self.evaluationFunction(gameState)
        return min(values)


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
        options = {}
        actions = gameState.getLegalActions(0)
        for action in actions:
            successor = gameState.generateSuccessor(0, action)
            options[action] = self.expectimax(successor, self.depth, 0)
        stopAction = options["Stop"]
        options["Stop"] = float("-inf")
        chosenAction = max(options, key=options.get)
        if stopAction > options[chosenAction]:
            return "Stop"
        return chosenAction

    def expectimax(self, gameState, depth, agent):
        if depth <= 0 or gameState.isWin() or gameState.isLose():
            return self.evaluationFunction(gameState)

        actions = gameState.getLegalActions(agent)
        values = []
        for action in actions:
            successor = gameState.generateSuccessor(agent, action)
            if agent == 0:  # PacMan
                v = self.expectimax(successor, depth, 1)
            elif agent == gameState.getNumAgents() - 1:  # Last Ghost
                v = self.expectimax(successor, depth - 1, 0)
            else:  # Not last Ghost
                v = self.expectimax(successor, depth, agent + 1)
            values.append(v)

        if not values:
            return self.evaluationFunction(gameState)
        return float(sum(values))/float(len(values))


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


class ContestAgent(MultiAgentSearchAgent):
    """
      Your agent for the mini-contest
    """

    def getAction(self, gameState):
        """
          Returns an action.  You can use any method you want and search to any depth you want.
          Just remember that the mini-contest is timed, so you have to trade off speed and computation.

          Ghosts don't behave randomly anymore, but they aren't perfect either -- they'll usually
          just make a beeline straight towards Pacman (or away from him if they're scared!)
        """
        "*** YOUR CODE HERE ***"
        util.raiseNotDefined()
