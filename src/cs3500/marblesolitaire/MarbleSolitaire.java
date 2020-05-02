package cs3500.marblesolitaire;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;
import cs3500.marblesolitaire.model.hw04.AMarbleSolitaireModelImpl;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModelImpl;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModelImpl;

import java.io.InputStreamReader;

/**
 * Represents the main class for actually running a marble solitaire game and entering the program
 * for using it. It takes in and works with simple command line arguments in order to figure out
 * what model to use and what conditions to use when running the game architecture.
 */
public final class MarbleSolitaire {

  /**
   * The Main method which takes in simple command line arguments in order to run and instantiate
   * the game of Marble Solitaire.
   *
   * @param args the array of strings that is the inputs for the command line arguments.
   */

  public static void main(String[] args) {
    // the first string in the command line should be the model indicator
    String modelIndicator = args[0];
    AMarbleSolitaireModelImpl modelAbstraction;
    MarbleSolitaireController gameController;
    // this allows us to input system.in as an argument for the readable input
    InputStreamReader readable = new InputStreamReader(System.in);
    // logic: have all the numbers for the constructors start at 0, and if some of the values still
    // equal zero, then we'll know which constructor to use
    int sizePlaceHolder = 0;
    // these place holders have to be negative as they can actually be set to 0 in some cases (
    // triangular board).
    int sRowPlaceHolder = -1;
    int sColPlaceHolder = -1;
    // starting at the second string in the command line and incrementing by two, the hole or size
    // indicators should be entered to indicate which constructor to use for the model
    for (int i = 1; i < args.length; i += 2) {
      switch (args[i]) {
        // if the second string (after the model indicator) is "-size", then set the place holder
        // for the size value (arm thickness, side length) to the next number given after the size
        // indicator
        case "-size":
          sizePlaceHolder = Integer.valueOf(args[i + 1]);
          break;
          // if the second string after the model is "-hole", or the second string after the size
        // indicator is "-hole", it is our start position indicator, so we have to set those place
        // holder values to the two integer values that come after the indicator
        case "-hole":
          sRowPlaceHolder = Integer.valueOf(args[i + 1]);
          sColPlaceHolder = Integer.valueOf(args[i + 2]);
          // add one and increment instead of two now to the index
          i += 1;
          break;
        case "": break;
        default:
          throw new IllegalArgumentException("Misformatted or Inappropriate Command" +
                  " Line Arguments");
      }
    }
    // if none of the place holders for the values have changed, then no size or hole indicators
    // have been used, and the default (empty) constructor must be used
    if (sizePlaceHolder == 0 && sRowPlaceHolder == -1 && sColPlaceHolder == -1) {
      switch (modelIndicator) {
        case "english": modelAbstraction = new MarbleSolitaireModelImpl();
          break;
        case "european": modelAbstraction = new EuropeanSolitaireModelImpl();
          break;
        case "triangle": modelAbstraction = new TriangleSolitaireModelImpl();
          break;
        default:
          throw new IllegalArgumentException("Misformatted or Inappropriate Command" +
                  " Line Arguments");
      }
    }
    // if just the values from the starting row and column placeholders have changed, then only
    // the hole indicator was used, and we must use that corresponding constructor (the second
    // constructor)
    else if (sizePlaceHolder == 0 && sRowPlaceHolder >= 0 && sColPlaceHolder >= 0) {
      switch (modelIndicator) {
        case "english": modelAbstraction = new MarbleSolitaireModelImpl(sRowPlaceHolder,
                sColPlaceHolder);
          break;
        case "european": modelAbstraction = new EuropeanSolitaireModelImpl(sRowPlaceHolder,
                sColPlaceHolder);
          break;
        case "triangle": modelAbstraction = new TriangleSolitaireModelImpl(sRowPlaceHolder,
                sColPlaceHolder);
          break;
        default:
          throw new IllegalArgumentException("Misformatted or Inappropriate Command" +
                  " Line Arguments");
      }
    }
    // if just the size place holder has changed, then only the constructors that takes in the size
    // for the boards (the third constructor that only takes in one argument) is used
    else if (sizePlaceHolder != 0 && sRowPlaceHolder == -1 && sColPlaceHolder == -1) {
      switch (modelIndicator) {
        case "english": modelAbstraction = new MarbleSolitaireModelImpl(sizePlaceHolder);
          break;
        case "european": modelAbstraction = new EuropeanSolitaireModelImpl(sizePlaceHolder);
          break;
        case "triangle": modelAbstraction = new TriangleSolitaireModelImpl(sizePlaceHolder);
          break;
        default:
          throw new IllegalArgumentException("Misformatted or Inappropriate Command" +
                  " Line Arguments");
      }
    }
    // if neither of the above cases are true, it must be that the fourth constructor is being
    // used for the abstracted model, and thus we will set the parameters to it
    else {
      switch (modelIndicator) {
        case "english": modelAbstraction = new MarbleSolitaireModelImpl(sizePlaceHolder,
                sRowPlaceHolder, sColPlaceHolder);
          break;
        case "european": modelAbstraction = new EuropeanSolitaireModelImpl(sRowPlaceHolder,
                sRowPlaceHolder, sColPlaceHolder);
          break;
        case "triangle": modelAbstraction = new TriangleSolitaireModelImpl(sizePlaceHolder,
                sRowPlaceHolder, sColPlaceHolder);
          break;
        default:
          throw new IllegalArgumentException("Misformatted or Inappropriate Command" +
                  " Line Arguments");
      }
    }
    // have a controller now play the game with the appropriate selected model with constructor
    // using an input stream reader as the readable object
    gameController = new MarbleSolitaireControllerImpl(readable, System.out);
    gameController.playGame(modelAbstraction);
  }
}
