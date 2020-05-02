package cs3500.marblesolitaire.model.hw02;

import java.util.ArrayList;

import cs3500.marblesolitaire.model.hw04.AMarbleSolitaireModelImpl;

/**
 * Represents the implementation of the MarbleSolitaire Model. This is a model of the marble
 * solitaire game, where there is a board of slots or marbles for the game, and the board consists
 * of different rows that have an amount of slots equivalent to an "arm thickness", and the middle
 * rows of the game have rows that have an amount of slots equivalent to a "full thickness."
 * Additionally, this game has a starting position for where there is an empty slot that does not
 * contain a marble.
 */
public class MarbleSolitaireModelImpl extends AMarbleSolitaireModelImpl {

  /**
   * Empty constructor with no paramaters that initializes the game board to have an arm thickness
   * of three, and an empty slot at the center.
   */
  public MarbleSolitaireModelImpl() {
    this(3, 3, 3);
  }

  /**
   * Constructor for the marble solitaire model implementation that takes in the starting row and
   * column of the empty slot as parameters, and throws an exception if the slot can not exist on
   * the board for the given starting row and column.
   *
   * @param sRow the row location for the starting empty slot.
   * @param sCol the column location for the starting empty slot.
   * @throws IllegalArgumentException if the starting row and starting column give invalid positions
   *                                  for the starting empty slot to be in. The exception will be
   *                                  thrown if it needs to be upon the passing of this method to
   *                                  the constructor which takes all three arguments.
   */
  public MarbleSolitaireModelImpl(int sRow, int sCol) {
    this(3, sRow, sCol);
  }

  /**
   * Constructor for the Marble Solitaire model implementation that takes in armThickness as a
   * parameter for the board's arm thickness, and throws an exception if the arm thickness is one,
   * or is not a positive, odd number. It also places the empty slot at the center of the board.
   *
   * @param armThickness the arm thickness integer for the board of the game
   * @throws IllegalArgumentException if the arm thickness is invalid - meaning it is one, or is not
   *                                  a positive odd number. The exception will be thrown if it
   *                                  needs to be upon the passing of this method to the constructor
   *                                  which takes all three arguments.
   */

  public MarbleSolitaireModelImpl(int armThickness) {
    this(armThickness, (((2 * armThickness) + (armThickness - 2)) - 1) / 2,
            (((2 * armThickness) + (armThickness - 2)) - 1) / 2);
  }

  /**
   * Constructor for the Marble Soliataire model implementation that takes in armThickness as a
   * parameter for the board's arm thickness, takes in a starting position for the row of the
   * starting empty slot, and takes in a starting position for the column of the starting empty
   * slot. It also throws an exception if the given starting row and columns indicate a location
   * that is out of bounds, and throws an exception if the arm thickness is invalid. In addition, it
   * now initializes the board of marbles, spaces, and the empty slot for the game board of the
   * marble solitaire game according the passed in parameters for arm thickness and the starting
   * row and column numbers given of the empty slot.
   *
   * @param armThickness the arm thickness number for the board
   * @param sRow         the starting row for the empty slot
   * @param sCol         the starting column for the empty slot
   * @throws IllegalArgumentException if the arm thickness is invalid, or if the starting row and
   *                                  column for the empty slot give an invalid location.
   */
  public MarbleSolitaireModelImpl(int armThickness, int sRow, int sCol) {
    // here, the armThickness modulo two equaling zero would mean it is even
    if ((armThickness == 1) || (armThickness <= 0) || (armThickness % 2 == 0)) {
      throw new IllegalArgumentException("Invalid arm thickness");
    }
    int fullThickness = ((2 * armThickness) + (armThickness - 2)) - 1;
    for (int i = 0; i <= fullThickness; i++) {
      ArrayList<GamePiece> currentRow = new ArrayList<GamePiece>();
      for (int j = 0; j <= fullThickness; j++) {
        // if the location is not out of the confines of the board according to how English marble
        // solitaire is played, a marble is first added in the place
        if ((armThickness - 2 < i && i < 2 * armThickness - 1) ||
                (armThickness - 2 < j && j < 2 * armThickness - 1)) {
          currentRow.add(GamePiece.Marble);
        }
        // otherwise, it is out of the confines of the board, and we need to add the empty space
        // for those game piece locations
        else {
          currentRow.add(GamePiece.EmptySpace);
        }
      }
      // now add all of the rows to complete the board, this aspect makes up for the various void
      // methods I made in my previous implementation, such as initialize board
      this.board.add(currentRow);
    }
    // now, we need to try and, at the given locations for the starting row and column of the empty
    // slot, initialize the game piece to an empty slot, and catch the exception when the location
    // is invalid
    try {
      if (this.board.get(sRow).get(sCol).equals(GamePiece.Marble)) {
        this.setToEmptySlot(sRow, sCol);
      } else {
        throw new IndexOutOfBoundsException("The desired start location is out of the confines " +
                "according to traditional playing of marble solitaire");
      }
    } catch (IndexOutOfBoundsException e) {
      throw new IllegalArgumentException("The position," + " (" + sRow + "," + sCol + "), is out " +
              "of bounds for this board.");
    }
  }
}
