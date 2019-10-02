# W03 Practical Report

## Overview

The practical asks that a program which can read a description of a Finite state machine as a transition table and a sequence of input characters. Then interprets into a the description finite state machine program which runs on the sequence of strings inputted. If the description is not well formed then the program will print “Bad description” and exit without interpreting input. If the input is not in the set of accepted symbols (Input set) it should print “Bad input” and exit.

### Assumption and Intuitions 

Assumptions and intuitions extrapolated from specification made while designing the program:

* That the name of states can be numbers or Letters as long as they are unique.
* There must be 4 entries on each line of the `.fsm` file. e.g. 4 pieces of information or the description is bad.
* If a string input characters are not present in the set of valid input the it is a bad input.
* If next state function returns state that is not in the valid set of states then it is Bad Description.
* Each state much accept (e.g. have a corresponding next State and output) for each
* The first line is assumed to be the first state.
* Assume that non-determinism is Bad description and will not be implemented by the program
* You cannot have a state appear in the output of next state function which does not have a definition.

## Design

### `fsminterpreter`

This class was design to be an interface between the user and the finite state machine. This is where the input shall be gathered from the user and the file processing to obtain the string data will be made. It was designed with the intent to abstract all the functionality of the finite state machine into another place where it can be effectively tested. So in this classes basic processes could be factored for.

### `FiniteStateMachine`

was decided that this class would represent the all aspects of the finite $$F = \{S, \sigma, I, O, f, g\}$$. This allowed for all aspects of the machine to be decided in away that could be manipulated by the program. As it was found to be an easy way to explain the functionality of the system.

Using $S$ it can be checked from the transition table that next state function ($f$) does not return values which are not in the set. Making easy to reject bad descriptions.

The initial state state is only recorded in the creation from the state description. In this class the $\sigma$ was changed mean just current state and it’s initial value would be the initial state. So the that the program was currently at would be easily determined.

When a input was entered into the machine it is checked if it is contained in the set $I$. This made validation of input very easy as it could checked in 1 line.

The set outputs was not required $O$ as $g$ the output function and even the $f$ the next state function were modelled as hash maps. Hash maps model real mathematical functions as hash maps correlate and input to 1 and exactly 1 output. The key for the hash map was chosen to be the concatenation of the state and the input as this would uniquely identify the next state and output given state is represented. The $f$ and $g$ functions could have been merged into 1 hash map, but this further deviates from the mathematical model of the finite state machine hoping to be achieve and makes retrieval process and insertion process more difficult as an object or collection is required to store both data for the output and the next state.

This classes is also responsible for determining the validity of the state definitions provided to the program. As in checking each state has output and a next state for each input and for check that each state in that is range of values of next state function has definition. 