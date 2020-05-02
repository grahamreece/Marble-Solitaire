package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the implementation of the marble solitaire controller. This controller will control\
 * actions of the marble solitaire game based on inputs it receives.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  private final Readable inputted;
  private final Appendable outputted;

  /**
   * Constructor that takes in the Readable and Appendable fields in order to handle input and
   * outputs for the user.
   *
   * @param rd A readable object to represent the inputs and handling of inputs from the user.
   * @param ap An appendable object to represent the outputs and handling of them sent to the user.
   * @throws IllegalArgumentException if either of the arguments given to the controller constructor
   *                                  are null.
   */
  public MarbleSolitaireControllerImpl(Readable rd, Appendable ap) throws IllegalArgumentException {
    if (rd == null || ap == null) {
      throw new IllegalArgumentException("The arguments given to the marble solitaire controller" +
              "cannot be null");
    }
    this.inputted = rd;
    this.outputted = ap;
  }

  @Override
  public void playGame(MarbleSolitaireModel model) throws IllegalArgumentException,
          IllegalStateException {
    // throw and exception when the model is null
    if (model == null) {
      throw new IllegalArgumentException("The marble solitaire model cannot be null");
    }
    // add the initial game state and score to the output
    this.appendModelGameStateToOutput(model);
    this.appendModelScoreToOutput(model);
    Scanner scanner = new Scanner(this.inputted);
    ArrayList<Integer> moveInformation = new ArrayList<Integer>();
    // throw an exception if there is not input from the beginning -- if the scanner does not
    // have a next object to process
    if (!scanner.hasNext()) {
      throw new IllegalStateException("Readable has run out of input to process");
    }
    // check, with while loop, if there is still input from the user, and do the appropriate action
    // if the sequence of inputs is not over yet.
    while (scanner.hasNext()) {
      String nextInput = scanner.next();

      // return the appropriate information for the game being over if it is so
      if (model.isGameOver()) {
        this.appendGameOverToOutput(model);
        this.appendModelGameStateToOutput(model);
        this.appendModelScoreToOutput(model);
        return;
      }

      // handle the case that the input is Q or q, which is a special case in which the game has
      // been quit, and a special message must be appended to the output
      switch (nextInput) {
        case "Q":
        case "q":
          String gameQuitString = "Game quit!\n" + "State of game when quit:\n" +
                  model.getGameState() + "\nScore: " + model.getScore();
          String gameQuitError = "The game quit information could not be appended to the output";
          this.tryAppendingToOutput(gameQuitString, gameQuitError);
          return;
        default:
          try {
            int nextInt = Integer.parseInt(nextInput);
            moveInformation.add(nextInt);
          } catch (NumberFormatException e) {
            String stringSignalingValueReenter = "Please re-enter the value!\n";
            String valueReenterErrorMessage = "Could not append information signaling to" +
                    "re-enter a value";
            this.tryAppendingToOutput(stringSignalingValueReenter, valueReenterErrorMessage);
          }
          break;
      }

      // when four values are entered for the move information input from the user, signaling
      // that a move is wanted to be made
      if (moveInformation.size() == 4) {
        // this is to ensure that the numbers that the user enters are simpler, since the system
        // starts an index of 0, and users will wish to start at an index of 1,1, instead of
        // having a system with an index that starts at 0
        int fromRow = moveInformation.get(0) - 1;
        int fromCol = moveInformation.get(1) - 1;
        int toRow = moveInformation.get(2) - 1;
        int toCol = moveInformation.get(3) - 1;
        // set the move information back to empty so that when the user plays again, the move
        // information has been reset for the next move

        moveInformation = new ArrayList<Integer>();

        try {
          model.move(fromRow, fromCol, toRow, toCol);
          this.appendModelGameStateToOutput(model);
          this.appendModelScoreToOutput(model);
        } catch (IllegalArgumentException e) {
          String invalidMoveString = "Invalid move. Play again. " + e.getLocalizedMessage();
          String invalidMoveAppendError = "Could not append the invalid move information to the" +
                  "output.";
          this.tryAppendingToOutput(invalidMoveString, invalidMoveAppendError);
        }
      }
      // after each individual move now checks for the game being over
      if (model.isGameOver()) {
        this.appendGameOverToOutput(model);
        this.appendModelGameStateToOutput(model);
        this.appendModelScoreToOutput(model);
        return;
      }
    }
    // if there is ever the case that the user just ran out of inputs, or did not want to input
    // anything but there is no action left to perform, an illegal state exception should again be
    // caught
    throw new IllegalStateException("Game not over but no inputs left to provide");

  }

  /**
   * Appends the current game State of the game, received from the model, to the output of the
   * appendable object of the controller.
   *
   * @param model The model that is being used for the game.
   * @throws IllegalStateException If the game state can't be appended to the appendable.
   */
  private void appendModelGameStateToOutput(MarbleSolitaireModel model) {
    String gameStateErrorMessage = "Could not append the model's game state to the output";
    this.tryAppendingToOutput(model.getGameState() + "\n", gameStateErrorMessage);
  }

  /**
   * Appends the current score of the game, received from the model, to the output of the appendable
   * object of the controller.
   *
   * @param model The model that is being used for the game
   * @throws IllegalStateException If the score can't be appended to the appendable.
   */
  private void appendModelScoreToOutput(MarbleSolitaireModel model) {
    String scoreErrorMessage = "Could not append the model's score to the output";
    this.tryAppendingToOutput("Score: " + model.getScore() + "\n", scoreErrorMessage);
  }

  /**
   * Appends the current score of the game, received from the model, to the output of the appendable
   * object of the controller.
   *
   * @param model The model that is being used for the game
   * @throws IllegalStateException If the score can't be appended to the appendable.
   */
  private void appendGameOverToOutput(MarbleSolitaireModel model) {
    String gameOverErrorMessage = "Could not append the model's indication of the game being over" +
            " to the output";
    this.tryAppendingToOutput("Game over!\n", gameOverErrorMessage);
  }

  /**
   * Tries to append a given string to the appendable object of the controller. Gives an intended
   * error message if the appending fails and throws an IO exception.
   *
   * @param stringFromInput      The string that we wish to append to the appendable object of the
   *                             marble solitaire controller.
   * @param errorStringFromInput The error message that is to be returned in case the appending to
   *                             the output fails.
   * @throws IllegalStateException if the String can't be added to the appendable, and throws an IO
   *                               exception.
   */
  private void tryAppendingToOutput(String stringFromInput, String errorStringFromInput) {
    try {
      this.outputted.append(stringFromInput);
    } catch (IOException e) {
      throw new IllegalStateException(errorStringFromInput);
    }
  }
}
