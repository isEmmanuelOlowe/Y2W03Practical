import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;


/**
* Used to test if finite state machine achieves full functionality.
*/
public class FSMTests {

  private FiniteStateMachine fsm;

  @Before
  /**
  * Sets up the finite state machine that will be used to test the program.
  */
  public void setupFSM() {
    fsm = new FiniteStateMachine();
  }

  @Test
  /**
  * Tests that program rejects empty file as input.
  */
  public void emptyStateDescription() {
    assertFalse(fsm.addState(""));
  }

  @Test
  /**
  * Tests strings which do not follow pattern are rejected.
  */
  public void validStateSingleWord() {
    assertFalse(fsm.addState("HiFriend"));
  }

  @Test
  /**
  * Tests that strings which do not follow the pattern are rejected.
  */
  public void validStateEvenlySpacedWord() {
    assertFalse(fsm.addState("Hello Big Beautiful World"));
  }

  @Test
  /**
  * Tests given valid input input it continues.
  */
  public void validStateDescription() {
    assertTrue(fsm.addState("1 a b 3"));
  }

  @Test
  /**
  * Tests the finite machine cares about spacing.
  */
  public void validStateDescriptionOddSpacing() {
    assertTrue(fsm.addState("    2  c      d     4"));
  }

  @Test
  /**
  * Tests for rejection of Non-Determinism.
  */
  public void nonDeterminism() {
    fsm.addState("2 a b 5");
    assertFalse(fsm.addState("2 a b 5"));
  }

  @Test
  /**
  * Test that referenced states in next state have definition.
  */
  public void nextStateDefined() {
    fsm.addState("2 a b 3");
    fsm.addState("3 a b 5");
    assertFalse(fsm.verifyStates());
  }

  @Test
  /**
  * Tests that each state accepts all valid inputs.
  */
  public void allInputsAccepted() {
    fsm.addState("1 a b 2");
    fsm.addState("1 b a 2");
    fsm.addState("2 a b 1");
    assertFalse(fsm.verifyStates());
  }

  @Test
  /**
  * Tests if the program accepts valid input.
  */
  public void validInputAccepted() {
    fsm.addState("1 a b 2");
    fsm.addState("1 b a 2");
    fsm.addState("2 a b 1");
    fsm.addState("2 b a 1");
    assertTrue(fsm.runInput("a"));
  }

  @Test
  /**
  * Tests the program rejects input which is not in the valid state of inputs.
  */
  public void invalidInputRejected() {
    fsm.addState("1 a b 2");
    fsm.addState("1 b a 2");
    fsm.addState("2 a b 1");
    fsm.addState("2 b a 1");
    assertFalse(fsm.runInput("c"));
  }

  @Test
  /**
  * Tests sequence for finite state machine.
  */
  public void validSequence() {
    boolean testSuccess = true;
    fsm.addState("1 a b 2");
    fsm.addState("1 b a 2");
    fsm.addState("2 a b 1");
    fsm.addState("2 b a 1");
    for (int i = 0; i < 10; i++) {
      if (!fsm.runInput("a")) {
        testSuccess = false;
      }
      if (!fsm.runInput("b")) {
        testSuccess = false;
      }
    }
    assertTrue(testSuccess);
  }

  @Test
  /**
  * Tests invalid sequence for finite state machine.
  */
  public void invalidTest() {
    boolean testSuccess = true;
    fsm.addState("1 a b 2");
    fsm.addState("1 b a 2");
    fsm.addState("2 a b 1");
    fsm.addState("2 b a 1");
    String[] inputs = new String[]{"a", "a", "b", "b", "a", "b", "c", "d"};
    for(String input: inputs) {
      if(!fsm.runInput(input)) {
        testSuccess = false;
      }
    }
    assertFalse(testSuccess);
  }
}
