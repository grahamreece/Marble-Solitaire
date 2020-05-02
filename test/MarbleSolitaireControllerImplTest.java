import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModelImpl;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModelImpl;

import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Represents tests for the marble solitaire controller implementation and tests for the public
 * methods made, and tests for exceptions for those methods and the constructor of the marble
 * solitaire controller implementation.
 */

public class MarbleSolitaireControllerImplTest {
  Readable readable;

  Appendable appendable = new StringBuilder();

  MarbleSolitaireModel model1 = new MarbleSolitaireModelImpl();
  MarbleSolitaireModel model2 = new MarbleSolitaireModelImpl(2, 4);
  MarbleSolitaireModel model3 = new MarbleSolitaireModelImpl(5);
  MarbleSolitaireModel model4 = new MarbleSolitaireModelImpl(5, 9, 8);

  EuropeanSolitaireModelImpl european1 = new EuropeanSolitaireModelImpl();
  EuropeanSolitaireModelImpl european2 = new EuropeanSolitaireModelImpl(3, 0);
  EuropeanSolitaireModelImpl european3 = new EuropeanSolitaireModelImpl(5);
  EuropeanSolitaireModelImpl european4 = new EuropeanSolitaireModelImpl(5, 5, 5);

  TriangleSolitaireModelImpl triangle1 = new TriangleSolitaireModelImpl();
  TriangleSolitaireModelImpl triangle2 = new TriangleSolitaireModelImpl(2, 0);
  TriangleSolitaireModelImpl triangle3 = new TriangleSolitaireModelImpl(4);
  TriangleSolitaireModelImpl triangle4 = new TriangleSolitaireModelImpl(5, 2, 2);

  MarbleSolitaireController gameController;

  /**
   * This is a private class created specifically for the purpose of testing exceptions. In this the
   * appendable will always cause an input/output exception to be thrown on whether the append is
   * given a character sequence, a character, or a character sequence with a starting and ending
   * integer.
   */
  private class AppendableTester implements Appendable {

    @Override
    public Appendable append(CharSequence cs) throws IOException {
      throw new IOException("appendable error");
    }

    @Override
    public Appendable append(char character) throws IOException {
      throw new IOException("appendable error");
    }

    @Override
    public Appendable append(CharSequence cs, int start, int end) throws IOException {
      throw new IOException("appendable error");
    }

  }

  @Test(expected = IllegalArgumentException.class)
  public void modelIsNull() {
    this.readable = new StringReader("2 4 4 4 q");
    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);
    gameController.playGame(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testReadableNullError() {
    this.gameController = new MarbleSolitaireControllerImpl(null, this.appendable);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAppendableNullError() {
    this.readable = new StringReader("2 4 4 4 q");
    this.gameController = new MarbleSolitaireControllerImpl(this.readable, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testReadableAndAppendableNullError() {
    this.gameController = new MarbleSolitaireControllerImpl(null, null);
  }

  @Test(expected = IllegalStateException.class)
  public void testAppendError() {
    Appendable appendable = new AppendableTester();
    this.readable = new StringReader("4 2 4 4 q");
    this.gameController = new MarbleSolitaireControllerImpl(this.readable, appendable);
    this.gameController.playGame(this.model1);
  }

  @Test(expected = IllegalStateException.class)
  public void testEmptyReadable() {
    this.readable = new StringReader("");
    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);
    this.gameController.playGame(this.model1);
  }

  @Test(expected = IllegalStateException.class)
  public void testReadableBecomesEmptyAfterMove() {
    this.readable = new StringReader("4 2 4 4");
    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);
    this.gameController.playGame(this.model1);
  }

  @Test(expected = IllegalStateException.class)
  public void testReadableBecomesEmptyWithoutMove() {
    this.readable = new StringReader("4 ay 2 by 4 py yy");
    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);
    this.gameController.playGame(this.model1);
  }

  /**
   * Most tests will be set up in this way, where the readable and expected appendable objects for
   * the game controller will be specified first, then the specified readable and the empty string
   * builder from examples will be inputted to the controller constructor, the controller will play
   * the game, and the output of the game will be checked if it is correct.
   */

  @Test
  public void testQuitGameLowercaseQ() {
    this.readable = new StringReader("q");
    Appendable output = new StringBuilder("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.model1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testQuitGameCapitalQ() {
    this.readable = new StringReader("Q");
    Appendable output = new StringBuilder("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.model1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testQuitGameAfterBadValues() {
    this.readable = new StringReader("a s 2 4 4 q");
    Appendable output = new StringBuilder("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Please re-enter the value!\n"
            + "Please re-enter the value!\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.model1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testQuitHappensForFromRowValue() {
    this.readable = new StringReader("Q 2 4 4");
    Appendable output = new StringBuilder("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.model1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testQuitHappensForFromColValue() {
    this.readable = new StringReader("4 q 4 4");
    Appendable output = new StringBuilder("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.model1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testQuitHappensForToRowValue() {
    this.readable = new StringReader("4 2 Q 4");
    Appendable output = new StringBuilder("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.model1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testQuitHappensForToColValue() {
    this.readable = new StringReader("4 2 4 q");
    Appendable output = new StringBuilder("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.model1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testQuitHappensBeforeMove() {
    this.readable = new StringReader("Q 2 4 4 4");
    Appendable output = new StringBuilder("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.model1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testQuitHappensBeforeErrorMove() {
    this.readable = new StringReader("Q 8 8 4 4");
    Appendable output = new StringBuilder("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.model1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testQuitHappensBeforeErrorValue() {
    this.readable = new StringReader("Q f a s p");
    Appendable output = new StringBuilder("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.model1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testSpaceAndNewLinesDoNotAffectInput() {
    this.readable = new StringReader("           \n \n \n \n \n q");
    Appendable output = new StringBuilder("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.model1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testGameQuitsOnlyOnce() {
    this.readable = new StringReader("q Q");
    Appendable output = new StringBuilder("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.model1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testGameQuitsOnlyOnce2() {
    this.readable = new StringReader("q Q q Q");
    Appendable output = new StringBuilder("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.model1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  // test that it quits after a move to the left
  @Test
  public void testMovesThenQuits1() {

    this.readable = new StringReader("4 6 4 4 q");

    Appendable output = new StringBuilder("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O _ _ O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 31\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O _ _ O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 31");

    this.gameController = new MarbleSolitaireControllerImpl(readable, appendable);

    this.gameController.playGame(model1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  // test that it quits after a move to the right
  @Test
  public void testMovesThenQuits2() {

    this.readable = new StringReader("4 2 4 4 q");

    Appendable output = new StringBuilder("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 31\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 31");

    this.gameController = new MarbleSolitaireControllerImpl(readable, appendable);

    this.gameController.playGame(model1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  // test that it quits after a move upward
  @Test
  public void testMovesThenQuits3() {

    this.readable = new StringReader("6 4 4 4 q");

    Appendable output = new StringBuilder("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "    O _ O\n"
            + "    O O O\n"
            + "Score: 31\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "    O _ O\n"
            + "    O O O\n"
            + "Score: 31");

    this.gameController = new MarbleSolitaireControllerImpl(readable, appendable);

    this.gameController.playGame(model1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  // test that it quits after a move downward
  @Test
  public void testMovesThenQuits4() {

    this.readable = new StringReader("2 4 4 4 q");

    Appendable output = new StringBuilder("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "    O O O\n"
            + "    O _ O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 31\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O _ O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 31");

    this.gameController = new MarbleSolitaireControllerImpl(readable, appendable);

    this.gameController.playGame(model1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  // need to test proper processing of the controller when working with other models
  @Test
  public void testControllerForOtherModelConstructor1() {
    this.readable = new StringReader("3 3 3 5 q");
    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.model2);

    Appendable output = new StringBuilder("    O O O\n"
            + "    O O O\n"
            + "O O O O _ O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O _ _ O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 31\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O _ _ O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 31");

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testControllerForOtherModelConstructor2() {
    this.readable = new StringReader("7 5 7 7 a q");
    Appendable output = new StringBuilder("        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O _ O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "Score: 104\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O _ _ O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "Score: 103\n"
            + "Please re-enter the value!\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O _ _ O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "Score: 103");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.model3);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testControllerForOtherModelConstructor3() {
    this.readable = new StringReader("10 7 10 9 q");
    Appendable output = new StringBuilder("        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "        O O O O _\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "Score: 104\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "        O O _ _ O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "Score: 103\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "        O O _ _ O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "Score: 103");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.model4);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testValueNeedsToBeReenteredForVarietyOfCharacters() {
    this.readable = new StringReader("w e r, , i o p ! @ # $ % ^ &  * ) < > ; q");
    Appendable output = new StringBuilder("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Please re-enter the value!\n"
            + "Please re-enter the value!\n"
            + "Please re-enter the value!\n"
            + "Please re-enter the value!\n"
            + "Please re-enter the value!\n"
            + "Please re-enter the value!\n"
            + "Please re-enter the value!\n"
            + "Please re-enter the value!\n"
            + "Please re-enter the value!\n"
            + "Please re-enter the value!\n"
            + "Please re-enter the value!\n"
            + "Please re-enter the value!\n"
            + "Please re-enter the value!\n"
            + "Please re-enter the value!\n"
            + "Please re-enter the value!\n"
            + "Please re-enter the value!\n"
            + "Please re-enter the value!\n"
            + "Please re-enter the value!\n"
            + "Please re-enter the value!\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.model1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testControllerStillMovesAfterHavingToReenterValues() {
    this.readable = new StringReader("4 @ 2 @ 4 lolxD 4 xD 4 q");
    Appendable output = new StringBuilder("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Please re-enter the value!\n"
            + "Please re-enter the value!\n"
            + "Please re-enter the value!\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 31\n"
            + "Please re-enter the value!\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 31");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.model1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  // test that the inputted values catch a move that is out of bounds
  @Test
  public void testFromPositionOutOfBoundsInFourCornersSignalsInOutput() {
    this.readable = new StringReader("1 2 3 2 q");
    Appendable output = new StringBuilder("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Invalid move. Play again. The from position is out of bounds\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.model1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testFromPositionOutOfBoundsNegativeSignalsInOutput() {
    this.readable = new StringReader("-1 -1 -1 1 q");
    Appendable output = new StringBuilder("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Invalid move. Play again. The from position is out of bounds\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.model1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testFromPositionOutOfBoundsOverFullThickness() {
    this.readable = new StringReader("10 20 6 6 q");
    Appendable output = new StringBuilder("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Invalid move. Play again. The from position is out of bounds\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.model1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testToPositionOutOfBoundsInFourCornersSignalsInOutput() {
    this.readable = new StringReader("3 2 1 2 q");
    Appendable output = new StringBuilder("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Invalid move. Play again. The to position is out of bounds\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.model1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testToPositionOutOfBoundsNegativeSignalsInOutput() {
    this.readable = new StringReader("4 1 4 -1 q");
    Appendable output = new StringBuilder("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Invalid move. Play again. The to position is out of bounds\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.model1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testOnlyNoticesInvalidInputAndStillMoves() {
    this.readable = new StringReader("4 2 a 4 4 q");
    Appendable output = new StringBuilder("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Please re-enter the value!\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 31\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 31");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.model1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testToPositionOutOfBoundsOverFullThickness() {
    this.readable = new StringReader("4 4 10 20 q");
    Appendable output = new StringBuilder("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Invalid move. Play again. The to position is out of bounds\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.model1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  // test that the inputted values catch a move where the to position is not an empty slot
  @Test
  public void testToPositionNotAnEmptySlotSignalsInOuput() {
    this.readable = new StringReader("3 1 3 3 Q");
    Appendable output = new StringBuilder("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Invalid move. Play again. The to position is not an empty slot\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.model1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  // test that inputted values catch a move where the from position is not a marble

  // in the third move, the to position wll be an empty slot, but so will the from position
  @Test
  public void testFromPositionNotAMarbleSignalsInOutput() {
    this.readable = new StringReader("4 2 4 4  4 5 4 3  4 2 4 4 q");
    Appendable output = new StringBuilder("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 31\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ O _ _ O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 30\n"
            + "Invalid move. Play again. The from position is not a marble\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ O _ _ O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 30");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.model1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  // test that the inputted values catch the case where the position in between for the move is
  // not a marble as well

  // Here, the second move will result in the from position being a marble and the to position being
  // an empty slot, but the position in between will also be a slot and not a marble
  @Test
  public void testJumpedPositionNotAMarbleSignalsInOutput() {
    this.readable = new StringReader("4 2 4 4 4 1 4 3 q");
    Appendable output = new StringBuilder("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 31\n"
            + "Invalid move. Play again. The jumped position is not a marble\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 31");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.model1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  // test that the inputted values catch a move that is improper or diagonal
  @Test
  public void testDiagonalMoveSignalsInOutput() {
    this.readable = new StringReader("3 3 4 4 q");
    Appendable output = new StringBuilder("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Invalid move. Play again. The move cannot be diagonal\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.model1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  // test that the inputted values catch a move that is improper or diagonal
  @Test
  public void testDiagonalMoveSignalsInOutput2() {
    this.readable = new StringReader("6 5 4 4 q");
    Appendable output = new StringBuilder("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Invalid move. Play again. The move cannot be diagonal\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.model1);

    assertEquals(this.appendable.toString(), output.toString());
  }


  // test that the move entered must be exactly two game pieces apart
  @Test
  public void moveNotTwoPositionsApartGameTest() {
    this.readable = new StringReader("7 4 4 4 q");
    Appendable output = new StringBuilder("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "Invalid move. Play again. The move has to be two pieces apart\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.model1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testGameOver() {
    this.readable = new StringReader("4 2 4 4  6 3 4 3  5 1 5 3  5 4 5 2  6 5 6 3  7 3 5 3  " +
            "7 5 7 3  5 6 5 4  5 3 5 5  3 1 5 1  5 1 5 3  4 3 6 3  4 5 6 5  7 3 5 3  4 7 4 5 " +
            "4 5 4 3  3 3 3 1  5 3 3 3  3 4 3 2  3 1 3 3  3 6 3 4  3 3 3 5  2 5 4 5  2 3 2 5 " +
            "1 5 3 5  4 5 2 5  1 3 1 5  1 5 3 5");

    Appendable output = new StringBuilder("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 31\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            + "O O _ O O O O\n"
            + "    _ O O\n"
            + "    O O O\n"
            + "Score: 30\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            + "_ _ O O O O O\n"
            + "    _ O O\n"
            + "    O O O\n"
            + "Score: 29\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            + "_ O _ _ O O O\n"
            + "    _ O O\n"
            + "    O O O\n"
            + "Score: 28\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            + "_ O _ _ O O O\n"
            + "    O _ _\n"
            + "    O O O\n"
            + "Score: 27\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            + "_ O O _ O O O\n"
            + "    _ _ _\n"
            + "    _ O O\n"
            + "Score: 26\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            + "_ O O _ O O O\n"
            + "    _ _ _\n"
            + "    O _ _\n"
            + "Score: 25\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            + "_ O O O _ _ O\n"
            + "    _ _ _\n"
            + "    O _ _\n"
            + "Score: 24\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            + "_ O _ _ O _ O\n"
            + "    _ _ _\n"
            + "    O _ _\n"
            + "Score: 23\n"
            + "    O O O\n"
            + "    O O O\n"
            + "_ O O O O O O\n"
            + "_ _ O O O O O\n"
            + "O O _ _ O _ O\n"
            + "    _ _ _\n"
            + "    O _ _\n"
            + "Score: 22\n"
            + "    O O O\n"
            + "    O O O\n"
            + "_ O O O O O O\n"
            + "_ _ O O O O O\n"
            + "_ _ O _ O _ O\n"
            + "    _ _ _\n"
            + "    O _ _\n"
            + "Score: 21\n"
            + "    O O O\n"
            + "    O O O\n"
            + "_ O O O O O O\n"
            + "_ _ _ O O O O\n"
            + "_ _ _ _ O _ O\n"
            + "    O _ _\n"
            + "    O _ _\n"
            + "Score: 20\n"
            + "    O O O\n"
            + "    O O O\n"
            + "_ O O O O O O\n"
            + "_ _ _ O _ O O\n"
            + "_ _ _ _ _ _ O\n"
            + "    O _ O\n"
            + "    O _ _\n"
            + "Score: 19\n"
            + "    O O O\n"
            + "    O O O\n"
            + "_ O O O O O O\n"
            + "_ _ _ O _ O O\n"
            + "_ _ O _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 18\n"
            + "    O O O\n"
            + "    O O O\n"
            + "_ O O O O O O\n"
            + "_ _ _ O O _ _\n"
            + "_ _ O _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 17\n"
            + "    O O O\n"
            + "    O O O\n"
            + "_ O O O O O O\n"
            + "_ _ O _ _ _ _\n"
            + "_ _ O _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 16\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O _ _ O O O O\n"
            + "_ _ O _ _ _ _\n"
            + "_ _ O _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 15\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O _ O O O O O\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 14\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O _ _ O O O\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 13\n"
            + "    O O O\n"
            + "    O O O\n"
            + "_ _ O _ O O O\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 12\n"
            + "    O O O\n"
            + "    O O O\n"
            + "_ _ O O _ _ O\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 11\n"
            + "    O O O\n"
            + "    O O O\n"
            + "_ _ _ _ O _ O\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 10\n"
            + "    O O O\n"
            + "    O O _\n"
            + "_ _ _ _ _ _ O\n"
            + "_ _ _ _ O _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 9\n"
            + "    O O O\n"
            + "    _ _ O\n"
            + "_ _ _ _ _ _ O\n"
            + "_ _ _ _ O _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 8\n"
            + "    O O _\n"
            + "    _ _ _\n"
            + "_ _ _ _ O _ O\n"
            + "_ _ _ _ O _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 7\n"
            + "    O O _\n"
            + "    _ _ O\n"
            + "_ _ _ _ _ _ O\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 6\n"
            + "    _ _ O\n"
            + "    _ _ O\n"
            + "_ _ _ _ _ _ O\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 5\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "_ _ _ _ O _ O\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 4\n"
            + "Game over!\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "_ _ _ _ O _ O\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 4\n");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.model1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testGameOverWorksWithExtraMove() {
    this.readable = new StringReader("4 2 4 4  6 3 4 3  5 1 5 3  5 4 5 2  6 5 6 3  7 3 5 3  " +
            "7 5 7 3  5 6 5 4  5 3 5 5  3 1 5 1  5 1 5 3  4 3 6 3  4 5 6 5  7 3 5 3  4 7 4 5 " +
            "4 5 4 3  3 3 3 1  5 3 3 3  3 4 3 2  3 1 3 3  3 6 3 4  3 3 3 5  2 5 4 5  2 3 2 5 " +
            "1 5 3 5  4 5 2 5  1 3 1 5  1 5 3 5 3 5 3 7");

    Appendable output = new StringBuilder("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 31\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            + "O O _ O O O O\n"
            + "    _ O O\n"
            + "    O O O\n"
            + "Score: 30\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            + "_ _ O O O O O\n"
            + "    _ O O\n"
            + "    O O O\n"
            + "Score: 29\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            + "_ O _ _ O O O\n"
            + "    _ O O\n"
            + "    O O O\n"
            + "Score: 28\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            + "_ O _ _ O O O\n"
            + "    O _ _\n"
            + "    O O O\n"
            + "Score: 27\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            + "_ O O _ O O O\n"
            + "    _ _ _\n"
            + "    _ O O\n"
            + "Score: 26\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            + "_ O O _ O O O\n"
            + "    _ _ _\n"
            + "    O _ _\n"
            + "Score: 25\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            + "_ O O O _ _ O\n"
            + "    _ _ _\n"
            + "    O _ _\n"
            + "Score: 24\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            + "_ O _ _ O _ O\n"
            + "    _ _ _\n"
            + "    O _ _\n"
            + "Score: 23\n"
            + "    O O O\n"
            + "    O O O\n"
            + "_ O O O O O O\n"
            + "_ _ O O O O O\n"
            + "O O _ _ O _ O\n"
            + "    _ _ _\n"
            + "    O _ _\n"
            + "Score: 22\n"
            + "    O O O\n"
            + "    O O O\n"
            + "_ O O O O O O\n"
            + "_ _ O O O O O\n"
            + "_ _ O _ O _ O\n"
            + "    _ _ _\n"
            + "    O _ _\n"
            + "Score: 21\n"
            + "    O O O\n"
            + "    O O O\n"
            + "_ O O O O O O\n"
            + "_ _ _ O O O O\n"
            + "_ _ _ _ O _ O\n"
            + "    O _ _\n"
            + "    O _ _\n"
            + "Score: 20\n"
            + "    O O O\n"
            + "    O O O\n"
            + "_ O O O O O O\n"
            + "_ _ _ O _ O O\n"
            + "_ _ _ _ _ _ O\n"
            + "    O _ O\n"
            + "    O _ _\n"
            + "Score: 19\n"
            + "    O O O\n"
            + "    O O O\n"
            + "_ O O O O O O\n"
            + "_ _ _ O _ O O\n"
            + "_ _ O _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 18\n"
            + "    O O O\n"
            + "    O O O\n"
            + "_ O O O O O O\n"
            + "_ _ _ O O _ _\n"
            + "_ _ O _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 17\n"
            + "    O O O\n"
            + "    O O O\n"
            + "_ O O O O O O\n"
            + "_ _ O _ _ _ _\n"
            + "_ _ O _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 16\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O _ _ O O O O\n"
            + "_ _ O _ _ _ _\n"
            + "_ _ O _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 15\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O _ O O O O O\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 14\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O _ _ O O O\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 13\n"
            + "    O O O\n"
            + "    O O O\n"
            + "_ _ O _ O O O\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 12\n"
            + "    O O O\n"
            + "    O O O\n"
            + "_ _ O O _ _ O\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 11\n"
            + "    O O O\n"
            + "    O O O\n"
            + "_ _ _ _ O _ O\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 10\n"
            + "    O O O\n"
            + "    O O _\n"
            + "_ _ _ _ _ _ O\n"
            + "_ _ _ _ O _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 9\n"
            + "    O O O\n"
            + "    _ _ O\n"
            + "_ _ _ _ _ _ O\n"
            + "_ _ _ _ O _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 8\n"
            + "    O O _\n"
            + "    _ _ _\n"
            + "_ _ _ _ O _ O\n"
            + "_ _ _ _ O _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 7\n"
            + "    O O _\n"
            + "    _ _ O\n"
            + "_ _ _ _ _ _ O\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 6\n"
            + "    _ _ O\n"
            + "    _ _ O\n"
            + "_ _ _ _ _ _ O\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 5\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "_ _ _ _ O _ O\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 4\n"
            + "Game over!\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "_ _ _ _ O _ O\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 4\n");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.model1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testGameOverWorksWithQuitAtTheEnd() {
    this.readable = new StringReader("4 2 4 4  6 3 4 3  5 1 5 3  5 4 5 2  6 5 6 3  7 3 5 3  " +
            "7 5 7 3  5 6 5 4  5 3 5 5  3 1 5 1  5 1 5 3  4 3 6 3  4 5 6 5  7 3 5 3  4 7 4 5 " +
            "4 5 4 3  3 3 3 1  5 3 3 3  3 4 3 2  3 1 3 3  3 6 3 4  3 3 3 5  2 5 4 5  2 3 2 5 " +
            "1 5 3 5  4 5 2 5  1 3 1 5  1 5 3 5 q");

    Appendable output = new StringBuilder("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 31\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            + "O O _ O O O O\n"
            + "    _ O O\n"
            + "    O O O\n"
            + "Score: 30\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            + "_ _ O O O O O\n"
            + "    _ O O\n"
            + "    O O O\n"
            + "Score: 29\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            + "_ O _ _ O O O\n"
            + "    _ O O\n"
            + "    O O O\n"
            + "Score: 28\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            + "_ O _ _ O O O\n"
            + "    O _ _\n"
            + "    O O O\n"
            + "Score: 27\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            + "_ O O _ O O O\n"
            + "    _ _ _\n"
            + "    _ O O\n"
            + "Score: 26\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            + "_ O O _ O O O\n"
            + "    _ _ _\n"
            + "    O _ _\n"
            + "Score: 25\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            + "_ O O O _ _ O\n"
            + "    _ _ _\n"
            + "    O _ _\n"
            + "Score: 24\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            + "_ O _ _ O _ O\n"
            + "    _ _ _\n"
            + "    O _ _\n"
            + "Score: 23\n"
            + "    O O O\n"
            + "    O O O\n"
            + "_ O O O O O O\n"
            + "_ _ O O O O O\n"
            + "O O _ _ O _ O\n"
            + "    _ _ _\n"
            + "    O _ _\n"
            + "Score: 22\n"
            + "    O O O\n"
            + "    O O O\n"
            + "_ O O O O O O\n"
            + "_ _ O O O O O\n"
            + "_ _ O _ O _ O\n"
            + "    _ _ _\n"
            + "    O _ _\n"
            + "Score: 21\n"
            + "    O O O\n"
            + "    O O O\n"
            + "_ O O O O O O\n"
            + "_ _ _ O O O O\n"
            + "_ _ _ _ O _ O\n"
            + "    O _ _\n"
            + "    O _ _\n"
            + "Score: 20\n"
            + "    O O O\n"
            + "    O O O\n"
            + "_ O O O O O O\n"
            + "_ _ _ O _ O O\n"
            + "_ _ _ _ _ _ O\n"
            + "    O _ O\n"
            + "    O _ _\n"
            + "Score: 19\n"
            + "    O O O\n"
            + "    O O O\n"
            + "_ O O O O O O\n"
            + "_ _ _ O _ O O\n"
            + "_ _ O _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 18\n"
            + "    O O O\n"
            + "    O O O\n"
            + "_ O O O O O O\n"
            + "_ _ _ O O _ _\n"
            + "_ _ O _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 17\n"
            + "    O O O\n"
            + "    O O O\n"
            + "_ O O O O O O\n"
            + "_ _ O _ _ _ _\n"
            + "_ _ O _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 16\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O _ _ O O O O\n"
            + "_ _ O _ _ _ _\n"
            + "_ _ O _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 15\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O _ O O O O O\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 14\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O _ _ O O O\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 13\n"
            + "    O O O\n"
            + "    O O O\n"
            + "_ _ O _ O O O\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 12\n"
            + "    O O O\n"
            + "    O O O\n"
            + "_ _ O O _ _ O\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 11\n"
            + "    O O O\n"
            + "    O O O\n"
            + "_ _ _ _ O _ O\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 10\n"
            + "    O O O\n"
            + "    O O _\n"
            + "_ _ _ _ _ _ O\n"
            + "_ _ _ _ O _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 9\n"
            + "    O O O\n"
            + "    _ _ O\n"
            + "_ _ _ _ _ _ O\n"
            + "_ _ _ _ O _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 8\n"
            + "    O O _\n"
            + "    _ _ _\n"
            + "_ _ _ _ O _ O\n"
            + "_ _ _ _ O _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 7\n"
            + "    O O _\n"
            + "    _ _ O\n"
            + "_ _ _ _ _ _ O\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 6\n"
            + "    _ _ O\n"
            + "    _ _ O\n"
            + "_ _ _ _ _ _ O\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 5\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "_ _ _ _ O _ O\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 4\n"
            + "Game over!\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "_ _ _ _ O _ O\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 4\n");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.model1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testGameOverWorksWithInvalidMoveAndInputsAtTheEnd() {
    this.readable = new StringReader("4 2 4 4  6 3 4 3  5 1 5 3  5 4 5 2  6 5 6 3  7 3 5 3  " +
            "7 5 7 3  5 6 5 4  5 3 5 5  3 1 5 1  5 1 5 3  4 3 6 3  4 5 6 5  7 3 5 3  4 7 4 5 " +
            "4 5 4 3  3 3 3 1  5 3 3 3  3 4 3 2  3 1 3 3  3 6 3 4  3 3 3 5  2 5 4 5  2 3 2 5 " +
            "1 5 3 5  4 5 2 5  1 3 1 5  1 5 3 5 -1 -1 3 4 a xd Xd xD lol");

    Appendable output = new StringBuilder("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 31\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            + "O O _ O O O O\n"
            + "    _ O O\n"
            + "    O O O\n"
            + "Score: 30\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            + "_ _ O O O O O\n"
            + "    _ O O\n"
            + "    O O O\n"
            + "Score: 29\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            + "_ O _ _ O O O\n"
            + "    _ O O\n"
            + "    O O O\n"
            + "Score: 28\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            + "_ O _ _ O O O\n"
            + "    O _ _\n"
            + "    O O O\n"
            + "Score: 27\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            + "_ O O _ O O O\n"
            + "    _ _ _\n"
            + "    _ O O\n"
            + "Score: 26\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            + "_ O O _ O O O\n"
            + "    _ _ _\n"
            + "    O _ _\n"
            + "Score: 25\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            + "_ O O O _ _ O\n"
            + "    _ _ _\n"
            + "    O _ _\n"
            + "Score: 24\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            + "_ O _ _ O _ O\n"
            + "    _ _ _\n"
            + "    O _ _\n"
            + "Score: 23\n"
            + "    O O O\n"
            + "    O O O\n"
            + "_ O O O O O O\n"
            + "_ _ O O O O O\n"
            + "O O _ _ O _ O\n"
            + "    _ _ _\n"
            + "    O _ _\n"
            + "Score: 22\n"
            + "    O O O\n"
            + "    O O O\n"
            + "_ O O O O O O\n"
            + "_ _ O O O O O\n"
            + "_ _ O _ O _ O\n"
            + "    _ _ _\n"
            + "    O _ _\n"
            + "Score: 21\n"
            + "    O O O\n"
            + "    O O O\n"
            + "_ O O O O O O\n"
            + "_ _ _ O O O O\n"
            + "_ _ _ _ O _ O\n"
            + "    O _ _\n"
            + "    O _ _\n"
            + "Score: 20\n"
            + "    O O O\n"
            + "    O O O\n"
            + "_ O O O O O O\n"
            + "_ _ _ O _ O O\n"
            + "_ _ _ _ _ _ O\n"
            + "    O _ O\n"
            + "    O _ _\n"
            + "Score: 19\n"
            + "    O O O\n"
            + "    O O O\n"
            + "_ O O O O O O\n"
            + "_ _ _ O _ O O\n"
            + "_ _ O _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 18\n"
            + "    O O O\n"
            + "    O O O\n"
            + "_ O O O O O O\n"
            + "_ _ _ O O _ _\n"
            + "_ _ O _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 17\n"
            + "    O O O\n"
            + "    O O O\n"
            + "_ O O O O O O\n"
            + "_ _ O _ _ _ _\n"
            + "_ _ O _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 16\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O _ _ O O O O\n"
            + "_ _ O _ _ _ _\n"
            + "_ _ O _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 15\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O _ O O O O O\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 14\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O _ _ O O O\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 13\n"
            + "    O O O\n"
            + "    O O O\n"
            + "_ _ O _ O O O\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 12\n"
            + "    O O O\n"
            + "    O O O\n"
            + "_ _ O O _ _ O\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 11\n"
            + "    O O O\n"
            + "    O O O\n"
            + "_ _ _ _ O _ O\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 10\n"
            + "    O O O\n"
            + "    O O _\n"
            + "_ _ _ _ _ _ O\n"
            + "_ _ _ _ O _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 9\n"
            + "    O O O\n"
            + "    _ _ O\n"
            + "_ _ _ _ _ _ O\n"
            + "_ _ _ _ O _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 8\n"
            + "    O O _\n"
            + "    _ _ _\n"
            + "_ _ _ _ O _ O\n"
            + "_ _ _ _ O _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 7\n"
            + "    O O _\n"
            + "    _ _ O\n"
            + "_ _ _ _ _ _ O\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 6\n"
            + "    _ _ O\n"
            + "    _ _ O\n"
            + "_ _ _ _ _ _ O\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 5\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "_ _ _ _ O _ O\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 4\n"
            + "Game over!\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "_ _ _ _ O _ O\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _\n"
            + "Score: 4\n");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.model1);

    assertEquals(this.appendable.toString(), output.toString());
  }


  @Test
  public void testQuitGameLowercaseQEuropean() {
    this.readable = new StringReader("q");
    Appendable output = new StringBuilder("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.european1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testQuitGameCapitalQEuropean() {
    this.readable = new StringReader("Q");
    Appendable output = new StringBuilder("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.european1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testQuitGameAfterMultipleValuesEuropean() {
    this.readable = new StringReader(" 1 2 1 Q");
    Appendable output = new StringBuilder("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.european1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testQuitHappensForFromRowValueEuropean() {
    this.readable = new StringReader("Q 2 4 4");
    Appendable output = new StringBuilder("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.european1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testQuitHappensForFromColValueEuropean() {
    this.readable = new StringReader("4 Q 4 4");
    Appendable output = new StringBuilder("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.european1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testQuitHappensForToRowValueEuropean() {
    this.readable = new StringReader("4 2 q 4");
    Appendable output = new StringBuilder("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.european1);

    assertEquals(this.appendable.toString(), output.toString());
  }


  @Test
  public void testQuitHappensForToColValueEuropean() {
    this.readable = new StringReader("4 2 4 q");
    Appendable output = new StringBuilder("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.european1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testQuitHappensBeforeMoveEuropean() {
    this.readable = new StringReader("Q 4 2 4 4");
    Appendable output = new StringBuilder("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.european1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testQuitHappensBeforeInvalidMoveEuropean() {
    this.readable = new StringReader("Q 8 8 4 4");
    Appendable output = new StringBuilder("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.european1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testQuitHappensBeforeErrorValuesEuropean() {
    this.readable = new StringReader("Q aasd lolxD enteragainidiot");
    Appendable output = new StringBuilder("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.european1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testSpacesAndLinesDontAffectInputEuropean() {
    this.readable = new StringReader("     \n\n\n\n\n    q");
    Appendable output = new StringBuilder("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.european1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testGameOnlyQuitsOnceEuropean() {
    this.readable = new StringReader("q Q q");
    Appendable output = new StringBuilder("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.european1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  // test that it quits after a move to the left
  @Test
  public void testMovesThenQuits1European() {
    this.readable = new StringReader("4 6 4 4 Q");
    Appendable output = new StringBuilder("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O _ _ O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 35\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O _ _ O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 35");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.european1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  // test that it quits after a move to the right
  @Test
  public void testMovesThenQuits2European() {
    this.readable = new StringReader("4 2 4 4 Q");
    Appendable output = new StringBuilder("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 35\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 35");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.european1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  // test that it quits after a move downward
  @Test
  public void testMovesThenQuits3European() {
    this.readable = new StringReader("2 4 4 4 Q");
    Appendable output = new StringBuilder("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36\n"
            + "    O O O\n"
            + "  O O _ O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 35\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "  O O _ O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 35");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.european1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  // test that it quits after a move upward
  @Test
  public void testMovesThenQuits4European() {
    this.readable = new StringReader("6 4 4 4 Q");
    Appendable output = new StringBuilder("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "  O O _ O O\n"
            + "    O O O\n"
            + "Score: 35\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "  O O _ O O\n"
            + "    O O O\n"
            + "Score: 35");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.european1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testControllerForOtherEuropeanConstructor1() {
    this.readable = new StringReader("4 3 4 1 Q");
    Appendable output = new StringBuilder("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "_ O O O O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 35\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 35");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.european2);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testControllerForOtherEuropeanConstructor2() {
    this.readable = new StringReader("7 5 7 7 Q");
    Appendable output = new StringBuilder("        O O O O O\n" +
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
            "        O O O O O\n"
            + "Score: 128\n"
            + "        O O O O O\n" +
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
            "        O O O O O\n"
            + "Score: 127\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "        O O O O O\n" +
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
            "        O O O O O\n"
            + "Score: 127");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.european3);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testControllerForOtherEuropeanConstructor3() {
    this.readable = new StringReader("4 6 6 6 Q");
    Appendable output = new StringBuilder("        O O O O O\n" +
            "      O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O _ O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "      O O O O O O O\n" +
            "        O O O O O\n"
            + "Score: 128\n"
            + "        O O O O O\n" +
            "      O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "  O O O O _ O O O O O O\n" +
            "O O O O O _ O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "      O O O O O O O\n" +
            "        O O O O O\n"
            + "Score: 127\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "        O O O O O\n" +
            "      O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "  O O O O _ O O O O O O\n" +
            "O O O O O _ O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "      O O O O O O O\n" +
            "        O O O O O\n"
            + "Score: 127");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.european4);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testControllerStillMovesAfterHavingToReenterValuesEuropean() {
    this.readable = new StringReader("6 @ 4 @ 4 lolxD 4 xD 4 q");
    Appendable output = new StringBuilder("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36\n"
            + "Please re-enter the value!\n"
            + "Please re-enter the value!\n"
            + "Please re-enter the value!\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "  O O _ O O\n"
            + "    O O O\n"
            + "Score: 35\n"
            + "Please re-enter the value!\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "  O O _ O O\n"
            + "    O O O\n"
            + "Score: 35");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.european1);

    assertEquals(this.appendable.toString(), output.toString());

  }

  @Test
  public void testFromPositionOutOfBoundsCaughtEuropean() {
    this.readable = new StringReader("-1 -1 0 -1 q");
    Appendable output = new StringBuilder("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36\n"
            + "Invalid move. Play again. The from position is out of bounds\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.european1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testToPositionOutOfBoundsCaughtEuropean() {
    this.readable = new StringReader("2 2 0 -1 q");
    Appendable output = new StringBuilder("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36\n"
            + "Invalid move. Play again. The to position is out of bounds\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.european1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testOnlyNoticesInvalidInputAndStillMovesEuropean() {
    this.readable = new StringReader("4 2 a 4 4 q");
    Appendable output = new StringBuilder("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36\n"
            + "Please re-enter the value!\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 35\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 35");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.european1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testJumpedPositionNotAMarbleSignalsInOutputEuropean() {
    this.readable = new StringReader("4 2 4 4 4 1 4 3 q");
    Appendable output = new StringBuilder("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 36\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 35\n"
            + "Invalid move. Play again. The jumped position is not a marble\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n"
            + "Score: 35");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.european1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testQuitGameLowercaseQTriangle() {
    this.readable = new StringReader("q");
    Appendable output = new StringBuilder("    _\n"
            + "   O O\n"
            + "  O O O\n"
            + " O O O O\n"
            + "O O O O O\n"
            + "Score: 14\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    _\n"
            + "   O O\n"
            + "  O O O\n"
            + " O O O O\n"
            + "O O O O O\n"
            + "Score: 14");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.triangle1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testQuitHappensForFromRowTriangle() {
    this.readable = new StringReader("q 0 0 0");
    Appendable output = new StringBuilder("    _\n"
            + "   O O\n"
            + "  O O O\n"
            + " O O O O\n"
            + "O O O O O\n"
            + "Score: 14\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    _\n"
            + "   O O\n"
            + "  O O O\n"
            + " O O O O\n"
            + "O O O O O\n"
            + "Score: 14");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.triangle1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testQuitHappensForFromColTriangle() {
    this.readable = new StringReader("2 q 0 0");
    Appendable output = new StringBuilder("    _\n"
            + "   O O\n"
            + "  O O O\n"
            + " O O O O\n"
            + "O O O O O\n"
            + "Score: 14\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    _\n"
            + "   O O\n"
            + "  O O O\n"
            + " O O O O\n"
            + "O O O O O\n"
            + "Score: 14");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.triangle1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testQuitHappensForToRowTriangle() {
    this.readable = new StringReader("2 0 Q 0");
    Appendable output = new StringBuilder("    _\n"
            + "   O O\n"
            + "  O O O\n"
            + " O O O O\n"
            + "O O O O O\n"
            + "Score: 14\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    _\n"
            + "   O O\n"
            + "  O O O\n"
            + " O O O O\n"
            + "O O O O O\n"
            + "Score: 14");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.triangle1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testQuitHappensForToColTriangle() {
    this.readable = new StringReader("2 0 0 q");
    Appendable output = new StringBuilder("    _\n"
            + "   O O\n"
            + "  O O O\n"
            + " O O O O\n"
            + "O O O O O\n"
            + "Score: 14\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    _\n"
            + "   O O\n"
            + "  O O O\n"
            + " O O O O\n"
            + "O O O O O\n"
            + "Score: 14");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.triangle1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testOnlyNoticesInvalidInputStillMovesTriangle() {
    this.readable = new StringReader("3 1 a 1 1 q");
    Appendable output = new StringBuilder("    _\n"
            + "   O O\n"
            + "  O O O\n"
            + " O O O O\n"
            + "O O O O O\n"
            + "Score: 14\n"
            + "Please re-enter the value!\n"
            + "    O\n"
            + "   _ O\n"
            + "  _ O O\n"
            + " O O O O\n"
            + "O O O O O\n"
            + "Score: 13\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O\n"
            + "   _ O\n"
            + "  _ O O\n"
            + " O O O O\n"
            + "O O O O O\n"
            + "Score: 13");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.triangle1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testControllerForOtherTriangleConstructor1() {
    this.readable = new StringReader("1 1 3 1 q");
    Appendable output = new StringBuilder("    O\n"
            + "   O O\n"
            + "  _ O O\n"
            + " O O O O\n"
            + "O O O O O\n"
            + "Score: 14\n"
            + "    _\n"
            + "   _ O\n"
            + "  O O O\n"
            + " O O O O\n"
            + "O O O O O\n"
            + "Score: 13\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    _\n"
            + "   _ O\n"
            + "  O O O\n"
            + " O O O O\n"
            + "O O O O O\n"
            + "Score: 13");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.triangle2);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testControllerForOtherTriangleConstructor2() {
    this.readable = new StringReader("3 1 1 1 q");
    Appendable output = new StringBuilder("   _\n"
            + "  O O\n"
            + " O O O\n"
            + "O O O O\n"
            + "Score: 9\n"
            + "   O\n"
            + "  _ O\n"
            + " _ O O\n"
            + "O O O O\n"
            + "Score: 8\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "   O\n"
            + "  _ O\n"
            + " _ O O\n"
            + "O O O O\n"
            + "Score: 8");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.triangle3);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testControllerForOtherTriangleConstructor3() {
    this.readable = new StringReader("3 1 3 3 q");
    Appendable output = new StringBuilder("    O\n"
            + "   O O\n"
            + "  O O _\n"
            + " O O O O\n"
            + "O O O O O\n"
            + "Score: 14\n"
            + "    O\n"
            + "   O O\n"
            + "  _ _ O\n"
            + " O O O O\n"
            + "O O O O O\n"
            + "Score: 13\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O\n"
            + "   O O\n"
            + "  _ _ O\n"
            + " O O O O\n"
            + "O O O O O\n"
            + "Score: 13");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.triangle4);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testFromPositionOutOfBoundsCaughtTriangle() {
    this.readable = new StringReader("-1 -1 1 1 q");
    Appendable output = new StringBuilder("    _\n"
            + "   O O\n"
            + "  O O O\n"
            + " O O O O\n"
            + "O O O O O\n"
            + "Score: 14\n"
            + "Invalid move. Play again. The from position is out of bounds\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    _\n"
            + "   O O\n"
            + "  O O O\n"
            + " O O O O\n"
            + "O O O O O\n"
            + "Score: 14");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.triangle1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testToPositionOutOfBoundsCaughtTriangle() {
    this.readable = new StringReader("1 1 -1 -1 q");
    Appendable output = new StringBuilder("    _\n"
            + "   O O\n"
            + "  O O O\n"
            + " O O O O\n"
            + "O O O O O\n"
            + "Score: 14\n"
            + "Invalid move. Play again. The to position is out of bounds\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    _\n"
            + "   O O\n"
            + "  O O O\n"
            + " O O O O\n"
            + "O O O O O\n"
            + "Score: 14");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.triangle1);

    assertEquals(this.appendable.toString(), output.toString());
  }

  @Test
  public void testGameOverTriangle() {
    this.triangle2 = new TriangleSolitaireModelImpl(2);
    this.readable = new StringReader("1 1 3 1 q");

    Appendable output = new StringBuilder(" _\n"
            + "O O\n"
            + "Score: 2\n"
            + "Game over!\n"
            + " _\n"
            + "O O\n"
            + "Score: 2\n");

    this.gameController = new MarbleSolitaireControllerImpl(this.readable, this.appendable);

    this.gameController.playGame(this.triangle2);

    assertEquals(this.appendable.toString(), output.toString());
  }


}

