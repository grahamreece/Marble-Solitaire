import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Represents tests for the public methods made, and tests for exceptions for those methods and the
 * constructor of the marbleSolitaireModelImpl.
 */
public class MarbleSolitaireModelImplTest {

  MarbleSolitaireModelImpl model1EmptyConstructor;
  MarbleSolitaireModelImpl model2EmptySlotOne;
  MarbleSolitaireModelImpl model3EmptySlotTwo;
  MarbleSolitaireModelImpl model4ArmThicknessOne;
  MarbleSolitaireModelImpl model5ArmThicknessTwo;
  MarbleSolitaireModelImpl model6ArmThicknessAndSlotOne;
  MarbleSolitaireModelImpl model7ArmThicknessAndSlotTwo;
  MarbleSolitaireModelImpl model8ArmThicknessThree;

  MarbleSolitaireModelImpl willHaveArmThicknessError1;
  MarbleSolitaireModelImpl willHaveArmThicknessError2;

  MarbleSolitaireModelImpl willHaveOutOfBoundsError1;
  MarbleSolitaireModelImpl willHaveOutOfBoundsError2;
  MarbleSolitaireModelImpl willHaveOutOfBoundsError3;
  MarbleSolitaireModelImpl willHaveOutOfBoundsError4;
  MarbleSolitaireModelImpl willHaveOutOfBoundsError5;
  MarbleSolitaireModelImpl willHaveOutOfBoundsError6;
  MarbleSolitaireModelImpl willHaveOutOfBoundsError7;
  MarbleSolitaireModelImpl willHaveOutOfBoundsError8;


  /**
   * Set the initial data for the useful examples that will be used to test the methods made.
   */
  public void initData() {

    this.model1EmptyConstructor = new MarbleSolitaireModelImpl();
    this.model2EmptySlotOne = new MarbleSolitaireModelImpl(3, 3);
    this.model3EmptySlotTwo = new MarbleSolitaireModelImpl(2, 4);
    this.model4ArmThicknessOne = new MarbleSolitaireModelImpl(3);
    this.model5ArmThicknessTwo = new MarbleSolitaireModelImpl(5);
    this.model6ArmThicknessAndSlotOne = new MarbleSolitaireModelImpl(5, 5, 5);
    this.model7ArmThicknessAndSlotTwo = new MarbleSolitaireModelImpl(9, 11, 9);
    this.model8ArmThicknessThree = new MarbleSolitaireModelImpl(9);
  }


  @Test
  public void TestGetGameState() {

    this.initData();

    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", this.model1EmptyConstructor.getGameState());
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", this.model2EmptySlotOne.getGameState());
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O _ O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", this.model3EmptySlotTwo.getGameState());
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", this.model4ArmThicknessOne.getGameState());
    assertEquals("        O O O O O\n"
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
            + "        O O O O O", this.model5ArmThicknessTwo.getGameState());
    assertEquals("        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O _ O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O", this.model6ArmThicknessAndSlotOne.getGameState());
    assertEquals("                O O O O O O O O O\n"
                    + "                O O O O O O O O O\n"
                    + "                O O O O O O O O O\n"
                    + "                O O O O O O O O O\n"
                    + "                O O O O O O O O O\n"
                    + "                O O O O O O O O O\n"
                    + "                O O O O O O O O O\n"
                    + "                O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O _ O O O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O O O O O O O O O O O O O\n"
                    + "                O O O O O O O O O\n"
                    + "                O O O O O O O O O\n"
                    + "                O O O O O O O O O\n"
                    + "                O O O O O O O O O\n"
                    + "                O O O O O O O O O\n"
                    + "                O O O O O O O O O\n"
                    + "                O O O O O O O O O\n"
                    + "                O O O O O O O O O",
            this.model7ArmThicknessAndSlotTwo.getGameState());
    assertEquals("                O O O O O O O O O\n"
            + "                O O O O O O O O O\n"
            + "                O O O O O O O O O\n"
            + "                O O O O O O O O O\n"
            + "                O O O O O O O O O\n"
            + "                O O O O O O O O O\n"
            + "                O O O O O O O O O\n"
            + "                O O O O O O O O O\n"
            + "O O O O O O O O O O O O O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O _ O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O O O O O O O O O O O O O\n"
            + "                O O O O O O O O O\n"
            + "                O O O O O O O O O\n"
            + "                O O O O O O O O O\n"
            + "                O O O O O O O O O\n"
            + "                O O O O O O O O O\n"
            + "                O O O O O O O O O\n"
            + "                O O O O O O O O O\n"
            + "                O O O O O O O O O", this.model8ArmThicknessThree.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void TestErrorForArmThicknessOfOne() {
    this.willHaveArmThicknessError1 = new MarbleSolitaireModelImpl(1);
    this.willHaveArmThicknessError2 = new MarbleSolitaireModelImpl(1, 1, 0);
    assertEquals("", this.willHaveArmThicknessError1.getGameState());
    assertEquals("", this.willHaveArmThicknessError2.getGameState());

  }

  @Test(expected = IllegalArgumentException.class)
  public void TestErrorForEvenArmThickness() {
    this.willHaveArmThicknessError1 = new MarbleSolitaireModelImpl(2);
    this.willHaveArmThicknessError2 = new MarbleSolitaireModelImpl(4, 3, 3);
    assertEquals("", this.willHaveArmThicknessError1.getGameState());
    assertEquals("", this.willHaveArmThicknessError2.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void TestErrorForOtherInvalidArmThicknesses() {
    this.willHaveArmThicknessError1 = new MarbleSolitaireModelImpl(0, -3, -4);
    this.willHaveArmThicknessError2 = new MarbleSolitaireModelImpl(-1, -3, -6);
    assertEquals("", this.willHaveArmThicknessError1.getGameState());
    assertEquals("", this.willHaveArmThicknessError2.getGameState());
    this.willHaveArmThicknessError1 = new MarbleSolitaireModelImpl(-4, 2, 2);
    assertEquals("", this.willHaveArmThicknessError1.getGameState());

  }

  @Test(expected = IllegalArgumentException.class)
  public void TestOutOfBoundsErrorForGoingOverFullThickness() {
    this.willHaveOutOfBoundsError1 = new MarbleSolitaireModelImpl(3, 10);
    this.willHaveOutOfBoundsError2 = new MarbleSolitaireModelImpl(10, 3);
    this.willHaveOutOfBoundsError3 = new MarbleSolitaireModelImpl(9, 9);
    this.willHaveOutOfBoundsError4 = new MarbleSolitaireModelImpl(3, 9, 2);
    this.willHaveOutOfBoundsError5 = new MarbleSolitaireModelImpl(5, 4, 13);
    this.willHaveOutOfBoundsError6 = new MarbleSolitaireModelImpl(5, 13, 10);
    assertEquals("", this.willHaveOutOfBoundsError1.getGameState());
    assertEquals("", this.willHaveOutOfBoundsError2.getGameState());
    assertEquals("", this.willHaveOutOfBoundsError3.getGameState());
    assertEquals("", this.willHaveOutOfBoundsError4.getGameState());
    assertEquals("", this.willHaveOutOfBoundsError5.getGameState());
    assertEquals("", this.willHaveOutOfBoundsError6.getGameState());

  }

  @Test(expected = IllegalArgumentException.class)
  public void TestNegativePositionOutOfBoundsErrors() {
    this.willHaveOutOfBoundsError1 = new MarbleSolitaireModelImpl(-1, -1);
    this.willHaveOutOfBoundsError2 = new MarbleSolitaireModelImpl(0, -1);
    this.willHaveOutOfBoundsError3 = new MarbleSolitaireModelImpl(-0, -0);
    this.willHaveOutOfBoundsError4 = new MarbleSolitaireModelImpl(-3, -15);
    this.willHaveOutOfBoundsError5 = new MarbleSolitaireModelImpl(3, -3, 2);
    this.willHaveOutOfBoundsError6 = new MarbleSolitaireModelImpl(5, 5, -1);
    assertEquals("", this.willHaveOutOfBoundsError1.getGameState());
    assertEquals("", this.willHaveOutOfBoundsError2.getGameState());
    assertEquals("", this.willHaveOutOfBoundsError3.getGameState());
    assertEquals("", this.willHaveOutOfBoundsError4.getGameState());
    assertEquals("", this.willHaveOutOfBoundsError5.getGameState());
    assertEquals("", this.willHaveOutOfBoundsError6.getGameState());
  }


  @Test(expected = IllegalArgumentException.class)
  public void TestOutOfBoundsErrorForBeingInCorners() {
    // these two will test for it being out of bounds in the top left corner according to the arm
    // thickness
    this.willHaveOutOfBoundsError1 = new MarbleSolitaireModelImpl(1, 1);
    this.willHaveOutOfBoundsError2 = new MarbleSolitaireModelImpl(5, 2, 1);
    // these two will test for it being out of bounds in the top right corner according to the arm
    // thickness
    this.willHaveOutOfBoundsError3 = new MarbleSolitaireModelImpl(0, 6);
    this.willHaveOutOfBoundsError4 = new MarbleSolitaireModelImpl(5, 2, 11);
    // these two will test for it being out of bounds in the bottom left corner according to the arm
    // thickness
    this.willHaveOutOfBoundsError5 = new MarbleSolitaireModelImpl(6, 1);
    this.willHaveOutOfBoundsError6 = new MarbleSolitaireModelImpl(5, 11, 2);
    // these two will test for it being out of bounds in the bottom right corner according to the
    // arm thickness
    this.willHaveOutOfBoundsError7 = new MarbleSolitaireModelImpl(6, 5);
    this.willHaveOutOfBoundsError8 = new MarbleSolitaireModelImpl(5, 11, 11);
    assertEquals("", this.willHaveOutOfBoundsError1.getGameState());
    assertEquals("", this.willHaveOutOfBoundsError2.getGameState());
    assertEquals("", this.willHaveOutOfBoundsError3.getGameState());
    assertEquals("", this.willHaveOutOfBoundsError4.getGameState());
    assertEquals("", this.willHaveOutOfBoundsError5.getGameState());
    assertEquals("", this.willHaveOutOfBoundsError6.getGameState());
    assertEquals("", this.willHaveOutOfBoundsError7.getGameState());
    assertEquals("", this.willHaveOutOfBoundsError8.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void TestErrorsForMove() {
    this.initData();

    // given a from or to position that is out of bounds by being negative
    this.model1EmptyConstructor.move(1, -2, 1, 0);
    this.model1EmptyConstructor.move(-2, -2, -2, -4);
    this.model1EmptyConstructor.move(2, 1, 2, -1);


    // given a from or to position that is out of bounds by being over or equal to the fullThickness
    this.model1EmptyConstructor.move(10, 9, 12, 9);
    this.model1EmptyConstructor.move(3, 6, 3, 8);
    this.model1EmptyConstructor.move(8, 2, 6, 2);

    // given a from position that is not a String that represents a marble
    this.model1EmptyConstructor.move(3, 3, 5, 3);
    this.model1EmptyConstructor.move(3, 3, 3, 1);


    // given a move where there is not a marble in between the from and to positions
    this.model1EmptyConstructor.move(2, 3, 4, 3);
    this.model1EmptyConstructor.move(3, 2, 3, 4);

    // given a to position that is not a String that represents an empty slot
    this.model1EmptyConstructor.move(1, 2, 3, 2);
    this.model1EmptyConstructor.move(3, 6, 3, 4);

    // given a move that is not 2 positions to the left, right, down, or up
    this.model1EmptyConstructor.move(1, 3, 5, 5);
    this.model1EmptyConstructor.move(5, 3, 0, 3);
    this.model1EmptyConstructor.move(3, 2, 3, 3);
    this.model1EmptyConstructor.move(1, 2, 2, 3);


  }

  @Test
  public void moveTest() {

    this.initData();

    this.model1EmptyConstructor.move(3, 1, 3, 3);
    assertEquals(
            "    O O O\n"
                    + "    O O O\n"
                    + "O O O O O O O\n"
                    + "O _ _ O O O O\n"
                    + "O O O O O O O\n"
                    + "    O O O\n"
                    + "    O O O", this.model1EmptyConstructor.getGameState());
    this.model1EmptyConstructor.move(5, 2, 3, 2);
    assertEquals(
            "    O O O\n"
                    + "    O O O\n"
                    + "O O O O O O O\n"
                    + "O _ O O O O O\n"
                    + "O O _ O O O O\n"
                    + "    _ O O\n"
                    + "    O O O", this.model1EmptyConstructor.getGameState());
    this.model1EmptyConstructor.move(4, 0, 4, 2);
    assertEquals(
            "    O O O\n"
                    + "    O O O\n"
                    + "O O O O O O O\n"
                    + "O _ O O O O O\n"
                    + "_ _ O O O O O\n"
                    + "    _ O O\n"
                    + "    O O O", this.model1EmptyConstructor.getGameState());
    this.model1EmptyConstructor.move(4, 3, 4, 1);
    assertEquals(
            "    O O O\n"
                    + "    O O O\n"
                    + "O O O O O O O\n"
                    + "O _ O O O O O\n"
                    + "_ O _ _ O O O\n"
                    + "    _ O O\n"
                    + "    O O O", this.model1EmptyConstructor.getGameState());
    this.model1EmptyConstructor.move(5, 4, 5, 2);
    assertEquals(
            "    O O O\n"
                    + "    O O O\n"
                    + "O O O O O O O\n"
                    + "O _ O O O O O\n"
                    + "_ O _ _ O O O\n"
                    + "    O _ _\n"
                    + "    O O O", this.model1EmptyConstructor.getGameState());
    this.model1EmptyConstructor.move(6, 2, 4, 2);
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            + "_ O O _ O O O\n"
            + "    _ _ _\n"
            + "    _ O O", this.model1EmptyConstructor.getGameState());
    this.model1EmptyConstructor.move(6, 4, 6, 2);
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            + "_ O O _ O O O\n"
            + "    _ _ _\n"
            + "    O _ _", this.model1EmptyConstructor.getGameState());
    this.model1EmptyConstructor.move(4, 5, 4, 3);
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            + "_ O O O _ _ O\n"
            + "    _ _ _\n"
            + "    O _ _", this.model1EmptyConstructor.getGameState());
    this.model1EmptyConstructor.move(4, 2, 4, 4);
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ O O O O O\n"
            + "_ O _ _ O _ O\n"
            + "    _ _ _\n"
            + "    O _ _", this.model1EmptyConstructor.getGameState());
    this.model1EmptyConstructor.move(2, 0, 4, 0);
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "_ O O O O O O\n"
            + "_ _ O O O O O\n"
            + "O O _ _ O _ O\n"
            + "    _ _ _\n"
            + "    O _ _", this.model1EmptyConstructor.getGameState());
    this.model1EmptyConstructor.move(4, 0, 4, 2);
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "_ O O O O O O\n"
            + "_ _ O O O O O\n"
            + "_ _ O _ O _ O\n"
            + "    _ _ _\n"
            + "    O _ _", this.model1EmptyConstructor.getGameState());
    this.model1EmptyConstructor.move(3, 2, 5, 2);
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "_ O O O O O O\n"
            + "_ _ _ O O O O\n"
            + "_ _ _ _ O _ O\n"
            + "    O _ _\n"
            + "    O _ _", this.model1EmptyConstructor.getGameState());

    this.model1EmptyConstructor.move(3, 4, 5, 4);
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "_ O O O O O O\n"
            + "_ _ _ O _ O O\n"
            + "_ _ _ _ _ _ O\n"
            + "    O _ O\n"
            + "    O _ _", this.model1EmptyConstructor.getGameState());
    this.model1EmptyConstructor.move(6, 2, 4, 2);
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "_ O O O O O O\n"
            + "_ _ _ O _ O O\n"
            + "_ _ O _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _", this.model1EmptyConstructor.getGameState());
    this.model1EmptyConstructor.move(3, 6, 3, 4);
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "_ O O O O O O\n"
            + "_ _ _ O O _ _\n"
            + "_ _ O _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _", this.model1EmptyConstructor.getGameState());
    this.model1EmptyConstructor.move(3, 4, 3, 2);
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "_ O O O O O O\n"
            + "_ _ O _ _ _ _\n"
            + "_ _ O _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _", this.model1EmptyConstructor.getGameState());
    this.model1EmptyConstructor.move(2, 2, 2, 0);
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O _ _ O O O O\n"
            + "_ _ O _ _ _ _\n"
            + "_ _ O _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _", this.model1EmptyConstructor.getGameState());
    this.model1EmptyConstructor.move(4, 2, 2, 2);
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O _ O O O O O\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _", this.model1EmptyConstructor.getGameState());
    this.model1EmptyConstructor.move(2, 3, 2, 1);
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O _ _ O O O\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _", this.model1EmptyConstructor.getGameState());
    this.model1EmptyConstructor.move(2, 0, 2, 2);
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "_ _ O _ O O O\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _", this.model1EmptyConstructor.getGameState());
    this.model1EmptyConstructor.move(2, 5, 2, 3);
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "_ _ O O _ _ O\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _", this.model1EmptyConstructor.getGameState());
    this.model1EmptyConstructor.move(2, 2, 2, 4);
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "_ _ _ _ O _ O\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _", this.model1EmptyConstructor.getGameState());
    this.model1EmptyConstructor.move(1, 4, 3, 4);
    assertEquals("    O O O\n"
            + "    O O _\n"
            + "_ _ _ _ _ _ O\n"
            + "_ _ _ _ O _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _", this.model1EmptyConstructor.getGameState());
    this.model1EmptyConstructor.move(1, 2, 1, 4);
    assertEquals("    O O O\n"
            + "    _ _ O\n"
            + "_ _ _ _ _ _ O\n"
            + "_ _ _ _ O _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _", this.model1EmptyConstructor.getGameState());
    this.model1EmptyConstructor.move(0, 4, 2, 4);
    assertEquals("    O O _\n"
            + "    _ _ _\n"
            + "_ _ _ _ O _ O\n"
            + "_ _ _ _ O _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _", this.model1EmptyConstructor.getGameState());
    this.model1EmptyConstructor.move(3, 4, 1, 4);
    assertEquals("    O O _\n"
            + "    _ _ O\n"
            + "_ _ _ _ _ _ O\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _", this.model1EmptyConstructor.getGameState());
    this.model1EmptyConstructor.move(0, 2, 0, 4);
    assertEquals("    _ _ O\n"
            + "    _ _ O\n"
            + "_ _ _ _ _ _ O\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _", this.model1EmptyConstructor.getGameState());
    this.model1EmptyConstructor.move(0, 4, 2, 4);
    assertEquals("    _ _ _\n"
            + "    _ _ _\n"
            + "_ _ _ _ O _ O\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ _ _ _ O\n"
            + "    _ _ O\n"
            + "    _ _ _", this.model1EmptyConstructor.getGameState());
  }

  @Test
  public void TestGetScore() {
    this.initData();

    // check get score for the first constructor
    assertEquals(32, this.model1EmptyConstructor.getScore());

    this.model1EmptyConstructor.move(3, 1, 3, 3);
    assertEquals(31, this.model1EmptyConstructor.getScore());

    this.model1EmptyConstructor.move(5, 2, 3, 2);
    assertEquals(30, this.model1EmptyConstructor.getScore());


    // check get score for the second constructor
    assertEquals(32, this.model2EmptySlotOne.getScore());

    this.model2EmptySlotOne.move(3, 5, 3, 3);
    assertEquals(31, this.model2EmptySlotOne.getScore());

    // check get score for the third constructor

    assertEquals(104, this.model5ArmThicknessTwo.getScore());

    this.model5ArmThicknessTwo.move(6, 4, 6, 6);

    assertEquals(103, this.model5ArmThicknessTwo.getScore());

    // check get score for the fourth constructor

    assertEquals(368, this.model7ArmThicknessAndSlotTwo.getScore());

    this.model7ArmThicknessAndSlotTwo.move(11, 7, 11, 9);

    assertEquals(367, this.model7ArmThicknessAndSlotTwo.getScore());

    this.moveTest();

    assertEquals(this.model1EmptyConstructor.getScore(), 4);

  }

  @Test
  public void TestIsGameOver() {

    this.initData();

    // as, for each constructor, the game should not start in a winning position, each of these
    // tests should return false
    assertEquals(false, this.model1EmptyConstructor.isGameOver());
    assertEquals(false, this.model2EmptySlotOne.isGameOver());
    assertEquals(false, this.model5ArmThicknessTwo.isGameOver());
    assertEquals(false, this.model7ArmThicknessAndSlotTwo.isGameOver());

    // after taking a look at the move test test method, it is clear that, at the end of this test,
    // the game is indeed over as there are no more moves to make, therefore this test should pass
    this.moveTest();
    assertEquals(true, this.model1EmptyConstructor.isGameOver());

  }

}