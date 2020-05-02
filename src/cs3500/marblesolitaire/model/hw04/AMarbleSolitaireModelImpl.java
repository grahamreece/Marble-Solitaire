package cs3500.marblesolitaire.model.hw04;

import java.util.ArrayList;

import cs3500.marblesolitaire.model.hw02.GamePiece;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * Represents an abstract class for marble solitaire implementations. Children classes of this
 * abstract class will inherit its methods in order to extend the functionality that it provides
 * for marble solitaire implementations.
 */
public abstract class AMarbleSolitaireModelImpl implements MarbleSolitaireModel {

  protected ArrayList<ArrayList<GamePiece>> board = new ArrayList<ArrayList<GamePiece>>(); // a 2D
  // array of game pieces to represent the board of the model

  /** The constructors for the marble solitaire games will be implemented within the children
   *  classes of this abstract marble solitaire class. This is because the constructors will have
   *  to take in different details according to the armThickness and starting rows and columns for
   *  the games, and therefore constructors have not been abstracted to this class.
   */

  /**
   * Sets the space at the given position to a slot with or without a space, depending on the state
   * it is in now.
   *
   * @param row the row or x-position of the space
   * @param col the column or y-position of the space
   */
  protected void setToEmptySlot(int row, int col) {
    this.board.get(row).set(col, GamePiece.EmptySlot);
  }

  /**
   * Sets the space at the given position to a marble with or without a space, depending on the
   * state it is in now.
   *
   * @param row the row or x-position of the space
   * @param col the column or y-position of the space
   */
  private void setToMarble(int row, int col) {
    this.board.get(row).set(col, GamePiece.Marble);
  }

  /**
   * Determines if a set of conditions is true for the given positions of the move. If one of the
   * set of conditions is true, then it will throw an exception which indicates the reason for the
   * move being invalid. The conditions that must be checked for a marble solitaire game move to be
   * invalid include that the from or to positions are out of the bounds of the board, the from
   * position is not a marble, the to position is not an empty slot, the position in between is not
   * a marble, and the move is diagonal or is not two spaces apart.
   *
   * @param fromRow the row or x position of the from position of the model
   * @param fromCol the column or y position of the from position of the model
   * @param toRow   the row or x position of the to position of the model
   * @param toCol   the column or y position of the to position of the model
   * @throws IllegalArgumentException if the move is invalid under any circumstance
   */
  protected void checkInvalidMovePossibilities(int fromRow, int fromCol, int toRow, int toCol)
          throws IllegalArgumentException {
    int midpointOfMoveX = (fromRow + toRow) / 2;
    int midpointOfMoveY = (fromCol + toCol) / 2;
    // need to output a different error message for each type of invalid move because the
    // controller will need to output a reason for the user to input a new move when they play
    // the game and make an invalid move
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
    // if the x or y position after the move is not the same, then this means the move was not in
    // one of the acceptable four directions, meaning the error is the move is diagonal
    else if (!((fromRow == toRow) || (fromCol == toCol))) {
      throw new IllegalArgumentException("The move cannot be diagonal\n");
    } else if (!this.moveIsTwoPiecesApart(fromRow, fromCol, toRow, toCol)) {
      throw new IllegalArgumentException("The move has to be two pieces apart\n");
    }
  }

  /**
   * Determines if the space given by the row and column is outside of the boundaries of a typical
   * marble solitaire board, and returns true if it is indeed outside of these bounds.
   *
   * @param row the row position, or x position, of the space.
   * @param col the column position, or y position, of the space.
   * @return true if the given position is outside the boundaries of the marble solitaire board.
   */
  protected boolean isOutOfBounds(int row, int col) {
    // this first line ensures that position won't be out of bounds in the top left quadrant
    int fullThickness = (this.board.size() - 1);
    return row < 0 || col < 0 || row > fullThickness || col > fullThickness ||
            this.board.get(row).get(col).equals(GamePiece.EmptySpace);
  }

  /**
   * Determines if the game piece at the given position is a marble.
   *
   * @param row the row, or x, of the position
   * @param col the column, or y, of the position
   * @return true if the given position is a game piece that is equivalent to a marble
   */
  protected boolean pieceIsMarble(int row, int col) {
    return this.board.get(row).get(col) == GamePiece.Marble;
  }

  /**
   * Determines if the game piece at the given position is an empty slot.
   *
   * @param row the row, or x, of the position
   * @param col the column, or y, of the position
   * @return true if the given position is a game piece that is equivalent to an empty slot
   */
  protected boolean pieceIsEmptySlot(int row, int col) {
    return this.board.get(row).get(col) == GamePiece.EmptySlot;
  }

  /**
   * Determines if the positions given by the to coordinates and the from coordinates are exactly
   * two spaces apart going up, down, left, or right, and does so by checking that one of the x or y
   * positions is equivalent between positions, and the other has an absolute difference of two.
   *
   * @param fromRow the row, or x position, of the from position
   * @param fromCol the column, or y position, of the from position
   * @param toRow   the row, or x position, of the to position
   * @param toCol   the column, or y position, of the to position
   * @return true if the from and two positions are exactly two spaces apart going up, down, left,
   *         or right.
   */
  private boolean moveIsTwoPiecesApart(int fromRow, int fromCol, int toRow, int toCol) {
    return ((Math.abs(toCol - fromCol) == 2 && toRow - fromRow == 0) ||
            (Math.abs(toRow - fromRow) == 2 && toCol - fromCol == 0));
  }

  /**
   * Determines if there is a move available in any of the four possible directions. This abstracted
   * form will be useful for both the English and European marble solitaire versions, but will need
   * to be re-implemented for the more unique triangle solitaire game board.
   *
   * @param row the row, or x, position of the marble to check if there are moves available
   * @param col the column, or y, position of the marble to check if there are moves available
   * @throws IllegalArgumentException if the row and column give the position of a game piece which
   *                                  is not a marble.
   */
  protected boolean marbleWithMove(int row, int col) {
    int fullThickness = (this.board.size() - 1);
    if (this.board.get(row).get(col) != GamePiece.Marble) {
      throw new IllegalArgumentException("The game piece checking for moves is not a marble");
    }
    return // is there a move in the upward direction
            row > 1 && this.validMove(row, col, row - 2, col) ||
                    // is there a move in the left direction
                    col > 1 && this.validMove(row, col, row, col - 2) ||
                    // is there a move in the right direction
                    col < fullThickness && this.validMove(row, col, row, col + 2) ||
                    // is there a move in the downward direction
                    row < fullThickness && this.validMove(row, col, row + 2, col);
  }

  /**
   * Determines if a move made from the given from coordinates to the given to coordinates would
   * be a valid move according to what the state of the model is.
   *
   * @param fromRow the from position of the row of the game piece.
   * @param fromCol the from position of the column of the game piece.
   * @param toRow   the row to move the game piece to.
   * @param toCol   the row to move the game piece to.
   * @return whether or not the given positions indicate a move that, according to this model,
   *         will be valid depending on the implementation.
   */
  protected boolean validMove(int fromRow, int fromCol, int toRow, int toCol) {
    int midpointOfMoveX = (fromRow + toRow) / 2;
    int midpointOfMoveY = (fromCol + toCol) / 2;
    int fullThickness = (this.board.size() - 1);
    // rather than breaking it up into separate parts like the move method does in order to check
    // all the improper move possibilities, this just checks that all of the cases are met and
    // it therefore checks if a move is overall valid, rather than checking why it isn't valid

    // check that its within bounds
    return ((fromRow >= 0 && fromCol >= 0) && (toRow >= 0 && toCol >= 0))
            && ((fromRow <= fullThickness && fromCol <= fullThickness) &&
            (toRow <= fullThickness && toCol <= fullThickness))
            // check that the move was made in a distance of 2 in either direction
            && ((Math.abs(fromRow - toRow) == 2 && fromCol - toCol == 0)
            || (Math.abs(fromCol - toCol) == 2 && fromRow - toRow == 0))
            // check that the from piece is a marble
            && this.pieceIsMarble(fromRow, fromCol)
            // check that the jumped piece is a marble
            && this.pieceIsMarble(midpointOfMoveX, midpointOfMoveY)
            // check that the to piece is an empty slot
            && this.pieceIsEmptySlot(toRow, toCol);
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws
          IllegalArgumentException {
    this.checkInvalidMovePossibilities(fromRow, fromCol, toRow, toCol);
    int midpointOfMoveX = (fromRow + toRow) / 2;
    int midpointOfMoveY = (fromCol + toCol) / 2;
    // set the "from" position to an empty slot now
    this.setToEmptySlot(fromRow, fromCol);
    // set the "to" position to a marble now
    this.setToMarble(toRow, toCol);
    // set the jumped space, the one that the marble hopped over to get to the empty space, whose
    // position is given by the midpoint of the move, to an empty slot as well
    this.setToEmptySlot(midpointOfMoveX, midpointOfMoveY);
  }

  @Override
  public boolean isGameOver() {
    int fullThickness = (this.board.size() - 1);
    for (int i = 0; i <= fullThickness; i++) {
      for (int j = 0; j <= fullThickness; j++) {
        if (board.get(i).get(j).equals(GamePiece.Marble) && this.marbleWithMove(i, j)) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public String getGameState() {
    StringBuilder stringToBuild = new StringBuilder();
    int fullThickness = (this.board.size() - 1);
    for (int i = 0; i <= fullThickness; i++) {
      for (int j = 0; j <= fullThickness; j++) {
        GamePiece current = board.get(i).get(j);
        // in this instance, we need to add an extra space in order to adhere to how the string
        // of the board is wished to be represented
        if (j > 0 && !(current == GamePiece.EmptySpace && j > this.board.size() / 2)) {
          stringToBuild.append(" ");
        }
        // in this instance, we can just add the overridden to string methods that we created
        // in the game piece enumeration, we don't need to add space
        if (!(current == GamePiece.EmptySpace && j > (2 * this.board.size() / 3 - 1))) {
          stringToBuild.append(current.toString());
        }
      }
      // for every row besides the last row, add a new line at the end as that is how we wish to
      // represent the board
      if (i < fullThickness) {
        stringToBuild.append("\n");
      }
    }
    return stringToBuild.toString();
  }

  @Override
  public int getScore() {
    int fullThickness = (this.board.size() - 1);
    int scoreCount = 0;
    for (int i = 0; i <= fullThickness; i++) {
      for (int j = 0; j <= fullThickness; j++) {
        GamePiece current = this.board.get(i).get(j);
        if (current == GamePiece.Marble) {
          scoreCount++;
        }
      }
    }
    return scoreCount;
  }

}

