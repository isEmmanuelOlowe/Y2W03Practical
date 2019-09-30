# W03 Practical Report

## Overview

The practical asks that a program which can read a description of a Finite state machine as a transition table and a sequence of input characters. Then interprets into a the description finite state machine program which runs on the sequence of strings inputted. If the description is not well formed then the program will print “Bad description” and exit without interpreting input. If the input is not in the set of accepted symbols it should print “Bad input” and exit.

### Assumption

Assumptions made about while designing the program:

* That the name of states can be numbers, letters and words as long as they are unique.
* There must be 4 entries on each line of the `.fsm` file. e.g. 4 pieces of information or the description is bad.
* If a string input characters are not present in the set of valid input the it is a bad input.
* If next state function returns state that is not in the valid set of states then it is Bad Description



## Design

### `fsminterpreter`

### `FiniteStateMachine`



It was decided that this class would represent the all aspects of the finite $$F = \{S, \sigma, I, O, f, g\}$$. This allowed for all aspects of the machine to be decided in away that could be manipulated by the program.

Using $S$ it can be checked from the transition table that next state function ($f$) does not return values which are not in the set. Making easy to reject bad descriptions.