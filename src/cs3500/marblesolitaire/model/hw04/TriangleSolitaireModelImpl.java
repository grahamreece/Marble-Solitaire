package cs3500.marblesolitaire.model.hw04;

import java.util.ArrayList;

import cs3500.marblesolitaire.model.hw02.GamePiece;

/**
 * Represents the implementation of the Triangular version of the marble solitaire game model. This
 * is a model of the Triangular solitaire game, where there is a board of slots or marbles for the
 * game, and the board consists of different rows that have an amount of game pieces equivalent to
 * a side length, and each row has an incrementing number of marbles in order to give the board the
 * shape and representation of a triangular shape.
 */
public class TriangleSolitaireModelImpl extends AMarbleSolitaireModelImpl {

  /**
   * An empty constructor with no parameters for the Triangle Solitaire game that creates a 5-row
   * game with the empty slot starting at the row and column position of(0,0), or the top position
   * of the triangular board.
   */
  public TriangleSolitaireModelImpl() {
    this(5, 0, 0);
  }

  /**
   * Constructor for the Triangular solitaire model implementation that takes in the starting row
   * and staring column of the empty slot as parameters, and throws an exception if the slot can not
   * exist on the board for the given starting row and column. It also sets the Triangular board's
   * bottom row, or base, to have a thickness of 5 and sets the triangular board to have five rows.
   *
   * @param sRow the row location for the starting empty slot.
   * @param sCol the column location for the starting empty slot.
   * @throws IllegalArgumentException if the starting row and starting column give invalid positions
   *                                  for the starting empty slot to be in. The exception will be
   *                                  thrown if it needs to be upon the passing of this method to
   *                                  the constructor which takes all three arguments.
   */
  public TriangleSolitaireModelImpl(int sRow, int sCol) {
    this(5, sRow, sCol);
  }

  /**
   * Constructor for the Triangular Solitaire model implementation that takes in sideLength as a
   * parameter for the triangular board's sides' length. Since the board is triangular in this
   * implementation, each side should have an equal amount of game pieces, and the side length
   * should be equivalent to both the number of game pieces in the bottom row, and the number of
   * rows needed to be created overall to represent the board. This constructor also throws an
   * exception if the side length is invalid which, for this implementation, simply means it is not
   * greater than a length of zero (it is a positive integer), and this constructor sets the
   * starting position of the empty slot of the game to the top point, or (0,0), as did the empty
   * constructor.
   *
   * @param sideLength the side length integer for the the size and number of game pieces that
   *                   represent each side of the Triangular board.
   * @throws IllegalArgumentException if the side length is invalid - meaning it is not a positive
   *                                  number greater than zero. The exception will be thrown if it
   *                                  needs to be upon the passing of this method to the Triangle
   *                                  model constructor which takes in all three recurring
   *                                  parameters.
   */

  public TriangleSolitaireModelImpl(int sideLength) {
    this(sideLength, 0, 0);
  }

  /**
   * Constructor for the Triangle Solitaire model implementation that takes in side length as a
   * parameter for the triangle's sides' length, takes in a starting position for the row of the
   * starting empty slot, and takes in a starting position for the column of the starting empty
   * slot. It also throws an exception if the given starting row and columns indicate a location
   * that is out of bounds, and throws an exception if the side length is invalid. In addition, it
   * now initializes the board of marbles, spaces, and the empty slot for the game board of the
   * Triangle solitaire game according the passed in parameters for side length and the starting row
   * and column numbers given of the empty slot.
   *
   * @param sideLength the side length integer for the the size and number of game pieces that
   *                   represent each side of the Triangular board.
   * @param sRow       the starting row for the empty slot
   * @param sCol       the starting column for the empty slot
   * @throws IllegalArgumentException if the side length is invalid (not a posiitve integer), or if
   *                                  the starting row and column for the empty slot give an invalid
   *                                  location.
   */
  public TriangleSolitaireModelImpl(int sideLength, int sRow, int sCol) {
    // first catch an exception for the side length being less than one - not a positive integer
    if (sideLength < 1) {
      throw new IllegalArgumentException("This Triangular side length is impractical.");
    }
    for (int i = 0; i < sideLength; i++) {
      ArrayList<GamePiece> currentRow = new ArrayList<GamePiece>();
      // add an extra marble for every row, as the index starts at 0, each time that row index,
      // or i in this case, increments, j will have an incrementing number of spaces to add the
      // marble game piece to
      for (int j = 0; j < i + 1; j++) {
        currentRow.add(GamePiece.Marble);
      }
      // when j, or the the column index, is equivalent to the space index, it is one step ahead of
      // it as indexing starts at 0, and therefore it is from here until the index reaches the side
      // length that we must add the empty spaces every time
      for (int j = i + 1; j < sideLength; j++) {
        currentRow.add(GamePiece.EmptySpace);
      }
      // add the rows to the board, as in the earlier implementations
      this.board.add(currentRow);
    }
    // try to initialize the empty slot where it is given by the starting row and column parameters
    // if that piece is a marble, and catch the out of bounds exception if it is not a marble at
    // that starting location
    try {
      if (this.board.get(sRow).get(sCol).equals(GamePiece.Marble)) {
        this.setToEmptySlot(sRow, sCol);
      } else {
        throw new IndexOutOfBoundsException("The desired start location is out of the confines " +
                "according to traditional playing of Triangular solitaire");
      }
    } catch (IndexOutOfBoundsException e) {
      throw new IllegalArgumentException("The position," + " (" + sRow + "," + sCol + "), is out " +
              "of bounds for this board.");
    }
  }

  /**
   * Determines if the move given by the from position and to position is, for the triangle
   * soliataire board, a move that is two spaces left or two spaces right, which would ensure that
   * it is valid for a triangular board.
   *
   * @param fromRow the from position of the row of the game piece.
   * @param fromCol the from position of the column of the game piece.
   * @param toRow   the row to move the game piece to.
   * @param toCol   the row to move the game piece to.
   * @return whether or not the from position and to position indicate a move that is either two
   *         game pieces left or two game pieces right.
   */
  private boolean triangularLeftRightMove(int fromRow, int fromCol, int toRow, int toCol) {
    return Math.abs(fromCol - toCol) == 2 && (fromRow - toRow) == 0;
  }

  /**
   * Determines if the move given by the from position and to position is, for the triangle
   * soliataire board, a move that is two spaces diagonally, which would ensure that it is valid for
   * a triangular board. The cases are that it is up and to the left diagonally, up and to the right
   * diagonally two pieces, down and to the right diagonally two pieces, and down and to the left
   * diagonally two pieces.
   *
   * @param fromRow the from position of the row of the game piece.
   * @param fromCol the from position of the column of the game piece.
   * @param toRow   the row to move the game piece to.
   * @param toCol   the row to move the game piece to.
   * @return whether or not the from position and to position indicate a move that is, for the
   *         triangular board, two spaces in either diagonal direction.
   */
  private boolean triangularDiagonalMove(int fromRow, int fromCol, int toRow, int toCol) {
    return (Math.abs(fromRow - toRow) == 2) && ((Math.abs(fromCol - toCol) == 2) ||
            (Math.abs(fromCol - toCol) == 0));
  }

  @Override
  protected void checkInvalidMovePossibilities(int fromRow, int fromCol, int toRow, int toCol)
          throws IllegalArgumentException {
    int midpointOfMoveX = (fromRow + toRow) / 2;
    int midpointOfMoveY = (fromCol + toCol) / 2;
    if (this.isOutOfBounds(fromRow, fromCol)) {
      throw new IllegalArgumentException("The from position is out of bounds\n");
    } else if (this.isOutOfBounds(toRow, toCol)) {
      throw new IllegalArgumentException("The to position is out of bounds\n");
    } else if (!this.pieceIsEmptySlot(toRow, toCol)) {
      throw new IllegalArgumentException("The to position is not an empty slot\n");
    } else if (!this.pieceIsMarble(fromRow, fromCol)) {
      throw new IllegalArgumentException("The from position is not a marble\n");
    } else if (!this.pieceIsMarble(midpointOfMoveX, midpointOfMoveY)) {
      throw new IllegalArgumentException("The jumped position is not a marble\n");
    }
    // this is where the implementation changes, because, besides the above factors, the moves
    // for triangle solitaire have more rules than just being two spaces apart
    else if (!this.triangularLeftRightMove(fromRow, fromCol, toRow, toCol) &&
            !this.triangularDiagonalMove(fromRow, fromCol, toRow, toCol)) {
      throw new IllegalArgumentException("The move is not two spaces horizontally, or is not two" +
              "spaces diagonally, making it invalid for a triangular board\n");
    }
  }

  @Override
  protected boolean validMove(int fromRow, int fromCol, int toRow, int toCol) {
    int midpointOfMoveX = (fromRow + toRow) / 2;
    int midpointOfMoveY = (fromCol + toCol) / 2;
    int fullThickness = (this.board.size() - 1);
    // check that its within bounds
    return ((fromRow >= 0 && fromCol >= 0) && (toRow >= 0 && toCol >= 0))
            && ((fromRow <= fullThickness && fromCol <= fullThickness) &&
            (toRow <= fullThickness && toCol <= fullThickness))
            // check that the from piece is a marble
            && this.pieceIsMarble(fromRow, fromCol)
            // check that the jumped piece is a marble
            && this.pieceIsMarble(midpointOfMoveX, midpointOfMoveY)
            // check that the to piece is an empty slot
            && this.pieceIsEmptySlot(toRow, toCol)
            // this is where the implementation changes,
            // check that the move was left / right two spaces or diagonal two spaces
            && (this.triangularLeftRightMove(fromRow, fromCol, toRow, toCol) ||
            this.triangularDiagonalMove(fromRow, fromCol, toRow, toCol));
  }

  @Override
  protected boolean marbleWithMove(int row, int col) {
    // we need to check that there is a valid move in six directions now instead of four, so the
    // implementation of this method (which is used in seeing if the game is over) is changed below

    // first line checks for a valid move to the left
    return this.validMove(row, col, row, col - 2) ||
            // check for a valid move to the right
            this.validMove(row, col, row, col + 2) ||
            // check for a valid move to diagonal top left
            this.validMove(row, col, row - 2, col - 2) ||
            // check for a valid move to diagonal top right
            this.validMove(row, col, row - 2, col) ||
            // check for a move diagonal bottom left
            this.validMove(row, col, row + 2, col) ||
            // check for a move diagonal bottom right
            this.validMove(row, col, row + 2, col + 2);
  }


  @Override
  public String getGameState() {
    StringBuilder stringToBuild = new StringBuilder();
    int fullThickness = board.size() - 1;
    for (int i = 0; i <= fullThickness; i++) {
      // this is where we need new implementation for the get game state of the triangular solitaire
      // board. There needs to be space building up to where the marble is located in the rows and,
      // for most of the top rows there will have to be space appended after the marble locations
      for (int spaceIndex = 0; spaceIndex <= (fullThickness - i) - 1; spaceIndex++) {
        stringToBuild.append(" ");
      }
      // the constructor has set up the locations of marbles properly so that in this case, we
      // can just return the overridden toString method from the enumeration for that game piece
      for (int j = 0; j <= fullThickness; j++) {
        GamePiece current = this.board.get(i).get(j);
        if (!current.equals(GamePiece.EmptySpace)) {
          stringToBuild.append(current.toString());
        }
        // otherwise, the space is equivalent to an empty space as we have designated already in the
        // constructor, and empty space can once again be appended
        if (j < i) {
          stringToBuild.append(" ");
        }
      }
      // and add a new line indicator after every row is added, except for when the row index
      // reaches the full thickness at which case it is the last line and does not need to be added
      if (i < fullThickness) {
        stringToBuild.append("\n");
      }
    }
    return stringToBuild.toString();
  }
}
