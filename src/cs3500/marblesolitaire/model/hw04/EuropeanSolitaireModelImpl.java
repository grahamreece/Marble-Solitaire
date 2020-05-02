package cs3500.marblesolitaire.model.hw04;

import java.util.ArrayList;

import cs3500.marblesolitaire.model.hw02.GamePiece;


/**
 * Represents the implementation of the European version of the marble solitaire game model. This is
 * a model of the European solitaire game, where there is a board of slots or marbles for the game,
 * and the board consists of different rows that have an amount of game pieces equivalent to a side
 * length, and other rows adjust to this side length to make an octagonal shape representative of
 * the European solitaire game.
 */
public class EuropeanSolitaireModelImpl extends AMarbleSolitaireModelImpl {

  /**
   * Empty constructor for the European solitaire game that initializes the game to have a side
   * length of three for the octagonal board, and sets the starting row and column for the empty
   * slot to the center of the octagonal board.
   */
  public EuropeanSolitaireModelImpl() {
    this(3, 3, 3);
  }

  /**
   * Constructor for the European solitaire model implementation that takes in the starting row and
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
  public EuropeanSolitaireModelImpl(int sRow, int sCol) {
    this(3, sRow, sCol);
  }

  /**
   * Constructor for the European Solitaire model implementation that takes in sideLength as a
   * parameter for the octagonal board's smallest side length, and throws an exception if the side
   * length is one, or is not a positive, odd number. It also places the empty slot at the center of
   * the board.
   *
   * @param sideLength the side length integer for the the smallest side of the octagonal board of
   *                   the European solitaire game.
   * @throws IllegalArgumentException if the side length is invalid - meaning it is one, or is not a
   *                                  positive odd number. The exception will be thrown if it needs
   *                                  to be upon the passing of this method to the constructor which
   *                                  takes all three arguments.
   */

  public EuropeanSolitaireModelImpl(int sideLength) {
    this(sideLength, (((2 * sideLength) + (sideLength - 2)) - 1) / 2,
            (((2 * sideLength) + (sideLength - 2)) - 1) / 2);
  }

  /**
   * Constructor for the European Solitaire model implementation that takes in side length as a
   * parameter for the board's arm thickness, takes in a starting position for the row of the
   * starting empty slot, and takes in a starting position for the column of the starting empty
   * slot. It also throws an exception if the given starting row and columns indicate a location
   * that is out of bounds, and throws an exception if the side length is invalid. In addition, it
   * now initializes the board of marbles, spaces, and the empty slot for the game board of the
   * European solitaire game according the passed in parameters for side length and the starting row
   * and column numbers given of the empty slot.
   *
   * @param sideLength the side length integer for the the smallest side of the octagonal board of
   *                   the European solitaire game.
   * @param sRow       the starting row for the empty slot
   * @param sCol       the starting column for the empty slot
   * @throws IllegalArgumentException if the side length is invalid, or if the starting row and
   *                                  column for the empty slot give an invalid location.
   */
  public EuropeanSolitaireModelImpl(int sideLength, int sRow, int sCol) {
    if ((sideLength == 1) || (sideLength <= 0) || (sideLength % 2 == 0)) {
      throw new IllegalArgumentException("Invalid side length for European Solitaire");
    }
    int fullThicknessActual = (2 * sideLength) + (sideLength - 2);
    for (int i = 0; i < fullThicknessActual; i++) {
      ArrayList<GamePiece> currentRow = new ArrayList<GamePiece>();
      // here the column index goes to half of the full thickness of the octagonal board because
      // the octagonal board is symmetric top to bottom, and, as the board is constructed from top
      // to bottom, the bottom half will have to be initialized the opposite direction
      for (int j = 0; j < fullThicknessActual / 2; j++) {
        // this will add rows where there is an amount of marbles equivalent to side length
        // in the first row, and then it will stack another marble on each end until the octagonal
        // shape has been accomplished
        if (i >= (sideLength - j - 1) && i <= (2 * sideLength) + (j - 2)) {
          currentRow.add(GamePiece.Marble);
        } else {
          currentRow.add(GamePiece.EmptySpace);
        }
      }
      // the bottom half of the board depends on different measurements according to the side
      // length parameter than the top, as so an additional for loop for these y-indexed rows
      // must be added and implemented
      for (int j = fullThicknessActual / 2; j < fullThicknessActual; j++) {
        if (i >= (sideLength * -2) + j + 2 && i <= (sideLength * 5) - j - 5) {
          currentRow.add(GamePiece.Marble);
        } else {
          currentRow.add(GamePiece.EmptySpace);
        }
      }
      // add the row with the proper marbles added on each end toward the end, and increasing empty
      // space as the index increases to the board of game pieces
      this.board.add(currentRow);
    }
    // now need to initialize the empty slot of the European solitaire game, and catch and throw
    // an exception where it is needed when the starting row and column are out of the confines
    // of a European solitaire board
    try {
      if (this.board.get(sRow).get(sCol).equals(GamePiece.Marble)) {
        this.setToEmptySlot(sRow, sCol);
      } else {
        throw new IndexOutOfBoundsException("The desired start location is out of the confines " +
                "according to traditional playing of European solitaire");
      }
    } catch (IndexOutOfBoundsException e) {
      throw new IllegalArgumentException("The position," + " (" + sRow + "," + sCol + "), is out " +
              "of bounds for this board.");
    }
  }
}