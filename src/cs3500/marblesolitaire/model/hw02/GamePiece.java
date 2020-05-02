package cs3500.marblesolitaire.model.hw02;

/**
 * This enumeration represents a singular space or piece of the marble solitaire game. The
 * conditions that the enumeration can be in is either a marble or an empty slot,
 * represented in the actual model and game state of the marble solitaire game. Additionally, in the
 * rows where the game board is the length of the arm thickness, and not the length of the full
 * thickness, there will be positions where empty space will need to be added, as the board will be
 * represented as a square while the marble solitaire game is not a square. Therefore, there is a
 * condition for a game piece that is just empty space.
 */
public enum GamePiece {
  EmptySlot {
    @Override
    public final String toString() {
      return "_";
    }
  },
  Marble {
    @Override
    public final String toString() {
      return "O";
    }
  },
  EmptySpace {
    @Override
    public final String toString() {
      return " ";
    }
  },
}