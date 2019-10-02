import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
* The finite state machine interpreter.
* Runs a finite state machine from a description given on a set of input.
*/
public class fsminterpreter {

  FiniteStateMachine finiteStateMachine;

  /**
  * Runs the program
  *
  * @param args arguments given
  */
  public static void main(String[] args) {
    String descriptionFile = args[0];
    fsminterpreter FSM = new fsminterpreter();
    FSM.run(descriptionFile);
  }

  /**
  * Runs the finite state machine interpreter.
  *
  * @param descriptionFile the file which describes the fintie state machine.
  */
  public void run(String descriptionFile) {
    //if machine can't build description was bad
    if (build(descriptionFile) == false) {
      BadDescription();
    }
    //If the machine can't run input was bad
    else if (runMachine()==false) {
      BadInput();
    }
  }

  /**
  * Builds the model for the finite state machine.
  *
  * @param descriptionFile the file in which describes the finite state machine
  * @return true if machime successful builds from description file.
  */
  public boolean build(String descriptionFile) {
    try {
      //Creates a instance of the finite state machine
      finiteStateMachine = new FiniteStateMachine();
      //imports the .fsm file
      BufferedReader description = new BufferedReader(new FileReader(descriptionFile));
      String line = description.readLine();
      //Checks each line is valid and continues to the next
      while (line != null) {
        if (finiteStateMachine.addState(line) == false) {
          description.close();
          return false;
        }
        line = description.readLine();
        if (line != null) {
          line = (line.isEmpty())? null : line;
        }
      }
      //Verifies some attributes of the states
      if (finiteStateMachine.verifyStates() == false) {
        description.close();
        return false;
      }
      description.close();
      return true;
    }
    catch (FileNotFoundException e){
      return false;
    }
    catch (IOException e) {
      return false;
    }
  }

  /**
  * Runs the finite state machine created on some input given to the machine.
  *
  * @return true if input was valid and machine ran successfully.
  */
  public boolean runMachine() {
    Scanner scanner = new Scanner(System.in);
    String inputString = scanner.nextLine();
    String[] inputSequence = inputString.split("");
    scanner.close();
    for (String input: inputSequence) {
      if (finiteStateMachine.runInput(input) == false) {
        return false;
      }
    }
    finiteStateMachine.displayOutput();
    return true;

  }

  /**
  * Notifies the user the description of the finite state machine is invalid and closes the program.
  */
  public void BadDescription() {
    System.out.println("Bad description");
    System.exit(0);
  }

  /**
  * Notifies the user the input is not valid and closes the program.
  */
  public void BadInput() {
    System.out.println("Bad input");
    System.exit(0);
  }
}
