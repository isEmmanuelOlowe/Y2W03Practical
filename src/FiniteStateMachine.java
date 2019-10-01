import java.util.HashMap;
import java.util.HashSet;

/**
 Represents a finite state machine
*
*/
public class FiniteStateMachine {

  //Stores the current state of the machine
  private String currentState;
  //Stores all the valid states of the finite state machine
  private HashSet<String> states;
  //Stores all the valid inputs to the finite state machine
  private HashSet<String> inputs;
  //Represents the output function
  private HashSet<String> nextStates;
  private HashMap<String, String> outputFunction;
  //Represents the next state function
  private HashMap<String, String> nextStateFunction;
  //The output string displayed.
  private String outputString = "";


  public FiniteStateMachine () {
    states = new HashSet<String>();
    inputs = new HashSet<String>();
    nextStates = new HashSet<String>();
    outputFunction = new HashMap<String, String>();
    nextStateFunction = new HashMap<String, String>();
  }

  /**
  * Adds a new state into the finite state machine.
  *
  * @param description the description of the state input.
  * @return true if state description is valid.
  */
  public boolean addState(String description) {
    //Indexs of the transition tables
    final int State = 0;
    final int Input = 1;
    final int Output = 2;
    final int NextState =3;

    //Splits by space
    String[] row = description.split("\\s+");

    //Checks that there are only 4 columns.
    if (row.length != 4) {
      return false;
    }
    //Sets the first entry to the initial state the program will begin.
    if (currentState == null) {
      //Adds the first set to next states in event first state is never returned to.
      currentState = row[State];
      nextStates.add(row[State]);
    }
    //adds the state to the set of sets
    states.add(row[State]);
    //adds the input to the set of valid outputs
    inputs.add(row[Input]);
    //adds all the functions which appear in the next state function
    nextStates.add(row[NextState]);
    //The combination of the state and input makes the input for the function.
    outputFunction.put(join(row[State], row[Input]), row[Output]);
    nextStateFunction.put(join(row[State], row[Input]), row[NextState]);
    return true;
  }

  public boolean runInput(String input){
    if (inputs.contains(input)) {
      //adds the output of state to output string that will be displayed.
      System.out.print(outputFunction.get(join(currentState, input)));
      currentState = nextStateFunction.get(join(currentState, input));
      return true;
    }
    else {
      return false;
    }

  }
  /**
  * Joins to strings together
  *
  * @param a the first string being joined to the second.
  * @param b the second being being joined to the first.
  * @return the joined strings
  */
  public String join(String a, String b) {
    return a + b;
  }


  /**
  * Determines that all the states which are in the set of states are present in the next state functions
  */
  public boolean verifyStates() {
    if (states.equals(nextStates)) {
      return true;
    }
    return false;
  }

  /**
  * Displays the output String.
  */
  public void displayOutput() {
    System.out.println(outputString);
  }
}
