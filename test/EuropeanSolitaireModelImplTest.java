import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModelImpl;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * To test the methods and exceptions that should be made and implemented with a European
 * solitaire board.
 */
public final class EuropeanSolitaireModelImplTest {
  EuropeanSolitaireModelImpl europeanEmptyConstructor;
  EuropeanSolitaireModelImpl european2Constructor1;
  EuropeanSolitaireModelImpl european2Constructor2;
  EuropeanSolitaireModelImpl european3Constructor1;
  EuropeanSolitaireModelImpl european3Constructor2;
  EuropeanSolitaireModelImpl european4Constructor1;
  EuropeanSolitaireModelImpl european4Constructor2;

  /**
   * Initializes the data that will be tested later in the written test methods.
   */
  @Before
  public void initData() {
    this.europeanEmptyConstructor = new EuropeanSolitaireModelImpl();
    this.european2Constructor1 = new EuropeanSolitaireModelImpl(1,1);
    this.european2Constructor2 = new EuropeanSolitaireModelImpl(3, 3);
    this.european3Constructor1 = new EuropeanSolitaireModelImpl(5);
    this.european3Constructor2 = new EuropeanSolitaireModelImpl(9);
    this.european4Constructor1 = new EuropeanSolitaireModelImpl(3, 3, 3);
    this.european4Constructor2 = new EuropeanSolitaireModelImpl(5, 9, 8);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2Errors() {
    this.european2Constructor1 = new EuropeanSolitaireModelImpl(0, 0);
    this.european2Constructor1 = new EuropeanSolitaireModelImpl(0, 6);
    this.european2Constructor1 = new EuropeanSolitaireModelImpl(10, 26);
    this.european2Constructor1 = new EuropeanSolitaireModelImpl(-5, 6);
    this.european2Constructor1 = new EuropeanSolitaireModelImpl(5, -1);
    this.european2Constructor1 = new EuropeanSolitaireModelImpl(-5, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor3Errors() {
    this.european3Constructor1 = new EuropeanSolitaireModelImpl(-5);
    this.european3Constructor1 = new EuropeanSolitaireModelImpl(1);
    this.european3Constructor1 = new EuropeanSolitaireModelImpl(0);
    this.european3Constructor1 = new EuropeanSolitaireModelImpl(8);
    this.european3Constructor1 = new EuropeanSolitaireModelImpl(2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor4Errors() {
    this.european4Constructor1 = new EuropeanSolitaireModelImpl(-5, 1, 1);
    this.european4Constructor1 = new EuropeanSolitaireModelImpl(1, 0, 4);
    this.european4Constructor1 = new EuropeanSolitaireModelImpl(0, 3, 3);
    this.european4Constructor1 = new EuropeanSolitaireModelImpl(2, 3, 3);
    this.european4Constructor1 = new EuropeanSolitaireModelImpl(3, 0, 0);
    this.european4Constructor1 = new EuropeanSolitaireModelImpl(3, 0, 20);
    this.european4Constructor1 = new EuropeanSolitaireModelImpl(3, 3, -3);
    this.european4Constructor1 = new EuropeanSolitaireModelImpl(0, -1, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptions() {
    this.initData();

    // given a from or to position that is out of bounds by being negative
    this.europeanEmptyConstructor.move(1, -2, 1, 0);
    this.europeanEmptyConstructor.move(-2, -2, -2, -4);
    this.europeanEmptyConstructor.move(2, 1, 2, -1);


    // given a from or to position that is out of bounds by being over or equal to the fullThickness
    this.europeanEmptyConstructor.move(10, 9, 12, 9);
    this.europeanEmptyConstructor.move(3, 6, 3, 8);
    this.europeanEmptyConstructor.move(8, 2, 6, 2);

    // given a from position that is not a String that represents a marble
    this.europeanEmptyConstructor.move(3, 3, 5, 3);
    this.europeanEmptyConstructor.move(3, 3, 3, 1);


    // given a move where there is not a marble in between the from and to positions
    this.europeanEmptyConstructor.move(2, 3, 4, 3);
    this.europeanEmptyConstructor.move(3, 2, 3, 4);

    // given a to position that is not a String that represents an empty slot
    this.europeanEmptyConstructor.move(1, 2, 3, 2);
    this.europeanEmptyConstructor.move(3, 6, 3, 4);

    // given a move that is not 2 positions to the left, right, down, or up
    this.europeanEmptyConstructor.move(1, 3, 5, 5);
    this.europeanEmptyConstructor.move(5, 3, 0, 3);
    this.europeanEmptyConstructor.move(3, 2, 3, 3);
    this.europeanEmptyConstructor.move(1, 2, 2, 3);

  }

  @Test
  public void testGetGameState() { 
    this.initData();
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", this.europeanEmptyConstructor.getGameState());
    assertEquals("    O O O\n" +
            "  _ O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", this.european2Constructor1.getGameState());
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", this.european2Constructor2.getGameState());
    assertEquals("        O O O O O\n" +
            "      O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "      O O O O O O O\n" +
            "        O O O O O", this.european3Constructor1.getGameState());
    assertEquals("                O O O O O O O O O\n" +
            "              O O O O O O O O O O O\n" +
            "            O O O O O O O O O O O O O\n" +
            "          O O O O O O O O O O O O O O O\n" +
            "        O O O O O O O O O O O O O O O O O\n" +
            "      O O O O O O O O O O O O O O O O O O O\n" +
            "    O O O O O O O O O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O _ O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O O O O O O O O O O O O O\n" +
            "    O O O O O O O O O O O O O O O O O O O O O\n" +
            "      O O O O O O O O O O O O O O O O O O O\n" +
            "        O O O O O O O O O O O O O O O O O\n" +
            "          O O O O O O O O O O O O O O O\n" +
            "            O O O O O O O O O O O O O\n" +
            "              O O O O O O O O O O O\n" +
            "                O O O O O O O O O", this.european3Constructor2.getGameState());
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", this.european4Constructor1.getGameState());
    assertEquals("        O O O O O\n" +
            "      O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "  O O O O O O O _ O O O\n" +
            "    O O O O O O O O O\n" +
            "      O O O O O O O\n" +
            "        O O O O O", this.european4Constructor2.getGameState());
  }

  @Test
  public void testMove() {
    this.initData();
    this.europeanEmptyConstructor.move(3,1,3,3);
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", this.europeanEmptyConstructor.getGameState());
    this.european2Constructor1.move(1,3,1,1);
    assertEquals("    O O O\n" +
            "  O _ _ O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", this.european2Constructor1.getGameState());
    this.european3Constructor1.move(6,4,6,6);
    assertEquals("        O O O O O\n" +
            "      O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O _ _ O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "      O O O O O O O\n" +
            "        O O O O O", this.european3Constructor1.getGameState());
    this.european4Constructor1.move(3,1,3,3);
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", this.european4Constructor1.getGameState());
    this.europeanEmptyConstructor.move(5,1,3,1);
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O _ O O O O\n" +
            "O _ O O O O O\n" +
            "  _ O O O O\n" +
            "    O O O", this.europeanEmptyConstructor.getGameState());
    this.europeanEmptyConstructor.move(3,0,3,2);
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "_ _ O O O O O\n" +
            "O _ O O O O O\n" +
            "  _ O O O O\n" +
            "    O O O", this.europeanEmptyConstructor.getGameState());
    this.europeanEmptyConstructor.move(3,3,3,1);
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "_ O _ _ O O O\n" +
            "O _ O O O O O\n" +
            "  _ O O O O\n" +
            "    O O O", this.europeanEmptyConstructor.getGameState());
    this.europeanEmptyConstructor.move(4,3,4,1);
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "_ O _ _ O O O\n" +
            "O O _ _ O O O\n" +
            "  _ O O O O\n" +
            "    O O O", this.europeanEmptyConstructor.getGameState());
    this.europeanEmptyConstructor.move(4,0,4,2);
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "_ O _ _ O O O\n" +
            "_ _ O _ O O O\n" +
            "  _ O O O O\n" +
            "    O O O", this.europeanEmptyConstructor.getGameState());
    this.europeanEmptyConstructor.move(5,3,5,1);
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "_ O _ _ O O O\n" +
            "_ _ O _ O O O\n" +
            "  O _ _ O O\n" +
            "    O O O", this.europeanEmptyConstructor.getGameState());
    this.europeanEmptyConstructor.move(5,5,5,3);
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "_ O _ _ O O O\n" +
            "_ _ O _ O O O\n" +
            "  O _ O _ _\n" +
            "    O O O", this.europeanEmptyConstructor.getGameState());
    this.europeanEmptyConstructor.move(6,3,4,3);
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "_ O _ _ O O O\n" +
            "_ _ O O O O O\n" +
            "  O _ _ _ _\n" +
            "    O _ O", this.europeanEmptyConstructor.getGameState());
    this.europeanEmptyConstructor.move(4,3,4,1);
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "_ O _ _ O O O\n" +
            "_ O _ _ O O O\n" +
            "  O _ _ _ _\n" +
            "    O _ O", this.europeanEmptyConstructor.getGameState());
    this.europeanEmptyConstructor.move(4,5, 4,3);
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "_ O _ _ O O O\n" +
            "_ O _ O _ _ O\n" +
            "  O _ _ _ _\n" +
            "    O _ O", this.europeanEmptyConstructor.getGameState());
    this.europeanEmptyConstructor.move(3,5, 3,3);
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "_ O _ O _ _ O\n" +
            "_ O _ O _ _ O\n" +
            "  O _ _ _ _\n" +
            "    O _ O", this.europeanEmptyConstructor.getGameState());
    this.europeanEmptyConstructor.move(1,2,3,2);
    assertEquals("    O O O\n" +
            "  O _ O O O\n" +
            "O O _ O O O O\n" +
            "_ O O O _ _ O\n" +
            "_ O _ O _ _ O\n" +
            "  O _ _ _ _\n" +
            "    O _ O", this.europeanEmptyConstructor.getGameState());
    this.europeanEmptyConstructor.move(3,2,3,0);
    assertEquals("    O O O\n" +
            "  O _ O O O\n" +
            "O O _ O O O O\n" +
            "O _ _ O _ _ O\n" +
            "_ O _ O _ _ O\n" +
            "  O _ _ _ _\n" +
            "    O _ O", this.europeanEmptyConstructor.getGameState());
    this.europeanEmptyConstructor.move(2,0,4,0);
    assertEquals("    O O O\n" +
            "  O _ O O O\n" +
            "_ O _ O O O O\n" +
            "_ _ _ O _ _ O\n" +
            "O O _ O _ _ O\n" +
            "  O _ _ _ _\n" +
            "    O _ O", this.europeanEmptyConstructor.getGameState());
    this.europeanEmptyConstructor.move(4,0,4,2);
    assertEquals("    O O O\n" +
            "  O _ O O O\n" +
            "_ O _ O O O O\n" +
            "_ _ _ O _ _ O\n" +
            "_ _ O O _ _ O\n" +
            "  O _ _ _ _\n" +
            "    O _ O", this.europeanEmptyConstructor.getGameState());
    this.europeanEmptyConstructor.move(4,2,4,4);
    assertEquals("    O O O\n" +
            "  O _ O O O\n" +
            "_ O _ O O O O\n" +
            "_ _ _ O _ _ O\n" +
            "_ _ _ _ O _ O\n" +
            "  O _ _ _ _\n" +
            "    O _ O", this.europeanEmptyConstructor.getGameState());
    this.europeanEmptyConstructor.move(2,3,4,3);
    assertEquals("    O O O\n" +
            "  O _ O O O\n" +
            "_ O _ _ O O O\n" +
            "_ _ _ _ _ _ O\n" +
            "_ _ _ O O _ O\n" +
            "  O _ _ _ _\n" +
            "    O _ O", this.europeanEmptyConstructor.getGameState());
    this.europeanEmptyConstructor.move(4,4,4,2);
    assertEquals("    O O O\n" +
            "  O _ O O O\n" +
            "_ O _ _ O O O\n" +
            "_ _ _ _ _ _ O\n" +
            "_ _ O _ _ _ O\n" +
            "  O _ _ _ _\n" +
            "    O _ O", this.europeanEmptyConstructor.getGameState());
    this.europeanEmptyConstructor.move(1,1,3,1);
    assertEquals("    O O O\n" +
            "  _ _ O O O\n" +
            "_ _ _ _ O O O\n" +
            "_ O _ _ _ _ O\n" +
            "_ _ O _ _ _ O\n" +
            "  O _ _ _ _\n" +
            "    O _ O", this.europeanEmptyConstructor.getGameState());
    this.europeanEmptyConstructor.move(1,4,1,2);
    assertEquals("    O O O\n" +
            "  _ O _ _ O\n" +
            "_ _ _ _ O O O\n" +
            "_ O _ _ _ _ O\n" +
            "_ _ O _ _ _ O\n" +
            "  O _ _ _ _\n" +
            "    O _ O", this.europeanEmptyConstructor.getGameState());
    this.europeanEmptyConstructor.move(1,5,3,5);
    assertEquals("    O O O\n" +
            "  _ O _ _ _\n" +
            "_ _ _ _ O _ O\n" +
            "_ O _ _ _ O O\n" +
            "_ _ O _ _ _ O\n" +
            "  O _ _ _ _\n" +
            "    O _ O", this.europeanEmptyConstructor.getGameState());
    this.europeanEmptyConstructor.move(3,6,3,4);
    assertEquals("    O O O\n" +
            "  _ O _ _ _\n" +
            "_ _ _ _ O _ O\n" +
            "_ O _ _ O _ _\n" +
            "_ _ O _ _ _ O\n" +
            "  O _ _ _ _\n" +
            "    O _ O", this.europeanEmptyConstructor.getGameState());
    this.europeanEmptyConstructor.move(3,4,1,4);
    assertEquals("    O O O\n" +
            "  _ O _ O _\n" +
            "_ _ _ _ _ _ O\n" +
            "_ O _ _ _ _ _\n" +
            "_ _ O _ _ _ O\n" +
            "  O _ _ _ _\n" +
            "    O _ O", this.europeanEmptyConstructor.getGameState());
    this.europeanEmptyConstructor.move(0,2,2,2);
    assertEquals("    _ O O\n" +
            "  _ _ _ O _\n" +
            "_ _ O _ _ _ O\n" +
            "_ O _ _ _ _ _\n" +
            "_ _ O _ _ _ O\n" +
            "  O _ _ _ _\n" +
            "    O _ O", this.europeanEmptyConstructor.getGameState());
    this.europeanEmptyConstructor.move(0,4,2,4);
    assertEquals("    _ O _\n" +
            "  _ _ _ _ _\n" +
            "_ _ O _ O _ O\n" +
            "_ O _ _ _ _ _\n" +
            "_ _ O _ _ _ O\n" +
            "  O _ _ _ _\n" +
            "    O _ O", this.europeanEmptyConstructor.getGameState());
  }

  @Test
  public void testIsGameOver() {
    this.initData();
    assertEquals(false, this.europeanEmptyConstructor.isGameOver());
    assertEquals(false, this.european2Constructor1.isGameOver());
    assertEquals(false, this.european3Constructor1.isGameOver());
    assertEquals(false, this.european4Constructor1.isGameOver());

    this.testMove();

    assertEquals(true, this.europeanEmptyConstructor.isGameOver());

  }

  @Test
  public void getScore() {
    this.initData();
    assertEquals(36, this.europeanEmptyConstructor.getScore());
    assertEquals(36, this.european2Constructor1.getScore());
    this.europeanEmptyConstructor.move(3, 1, 3, 3);
    assertEquals(35, this.europeanEmptyConstructor.getScore());

    this.testMove();
    assertEquals(10, this.europeanEmptyConstructor.getScore());
  }

}