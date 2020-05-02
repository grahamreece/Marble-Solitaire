import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModelImpl;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * To test the methods and exceptions that should be made and implemented with a triangle solitaire
 * board.
 */
public final class TriangleSolitaireModelImplTest {

  TriangleSolitaireModelImpl triangleEmptyConstructor;
  TriangleSolitaireModelImpl triangle2Constructor1;
  TriangleSolitaireModelImpl triangle2Constructor2;
  TriangleSolitaireModelImpl triangle3Constructor1;
  TriangleSolitaireModelImpl triangle3Constructor2;
  TriangleSolitaireModelImpl triangle4Constructor1;
  TriangleSolitaireModelImpl triangle4Constructor2;


  /**
   * Initializes the data that will be used later in the test methods.
   */
  @Before
  public void initData() {
    this.triangleEmptyConstructor = new TriangleSolitaireModelImpl();
    this.triangle2Constructor1 = new TriangleSolitaireModelImpl(2, 1);
    this.triangle2Constructor2 = new TriangleSolitaireModelImpl(3, 3);
    this.triangle3Constructor1 = new TriangleSolitaireModelImpl(2);
    this.triangle3Constructor2 = new TriangleSolitaireModelImpl(4);
    this.triangle4Constructor1 = new TriangleSolitaireModelImpl(7, 6, 3);
    this.triangle4Constructor2 = new TriangleSolitaireModelImpl(3, 2, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2Errors() {
    this.triangle2Constructor1 = new TriangleSolitaireModelImpl(0, -1);
    this.triangle2Constructor1 = new TriangleSolitaireModelImpl(-1, 0);
    this.triangle2Constructor1 = new TriangleSolitaireModelImpl(-1, -1);
    this.triangle2Constructor1 = new TriangleSolitaireModelImpl(0, 4);
    this.triangle2Constructor1 = new TriangleSolitaireModelImpl(10, 14);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor3Errors() {
    this.triangle3Constructor2 = new TriangleSolitaireModelImpl(0);
    this.triangle3Constructor2 = new TriangleSolitaireModelImpl(-2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor4Errors() {
    this.triangle4Constructor1 = new TriangleSolitaireModelImpl(3, 0, -1);
    this.triangle4Constructor1 = new TriangleSolitaireModelImpl(1, -1, 0);
    this.triangle4Constructor1 = new TriangleSolitaireModelImpl(2, -1, -1);
    this.triangle4Constructor1 = new TriangleSolitaireModelImpl(-1, 3, 4);
    this.triangle4Constructor1 = new TriangleSolitaireModelImpl(0, 10, 14);
    this.triangle4Constructor1 = new TriangleSolitaireModelImpl(0, 1, 1);
    this.triangle4Constructor1 = new TriangleSolitaireModelImpl(3, 5, 1);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptions() {
    this.initData();

    // given a from or to position that is out of bounds by being negative
    this.triangleEmptyConstructor.move(1, -2, 1, 0);
    this.triangleEmptyConstructor.move(-2, -2, -2, -4);
    this.triangleEmptyConstructor.move(2, 1, 2, -1);


    // given a from or to position that is out of bounds by being over or equal to the fullThickness
    this.triangleEmptyConstructor.move(10, 9, 12, 9);
    this.triangleEmptyConstructor.move(3, 6, 3, 8);
    this.triangleEmptyConstructor.move(8, 2, 6, 2);

    // given a from position that is not a String that represents a marble
    this.triangleEmptyConstructor.move(3, 3, 5, 3);
    this.triangleEmptyConstructor.move(3, 3, 3, 1);


    // given a move where there is not a marble in between the from and to positions
    this.triangleEmptyConstructor.move(2, 3, 4, 3);
    this.triangleEmptyConstructor.move(3, 2, 3, 4);

    // given a to position that is not a String that represents an empty slot
    this.triangleEmptyConstructor.move(1, 2, 3, 2);
    this.triangleEmptyConstructor.move(3, 6, 3, 4);

    // given a move that is not two positions left, right, or two positions diagonally
    // for a triangle solitaire board
    this.triangleEmptyConstructor.move(1, 3, 4, 4);
    this.triangleEmptyConstructor.move(5, 3, 0, 3);
    this.triangleEmptyConstructor.move(3, 2, 3, 3);
    this.triangleEmptyConstructor.move(1, 2, 2, 3);

  }

  @Test
  public void testGetGameState() {
    this.initData();
    assertEquals("    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O", this.triangleEmptyConstructor.getGameState());
    assertEquals("    O\n" +
            "   O O\n" +
            "  O _ O\n" +
            " O O O O\n" +
            "O O O O O", this.triangle2Constructor1.getGameState());
    assertEquals("    O\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O _\n" +
            "O O O O O", this.triangle2Constructor2.getGameState());
    assertEquals(" _\n" +
            "O O", this.triangle3Constructor1.getGameState());
    assertEquals("   _\n" +
            "  O O\n" +
            " O O O\n" +
            "O O O O", this.triangle3Constructor2.getGameState());
    assertEquals("      O\n" +
            "     O O\n" +
            "    O O O\n" +
            "   O O O O\n" +
            "  O O O O O\n" +
            " O O O O O O\n" +
            "O O O _ O O O", this.triangle4Constructor1.getGameState());
    assertEquals("  O\n" +
            " O O\n" +
            "_ O O", this.triangle4Constructor2.getGameState());
  }

  @Test
  public void testMove() {
    this.initData();
    this.triangleEmptyConstructor.move(2, 0, 0, 0);
    assertEquals("    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O", this.triangleEmptyConstructor.getGameState());
    this.triangle2Constructor1.move(4, 1, 2, 1);
    assertEquals("    O\n" +
            "   O O\n" +
            "  O O O\n" +
            " O _ O O\n" +
            "O _ O O O", this.triangle2Constructor1.getGameState());
    this.triangle2Constructor2.move(3, 1, 3, 3);
    assertEquals("    O\n" +
            "   O O\n" +
            "  O O O\n" +
            " O _ _ O\n" +
            "O O O O O", this.triangle2Constructor2.getGameState());
    assertEquals(true, this.triangle3Constructor1.isGameOver());
    assertEquals(" _\n" +
            "O O", this.triangle3Constructor1.getGameState());
    this.triangle3Constructor2.move(2, 0, 0, 0);
    assertEquals("   O\n" +
            "  _ O\n" +
            " _ O O\n" +
            "O O O O", this.triangle3Constructor2.getGameState());
    this.triangle4Constructor1.move(4, 1, 6, 3);
    assertEquals("      O\n" +
            "     O O\n" +
            "    O O O\n" +
            "   O O O O\n" +
            "  O _ O O O\n" +
            " O O _ O O O\n" +
            "O O O O O O O", this.triangle4Constructor1.getGameState());
    this.triangle4Constructor2.move(0, 0, 2, 0);
    assertEquals("  _\n" +
            " _ O\n" +
            "O O O", this.triangle4Constructor2.getGameState());
    this.triangleEmptyConstructor.move(4, 0, 2, 0);
    assertEquals("    O\n" +
            "   _ O\n" +
            "  O O O\n" +
            " _ O O O\n" +
            "_ O O O O", this.triangleEmptyConstructor.getGameState());
    this.triangleEmptyConstructor.move(4, 2, 4, 0);
    assertEquals("    O\n" +
            "   _ O\n" +
            "  O O O\n" +
            " _ O O O\n" +
            "O _ _ O O", this.triangleEmptyConstructor.getGameState());
    this.triangleEmptyConstructor.move(4, 4, 4, 2);
    assertEquals("    O\n" +
            "   _ O\n" +
            "  O O O\n" +
            " _ O O O\n" +
            "O _ O _ _", this.triangleEmptyConstructor.getGameState());
    this.triangleEmptyConstructor.move(2, 1, 4, 1);
    assertEquals("    O\n" +
            "   _ O\n" +
            "  O _ O\n" +
            " _ _ O O\n" +
            "O O O _ _", this.triangleEmptyConstructor.getGameState());
    this.triangleEmptyConstructor.move(4, 1, 4, 3);
    assertEquals("    O\n" +
            "   _ O\n" +
            "  O _ O\n" +
            " _ _ O O\n" +
            "O _ _ O _", this.triangleEmptyConstructor.getGameState());
    this.triangleEmptyConstructor.move(4, 3, 2, 1);
    assertEquals("    O\n" +
            "   _ O\n" +
            "  O O O\n" +
            " _ _ _ O\n" +
            "O _ _ _ _", this.triangleEmptyConstructor.getGameState());
    this.triangleEmptyConstructor.move(1, 1, 3, 1);
    assertEquals("    O\n" +
            "   _ _\n" +
            "  O _ O\n" +
            " _ O _ O\n" +
            "O _ _ _ _", this.triangleEmptyConstructor.getGameState());
    this.triangleEmptyConstructor.move(2, 0, 4, 2);
    assertEquals("    O\n" +
            "   _ _\n" +
            "  _ _ O\n" +
            " _ _ _ O\n" +
            "O _ O _ _", this.triangleEmptyConstructor.getGameState());
    this.triangleEmptyConstructor.move(3, 3, 1, 1);
    assertEquals("    O\n" +
            "   _ O\n" +
            "  _ _ _\n" +
            " _ _ _ _\n" +
            "O _ O _ _", this.triangleEmptyConstructor.getGameState());
    this.triangleEmptyConstructor.move(0, 0, 2, 2);
    assertEquals("    _\n" +
            "   _ _\n" +
            "  _ _ O\n" +
            " _ _ _ _\n" +
            "O _ O _ _", this.triangleEmptyConstructor.getGameState());


  }

  @Test
  public void testIsGameOver() {
    this.initData();

    assertEquals(false, this.triangleEmptyConstructor.isGameOver());
    assertEquals(false, this.triangle2Constructor1.isGameOver());
    assertEquals(true, this.triangle3Constructor1.isGameOver());
    assertEquals(false, this.triangle3Constructor2.isGameOver());
    assertEquals(false, this.triangle4Constructor1.isGameOver());

    this.testMove();

    assertEquals(true, this.triangleEmptyConstructor.isGameOver());
  }

  @Test
  public void testGetScore() {
    this.initData();
    assertEquals(14, this.triangleEmptyConstructor.getScore());
    this.triangleEmptyConstructor.move(2, 0, 0, 0);
    assertEquals(13, this.triangleEmptyConstructor.getScore());
    this.triangleEmptyConstructor.move(2, 2, 2, 0);
    assertEquals(12, this.triangleEmptyConstructor.getScore());

    this.testMove();
    assertEquals(3, this.triangleEmptyConstructor.getScore());
  }

}