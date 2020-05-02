package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * Represents the controller of the model - view - controller architecture for the marble
 * solitaire game. This controller lets users input a control that is processed and outputted by
 * the system.
 */
public interface MarbleSolitaireController {

  /** Plays a new game of Marble Solitaire using the provided model. It will parse input from the
   * user, and display the game state and the current score as output to the user.
   * The marble solitaire model that it takes in can not be a null value however. Also, if
   * unacceptable inputs are received from the user, it will prompt for re-entry of values before
   * outputting the intended action. This method also checks the users inputs against the model to
   * determine the validity of an action made, and, if the input results in an invalid move,
   * this method will again prompt for the correction of the user input.
   *
   * @param    model to represent the marble solitaire model that the controller works with.
   *
   * @throws    IllegalArgumentException If the provided model is null.
   * @throws    IllegalStateException If the Appendable object is unable to send appropraite output
   *                                  to the user, or if the Readable object is unable to provide
   *                                  appropriate input.
   */
  void playGame(MarbleSolitaireModel model) throws IllegalArgumentException, IllegalStateException;
}